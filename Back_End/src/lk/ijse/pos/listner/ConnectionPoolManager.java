package lk.ijse.pos.listner;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPoolManager {
    private static ConnectionPoolManager connectionPoolManager;
private BasicDataSource pool;

    private ConnectionPoolManager() {
        pool=new BasicDataSource();
        pool.setDriverClassName("com.mysql.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/AssCompany");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(3);
        pool.setMaxTotal(3);
    }

    public static ConnectionPoolManager getInstance() {
        if (connectionPoolManager==null){
            connectionPoolManager=new ConnectionPoolManager();
            }
        return connectionPoolManager;
    }

    public BasicDataSource getDataSource() {
        return pool;
    }
}
