package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/pages/customer")
public class CustomerServlet  extends HttpServlet {
    private CustomerBO customerBO;
    @Override
    public void init() throws ServletException {
        super.init();
         customerBO= (CustomerBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Customer);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            JsonArrayBuilder allCus = Json.createArrayBuilder();
            for (CustomerDTO cus : allCustomer) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", cus.getId());
                objectBuilder.add("name", cus.getName());
                objectBuilder.add("address", cus.getAddress());
                allCus.add(objectBuilder.build());
            }
            resp.setContentType("application/json");
            resp.getWriter().print(ResponseUtil.getJson("Success", "Successfully loaded", allCus.build()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error", e.getMessage()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader=Json.createReader(req.getReader());
        JsonObject jbo = reader.readObject();
        String id = jbo.getString("id");
        String name = jbo.getString("name");
        String address = jbo.getString("address");
        CustomerDTO customerDTO=new CustomerDTO(id,name,address);
        try {
            if(customerBO.updateCustomer(customerDTO)){

                resp.getWriter().print(ResponseUtil.getJson("Success", "Customer Updated..!"));
            }else{
                resp.getWriter().print(ResponseUtil.getJson("Failed", "Customer Updated Failed..!"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");

            if(customerBO.saveCustomer(new CustomerDTO(cusID,cusName,cusAddress))){
                resp.getWriter().print(ResponseUtil.getJson("Success","SuccessFully Added.."));
            }else {
                resp.getWriter().print(ResponseUtil.getJson("error","Customer Added Fail"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error",e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("cusID");
        try {
            if (customerBO.deleteCustomer(id)){

                resp.getWriter().print(ResponseUtil.getJson("Success", "Customer Delete..!"));
            }else{
                resp.getWriter().print(ResponseUtil.getJson("Failed", "Customer Delete Failed..!"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
        }

    }
}
