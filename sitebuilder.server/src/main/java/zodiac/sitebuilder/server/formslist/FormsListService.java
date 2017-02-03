package zodiac.sitebuilder.server.formslist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.server.sql.DerbySql;
import zodiac.sitebuilder.shared.formslist.FormsListTablePageData;
import zodiac.sitebuilder.shared.formslist.FormsListTablePageData.FormsListTableRowData;
import zodiac.sitebuilder.shared.formslist.IFormsListService;

public class FormsListService implements IFormsListService {

	@Override
	public FormsListTablePageData getFormsListTableData(SearchFilter filter) {
		FormsListTablePageData pageData = new FormsListTablePageData();
		List<String> l = DerbySql.getColumns("FORMS");
		
		Object[][] rawPageData = SQL.select(DerbySql.TablePageSelect("FORMS"));
		
		for (int i = 0; i < rawPageData.length; i++) {
			FormsListTableRowData r = pageData.addRow();
			r.setFormid((String) rawPageData[i][0]);
			r.setProject((String) rawPageData[i][1]);
			r.setFormname((String) rawPageData[i][2]);
			
			for (int j = 3; j < rawPageData[i].length; j++) {
				r.setCustomValue(DerbySql.ToProper(l.get(j)), (String) rawPageData[i][j]);
			}
		}
				
		return pageData;
	}
	
	@Override
	public List<String> getDBQuestionLabels() {
		List<String> q = new ArrayList<String>();
		Object[][] rawPageData = SQL.select(DerbySql.TablePageSelect("QUESTIONS"));
		
		for (int i = 0; i < rawPageData.length; i++) {
			q.add((String) rawPageData[i][1]);
		}
		
		return q;
	}
}
