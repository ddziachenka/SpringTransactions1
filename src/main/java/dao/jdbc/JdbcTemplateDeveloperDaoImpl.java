package dao.jdbc;

import dao.DeveloperDao;
import model.Developer;
import model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import util.DevelopersMapper;
import util.ProjectsMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Dzmitry Dziachenka on 10/26/2018
 */
public class JdbcTemplateDeveloperDaoImpl implements DeveloperDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    public void createDeveloper(String name, String specialty, Integer experience) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        String SQL = "INSERT INTO DEVELOPERS (NAME, SPECIALTY, EXPERIENCE) VALUES (?,?,?)";
        jdbcTemplate.update(SQL, name, specialty, experience);
        System.out.println("Developer's record created/updated successfully. Name: " +
                name + ", Specilaty: " + specialty + ", Experience: " + experience + "\n");
        transactionManager.commit(status);
    }


    public List listDevelopers() {
        String SQL = "SELECT * FROM DEVELOPERS";
        List developers = jdbcTemplate.query(SQL, new DevelopersMapper());

        return developers;
    }


    public List listDevelopersProjects(Integer id) {
        String SQL = "SELECT * FROM PROJECTS WHERE DEVELOPERS_ID = " + id;
        List projectList = jdbcTemplate.query(SQL,  new ProjectsMapper());
        return projectList;
    }
}
