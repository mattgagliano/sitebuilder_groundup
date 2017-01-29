package zodiac.sitebuilder.server.questionslist;

import java.util.UUID;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import zodiac.sitebuilder.shared.questionslist.CreateQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;
import zodiac.sitebuilder.shared.questionslist.QuestionsListTablePageData;
import zodiac.sitebuilder.shared.questionslist.ReadQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.UpdateQuestionPermission;

public class QuestionService implements IQuestionService {

	@Override
	public QuestionsListTablePageData getQuestionsListTableData(SearchFilter filter) {
		QuestionsListTablePageData pageData = new QuestionsListTablePageData();

		String tablePageSelectInto = "SELECT question_id, prompt "
				+ "	FROM QUESTIONS "
				+ " INTO :{page.QuestionId}, :{page.prompt}";
		
		//sql page select + into page data	{:page.fieldName}
		SQL.selectInto(tablePageSelectInto, new NVPair("page", pageData));
		
		return pageData;
	}
	
	@Override
	public QuestionFormData prepareCreate(QuestionFormData formData) {
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Matt.Gagliano] add business logic here.
		return formData;
	}

	@Override
	public QuestionFormData create(QuestionFormData formData) {
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		if (StringUtility.isNullOrEmpty(formData.getQuestionId())) {
			formData.setQuestionId(UUID.randomUUID().toString());
		}
		
		SQL.insert("INSERT INTO QUESTIONS (question_id) VALUES (:QuestionId)", formData);
				
		return store(formData);
	}

	@Override
	public QuestionFormData load(QuestionFormData formData) {
		if (!ACCESS.check(new ReadQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		String formDataSelectInto = "SELECT prompt FROM QUESTIONS "
				+ "	WHERE   question_id = :QuestionId "
				+ " INTO    :prompt";
		
		SQL.selectInto(formDataSelectInto, formData);
		
		return formData;
	}

	@Override
	public QuestionFormData store(QuestionFormData formData) {
		if (!ACCESS.check(new UpdateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		String formDataUpdate = "UPDATE QUESTIONS "
				+ "SET		prompt = :prompt "
				+ "WHERE 	question_id = :QuestionId";
		
		SQL.update(formDataUpdate, formData);
		
		return formData;
	}
}
