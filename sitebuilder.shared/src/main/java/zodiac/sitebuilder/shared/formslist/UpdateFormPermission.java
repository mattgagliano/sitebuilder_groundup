package zodiac.sitebuilder.shared.formslist;

import java.security.BasicPermission;

public class UpdateFormPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateFormPermission() {
		super(UpdateFormPermission.class.getSimpleName());
	}
}
