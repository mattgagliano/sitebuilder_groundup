package zodiac.sitebuilder.server.lookupcalls;

import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

import zodiac.sitebuilder.shared.lookupcalls.IFormTypesByProjectLookupService;

public class FormTypesByProjectLookupService extends AbstractSqlLookupService<String>
		implements IFormTypesByProjectLookupService {

	@Override 
	protected String getConfiguredSqlSelect() {
		
		return "SELECT F.form_id, "
				+ " F.formtype "
				+ " FROM FORMS F "
				+ " WHERE F.project = :master "
				+ " <text> AND UPPER(F.formtype) LIKE UPPER(:text||'%') </text> "
				+ " <all> </all> ";		
	}
}
