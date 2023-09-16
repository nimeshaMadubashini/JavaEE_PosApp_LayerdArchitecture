package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.listner.ConnectionPoolManager;
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
        connection = ConnectionPoolManager.getInstance().getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
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
        PreparedStatement pstm = connection.prepareStatement("SELECT  * FROM customer WHERE id=?");
        pstm.setString(1,id);
        ResultSet set = pstm.executeQuery();
        if (set.next()){
            return new Customer(
                    set.getString("id"),
                    set.getString("name"),
                    set.getString("address")

            );

        }
        set.close();
        return null;
    }


    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        String sql="SELECT id  FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rest = pstm.executeQuery();
        ArrayList<String> idList=new ArrayList<>();
        while (rest.next()){
            idList.add(rest.getString(1));
        }
        rest.close();
        return idList;
    }

}



