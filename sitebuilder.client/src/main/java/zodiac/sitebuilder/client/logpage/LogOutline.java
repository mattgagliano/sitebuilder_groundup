package zodiac.sitebuilder.client.logpage;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class LogOutline extends AbstractOutline {
	
	@Override 
	protected String getConfiguredTitle() {
		return TEXTS.get("Logs");
	}
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new LogTablePage());
	}

}
