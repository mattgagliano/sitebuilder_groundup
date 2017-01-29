package zodiac.sitebuilder.server.sql;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.config.AbstractBooleanConfigProperty;
import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;
import org.eclipse.scout.rt.platform.config.AbstractSubjectConfigProperty;

public class DatabaseProperties {

	public static class DatabaseAutoCreateProperty extends AbstractBooleanConfigProperty {
		
		@Override
		protected Boolean getDefaultValue() {
			return Boolean.TRUE;
		}
		
		@Override
		public String getKey() {
			return "sitebuilder.database.autocreate";
		}
	}
	
	public static class DatabaseAutoPopulateProperty extends AbstractBooleanConfigProperty {
		
		@Override
		protected Boolean getDefaultValue() {
			return Boolean.TRUE;
		}
		
		@Override
		public String getKey() {
			return "sitebuilder.database.autopopulate";
		}
	}
	
	public static class JdbcMappingNameProperty extends AbstractStringConfigProperty {
		
		@Override
		protected String getDefaultValue() {
			return "jdbc:derby:memory:sitebuilder-database";
		}
		
		@Override
		public String getKey() {
			return "sitebuilder.database.jdbc.mapping.name";
		}
	}
	
	public static class SuperUserSubjectProperty extends AbstractSubjectConfigProperty {
		
		@Override
		protected Subject getDefaultValue() {
			return convertToSubject("system");
		}
		
		@Override
		public String getKey() {
			return "sitebuilder.superuser";
		}
	}
}
