package zodiac.sitebuilder.client.formslist;

import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.client.formslist.FormsListTablePage.Table;

public class FormsListTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("FormsList");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IFormsListService.class).getTableData(filter));
	}

	public class Table extends AbstractTable {

		public FormNameColumn getFormNameColumn() {
			return getColumnSet().getColumnByClass(FormNameColumn.class);
		}

		public Q0Column getQ0Column() {
			return getColumnSet().getColumnByClass(Q0Column.class);
		}

		public Q1Column getQ1Column() {
			return getColumnSet().getColumnByClass(Q1Column.class);
		}

		public Q2Column getQ2Column() {
			return getColumnSet().getColumnByClass(Q2Column.class);
		}

		public Q3Column getQ3Column() {
			return getColumnSet().getColumnByClass(Q3Column.class);
		}

		public ProjectColumn getProjectColumn() {
			return getColumnSet().getColumnByClass(ProjectColumn.class);
		}

		public FormIdColumn getFormIdColumn() {
			return getColumnSet().getColumnByClass(FormIdColumn.class);
		}

		@Order(1000)
		public class FormIdColumn extends AbstractStringColumn {
			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(2000)
		public class ProjectColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Project");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class FormNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FormName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class Q0Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Q0");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class Q1Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Q1");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(6000)
		public class Q2Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Q2");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(7000)
		public class Q3Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Q3");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}
		
		
	}
}
