package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    List<ItemDTO> getAllItem()throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO)throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadItemId() throws SQLException, ClassNotFoundException ;

}
