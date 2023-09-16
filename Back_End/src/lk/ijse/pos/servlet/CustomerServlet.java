package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.listner.ConnectionPoolManager;
import lk.ijse.pos.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/pages/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerBO customerBO;
    private BasicDataSource pool;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            customerBO = (CustomerBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.pool= ConnectionPoolManager.getInstance().getDataSource();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = pool.getConnection()) {
            String option = req.getParameter("option");
            switch (option) {
                case "getAll":
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
                    break;
                case "search":
                    String code = req.getParameter("cusId");
                    CustomerDTO dto = customerBO.searchCustomer(code);
                    if (dto != null) {
                        JsonObjectBuilder obj = Json.createObjectBuilder();
                        obj.add("id", dto.getId());
                        obj.add("name", dto.getName());
                        obj.add("address", dto.getAddress());
                        resp.setContentType("application/json");
                        JsonObjectBuilder responseObj1 = Json.createObjectBuilder();
                        responseObj1.add("Status", "ok");
                        responseObj1.add("message", "Successfully Loaded...!");
                        responseObj1.add("data", obj.build());
                        resp.getWriter().print(responseObj1.build());
                    }
                    break;
                case "load":
                    ArrayList<String> id = customerBO.loadCustomerId();
                    JsonArrayBuilder all = Json.createArrayBuilder();
                    for (String i : id) {
                        JsonObjectBuilder ob = Json.createObjectBuilder();
                        ob.add("id", i);
                        all.add(ob);
                    }
                    resp.setContentType("application/json");
                    resp.getWriter().print(ResponseUtil.getJson("Success", "Successfully search", all.build()));
            }


        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error", e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jbo = reader.readObject();
        String id = jbo.getString("id");
        String name = jbo.getString("name");
        String address = jbo.getString("address");
        CustomerDTO customerDTO = new CustomerDTO(id, name, address);
        try {
            if (customerBO.updateCustomer(customerDTO)) {

                resp.getWriter().print(ResponseUtil.getJson("Success", "Customer Updated..!"));
            } else {
                resp.getWriter().print(ResponseUtil.getJson("Failed", "Customer Updated Failed..!"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            String cusID = req.getParameter("cusID");
            String cusName = req.getParameter("cusName");
            String cusAddress = req.getParameter("cusAddress");

            if (customerBO.saveCustomer(new CustomerDTO(cusID, cusName, cusAddress))) {
                resp.getWriter().print(ResponseUtil.getJson("Success", "SuccessFully Added.."));
            } else {
                resp.getWriter().print(ResponseUtil.getJson("error", "Customer Added Fail"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error", e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("cusID");
        try {
            if (customerBO.deleteCustomer(id)) {

                resp.getWriter().print(ResponseUtil.getJson("Success", "Customer Delete..!"));
            } else {
                resp.getWriter().print(ResponseUtil.getJson("Failed", "Customer Delete Failed..!"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error", e.getMessage()));
        }

    }
}
