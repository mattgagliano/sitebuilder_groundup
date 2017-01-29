package zodiac.sitebuilder.client.logform;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import zodiac.sitebuilder.client.logform.LogForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.logform.LogForm.MainBox.LogTypeField;
import zodiac.sitebuilder.client.logform.LogForm.MainBox.OkButton;
import zodiac.sitebuilder.client.logform.LogForm.MainBox.ProjectField;
import zodiac.sitebuilder.shared.logform.CreateLogPermission;
import zodiac.sitebuilder.shared.logform.ILogService;
import zodiac.sitebuilder.shared.logform.LogFormData;
import zodiac.sitebuilder.shared.logform.UpdateLogPermission;
import zodiac.sitebuilder.shared.lookupcalls.FormTypesByProjectLookupCall;
import zodiac.sitebuilder.shared.lookupcalls.ProjectNamesLookupCall;

@FormData(value = LogFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class LogForm extends AbstractForm {

	private String logId;
	
	@FormData
	public String getLogId() {
		return logId;
	}
	
	@FormData
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	@Override
	public Object computeExclusiveKey() {
		return logId;
	}
	
	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("LogForm");
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

	public LogTypeField getLogTypeField() {
		return getFieldByClass(LogTypeField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
				
		@Order(1000)
		public class ProjectField extends AbstractSmartField<String> {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Project");
			}
			
			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return ProjectNamesLookupCall.class;
			}
		}

		@Order(2000)
		public class LogTypeField extends AbstractSmartField<String> {
			
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("LogType");
			}
			
			@Override
			protected Class<? extends IValueField<String>> getConfiguredMasterField() {
				return MainBox.ProjectField.class;
			}
			
			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return FormTypesByProjectLookupCall.class;
			}
			
			@Override
			protected void execChangedValue() {
				if (!(getValue() == null)) {					
					ILogService service = BEANS.get(ILogService.class);
					Boolean[] fieldsConfig = service.loadFieldsConfig(getValue());
					
					for (Integer i = 0; i < fieldsConfig.length; i++) {
						getFieldById("Q"+i.toString()).setVisible(fieldsConfig[i]);
					}
				}
			}
		}

		@Override
		protected void injectFieldsInternal(OrderedCollection<IFormField> fieldList) {
			ILogService service = BEANS.get(ILogService.class);
			String[] fieldNames = service.loadFieldNames();
			
			for (String str : fieldNames) {
				fieldList.addLast(createCustomField(str));
			}
		}
		
		protected IFormField createCustomField(String fieldName) {
			return new AbstractStringField() {
				
				@Override
				public String classId() {
					return fieldName;
				}
				
				@Override
				protected String getConfiguredLabel() {
					return fieldName;
				}
				
				@Override
				public String getFieldId() {
					return fieldName;
				}
				
				@Override
				protected boolean getConfiguredVisible() {
					return false;
				}
			};
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
			ILogService service = BEANS.get(ILogService.class);
			LogFormData formData = new LogFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateLogPermission());
		}

		@Override
		protected void execStore() {
			ILogService service = BEANS.get(ILogService.class);
			LogFormData formData = new LogFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ILogService service = BEANS.get(ILogService.class);
			LogFormData formData = new LogFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateLogPermission());
		}

		@Override
		protected void execStore() {
			ILogService service = BEANS.get(ILogService.class);
			LogFormData formData = new LogFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
