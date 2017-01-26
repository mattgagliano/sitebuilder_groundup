package zodiac.sitebuilder.client.questionslist;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class QuestionFormTest {

	@BeanMock
	private IQuestionService m_mockSvc;

	@Before
	public void setup() {
		QuestionFormData answer = new QuestionFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(QuestionFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(QuestionFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(QuestionFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(QuestionFormData.class))).thenReturn(answer);
	}

	// TODO [Matt.Gagliano] add test cases
}
