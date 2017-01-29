package zodiac.sitebuilder.server.lookupcalls;

import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

import zodiac.sitebuilder.shared.lookupcalls.IProjectNamesLookupService;

public class ProjectNamesLookupService extends AbstractSqlLookupService<String> implements IProjectNamesLookupService {
	
	@Override 
	protected String getConfiguredSqlSelect() {
		return "SELECT project, "
				+ " project "
				+ " FROM FORMS "
				+ " GROUP BY project";
	}
}
