package zodiac.sitebuilder.shared.questionslist;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "zodiac.sitebuilder.client.questionslist.QuestionsListTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class QuestionsListTablePageData extends AbstractTablePageData {

	private static final long serialVersionUID = 1L;

	@Override
	public QuestionsListTableRowData addRow() {
		return (QuestionsListTableRowData) super.addRow();
	}

	@Override
	public QuestionsListTableRowData addRow(int rowState) {
		return (QuestionsListTableRowData) super.addRow(rowState);
	}

	@Override
	public QuestionsListTableRowData createRow() {
		return new QuestionsListTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return QuestionsListTableRowData.class;
	}

	@Override
	public QuestionsListTableRowData[] getRows() {
		return (QuestionsListTableRowData[]) super.getRows();
	}

	@Override
	public QuestionsListTableRowData rowAt(int index) {
		return (QuestionsListTableRowData) super.rowAt(index);
	}

	public void setRows(QuestionsListTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class QuestionsListTableRowData extends AbstractTableRowData {

		private static final long serialVersionUID = 1L;
		public static final String questionId = "questionId";
		public static final String prompt = "prompt";
		public static final String answer1 = "answer1";
		public static final String bool = "bool";
		private String m_questionId;
		private String m_prompt;
		private String m_answer1;
		private Boolean m_bool;

		public String getQuestionId() {
			return m_questionId;
		}

		public void setQuestionId(String newQuestionId) {
			m_questionId = newQuestionId;
		}

		public String getPrompt() {
			return m_prompt;
		}

		public void setPrompt(String newPrompt) {
			m_prompt = newPrompt;
		}

		public String getAnswer1() {
			return m_answer1;
		}

		public void setAnswer1(String newAnswer1) {
			m_answer1 = newAnswer1;
		}

		public Boolean getBool() {
			return m_bool;
		}

		public void setBool(Boolean newBool) {
			m_bool = newBool;
		}
	}
}
