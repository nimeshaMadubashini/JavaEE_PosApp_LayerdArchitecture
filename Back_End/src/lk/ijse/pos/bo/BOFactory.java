package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImp;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.bo.custom.impl.PurchaseOrderCOImpl;

import java.sql.SQLException;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public enum BoType{
        Customer,Item,PurchaseOrder
    }
    public SuperBO getBoType(BOFactory.BoType boType) throws SQLException {
        switch (boType){
            case Customer:
                return (SuperBO) new CustomerBOImp();
            case Item:
                return (SuperBO) new ItemBOImpl();
            case PurchaseOrder:
                return (SuperBO) new PurchaseOrderCOImpl();
        }
        return null;
    }
}
