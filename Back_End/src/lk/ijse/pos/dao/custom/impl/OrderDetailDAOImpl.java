package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.entity.OrDetails;
import lk.ijse.pos.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    private CrudUtil crudUtil;

    public OrderDetailDAOImpl() throws SQLException {
        crudUtil=new CrudUtil();
    }
    @Override
    public boolean save(Connection connection,OrDetails obj) throws SQLException, ClassNotFoundException {
        return crudUtil.execute("INSERT INTO orderdetails VALUES (?,?,?,?)",obj.getOid(),obj.getItemCode(),
                obj.getQty(),obj.getUnitPrice());
    }

    @Override
    public ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Connection connection, OrDetails obj) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean delete(Connection connection,String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrDetails search(Connection connection,String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
