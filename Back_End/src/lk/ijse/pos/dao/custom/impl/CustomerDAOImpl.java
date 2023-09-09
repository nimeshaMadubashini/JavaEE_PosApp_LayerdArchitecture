package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer obj) {
        try( Connection connection = DBConnection.getDbConnection().getConnection()) {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?)");
pstm.setString(1,obj.getId());
pstm.setString(2,obj.getName());
pstm.setString(3,obj.getAddress());
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            return resultSet;
        }

    @Override
    public boolean update(Customer obj) throws SQLException, ClassNotFoundException {
        String sql="UPDATE  customer SET name=?,address=? WHERE id=?";
        return CrudUtil.execute(sql,obj.getName(),obj.getAddress(),obj.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
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
    public ArrayList<String> loadId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
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

