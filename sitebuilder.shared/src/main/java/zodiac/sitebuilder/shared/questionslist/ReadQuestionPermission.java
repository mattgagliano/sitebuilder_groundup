package zodiac.sitebuilder.shared.questionslist;

import java.security.BasicPermission;

public class ReadQuestionPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadQuestionPermission() {
		super(ReadQuestionPermission.class.getSimpleName());
	}
}
