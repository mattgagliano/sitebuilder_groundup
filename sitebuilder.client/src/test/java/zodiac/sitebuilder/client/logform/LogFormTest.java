package zodiac.sitebuilder.client.logform;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import zodiac.sitebuilder.shared.logform.ILogService;
import zodiac.sitebuilder.shared.logform.LogFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class LogFormTest {

	@BeanMock
	private ILogService m_mockSvc;

	@Before
	public void setup() {
		LogFormData answer = new LogFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(LogFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(LogFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(LogFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(LogFormData.class))).thenReturn(answer);
	}

	// TODO [gagli] add test cases
}
