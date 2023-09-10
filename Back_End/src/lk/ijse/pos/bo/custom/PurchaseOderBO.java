package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrdDTO;
import lk.ijse.pos.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOderBO extends SuperBO {
    public boolean placeOder(OrdDTO ordDTO, List<OrderDetailDTO> ordDTOList) throws SQLException, ClassNotFoundException;
}
