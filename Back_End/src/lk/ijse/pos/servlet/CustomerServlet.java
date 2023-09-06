package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/pages/customer")
public class CustomerServlet  extends HttpServlet {
    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Customer);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");

        try {
            if(customerBO.saveCustomer(new CustomerDTO(cusID,cusName,cusAddress))){
                resp.getWriter().print(ResponseUtil.getJson("Success","SuccessFully Added.."));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error",e.getMessage()));
        }
    }
}
