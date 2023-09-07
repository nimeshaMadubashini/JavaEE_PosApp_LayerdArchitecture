package lk.ijse.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T obj);
ResultSet getAll() throws SQLException, ClassNotFoundException;
}
