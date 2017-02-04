package zodiac.sitebuilder.client.formslist;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
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

import zodiac.sitebuilder.client.formslist.FormsListTablePage.Table;
import zodiac.sitebuilder.shared.formslist.FormsListTablePageData;
import zodiac.sitebuilder.shared.formslist.IFormsListService;

@Data(FormsListTablePageData.class)
public class FormsListTablePage extends AbstractPageWithTable<Table> {

	private List<AbstractBooleanColumn> m_injectedColumns;
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Forms");
	}
	
	@Override
	protected void execLoadData(SearchFilter filter) {
		
		updateCustomColumns();
		
		importPageData(BEANS.get(IFormsListService.class).getFormsListTableData(filter));
	}
	
	private void updateCustomColumns() {
		
		m_injectedColumns = new ArrayList<AbstractBooleanColumn>();
		Table table = getTable();
		List<String> q = BEANS.get(IFormsListService.class).getDBQuestionLabels();

		for (int i = 0; i < q.size(); i++) {
			m_injectedColumns.add(createCustomColumn(q.get(i)));
		}
	
		table.resetColumnConfiguration();
	}
			
	private AbstractBooleanColumn createCustomColumn(String label) {
		return new AbstractBooleanColumn() {
			@Override
			protected String getConfiguredHeaderText() {
				return label;
			}
				
			@Override
			public String getColumnId() {
				return label;
			}
			
			@Override 
			public String classId() {
				return label;
			}
		};
	}

	@Override
	protected void execPageActivated() {
		SearchFilter sf = new SearchFilter();
		execLoadData(sf);
	}		
	
	public class Table extends AbstractTable {

		public FormnameColumn getFormnameColumn() {
			return getColumnSet().getColumnByClass(FormnameColumn.class);
		}

		public ProjectColumn getProjectColumn() {
			return getColumnSet().getColumnByClass(ProjectColumn.class);
		}

		public FormidColumn getFormidColumn() {
			return getColumnSet().getColumnByClass(FormidColumn.class);
		}

		@Order(1000)
		public class FormidColumn extends AbstractStringColumn {
			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
			
			@Override
			protected boolean getConfiguredDisplayable() {
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
		public class FormnameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FormName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Override
		public void injectColumnsInternal(OrderedCollection<IColumn<?>> columnList) {
			if (m_injectedColumns != null) {
		        columnList.addAllLast(m_injectedColumns);
		    }	
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
				FormForm form = new FormForm();
				form.setFormid(getFormidColumn().getSelectedValue());
				form.addFormListener(new FormFormListener());
				
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
				FormForm form = new FormForm();
				form.addFormListener(new FormFormListener());
				
				form.startNew();
			}
		}
		
		private class FormFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {

				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}
			
		}
	}

}
