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
		importPageData(BEANS.get(IQuestionService.class).getQuestionsListTableData(filter));
	}

	public class Table extends AbstractTable {

	
		public QuestionIdColumn getQuestionIdColumn() {
			return getColumnSet().getColumnByClass(QuestionIdColumn.class);
		}

	
		public F1Column getF1Column() {
			return getColumnSet().getColumnByClass(F1Column.class);
		}


		public F2Column getF2Column() {
			return getColumnSet().getColumnByClass(F2Column.class);
		}


		public F3Column getF3Column() {
			return getColumnSet().getColumnByClass(F3Column.class);
		}


		public F4Column getF4Column() {
			return getColumnSet().getColumnByClass(F4Column.class);
		}


		public F5Column getF5Column() {
			return getColumnSet().getColumnByClass(F5Column.class);
		}


		public F6Column getF6Column() {
			return getColumnSet().getColumnByClass(F6Column.class);
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
		public class F1Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F1");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(3000)
		public class F2Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F2");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(4000)
		public class F3Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F3");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(5000)
		public class F4Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F4");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(6000)
		public class F5Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F5");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}


		@Order(7000)
		public class F6Column extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("F6");
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
