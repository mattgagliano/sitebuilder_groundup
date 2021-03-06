package zodiac.sitebuilder.shared.formslist;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldBeanData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "zodiac.sitebuilder.client.formslist.FormForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class FormFormData extends AbstractFormData {

	private static final long serialVersionUID = 1L;

	public FormFormTable getFormFormTable() {
		return getFieldByClass(FormFormTable.class);
	}

	/**
	 * access method for property Formid.
	 */
	public String getFormid() {
		return getFormidProperty().getValue();
	}

	/**
	 * access method for property Formid.
	 */
	public void setFormid(String formid) {
		getFormidProperty().setValue(formid);
	}

	public FormidProperty getFormidProperty() {
		return getPropertyByClass(FormidProperty.class);
	}

	public Formname getFormname() {
		return getFieldByClass(Formname.class);
	}

	public Project getProject() {
		return getFieldByClass(Project.class);
	}

	public static class FormFormTable extends AbstractTableFieldBeanData {

		private static final long serialVersionUID = 1L;

		@Override
		public FormFormTableRowData addRow() {
			return (FormFormTableRowData) super.addRow();
		}

		@Override
		public FormFormTableRowData addRow(int rowState) {
			return (FormFormTableRowData) super.addRow(rowState);
		}

		@Override
		public FormFormTableRowData createRow() {
			return new FormFormTableRowData();
		}

		@Override
		public Class<? extends AbstractTableRowData> getRowType() {
			return FormFormTableRowData.class;
		}

		@Override
		public FormFormTableRowData[] getRows() {
			return (FormFormTableRowData[]) super.getRows();
		}

		@Override
		public FormFormTableRowData rowAt(int index) {
			return (FormFormTableRowData) super.rowAt(index);
		}

		public void setRows(FormFormTableRowData[] rows) {
			super.setRows(rows);
		}

		public static class FormFormTableRowData extends AbstractTableRowData {

			private static final long serialVersionUID = 1L;
			public static final String enabled = "enabled";
			public static final String prompt = "prompt";
			private Boolean m_enabled;
			private String m_prompt;

			public Boolean getEnabled() {
				return m_enabled;
			}

			public void setEnabled(Boolean newEnabled) {
				m_enabled = newEnabled;
			}

			public String getPrompt() {
				return m_prompt;
			}

			public void setPrompt(String newPrompt) {
				m_prompt = newPrompt;
			}
		}
	}

	public static class FormidProperty extends AbstractPropertyData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Formname extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Project extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}
}
