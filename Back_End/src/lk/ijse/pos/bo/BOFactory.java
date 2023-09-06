package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImp;

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
        Customer
    }
    public SuperBO getBoType(BOFactory.BoType boType){
        switch (boType){
            case Customer:
                return (SuperBO) new CustomerBOImp();
        }
        return null;
    }
}
