package zodiac.sitebuilder.server.formslist;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.server.sql.DerbySql;
import zodiac.sitebuilder.shared.formslist.FormsListTablePageData;
import zodiac.sitebuilder.shared.formslist.IFormsListService;

public class FormsListService implements IFormsListService {

	@Override
	public FormsListTablePageData getFormsListTableData(SearchFilter filter) {
		FormsListTablePageData pageData = new FormsListTablePageData();
		
		SQL.selectInto(DerbySql.TablePageSelectInto("FORMS"), new NVPair("page", pageData));
				
		return pageData;
	}
}
