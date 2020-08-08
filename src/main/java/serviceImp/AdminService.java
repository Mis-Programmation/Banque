package serviceImp;

import org.springframework.stereotype.Service;
import serviceInterface.AdminServiceInterface;
import daoInterface.AdminDaoInterface;
import entity.AdminEntity;
import exception.IncorrectPasswordException;
import exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Service("AdminService")
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
            throw new NotFoundEntityException("cette utilisateur existe pas");
         }
        if(!passworVerify(password, adminEntity.getPassword())){
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
     * permet d'hasher le mot de passe
     * @param password
     * @return
     */
    @Override
    public String passWordHash(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(10));
    }

    /**
     * permet de verifier le mot de passe
     */
    @Override
    public boolean passworVerify(String passwordPlaint,String password) {
        return BCrypt.checkpw(passwordPlaint,password);
    }

    @Override
    public void save(AdminEntity adminEntity) throws SQLException {
        String pwd = passWordHash(adminEntity.getPassword());
        adminEntity.setPassword(pwd);
        adminDao.save(adminEntity);
    }
}
