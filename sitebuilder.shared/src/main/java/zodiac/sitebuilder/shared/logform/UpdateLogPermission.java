package zodiac.sitebuilder.shared.logform;

import java.security.BasicPermission;

public class UpdateLogPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateLogPermission() {
		super(UpdateLogPermission.class.getSimpleName());
	}
}
