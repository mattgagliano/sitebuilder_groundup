package zodiac.sitebuilder.client.questionslist;

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

import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.Answer1Field;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.BoolField;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.OkButton;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.PromptField;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;
import zodiac.sitebuilder.shared.questionslist.UpdateQuestionPermission;

@FormData(value = QuestionFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class QuestionForm extends AbstractForm {

	private String questionId;
	
	@FormData
	public String getQuestionId() {
		return questionId;
	}
	
	@FormData
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	@Override
	public Object computeExclusiveKey() {
		return questionId;
	}
	
	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("QuestionForm");
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

	public PromptField getPromptField() {
		return getFieldByClass(PromptField.class);
	}

	public Answer1Field getAnswer1Field() {
		return getFieldByClass(Answer1Field.class);
	}

	public BoolField getBoolField() {
		return getFieldByClass(BoolField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		
		
		@Order(1000)
		public class PromptField extends AbstractStringField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Prompt");
			}

			@Override
			protected int getConfiguredMaxLength() {
				return 128;
			}
		}

		@Order(2000)
		public class Answer1Field extends AbstractStringField {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Answer1");
			}

			@Override
			protected int getConfiguredMaxLength() {
				return 128;
			}
		}

		@Order(3000)
		public class BoolField extends AbstractBooleanField {
			@Override
			protected String getConfiguredLabel() {
				return "BoolCol";
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
			IQuestionService service = BEANS.get(IQuestionService.class);
			QuestionFormData formData = new QuestionFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateQuestionPermission());
		}

		@Override
		protected void execStore() {
			IQuestionService service = BEANS.get(IQuestionService.class);
			QuestionFormData formData = new QuestionFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}
	

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execStore() {
			IQuestionService service = BEANS.get(IQuestionService.class);
			QuestionFormData formData = new QuestionFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
