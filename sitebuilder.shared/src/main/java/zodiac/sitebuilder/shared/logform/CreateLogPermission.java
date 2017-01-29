package zodiac.sitebuilder.shared.logform;

import java.security.BasicPermission;

public class CreateLogPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateLogPermission() {
		super(CreateLogPermission.class.getSimpleName());
	}
}
