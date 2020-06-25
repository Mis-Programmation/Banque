package businessLayerImp;

import businessLayerInterface.AdminServiceInterface;
import daoInterface.AdminDaoInterface;
import entity.AdminEntity;
import exception.IncorrectPasswordException;
import exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component("AdminService")
public class AdminService implements AdminServiceInterface {

    @Autowired
    private AdminDaoInterface adminDao;

    public void setAdminDao(AdminDaoInterface adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public AdminEntity login(String login, String password) throws SQLException, NotFoundEntityException, IncorrectPasswordException {
        AdminEntity adminEntity = adminDao.getUser(login);
         if(adminEntity == null){
            throw new NotFoundEntityException();
         }
        if(!passworVerifie(password, adminEntity.getPassword())){
            throw new IncorrectPasswordException("Les mots de passe ne corresponde pas");
        }
        return adminEntity;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    /**
     * permet d'encripte le mot de passe
     * @param password
     * @return
     */
    @Override
    public String passWordHash(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(10));
    }

    /**
     * permet de verifier le mot de passe
     * @param passwordPlaint
     * @param password
     * @return
     */
    @Override
    public boolean passworVerifie(String passwordPlaint,String password) {
        return BCrypt.checkpw(passwordPlaint,password);
    }

    @Override
    public void createNewUser(String login, String password) throws SQLException {
        AdminEntity adminEntity;
        String pwd = passWordHash(password);
        adminEntity = new AdminEntity(login,pwd);
        adminDao.save(adminEntity);
    }
}
