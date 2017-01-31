package zodiac.sitebuilder.client;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

import zodiac.sitebuilder.client.formslist.FormsListOutline;
import zodiac.sitebuilder.client.questionslist.QuestionsListOutline;
import zodiac.sitebuilder.shared.Icons;

/**
 * <h3>{@link Desktop}</h3>
 *
 * @author Matt.Gagliano
 */
public class Desktop extends AbstractDesktop {
	@Override
	protected String getConfiguredTitle() {
		return "SiteBuilder App";
	}

	@Override
	protected String getConfiguredLogoId() {
		return Icons.AppLogo;
	}

	@Override
	protected List<Class<? extends IOutline>> getConfiguredOutlines() {
		return CollectionUtility.<Class<? extends IOutline>>arrayList(QuestionsListOutline.class, FormsListOutline.class);
	}

	@Override
	protected void execGuiAttached() {
		super.execGuiAttached();
		selectFirstVisibleOutline();
	}

	protected void selectFirstVisibleOutline() {
		for (IOutline outline : getAvailableOutlines()) {
			if (outline.isEnabled() && outline.isVisible()) {
				setOutline(outline);
				break;
			}
		}
	}

	@Order(1000)
	public class QuestionsListOutlineViewButton extends AbstractOutlineViewButton {

		public QuestionsListOutlineViewButton() {
			this(QuestionsListOutline.class);
		}

		protected QuestionsListOutlineViewButton(Class<? extends QuestionsListOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}
	}
	
	@Order(2000)
	public class FormsListOutlineViewButton extends AbstractOutlineViewButton {

		public FormsListOutlineViewButton() {
			this(FormsListOutline.class);
		}

		protected FormsListOutlineViewButton(Class<? extends FormsListOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}
	}
}
