package zodiac.sitebuilder.shared.logform;

import java.security.BasicPermission;

public class ReadLogPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadLogPermission() {
		super(ReadLogPermission.class.getSimpleName());
	}
}
