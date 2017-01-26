package zodiac.sitebuilder.shared.questionslist;

import java.security.BasicPermission;

public class UpdateQuestionPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateQuestionPermission() {
		super(UpdateQuestionPermission.class.getSimpleName());
	}
}
