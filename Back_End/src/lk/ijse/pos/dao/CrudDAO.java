package lk.ijse.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T obj) throws SQLException, ClassNotFoundException;
ResultSet getAll() throws SQLException, ClassNotFoundException;
boolean update (T obj)throws SQLException, ClassNotFoundException;
boolean delete(String id)throws SQLException, ClassNotFoundException;
T search(String id) throws SQLException, ClassNotFoundException;
ArrayList<String> loadId() throws SQLException, ClassNotFoundException;
}
