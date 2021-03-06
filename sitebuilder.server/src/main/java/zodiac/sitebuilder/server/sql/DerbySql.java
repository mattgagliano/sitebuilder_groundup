package zodiac.sitebuilder.server.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;
import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoPopulateProperty;
import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.FormFormData.FormFormTable.FormFormTableRowData;

public class DerbySql {

	public static void createTable(String tableName, String[] columnNames, String[] columnTypes, 
										String[] columnAttributes) 
	{		
		if((!(getExistingTables().contains(tableName))) && (CONFIG.getPropertyValue(DatabaseAutoCreateProperty.class))) {
			
			StringBuilder str = new StringBuilder();
			str.append("CREATE TABLE "+tableName+" (");
		
			for (int i = 0; i < columnNames.length; i++) {
				str.append(columnNames[i]+" "+columnTypes[i]);
				
				if (columnAttributes[i].length() > 0) {
					str.append(" "+columnAttributes[i]);
				}
				if (i < (columnNames.length - 1)) {
					str.append(",");
				}
				str.append(" ");
			}
			str.append(")");
			
			SQL.insert(str.toString());
		}
	}
	
	public static void autoPopulate(String tableName, String[][] sampleValues) {
		
		if (CONFIG.getPropertyValue(DatabaseAutoPopulateProperty.class)) {
			
			StringBuilder str = new StringBuilder();
			List<String> columnNames = getColumns(tableName);
			
			str.append("INSERT INTO "+tableName+" (");
			
			for (int k = 0; k < sampleValues[0].length; k++) {
				str.append(columnNames.get(k));
				if (k < (sampleValues[0].length-1)) {
					str.append(", ");
				}
			}
			str.append(") VALUES ");
			
			for (int i = 0; i < sampleValues.length; i++) {				
				
				str.append("(");
				for (int j = 0; j < sampleValues[i].length; j++) {
					str.append("'"+sampleValues[i][j]+"'");
					if (j < (sampleValues[i].length-1)) {
						str.append(", ");
					}
					else {
						str.append(")");
					}
				}
				if (i < (sampleValues.length-1)) {
					str.append(",");
				}
				str.append(" ");
			}
			
			SQL.insert(str.toString());
		}		
	}
	
	public static Set<String> getExistingTables() {
		StringArrayHolder tables = new StringArrayHolder();
		SQL.selectInto("SELECT UPPER(tablename) "
				+ "FROM sys.systables "
				+ "INTO :result", new NVPair("result", tables));
		return CollectionUtility.hashSet(tables.getValue());
	}
	
	public static List<String> getColumns(String tableName) {
		List<String> columnsList = new ArrayList<String>();		
		StringArrayHolder columns = new StringArrayHolder();
		
		SQL.selectInto("SELECT COLUMNNAME FROM sys.syscolumns c "
				+ "INNER JOIN sys.systables t "
				+ "ON t.tableid = c.referenceid "
				+ "WHERE t.tablename = '"+tableName.toUpperCase()+"' "
				+ "ORDER BY COLUMNNUMBER "
				+ "INTO :result", new NVPair("result", columns));
		
		for (int i = 0; i < columns.getValue().length; i++) {
			columnsList.add(i, columns.getValue()[i]);
		}
		
		return columnsList;
	}
	
	public static String TablePageSelectInto(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("SELECT ");
		
		for (int i = 0; i < columnNames.size(); i++) {
			str.append(columnNames.get(i));
			if (i < (columnNames.size() - 1)) {
				str.append(", ");
			}
		}
		
		str.append(" FROM " + tableName + " INTO ");
		
		for (int j = 0; j < columnNames.size(); j++) {
			str.append(":{page." + ToProper(columnNames.get(j)) + "}");
			if (j < (columnNames.size() - 1)) {
				str.append(", ");
			}
		}
		
		return str.toString();
	}
	
	public static String SelectAllColumnsFrom(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("SELECT ");
		
		for (int i = 0 ; i < columnNames.size(); i++) {
			
			str.append(columnNames.get(i));
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		str.append("FROM " + tableName);
				
		return str.toString();
	}
	
	public static String FormDataCreate(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("INSERT INTO " + tableName + " ");
		str.append("(" + columnNames.get(0) + ") ");
		str.append("VALUES (:" + ToProper(columnNames.get(0)) + ")");
		
		return str.toString();
	}
	
	public static String SelectFromWhereIdEqual(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("SELECT ");
		
		for (int i = 1 ; i < columnNames.size(); i++) {
			
			str.append(columnNames.get(i));
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		str.append("FROM " + tableName + " WHERE ");
		str.append(getColumns(tableName).get(0) + " = :");
		str.append(ToProper(getColumns(tableName).get(0)));
		
		return str.toString();
	}
	
	//only works if primary key is first column in table
	public static String FormDataLoad(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("SELECT ");
		
		for (int i = 1 ; i < columnNames.size(); i++) {
			
			str.append(columnNames.get(i));
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		str.append("FROM " + tableName + " WHERE ");
		str.append(getColumns(tableName).get(0) + " = :");
		str.append(ToProper(getColumns(tableName).get(0)) + " INTO ");
		
		for (int i = 1 ; i < columnNames.size(); i++) {
			
			str.append(":" + ToProper(columnNames.get(i)));
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		return str.toString();
	}
	
	public static void FormFormDataStore(FormFormData formData) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns("FORMS");
		FormFormTableRowData[] rowData = formData.getFormFormTable().getRows();
		
		str.append("UPDATE FORMS SET ");
		
		str.append(columnNames.get(1) + " = '" + formData.getProject().getValue() +"', ");
		str.append(columnNames.get(2) + " = '" + formData.getFormname().getValue() + "', ");
		
		for (int i = 3; i < columnNames.size(); i++) {
			str.append(columnNames.get(i) + " = '" + rowData[i-3].getEnabled() + "'");
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		str.append("WHERE " + columnNames.get(0) + " = :" + ToProper(columnNames.get(0)));
		
		SQL.update(str.toString(), formData);
	}
	
	public static String FormDataStore(String tableName) {
		StringBuilder str = new StringBuilder();
		List<String> columnNames = getColumns(tableName);
		
		str.append("UPDATE " + tableName + " SET ");
		
		for (int i = 1; i < columnNames.size(); i++) {
			str.append(columnNames.get(i) + " = :" + ToProper(columnNames.get(i)) + " ");
			
			if (i < (columnNames.size() - 1)) {
				str.append(",");
			}
			
			str.append(" ");
		}
		
		str.append("WHERE " + columnNames.get(0) + " = :" + ToProper(columnNames.get(0)));
		
		return str.toString();
	}
	
	
	//only works for table with primary key id column 0
	public static void deleteRow(String row_id, String tableName) {
		SQL.insert("DELETE FROM " + tableName + " WHERE " + getColumns(tableName).get(0) + " = '" + row_id +"'");
	}
	
	public static void updateFormsTableColumns() {
		//select all prompts from questions table
		//for now just add items to end & update
		//later add capability to edit & move columns
		
		Object[][] rawQuestionData = SQL.select("SELECT PROMPT FROM QUESTIONS");
		List<String> formsTableColumns = getColumns("FORMS");
		
		int columnDifferential = (rawQuestionData.length - (formsTableColumns.size() - 3));     //skip first 3 columns bc they're not dynamic
		
		if (columnDifferential == 0) {
			System.out.println("No Columns to add");
		}
		else if  (columnDifferential < 0) {
			System.out.println("Negative column differential - system error - forms table has extra columns");
		}
		else if (columnDifferential == 1) {
			SQL.insert("ALTER TABLE FORMS ADD " + rawQuestionData[rawQuestionData.length-1][0].toString() + " VARCHAR(64)");			
		}
		else {
			System.out.println("Number of columns to generate >1 - system error - can't add two questions at once");
		}
	}
	
	public static String DropDataStore(String tableName) {
		return ("DROP TABLE " + tableName);
	}
	
	public static String ToProper(String str) {
		return (str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
	}
}
