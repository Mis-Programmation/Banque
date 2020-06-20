package businessLayerImp;

import businessLayerInterface.CompteServiceInterface;
import entity.CompteEntity;
import daoInterface.CompteDaoInterface;
import entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteService implements CompteServiceInterface {

    private CompteDaoInterface compteDao;

    public void setCompteDao(CompteDaoInterface compteDao) {
        this.compteDao = compteDao;
    }



    @Override
    public void deposit(double montant) {

    }

    @Override
    public void withdraw(double montant) {

    }

    @Override
    public void transfer(double montant, CompteEntity compteEntity) {

    }

    @Override
    public double getSolde() {
        return 0;
    }

    @Override
    public List<OperationService> getAllOperation() {
        return null;
    }


}
