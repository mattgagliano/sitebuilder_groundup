package zodiac.sitebuilder.shared.lookupcalls;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IFormTypesByProjectLookupService extends ILookupService<String> {
}