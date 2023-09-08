package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public enum DoType{
        Customer,Item
    }
     public SuperDAO getDo(DAOFactory.DoType doType){
switch (doType){
    case Customer:
        return (SuperDAO) new CustomerDAOImpl();
    case Item:
        return (SuperDAO) new ItemDAOImpl();
}
return null;
     }
}
