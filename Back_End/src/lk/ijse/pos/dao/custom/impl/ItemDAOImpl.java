package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    private CrudUtil crudUtil;

    public ItemDAOImpl() throws SQLException {
        crudUtil=new CrudUtil();
    }

    @Override
    public boolean save(Connection connection,Item obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        return crudUtil.execute(sql, obj.getCode(), obj.getName(), obj.getQty(), obj.getPrice());
    }

    @Override
    public ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =crudUtil.execute("SELECT * FROM item");
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

        ResultSet set  = crudUtil.execute("SELECT  * FROM item WHERE code=?",id);
        if (set.next()) {
            return new Item(
                    set.getString("code"),
                    set.getString("name"),
                    set.getDouble("qty"),
                    set.getDouble("price")
            );
        }

        return null;
    }

    @Override
    public ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet set = crudUtil.execute("SELECT  code FROM item");
        ArrayList<String> code = new ArrayList<>();
        while (set.next()) {
            code.add(set.getString("code"));
        }

        return code;
    }

    @Override
    public boolean updateQty(Connection connection,double qty, String id) throws SQLException, ClassNotFoundException {
        return crudUtil.execute("UPDATE item SET qty=? WHERE code=?", qty, id);
    }
}
