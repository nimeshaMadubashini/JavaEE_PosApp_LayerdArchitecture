package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.listner.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);

    public ItemBOImpl() throws SQLException {
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            return itemDAO.save(connection,new Item(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice()));
        }
    }
    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            List<ItemDTO> allItem = new ArrayList<>();
            ResultSet all = itemDAO.getAll(connection);
            while (all.next()) {
                ItemDTO dto = new ItemDTO();
                dto.setCode(all.getString("code"));
                dto.setName(all.getString("name"));
                dto.setQty(all.getDouble("qty"));
                dto.setPrice(all.getDouble("price"));
                allItem.add(dto);
            }
            return allItem;
        }
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            return itemDAO.update(connection,new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getQty(), itemDTO.getPrice()));
        }
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {
            return itemDAO.delete(connection,id);
        }
    }
    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {
            Item dto = itemDAO.search(connection,id);
            return new ItemDTO(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice());
        }
    }

    @Override
    public ArrayList<String> loadItemId() throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionPoolManager.getInstance().getDataSource().getConnection()) {

            return itemDAO.loadId(connection);
        }
    }
}
