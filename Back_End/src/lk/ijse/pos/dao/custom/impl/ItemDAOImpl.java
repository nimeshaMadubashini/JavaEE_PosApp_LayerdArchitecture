package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.util.CrudUtil;

import java.sql.*;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item obj) throws SQLException, ClassNotFoundException {
String sql="INSERT INTO item VALUES(?,?,?,?)";
return CrudUtil.execute(sql,obj.getCode(),obj.getName(),obj.getQty(),obj.getPrice());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
        return resultSet;
    }

    @Override
    public boolean update(Item obj) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET name=?,qty=?,price=?  WHERE code=?";
return  CrudUtil.execute(sql,obj.getName(),obj.getQty(),obj.getPrice(),obj.getCode());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE code=?",id);
    }
}
