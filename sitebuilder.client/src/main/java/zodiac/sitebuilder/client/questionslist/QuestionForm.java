package zodiac.sitebuilder.client.questionslist;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.CancelButton;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.OkButton;
import zodiac.sitebuilder.client.questionslist.QuestionForm.MainBox.PromptField;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionFormData;
import zodiac.sitebuilder.shared.questionslist.UpdateQuestionPermission;

@FormData(value = QuestionFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class QuestionForm extends AbstractForm {

	private String questionid;
	
	@FormData
	public String getQuestionid() {
		return questionid;
	}
	
	@FormData
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	
	@Override
	public Object computeExclusiveKey() {
		return questionid;
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
