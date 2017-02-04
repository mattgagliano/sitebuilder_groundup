package zodiac.sitebuilder.client.formslist;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

import zodiac.sitebuilder.client.formslist.FormForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormFormTableField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormIdentityBox;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormIdentityBox.FormnameField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.FormIdentityBox.ProjectField;
import zodiac.sitebuilder.client.formslist.FormForm.MainBox.OkButton;
import zodiac.sitebuilder.shared.formslist.FormFormData;
import zodiac.sitebuilder.shared.formslist.IFormService;
import zodiac.sitebuilder.shared.formslist.UpdateFormPermission;

@FormData(value = FormFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class FormForm extends AbstractForm {

	private String formid;
//	private List<IFormField> m_injectedTableFieldRows;
	
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

	
	public FormFormTableField getFormFormTableField() {
		return getFieldByClass(FormFormTableField.class);
	}

	public ProjectField getProjectField() {
		return getFieldByClass(ProjectField.class);
	}

	public FormnameField getFormnameField() {
		return getFieldByClass(FormnameField.class);
	}

	public FormIdentityBox getFormIdentityBox() {
		return getFieldByClass(FormIdentityBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		
		
		@Order(-2000)
		public class FormIdentityBox extends AbstractGroupBox {
				
			@Override
			protected int getConfiguredGridW() {
				return 2;
			}
			
			@Override
			protected int getConfiguredGridH() {
				return 1;
			}
			
			@Order(-1000)
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
	
			@Order(-500)
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
		}
		
		@Order(0)
		public class FormFormTableField extends AbstractTableField<FormFormTableField.Table> {
			
			@Override
			protected int getConfiguredGridH() {
				return 10;
			}
			
			@Override
			protected int getConfiguredGridW() {
				return 2;
			}
						
			public class Table extends AbstractTable {

				public EnabledColumn getEnabledColumn() {
					return getColumnSet().getColumnByClass(EnabledColumn.class);
				}

				public PromptColumn getPromptColumn() {
					return getColumnSet().getColumnByClass(PromptColumn.class);
				}
				
				@Order(2000)
				public class PromptColumn extends AbstractStringColumn {
					@Override
					protected String getConfiguredHeaderText() {
						return TEXTS.get("Prompt");
					}

					@Override
					protected int getConfiguredWidth() {
						return 750;
					}
					
					@Override
					protected boolean getConfiguredAutoOptimizeWidth() {						
						return true;
					}
				}

				@Order(1000)
				public class EnabledColumn extends AbstractBooleanColumn {
					@Override
					protected String getConfiguredHeaderText() {
						return TEXTS.get("Enable");
					}

					@Override
					protected int getConfiguredWidth() {
						return 80;
					}
					
					@Override
					protected boolean getConfiguredEditable() {
						return true;
					}
					
					@Override
					public boolean isFixedWidth() {
						return true;
					}
				}				
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
