package zodiac.sitebuilder.shared.questionslist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IQuestionsListService extends IService {

	QuestionsListTablePageData getQuestionsListTableData(SearchFilter filter);
}
