package zodiac.sitebuilder.shared.formslist;

import java.security.BasicPermission;

public class CreateFormPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateFormPermission() {
		super(CreateFormPermission.class.getSimpleName());
	}
}
