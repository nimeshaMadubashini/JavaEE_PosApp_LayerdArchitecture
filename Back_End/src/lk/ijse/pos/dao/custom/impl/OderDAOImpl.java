package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Oder;
import lk.ijse.pos.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OderDAOImpl implements OrderDAO {
    private CrudUtil crudUtil;

    public OderDAOImpl() throws SQLException {
        crudUtil=new CrudUtil();
    }

    @Override
    public boolean save(Connection connection,Oder obj) throws SQLException, ClassNotFoundException {

        return crudUtil.execute("INSERT INTO orders VALUES (?,?,?)",obj.getOrderId(),obj.getOrderDate(),obj.getCusId());
    }

    @Override
    public ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Connection connection,Oder obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection,String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Oder search(Connection connection,String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
