package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.listner.ConnectionPoolManager;
import lk.ijse.pos.util.CrudUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    private CrudUtil crudUtil;

    public ItemDAOImpl() throws SQLException {
        this.crudUtil=new CrudUtil();
    }

    @Override
    public boolean save(Connection connection,Item obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        return crudUtil.execute(sql, obj.getCode(), obj.getName(), obj.getQty(), obj.getPrice());
    }

    @Override
    public ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException {
        connection = ConnectionPoolManager.getInstance().getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
        return resultSet;
    }

    @Override
    public boolean update(Connection connection,Item obj) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET name=?,qty=?,price=?  WHERE code=?";
        return crudUtil.execute(sql, obj.getName(), obj.getQty(), obj.getPrice(), obj.getCode());

    }

    @Override
    public boolean delete(Connection connection,String id) throws SQLException, ClassNotFoundException {
        return crudUtil.execute("DELETE FROM item WHERE code=?", id);
    }

    @Override
    public Item search(Connection connection,String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT  * FROM item WHERE code=?");
        pstm.setString(1, id);
        ResultSet set = pstm.executeQuery();
        if (set.next()) {
            return new Item(
                    set.getString("code"),
                    set.getString("name"),
                    set.getDouble("qty"),
                    set.getDouble("price")
            );
        }
        set.close();
        return null;
    }

    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT  code FROM item");
        ResultSet set = pstm.executeQuery();
        ArrayList<String> code = new ArrayList<>();
        while (set.next()) {
            code.add(set.getString("code"));
        }
        set.close();
        return code;
    }

    @Override
    public boolean updateQty(Connection connection,double qty, String id) throws SQLException, ClassNotFoundException {
        return crudUtil.execute("UPDATE item SET qty=? WHERE code=?", qty, id);
    }
}
