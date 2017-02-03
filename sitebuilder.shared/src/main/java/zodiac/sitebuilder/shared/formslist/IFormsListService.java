package zodiac.sitebuilder.shared.formslist;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IFormsListService extends IService {

	FormsListTablePageData getFormsListTableData(SearchFilter filter);
	
	List<String> getDBQuestionLabels();
}
