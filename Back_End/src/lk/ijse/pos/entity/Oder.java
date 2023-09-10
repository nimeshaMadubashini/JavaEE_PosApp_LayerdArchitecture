package lk.ijse.pos.entity;

import java.sql.Date;

public class Oder {
    private String OrderId;
    private Date OrderDate;
    private String CusId;

    public Oder() {
    }

    public Oder(String orderId, Date orderDate, String cusId) {
        OrderId = orderId;
        OrderDate = orderDate;
        CusId = cusId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }
}
