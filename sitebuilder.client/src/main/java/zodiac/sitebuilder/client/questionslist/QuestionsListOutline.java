package zodiac.sitebuilder.client.questionslist;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class QuestionsListOutline extends AbstractOutline {

	@Override 
	protected String getConfiguredTitle() {
		return TEXTS.get("QuestionsList");
	}
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new QuestionsListTablePage());
	}
}
