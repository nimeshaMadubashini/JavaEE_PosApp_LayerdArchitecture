package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PurchaseOderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrdDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Oder;
import lk.ijse.pos.entity.OrDetails;

import java.sql.SQLException;
import java.util.List;

public class PurchaseOrderCOImpl implements PurchaseOderBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Order);
    OrderDetailDAO detailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.OrDetail);

    @Override
    public boolean placeOder(OrdDTO ordDTO, List<OrderDetailDTO> ordDTOList) throws SQLException, ClassNotFoundException {
try {
        DBConnection.getDbConnection().getConnection().setAutoCommit(false);

        boolean isSave = orderDAO.save(new Oder(ordDTO.getOrderId(), ordDTO.getOrderDate(), ordDTO.getCusId()));
        if (isSave) {
            for (OrderDetailDTO odt : ordDTOList) {
                boolean isSaveOD = detailDAO.save(new OrDetails(odt.getOid(), odt.getItemCode(),
                        odt.getQty(), odt.getUnitPrice()));
                if(isSaveOD){
                    boolean updateQty = itemDAO.updateQty(odt.getQty()-odt.getBuyQty(), odt.getItemCode());
                    if (updateQty){
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
        }
        DBConnection.getDbConnection().getConnection().rollback();
        return false;
    }finally {
    DBConnection.getDbConnection().getConnection().setAutoCommit(true);
    }
    }
}
