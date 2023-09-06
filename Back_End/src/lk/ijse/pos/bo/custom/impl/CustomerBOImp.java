package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class CustomerBOImp implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Customer);
    @Override
    public boolean saveCustomer(CustomerDTO cusDto) throws SQLException {
        return customerDAO.save(new Customer(cusDto.getId(),cusDto.getName(),cusDto.getAddress()));
    }
}
