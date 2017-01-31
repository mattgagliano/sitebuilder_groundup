package zodiac.sitebuilder.server.sql;

import javax.annotation.PostConstruct;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.CreateImmediately;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.context.RunContext;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;

import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;

@ApplicationScoped
@CreateImmediately
public class DatabaseSetupService implements IDataStoreService {
	
	//private static final Logger LOG = LoggerFactory.getLogger(DatabaseSetupService.class);
	
	@PostConstruct
	public void autoCreateDatabase() {
		if (CONFIG.getPropertyValue(DatabaseAutoCreateProperty.class)) {
			try {
				RunContext context = BEANS.get(SuperUserRunContextProducer.class).produce();
				IRunnable runnable = new IRunnable() {
					
					@Override
					public void run() throws Exception {
						createDataStore();
					}
				};
				
				context.run(runnable);
			}
			catch (RuntimeException e) {
				BEANS.get(ExceptionHandler.class).handle(e);
			}
		}
	}

	@Override
	public void dropDataStore() {
		DerbySql.DropDataStore("QUESTIONS");
		DerbySql.DropDataStore("FORMS");
	}
	
	@Override
	public void createDataStore() {
		
		String tn1 = "FORMS";
		String[] colnam1 = new String[7];
		colnam1[0]="formid";
		colnam1[1]="project";
		colnam1[2]="formname";
		colnam1[3]="Q0";
		colnam1[4]="Q1";
		colnam1[5]="Q2";
		colnam1[6]="Q3";
		String[] colty1 = new String[7];
		colty1[0] = "VARCHAR(64)";
		colty1[1] = "VARCHAR(64)";
		colty1[2] = "VARCHAR(64)";
		colty1[3] = "BOOLEAN";
		colty1[4] = "BOOLEAN";
		colty1[5] = "BOOLEAN";
		colty1[6] = "BOOLEAN";
		String[] colatt1 = new String[7];
		colatt1[0] = "NOT NULL CONSTRAINT FORMS_PK PRIMARY KEY";
		colatt1[1] = "";
		colatt1[2] = "";
		colatt1[3] = "";
		colatt1[4] = "";
		colatt1[5] = "";
		colatt1[6] = "";
		String[][] smp1 = new String[7][7];
		smp1[0][0] = "F0";
		smp1[0][1] = "project0";
		smp1[0][2] = "formname0";
		smp1[0][3] = "false";
		smp1[0][4] = "false";
		smp1[0][5] = "false";
		smp1[0][6] = "false";
		smp1[1][0] = "F1";
		smp1[1][1] = "project1";
		smp1[1][2] = "formname1";
		smp1[1][3] = "true";
		smp1[1][4] = "false";
		smp1[1][5] = "false";
		smp1[1][6] = "false";
		smp1[2][0] = "F2";
		smp1[2][1] = "project2";
		smp1[2][2] = "formname2";
		smp1[2][3] = "false";
		smp1[2][4] = "true";
		smp1[2][5] = "false";
		smp1[2][6] = "false";
		smp1[3][0] = "F3";
		smp1[3][1] = "project3";
		smp1[3][2] = "formname3";
		smp1[3][3] = "true";
		smp1[3][4] = "true";
		smp1[3][5] = "false";
		smp1[3][6] = "false";
		smp1[4][0] = "F4";
		smp1[4][1] = "project4";
		smp1[4][2] = "formname4";
		smp1[4][3] = "false";
		smp1[4][4] = "false";
		smp1[4][5] = "true";
		smp1[4][6] = "false";
		smp1[5][0] = "F5";
		smp1[5][1] = "project5";
		smp1[5][2] = "formname5";
		smp1[5][3] = "true";
		smp1[5][4] = "false";
		smp1[5][5] = "true";
		smp1[5][6] = "false";
		smp1[6][0] = "F6";
		smp1[6][1] = "project6";
		smp1[6][2] = "formname6";
		smp1[6][3] = "false";
		smp1[6][4] = "true";
		smp1[6][5] = "true";
		smp1[6][6] = "false";
		
						
		DerbySql.createTable(tn1, colnam1, colty1, colatt1); 
		DerbySql.autoPopulate(tn1, smp1);
		
		String tn = "QUESTIONS";
		String[] colnam = new String[2];
		colnam[0]="questionid";
		colnam[1]="prompt";
		String[] colty = new String[2];
		colty[0] = "VARCHAR(64)";
		colty[1] = "VARCHAR(64)";
		String[] colatt = new String[2];
		colatt[0] = "NOT NULL CONSTRAINT QUESTIONS_PK PRIMARY KEY";
		colatt[1] = "";
		String[][] smp = new String[4][2];
		smp[0][0] = "Q0";
		smp[0][1] = "prompt0";
		smp[1][0] = "Q1";
		smp[1][1] = "prompt1";
		smp[2][0] = "Q2";
		smp[2][1] = "prompt2";
		smp[3][0] = "Q3";
		smp[3][1] = "prompt3";
			
		DerbySql.createTable(tn, colnam, colty, colatt);
		DerbySql.autoPopulate(tn, smp); 
	}

}
