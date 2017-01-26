package zodiac.sitebuilder.shared.questionslist;

import java.security.BasicPermission;

public class CreateQuestionPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateQuestionPermission() {
		super(CreateQuestionPermission.class.getSimpleName());
	}
}
