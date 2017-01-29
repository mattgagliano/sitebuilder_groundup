package zodiac.sitebuilder.server.logpage;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.shared.logpage.ILogService;
import zodiac.sitebuilder.shared.logpage.LogTablePageData;

public class LogService implements ILogService {

	@Override
	public LogTablePageData getLogTableData(SearchFilter filter) {
		LogTablePageData pageData = new LogTablePageData();
		
		String str = "SELECT project, formtype, Q0, Q1, Q2, Q3 FROM FORMS "
				+ " INTO :{page.Project}, :{page.FormType}, :{page.Q0}, :{page.Q1}, :{page.Q2}, :{page.Q3}";
				
		SQL.selectInto(str, new NVPair("page", pageData));
		
		return pageData;
	}
}
