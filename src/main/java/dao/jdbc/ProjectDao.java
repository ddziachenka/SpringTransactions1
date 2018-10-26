package dao.jdbc;

import javax.sql.DataSource;

/**
 * Created by Dzmitry Dziachenka on 10/26/2018
 */
public interface ProjectDao {
    public void setDataSource(DataSource dataSource);

    public void createProject(Integer developersId, String projectName, String companyName);
}
