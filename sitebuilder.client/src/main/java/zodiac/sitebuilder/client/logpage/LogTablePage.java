package zodiac.sitebuilder.client.logpage;

import java.util.Set;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.IColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.client.logform.LogForm;
import zodiac.sitebuilder.client.logpage.LogTablePage.Table;
import zodiac.sitebuilder.shared.logpage.ILogService;
import zodiac.sitebuilder.shared.logpage.LogTablePageData;

@Data(LogTablePageData.class)
public class LogTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("LogTablePage");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(ILogService.class).getLogTableData(filter));
	}

	public class Table extends AbstractTable {
		
		
		
		@Order(1000)
		public class LogIdColumn extends AbstractStringColumn {
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
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
		public class FormTypeColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FormType");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(4000)
		public class Q0Column extends AbstractStringColumn {
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
		public class Q1Column extends AbstractStringColumn {
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
		public class Q2Column extends AbstractStringColumn {
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
		public class Q3Column extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Q3");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		
		@Override
		protected void injectColumnsInternal(OrderedCollection<IColumn<?>> columnList) {
			//columnList.addLast(createCustomColumn("Q1"));
			//columnList.addLast(createCustomColumn("Q2"));
			//columnList.addLast(createCustomColumn("Q3"));
		}
		
		protected IColumn<?> createCustomColumn(String columnName) {
			return new AbstractStringColumn() {
				
				@Override
				public String classId() {
					return columnName;
				}
				
				@Override
				protected String getConfiguredHeaderText() {
					return columnName;
				}
				
				@Override
				public String getColumnId() {
					return columnName;
				}
			};
		}
		
		@Override
		protected Class<? extends IMenu> getConfiguredDefaultMenu() {
			return EditMenu.class;
		}


		@Order(1000)
		public class EditMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				LogForm form = new LogForm();
				form.setLogId(getLogIdColumn().getSelectedValue());
				form.addFormListener(new LogFormListener());
				
				form.startModify();
			}
		}


		@Order(2000)
		public class NewMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("New");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				System.out.println(Table.this.getColumns().toString());
				LogForm form = new LogForm();
				form.addFormListener(new LogFormListener());
				
				form.startNew();
			}
		}
		
		private class LogFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {

				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}
			
		}

		public FormTypeColumn getFormTypeColumn() {
			return getColumnSet().getColumnByClass(FormTypeColumn.class);
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

		public LogIdColumn getLogIdColumn() {
			return getColumnSet().getColumnByClass(LogIdColumn.class);
		}
	}
}
