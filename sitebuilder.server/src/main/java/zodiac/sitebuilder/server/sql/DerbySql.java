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
			str.append("INSERT INTO "+tableName+" (");
			
			for (int k = 0; k < sampleValues[0].length; k++) {
				str.append(getColumns(tableName).get(k));
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
}
