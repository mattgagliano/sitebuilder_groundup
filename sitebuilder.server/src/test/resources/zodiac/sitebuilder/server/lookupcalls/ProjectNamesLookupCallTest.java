package zodiac.sitebuilder.server.lookupcalls;

import java.util.List;

import org.eclipse.scout.rt.server.AbstractServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import zodiac.sitebuilder.shared.lookupcalls.ProjectNamesLookupCall;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(AbstractServerSession.class)
public class ProjectNamesLookupCallTest {

	protected ProjectNamesLookupCall createLookupCall() {
		return new ProjectNamesLookupCall();
	}

	@Test
	public void testLookupByAll() {
		ProjectNamesLookupCall call = createLookupCall();
		// TODO [gagli] fill call
		List<? extends ILookupRow<String>> data = call.getDataByAll();
		// TODO [gagli] verify data
	}

	@Test
	public void testLookupByKey() {
		ProjectNamesLookupCall call = createLookupCall();
		// TODO [gagli] fill call
		List<? extends ILookupRow<String>> data = call.getDataByKey();
		// TODO [gagli] verify data
	}

	@Test
	public void testLookupByText() {
		ProjectNamesLookupCall call = createLookupCall();
		// TODO [gagli] fill call
		List<? extends ILookupRow<String>> data = call.getDataByText();
		// TODO [gagli] verify data
	}

	// TODO [gagli] add test cases
}
