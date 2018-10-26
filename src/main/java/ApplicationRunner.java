import dao.jdbc.JdbcTemplateDeveloperDaoImpl;
import dao.jdbc.JdbcTemplateProjectDaoImpl;
import model.Developer;
import model.Project;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

/**
 * Created by Dzmitry Dziachenka on 10/26/2018
 */
public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("transactions-programmatic-config.xml");
        JdbcTemplateDeveloperDaoImpl developerDao =
                (JdbcTemplateDeveloperDaoImpl) context.getBean("developerJDBCTemplate");
        JdbcTemplateProjectDaoImpl projectDao =
                (JdbcTemplateProjectDaoImpl) context.getBean("projectJDBCTemplate");

        System.out.println("======== Creating project's records ========");
        projectDao.createProject(82, "ProselyteTutorial", "Proselyte.net");
        projectDao.createProject(82, "SkybleLib", "SkybleSoft");

        System.out.println("======== Creating developer's records ========");
        developerDao.createDeveloper("Proselyte", "Java Developer", 3);
        developerDao.createDeveloper("Mike", "C++ Developer", 5);

        System.out.println("======== List of Developers ========");
        List<Developer> developerList = developerDao.listDevelopers();
        for (Developer developer : developerList) {
            System.out.println(developer);
        }

        System.out.println("======== Proselyte Developer's Projects ========");
        List<Project> projects = developerDao.listDevelopersProjects(82);
        for (Project project : projects) {
            System.out.println(project);
        }

    }
}

