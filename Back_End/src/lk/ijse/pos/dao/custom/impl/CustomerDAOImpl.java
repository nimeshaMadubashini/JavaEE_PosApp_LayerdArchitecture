package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.util.CrudUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private CrudUtil crudUtil;

    public CustomerDAOImpl() throws SQLException {
        crudUtil = new CrudUtil();
    }

    @Override
    public boolean save(Connection connection, Customer obj) throws SQLException {
        String sql = "INSERT INTO customer VALUES (?,?,?)";
        return crudUtil.execute(sql, obj.getId(), obj.getName(), obj.getAddress());
    }

    @Override
    public ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = crudUtil.execute("SELECT * FROM customer");
        return resultSet;
    }

    @Override
    public boolean update(Connection connection, Customer obj) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE  customer SET name=?,address=? WHERE id=?";
        return crudUtil.execute(sql, obj.getName(), obj.getAddress(), obj.getId());
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE id=?";
        return crudUtil.execute(sql, id);
    }

    @Override
    public Customer search(Connection connection, String id) throws SQLException, ClassNotFoundException {

        ResultSet set = crudUtil.execute("SELECT  * FROM customer WHERE id=?", id);
        if (set.next()) {
            return new Customer(
                    set.getString("id"),
                    set.getString("name"),
                    set.getString("address")

            );

        }
        return null;
    }


    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id  FROM customer";
        ResultSet rest = crudUtil.execute(sql);
        ArrayList<String> idList = new ArrayList<>();
        while (rest.next()) {
            idList.add(rest.getString(1));
        }
        return idList;
    }

}

