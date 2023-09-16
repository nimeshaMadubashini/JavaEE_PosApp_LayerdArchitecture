package lk.ijse.pos.util;

import lk.ijse.pos.listner.ConnectionPoolManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private  BasicDataSource pool;


    public CrudUtil() throws SQLException {
        this.pool = ConnectionPoolManager.getInstance().getDataSource();
        if (pool == null) {
            throw new SQLException("Connection pool is null.");
        }
    }
    public  <T> T execute(String sql, Object... arg) throws SQLException {
        try(Connection connection= pool.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(sql);
            for (int i = 0; i < arg.length; i++) {
                pstm.setObject(i+1,arg[i]);

            }
            if (sql.startsWith("SELECT") || sql.startsWith("select")){
                ResultSet resultSet = pstm.executeQuery();
                return (T) resultSet;
            }else {
                int i = pstm.executeUpdate();
                return (T)(Boolean)(i>0);
            }
        }
    }
}
