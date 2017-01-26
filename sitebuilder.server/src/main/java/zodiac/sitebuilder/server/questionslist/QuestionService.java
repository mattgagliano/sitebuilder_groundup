package zodiac.sitebuilder.server.questionslist;

import java.util.UUID;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import zodiac.sitebuilder.shared.questionslist.CreateQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;
import zodiac.sitebuilder.shared.questionslist.ReadQuestionPermission;
import zodiac.sitebuilder.shared.questionslist.UpdateQuestionPermission;

public class QuestionService implements IQuestionService {

	@Override
	public QuestionFormData prepareCreate(QuestionFormData formData) {
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Matt.Gagliano] add business logic here.
		return formData;
	}

	@Override
	public QuestionFormData create(QuestionFormData formData) {
		if (!ACCESS.check(new CreateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		if (StringUtility.isNullOrEmpty(formData.getQuestionId())) {
			formData.setQuestionId(UUID.randomUUID().toString());
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(formData.getPrompt().getValue());
		System.out.println(formData.getAnswer1().getValue());
		System.out.println(formData.getBool().getValue());

		formData.getPrompt().setValue("prompt - create");
		formData.getAnswer1().setValue("ans1 - create");
		formData.getBool().setValue(true);
		
		System.out.println("post-manipulation");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(formData.getPrompt().getValue());
		System.out.println(formData.getAnswer1().getValue());
		System.out.println(formData.getBool().getValue());
				
		return store(formData);
	}

	@Override
	public QuestionFormData load(QuestionFormData formData) {
		if (!ACCESS.check(new ReadQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		formData.getPrompt().setValue("prompt - load");
		formData.getAnswer1().setValue("ans1 - load");
		formData.getBool().setValue(true);
		
		return formData;
	}

	@Override
	public QuestionFormData store(QuestionFormData formData) {
		if (!ACCESS.check(new UpdateQuestionPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("storing question formdata");

		formData.getPrompt().setValue("prompt - store");
		formData.getAnswer1().setValue("ans1 - store");
		formData.getBool().setValue(true);
		
		return formData;
	}
}
