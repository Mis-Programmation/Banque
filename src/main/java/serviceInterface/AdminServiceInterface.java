package serviceInterface;

import entity.AdminEntity;
import exception.IncorrectPasswordException;
import exception.NotFoundEntityException;

import java.sql.SQLException;

public interface AdminServiceInterface {

    public AdminEntity login(String login, String password) throws SQLException, NotFoundEntityException, IncorrectPasswordException;
    public void logout();
    public boolean isConnected();
    public String passWordHash(String password);
    public boolean passworVerify(String passwordPlaint,String password);
    public void save(AdminEntity adminEntity) throws SQLException;
}
