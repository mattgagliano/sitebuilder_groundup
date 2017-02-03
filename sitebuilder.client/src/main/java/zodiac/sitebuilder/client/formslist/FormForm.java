package zodiac.sitebuilder.client.formslist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.rt.shared.TEXTS;

import zodiac.sitebuilder.client.formslist.FormForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormnameField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.OkButton;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.ProjectField;
import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.IFormService;
import zodiac.sitebuilder.shared.formslist.IFormsListService;
import zodiac.sitebuilder.shared.formslist.UpdateFormPermission;

@FormData(value = FormFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class FormForm extends AbstractForm {

	private String formid;
	private List<IFormField> m_injectedFields;
	
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

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}
	
	private void updateCustomFields() {
		
		m_injectedFields = new ArrayList<IFormField>();
		List<String> q = BEANS.get(IFormsListService.class).getDBQuestionLabels();
		
		for (int i = 0; i < q.size(); i++) {
			m_injectedFields.add(createCustomField(q.get(i)));
		}
		
		//table.resetcolumns was here
	}
	
	private IFormField createCustomField(String label) {
		return new AbstractStringField() {
			@Override
			protected String getConfiguredLabel() {
				return label;
			}
				
			@Override
			public String getFieldId() {
				return label;
			}
			
			@Override 
			public String classId() {
				return label;
			}
		};
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

		@Override
		public void injectFieldsInternal(OrderedCollection<IFormField> fieldList) {
			if (m_injectedFields != null) {
		        fieldList.addAllLast(m_injectedFields);
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
			updateCustomFields();
			
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateFormPermission());
		}

		@Override
		protected void execStore() {
			updateCustomFields();
			
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execStore() {
			updateCustomFields();
			
			IFormService service = BEANS.get(IFormService.class);
			FormFormData formData = new FormFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
