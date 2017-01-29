package zodiac.sitebuilder.shared.logpage;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface ILogService extends IService {

	LogTablePageData getLogTableData(SearchFilter filter);
}
