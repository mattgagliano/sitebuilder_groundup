package zodiac.sitebuilder.server.formslist;

import java.util.List;
import java.util.UUID;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import zodiac.sitebuilder.server.sql.DerbySql;
import zodiac.sitebuilder.shared.formslist.CreateFormPermission;
import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.FormFormData.FormFormTable.FormFormTableRowData;
import zodiac.sitebuilder.shared.formslist.IFormService;
import zodiac.sitebuilder.shared.formslist.ReadFormPermission;
import zodiac.sitebuilder.shared.formslist.UpdateFormPermission;

public class FormService implements IFormService {

	@Override
	public FormFormData prepareCreate(FormFormData formData) {
		if (!ACCESS.check(new CreateFormPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [gagli] add business logic here.
		return formData;
	}

	@Override
	public FormFormData create(FormFormData formData) {
		if (!ACCESS.check(new CreateFormPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		if (StringUtility.isNullOrEmpty(formData.getFormid())) {
			formData.setFormid(UUID.randomUUID().toString());
		}
		
		SQL.insert(DerbySql.FormDataCreate("FORMS"), formData);
		
		return store(formData);
	}

	@Override
	public FormFormData load(FormFormData formData) {
		if (!ACCESS.check(new ReadFormPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		Object[][] rawFormData = SQL.select(DerbySql.SelectFromWhereIdEqual("FORMS"), formData);
		List<String> l = DerbySql.getColumns("FORMS");
				
		if (rawFormData.length == 1) {		
						
			formData.getProject().setValue((String) rawFormData[0][0]);
			formData.getFormname().setValue((String) rawFormData[0][1]);
			
			for (Integer j = 2; j < rawFormData[0].length; j++) {
				
				FormFormTableRowData r = formData.getFormFormTable().addRow();
				r.setPrompt(l.get(j+1));
				r.setEnabled((Boolean) rawFormData[0][j]);
			}
		}
		else if(rawFormData.length == 0) {
			System.out.println("FormId not found - returning empty formdata");
		}
		else {
			System.out.println("Multiple entries match this formId - returning empty formdata");
		}
		
		return formData;
	}

	@Override
	public FormFormData store(FormFormData formData) {
		if (!ACCESS.check(new UpdateFormPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		DerbySql.FormFormDataStore(formData);		
		
		return formData;
	}
}
