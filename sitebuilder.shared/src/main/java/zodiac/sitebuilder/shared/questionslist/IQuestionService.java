package zodiac.sitebuilder.shared.questionslist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IQuestionService extends IService {
	
	QuestionsListTablePageData getQuestionsListTableData();

	QuestionFormData prepareCreate(QuestionFormData formData);

	QuestionFormData create(QuestionFormData formData);

	QuestionFormData load(QuestionFormData formData);

	QuestionFormData store(QuestionFormData formData);

	void deleteRow(String row_id);
}
