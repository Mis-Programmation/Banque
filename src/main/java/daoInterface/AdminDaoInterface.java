package daoInterface;

import entity.AdminEntity;

import java.sql.SQLException;

public interface AdminDaoInterface {

    public AdminEntity getUser(String pseaudo) throws SQLException;
    public void save(AdminEntity adminEntity) throws SQLException;

}
