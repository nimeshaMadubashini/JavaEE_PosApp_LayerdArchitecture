package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {

    public boolean saveCustomer(CustomerDTO cusDto) throws SQLException;
}
