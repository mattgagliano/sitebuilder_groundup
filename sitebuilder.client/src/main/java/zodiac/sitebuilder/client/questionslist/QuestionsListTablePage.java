package zodiac.sitebuilder.client.questionslist;

import java.util.Set;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
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
import zodiac.sitebuilder.shared.questionslist.IQuestionsListService;
import zodiac.sitebuilder.shared.questionslist.QuestionsListTablePageData;

@Data(QuestionsListTablePageData.class)
public class QuestionsListTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("QuestionsList");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IQuestionsListService.class).getQuestionsListTableData(filter));
	}

	public class Table extends AbstractTable {

		public Answer1Column getAnswer1Column() {
			return getColumnSet().getColumnByClass(Answer1Column.class);
		}

		public BoolColumn getBoolColumn() {
			return getColumnSet().getColumnByClass(BoolColumn.class);
		}

		public QuestionIdColumn getQuestionIdColumn() {
			return getColumnSet().getColumnByClass(QuestionIdColumn.class);
		}

		public PromptColumn getPromptColumn() {
			return getColumnSet().getColumnByClass(PromptColumn.class);
		}


		@Order(0)
		public class QuestionIdColumn extends AbstractStringColumn {
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

		@Order(2000)
		public class Answer1Column extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Answer1");
			}
			
			@Override
			protected boolean getConfiguredEditable() {
				return true;
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class BoolColumn extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "BoolCol";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
			
			@Override
			protected boolean getConfiguredEditable() {
				return true;
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
				QuestionForm form = new QuestionForm();
				form.setQuestionId(getQuestionIdColumn().getSelectedValue());
				form.addFormListener(new QuestionFormListener());
				
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
				QuestionForm form = new QuestionForm();
				form.addFormListener(new QuestionFormListener());
				
				form.startNew();
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
