package dao;

import model.Developer;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Dzmitry Dziachenka on 10/26/2018
 */
public interface DeveloperDao {
    public void setDataSource(DataSource dataSource);

    public void createDeveloper(String name, String specialty, Integer experience);

    public List listDevelopers();

    public List listDevelopersProjects(Integer id);
}
