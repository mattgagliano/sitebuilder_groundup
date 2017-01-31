package zodiac.sitebuilder.shared.formslist;

import java.security.BasicPermission;

public class ReadFormPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadFormPermission() {
		super(ReadFormPermission.class.getSimpleName());
	}
}
