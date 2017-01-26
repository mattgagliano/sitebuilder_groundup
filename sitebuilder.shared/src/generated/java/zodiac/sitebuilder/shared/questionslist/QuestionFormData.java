package zodiac.sitebuilder.shared.questionslist;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "zodiac.sitebuilder.client.questionslist.QuestionForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class QuestionFormData extends AbstractFormData {

	private static final long serialVersionUID = 1L;

	public Answer1 getAnswer1() {
		return getFieldByClass(Answer1.class);
	}

	public Bool getBool() {
		return getFieldByClass(Bool.class);
	}

	public Prompt getPrompt() {
		return getFieldByClass(Prompt.class);
	}

	/**
	 * access method for property QuestionId.
	 */
	public String getQuestionId() {
		return getQuestionIdProperty().getValue();
	}

	/**
	 * access method for property QuestionId.
	 */
	public void setQuestionId(String questionId) {
		getQuestionIdProperty().setValue(questionId);
	}

	public QuestionIdProperty getQuestionIdProperty() {
		return getPropertyByClass(QuestionIdProperty.class);
	}

	public static class Answer1 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Bool extends AbstractValueFieldData<Boolean> {

		private static final long serialVersionUID = 1L;
	}

	public static class Prompt extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class QuestionIdProperty extends AbstractPropertyData<String> {

		private static final long serialVersionUID = 1L;
	}
}
