package zodiac.sitebuilder.server.questionslist;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.shared.questionslist.IQuestionsListService;
import zodiac.sitebuilder.shared.questionslist.QuestionsListTablePageData;

public class QuestionsListService implements IQuestionsListService {

	@Override
	public QuestionsListTablePageData getQuestionsListTableData(SearchFilter filter) {
		QuestionsListTablePageData pageData = new QuestionsListTablePageData();
		// TODO [Matt.Gagliano] fill pageData.
		return pageData;
	}
}
