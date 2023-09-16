package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.listner.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImp implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Customer);

    public CustomerBOImp() throws SQLException {
    }

    @Override
    public boolean saveCustomer(CustomerDTO cusDto) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {
            return customerDAO.save(connection,new Customer(cusDto.getId(),cusDto.getName(),cusDto.getAddress()));

        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {
            ResultSet rst = customerDAO.getAll(connection);
            List<CustomerDTO> allCustomer = new ArrayList<>();
            while (rst.next()) {
                CustomerDTO customer = new CustomerDTO();
                customer.setId(rst.getString("id"));
                customer.setName(rst.getString("name"));
                customer.setAddress(rst.getString("address"));
                allCustomer.add(customer);
            }
            return allCustomer;
        }
    }


    @Override
    public boolean updateCustomer(CustomerDTO cusDto) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {
            return customerDAO.update(connection,new Customer(cusDto.getId(),cusDto.getName(),cusDto.getAddress()));
        }

    }


    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            return customerDAO.delete(connection,id);
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            Customer customer = customerDAO.search(connection,id);
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());

        }
    }

    @Override
    public ArrayList<String> loadCustomerId() throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            return customerDAO.loadId(connection);
        }
    }
}
