package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer obj) {
        try( Connection connection = DBConnection.getDbConnection().getConnection()) {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?)");
pstm.setString(1,obj.getId());
pstm.setString(2,obj.getName());
pstm.setString(3,obj.getAddress());
return pstm.executeUpdate()>0;
        }catch (SQLException e){

        }
        return false;
    }
}
