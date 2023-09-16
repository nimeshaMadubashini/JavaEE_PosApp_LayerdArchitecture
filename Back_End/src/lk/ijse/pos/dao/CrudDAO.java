package lk.ijse.pos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(Connection connection,T obj) throws SQLException, ClassNotFoundException;
    ResultSet getAll(Connection connection) throws SQLException, ClassNotFoundException;
    boolean update (Connection connection,T obj)throws SQLException, ClassNotFoundException;
    boolean delete(Connection connection,String id)throws SQLException, ClassNotFoundException;
    T search(Connection connection,String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> loadId(Connection connection) throws SQLException, ClassNotFoundException;
}
