package zodiac.sitebuilder.client.questionslist;

import java.util.Set;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import zodiac.sitebuilder.client.questionslist.QuestionsListTablePage.Table;
import zodiac.sitebuilder.shared.questionslist.IQuestionService;
import zodiac.sitebuilder.shared.questionslist.QuestionsListTablePageData;

@Data(QuestionsListTablePageData.class)
public class QuestionsListTablePage extends AbstractPageWithTable<Table> {
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("QuestionsList");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {			
		importPageData(BEANS.get(IQuestionService.class).getQuestionsListTableData());
	}
	
	@Override
	protected void execPageActivated() {
		SearchFilter sf = new SearchFilter();
		execLoadData(sf);
	}	
	
	public class Table extends AbstractTable {
		
		
	
		public PromptColumn getPromptColumn() {
			return getColumnSet().getColumnByClass(PromptColumn.class);
		}

		public QuestionidColumn getQuestionidColumn() {
			return getColumnSet().getColumnByClass(QuestionidColumn.class);
		}

		@Order(0)
		public class QuestionidColumn extends AbstractStringColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}

			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}
		}


		@Order(1000)
		public class PromptColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Prompt");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}		
		
		@Override
		protected Class<? extends IMenu> getConfiguredDefaultMenu() {
			return EditMenu.class;
		}

		@Order(2000)
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
				QuestionForm form = new QuestionForm();
				form.setQuestionid(getQuestionidColumn().getSelectedValue());
				form.addFormListener(new QuestionFormListener());
				
				form.startModify();
			}
		}


		@Order(1000)
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
				QuestionForm form = new QuestionForm();
				form.addFormListener(new QuestionFormListener());
				
				form.startNew();
			}
		}


		@Order(3000)
		public class DeleteMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Delete");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection);
			}

			@Override
			protected void execAction() {
				BEANS.get(IQuestionService.class).deleteRow(getTable().getSelectedRow().getCell(getQuestionidColumn())
																									.getValue().toString());
				getTable().deleteRow(getTable().getSelectedRow());	
			}
		}
		
		private class QuestionFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {

				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}
			
		}
	}
}
