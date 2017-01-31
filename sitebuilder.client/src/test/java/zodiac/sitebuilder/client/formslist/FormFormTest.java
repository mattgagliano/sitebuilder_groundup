package zodiac.sitebuilder.client.formslist;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.IFormService;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class FormFormTest {

	@BeanMock
	private IFormService m_mockSvc;

	@Before
	public void setup() {
		FormFormData answer = new FormFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(FormFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(FormFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(FormFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(FormFormData.class))).thenReturn(answer);
	}

	// TODO [gagli] add test cases
}
