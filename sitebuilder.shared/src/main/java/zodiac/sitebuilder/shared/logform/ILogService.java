package zodiac.sitebuilder.shared.logform;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ILogService extends IService {

	LogFormData prepareCreate(LogFormData formData);

	LogFormData create(LogFormData formData);

	LogFormData load(LogFormData formData);

	LogFormData store(LogFormData formData);

	Boolean[] loadFieldsConfig(String value);

	String[] loadFieldNames();
}
