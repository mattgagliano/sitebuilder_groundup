package zodiac.sitebuilder.server.sql;

import java.util.Set;

import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;
import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoPopulateProperty;

public class DatabaseTable {

	private String db_tableName;
	private String[] db_columnNames;
	private String[] db_columnTypes;
	private String[] db_columnAttributes;
	private String[][] db_sampleValues;
	private int db_size;
	
	public DatabaseTable(String tableName, String[] columnNames, String[] columnTypes, 
			String[] columnAttributes, String[][] sampleValues) {
		this.db_tableName = tableName;
		this.db_columnNames = columnNames;
		this.db_columnTypes = columnTypes;
		this.db_columnAttributes = columnAttributes;
		this.db_sampleValues = sampleValues;
		this.db_size = columnNames.length;
		
		if((!(getExistingTables().contains(db_tableName))) && (CONFIG.getPropertyValue(DatabaseAutoCreateProperty.class))) {
			createTable();
			
			if (CONFIG.getPropertyValue(DatabaseAutoPopulateProperty.class)) {
				autoPopulateTable();
			}
		}
	}
	
	public void createTable() {
		StringBuilder str = new StringBuilder();
		str.append("CREATE TABLE "+db_tableName+" (");
	
		for (int i = 0; i < db_size; i++) {
			str.append(db_columnNames[i]+" "+db_columnTypes[i]);
			
			if (db_columnAttributes[i].length() > 0) {
				str.append(" "+db_columnAttributes[i]);
			}
			if (i < (db_size - 1)) {
				str.append(",");
			}
			str.append(" ");
		}
		str.append(")");
		
		SQL.insert(str.toString());
	}
	
	private void autoPopulateTable() {
				
		for (int i = 0; i < db_sampleValues.length; i++) {
			StringBuilder str = new StringBuilder();
			str.append("INSERT INTO "+db_tableName+" (");
			
			for (int j = 0; j < db_size; j++) {
				str.append(db_columnNames[j]);
				if (j < (db_size-1)) {
					str.append(", ");
				}
			}
			
			str.append(") VALUES (");
			for (int j = 0; j < db_size; j++) {
				str.append("'"+db_sampleValues[i][j]+"'");
				if (j < (db_size-1)) {
					str.append(", ");
				}
			}
			str.append(")");
			
			SQL.insert(str.toString());
		}
	}
	
	private Set<String> getExistingTables() {
		StringArrayHolder tables = new StringArrayHolder();
		SQL.selectInto("SELECT UPPER(tablename) "
				+ "FROM sys.systables "
				+ "INTO :result", new NVPair("result", tables));
		return CollectionUtility.hashSet(tables.getValue());
	}
}
