package zodiac.sitebuilder.client.formslist;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

import zodiac.sitebuilder.client.formslist.FormForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormnameField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.OkButton;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.ProjectField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.Q0Field;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.Q1Field;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.Q2Field;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.Q3Field;
import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.IFormService;
import zodiac.sitebuilder.shared.formslist.UpdateFormPermission;

@FormData(value = FormFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class FormForm extends AbstractForm {

	private String formid;
	
	@FormData
	public String getFormid() {
		return formid;
	}
	
	@FormData
	public void setFormid(String formid) {
		this.formid = formid;
	}
	
	@Override
	public Object computeExclusiveKey() {
		return formid;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Form");
	}
	
	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public ProjectField getProjectField() {
		return getFieldByClass(ProjectField.class);
	}

	public FormnameField getFormnameField() {
		return getFieldByClass(FormnameField.class);
	}

	public Q0Field getQ0Field() {
		return getFieldByClass(Q0Field.class);
	}

	public Q1Field getQ1Field() {
		return getFieldByClass(Q1Field.class);
	}

	public Q2Field getQ2Field() {
		return getFieldByClass(Q2Field.class);
	}

	public Q3Field getQ3Field() {
		return getFieldByClass(Q3Field.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class ProjectField extends AbstractStringField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Project");
			}

			@Override
			protected int getConfiguredMaxLength() {
				return 128;
			}
		}

		@Order(2000)
		public class FormnameField extends AbstractStringField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("FormName");
			}

			@Override
			protected int getConfiguredMaxLength() {
				return 128;
			}
		}

		@Order(3000)
		public class Q0Field extends AbstractBooleanField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Q0");
			}
		}

		@Order(4000)
		public class Q1Field extends AbstractBooleanField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Q1");
			}
		}

		@Order(5000)
		public class Q2Field extends AbstractBooleanField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Q2");
			}
		}

		@Order(6000)
		public class Q3Field extends AbstractBooleanField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Q3");
			}
		}
		
		@Order(100000)
		public class OkButton extends AbstractOkButton {
		}

		@Order(101000)
		public class CancelButton extends AbstractCancelButton {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateFormPermission());
		}

		@Override
		protected void execStore() {
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execStore() {
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
