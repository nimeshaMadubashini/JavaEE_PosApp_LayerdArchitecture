package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PurchaseOderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;

import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrdDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Oder;
import lk.ijse.pos.entity.OrDetails;
import lk.ijse.pos.listner.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseOrderCOImpl implements PurchaseOderBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Order);
    OrderDetailDAO detailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.OrDetail);

    public PurchaseOrderCOImpl() throws SQLException {
    }

    @Override
    public boolean placeOder(OrdDTO ordDTO, List<OrderDetailDTO> ordDTOList) throws SQLException, ClassNotFoundException {
        Connection connection=null;
        try {
            connection = ConnectionPoolManager.getInstance().getDataSource().getConnection();
            connection.setAutoCommit(false);
            boolean isSave = orderDAO.save(connection,new Oder(ordDTO.getOrderId(), ordDTO.getOrderDate(), ordDTO.getCusId()));
            if (isSave) {
                for (OrderDetailDTO odt : ordDTOList) {
                    boolean isSaveOD = detailDAO.save(connection,new OrDetails(odt.getOid(), odt.getItemCode(),
                            odt.getQty(), odt.getUnitPrice()));
                    if (isSaveOD) {
                        boolean updateQty = itemDAO.updateQty(connection,odt.getQty() - odt.getBuyQty(), odt.getItemCode());
                        if (updateQty) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            if (connection !=null){
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}
