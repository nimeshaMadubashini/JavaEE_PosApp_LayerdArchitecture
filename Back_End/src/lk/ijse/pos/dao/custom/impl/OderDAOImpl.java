package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Oder;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Oder obj) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?)",obj.getOrderId(),obj.getOrderDate(),obj.getCusId());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Oder obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Oder search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
