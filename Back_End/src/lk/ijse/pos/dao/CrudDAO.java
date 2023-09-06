package lk.ijse.pos.dao;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T obj);

}
