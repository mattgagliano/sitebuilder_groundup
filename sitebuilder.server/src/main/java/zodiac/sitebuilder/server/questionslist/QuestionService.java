package zodiac.sitebuilder.server.questionslist;

import java.util.UUID;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import zodiac.sitebuilder.server.sql.DerbySql;
import zodiac.sitebuilder.shared.questionslist.CreateQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;
import zodiac.sitebuilder.shared.questionslist.QuestionsListTablePageData;
import zodiac.sitebuilder.shared.questionslist.ReadQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.UpdateQuestionPermission;

public class QuestionService implements IQuestionService {

	@Override
	public QuestionsListTablePageData getQuestionsListTableData() {
		QuestionsListTablePageData pageData = new QuestionsListTablePageData();
		
		SQL.selectInto(DerbySql.TablePageSelectInto("QUESTIONS"), new NVPair("page", pageData));
		
		return pageData;
	}
	
	public void deleteRow(String row_id) {
		
		DerbySql.deleteRow(row_id, "QUESTIONS");
	}
	
	@Override
	public QuestionFormData prepareCreate(QuestionFormData formData) {		
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		return formData;
	}

	@Override
	public QuestionFormData create(QuestionFormData formData) {
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		if (StringUtility.isNullOrEmpty(formData.getQuestionid())) {
			formData.setQuestionid(UUID.randomUUID().toString());
		}
		
		SQL.insert(DerbySql.FormDataCreate("QUESTIONS"), formData);
				
		return store(formData);
	}

	@Override
	public QuestionFormData load(QuestionFormData formData) {
		if (!ACCESS.check(new ReadQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		SQL.selectInto(DerbySql.FormDataLoad("QUESTIONS"), formData);
		
		return formData;
	}

	@Override
	public QuestionFormData store(QuestionFormData formData) {
		if (!ACCESS.check(new UpdateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		SQL.update(DerbySql.FormDataStore("QUESTIONS"), formData);	
		
		DerbySql.updateFormsTableColumns();
		
		return formData;
	}
}
