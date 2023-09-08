package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(), dto.getName(), dto.getQty(),dto.getPrice())) ;
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        List<ItemDTO> allItem=new ArrayList<>();
        ResultSet all = itemDAO.getAll();
        while (all.next()){
            ItemDTO dto=new ItemDTO();
         dto.setCode(all.getString("code"));
         dto.setName(all.getString("name"));
         dto.setQty(all.getDouble("qty"));
         dto.setPrice(all.getDouble("price"));
allItem.add(dto);
        }
        all.close();
        return allItem;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getPrice()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
}
