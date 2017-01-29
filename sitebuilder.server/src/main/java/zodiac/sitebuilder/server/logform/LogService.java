package zodiac.sitebuilder.server.logform;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import zodiac.sitebuilder.shared.logform.CreateLogPermission;
import zodiac.sitebuilder.shared.logform.ILogService;
import zodiac.sitebuilder.shared.logform.LogFormData;
import zodiac.sitebuilder.shared.logform.ReadLogPermission;
import zodiac.sitebuilder.shared.logform.UpdateLogPermission;

public class LogService implements ILogService {

	@Override
	public LogFormData prepareCreate(LogFormData formData) {
		if (!ACCESS.check(new CreateLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [gagli] add business logic here.
		return formData;
	}

	@Override
	public LogFormData create(LogFormData formData) {
		if (!ACCESS.check(new CreateLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		SQL.insert("INSERT INTO FORMS (form_id) VALUES (:FormId)", formData);
		
		return store(formData);
	}

	@Override
	public LogFormData load(LogFormData formData) {
		if (!ACCESS.check(new ReadLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		String str = "SELECT project, formtype, Q0, Q1, Q2, Q3 FROM FORMS WHERE form_id = :FormId "
				+ " INTO 	:project, "
				+ "			:formtype, "
				+ "			:Q0, "
				+ "			:Q1, "
				+ "			:Q2, "
				+ "			:Q3";
		
		SQL.selectInto(str, formData);
		
		return formData;
	}

	@Override
	public LogFormData store(LogFormData formData) {
		if (!ACCESS.check(new UpdateLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		String str = "UPDATE FORMS "
				+ " SET		project = :Project, "
				+ "			formtype = :FormType, "
				+ "			Q0 = :Q0, "
				+ "			Q1 = :Q1, "
				+ "			Q2 = :Q2, "
				+ "			Q3 = :Q3 "
				+ " WHERE form_id = :FormId";

		SQL.update(str,  formData);
		
		return formData;
	}
	
	public Boolean[] loadFieldsConfig(String formId) {
		if (!ACCESS.check(new UpdateLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		
		Object[] obj = SQL.select("SELECT Q0, Q1, Q2, Q3 "
				+ " FROM FORMS WHERE form_id = '"+formId+"' ")[0];
		
		Boolean[] fieldsConfig = new Boolean[obj.length];
		int i = 0;
		
		for (Object o : obj) {
			fieldsConfig[i] = (boolean) o;
			i++;
		}
			
		return fieldsConfig;
	}
	
	public String[] loadFieldNames() {
		if (!ACCESS.check(new UpdateLogPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		Object[][] obj = SQL.select("SELECT question_id FROM QUESTIONS");
		
		String[] fieldNames = new String[obj.length];
		
		for (int i = 0; i < obj.length; i++) {
			for (int j = 0; j < obj[i].length; j++) {
				fieldNames[i] = (String) obj[i][j];
			}
		}
		
		return fieldNames;
	}
}
