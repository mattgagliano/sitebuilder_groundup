package zodiac.sitebuilder.shared.questionslist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IQuestionService extends IService {
	
	QuestionsListTablePageData getQuestionsListTableData(SearchFilter filter);

	QuestionFormData prepareCreate(QuestionFormData formData);

	QuestionFormData create(QuestionFormData formData);

	QuestionFormData load(QuestionFormData formData);

	QuestionFormData store(QuestionFormData formData);
}
