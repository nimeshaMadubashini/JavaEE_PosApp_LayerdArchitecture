package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.OderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailDAOImpl;

import java.sql.SQLException;

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
        Customer,Item,Order,OrDetail
    }
     public SuperDAO getDo(DAOFactory.DoType doType) throws SQLException {
switch (doType){
    case Customer:
        return (SuperDAO) new CustomerDAOImpl();
    case Item:
        return (SuperDAO) new ItemDAOImpl();
    case Order:
        return (SuperDAO) new OderDAOImpl();
    case OrDetail:
        return (SuperDAO) new OrderDetailDAOImpl();
}
return null;
     }
}
