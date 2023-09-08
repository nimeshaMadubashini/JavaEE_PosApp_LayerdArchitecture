package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
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

@WebServlet(urlPatterns = "/pages/item")
public class ItemServlet extends HttpServlet {
    private ItemBO itemBO;

    @Override
    public void init() throws ServletException {
        itemBO = (ItemBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.Item);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ItemDTO> allItem = itemBO.getAllItem();
            JsonArrayBuilder all= Json.createArrayBuilder();
            for(ItemDTO i:allItem){
                JsonObjectBuilder obj=Json.createObjectBuilder();
                obj.add("code",i.getCode());
                obj.add("name",i.getName());
                obj.add("qty",i.getQty());
                obj.add("price",i.getPrice());
                 all.add(obj);
            }
            resp.setContentType("application/json");
            resp.getWriter().print(ResponseUtil.getJson("Success", "Successfully loaded", all.build()));


        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            String code = req.getParameter("code");
            String description = req.getParameter("description");
            double qty = Double.parseDouble(req.getParameter("qty"));
            double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));

            if (itemBO.saveItem(new ItemDTO(code, description, qty, unitPrice))) {
                resp.getWriter().print(ResponseUtil.getJson("Success","Successfully added item"));
            }else {
                resp.getWriter().print(ResponseUtil.getJson("Fail","Fail added item"));

            }
        } catch (SQLException | ClassNotFoundException e) {
resp.setStatus(500);
resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader=Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String code = jsonObject.getString("code");
        String name=jsonObject.getString("name");
        double qty = Double.parseDouble(jsonObject.getString("qty"));
        double price= Double.parseDouble(jsonObject.getString("price"));

        try {
          if(itemBO.updateItem(new ItemDTO(code,name,qty,price)))  {
              resp.getWriter().print(ResponseUtil.getJson("Success","update Success"));
          }else {
              resp.getWriter().print(ResponseUtil.getJson("fail","update Fail"));
          }

        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String code = req.getParameter("code");
        try {
            if(itemBO.deleteItem(code)){
                resp.getWriter().print(ResponseUtil.getJson("Success","Delete Success"));
            }else {
                resp.getWriter().print(ResponseUtil.getJson("fail","Delete Fail"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
        }
    }
}
