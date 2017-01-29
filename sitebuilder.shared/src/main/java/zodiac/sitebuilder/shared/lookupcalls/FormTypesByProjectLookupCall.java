package zodiac.sitebuilder.shared.lookupcalls;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class FormTypesByProjectLookupCall extends LookupCall<String> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<? extends ILookupService<String>> getConfiguredService() {
		return IFormTypesByProjectLookupService.class;
	}
}