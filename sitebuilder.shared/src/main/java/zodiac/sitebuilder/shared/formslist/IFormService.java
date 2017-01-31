package zodiac.sitebuilder.shared.formslist;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IFormService extends IService {

	FormFormData prepareCreate(FormFormData formData);

	FormFormData create(FormFormData formData);

	FormFormData load(FormFormData formData);

	FormFormData store(FormFormData formData);
}
