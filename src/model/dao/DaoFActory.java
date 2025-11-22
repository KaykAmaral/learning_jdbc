package src.model.dao;

import src.model.dao.impl.SellerDaoJDBC;

public class DaoFActory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }

}