package zodiac.sitebuilder.server.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.PlatformExceptionTranslator;
import org.eclipse.scout.rt.server.jdbc.derby.AbstractDerbySqlService;

import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;
import zodiac.sitebuilder.server.sql.DatabaseProperties.JdbcMappingNameProperty;

public class DerbySqlService extends AbstractDerbySqlService {

	@Override
	protected String getConfiguredJdbcMappingName() {
		String mappingname = CONFIG.getPropertyValue(JdbcMappingNameProperty.class);
		
		//add create attribute if autocreate is set to true
		if (CONFIG.getPropertyValue(DatabaseAutoCreateProperty.class)) {
			return mappingname + ";create=true";
		}
		
		return mappingname;
	}
	
	public void dropDB() {
		try {
			String jdbcMappingName = CONFIG.getPropertyValue(DatabaseProperties.JdbcMappingNameProperty.class);
			DriverManager.getConnection(jdbcMappingName + ";drop=true");	
		}
		catch (SQLException e) {
			BEANS.get(PlatformExceptionTranslator.class).translate(e);
		}
	}
}
