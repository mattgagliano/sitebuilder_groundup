package zodiac.sitebuilder.server.sql;

import javax.annotation.PostConstruct;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.CreateImmediately;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.context.RunContext;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.jdbc.SQL;

import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;
import zodiac.sitebuilder.server.sql.DatabaseProperties.DatabaseAutoPopulateProperty;

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
		SQL.update("DROP TABLE QUESTIONS");
		//insert statement to drop all database table class objects
	}
	
	@Override
	public void createDataStore() {
		
		SQL.insert("CREATE TABLE QUESTIONS "
				+ "			(question_id VARCHAR(64) NOT NULL CONSTRAINT QUESTIONS_PK PRIMARY KEY, "
				+ "			 prompt VARCHAR(64) "
				+ "			)");
		
		if (CONFIG.getPropertyValue(DatabaseAutoPopulateProperty.class)) {
			
			SQL.insert("INSERT INTO QUESTIONS "
					+ "			(question_id, "
					+ "			 prompt) "
					+ " VALUES  ('Q0', "
					+ "			 'prompt00'"
					+ "			)");
			
			SQL.insert("INSERT INTO QUESTIONS "
					+ "			(question_id, "
					+ "			 prompt) "
					+ " VALUES  ('Q1', "
					+ "			 'prompt01'"
					+ "			)");
			
			SQL.insert("INSERT INTO QUESTIONS "
					+ "			(question_id, "
					+ "			 prompt) "
					+ " VALUES  ('Q2', "
					+ "			 'prompt02'"
					+ "			)");
		}
		
		String tableName = "table1";
		String[] columnNames = new String[3];
		String[] columnTypes = new String[3];
		String[] columnAttributes = new String[3];
		String[][] sampleValues = new String[3][3];
		
		for (Integer i = 0; i < 3; i++) {
			columnNames[i] = "col" + i.toString();
			columnTypes[i] = "VARCHAR(64)";
			columnAttributes[i] = "";
			for (Integer j = 0; j < 3; j++) {
				sampleValues[i][j] = "val"+i.toString()+j.toString();
			}
		}
				
		//DatabaseTable tbl1 = new DatabaseTable(tableName, columnNames, columnTypes, columnAttributes, sampleValues);
	}

}
