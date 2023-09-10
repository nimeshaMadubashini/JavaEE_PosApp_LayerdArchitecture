package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PurchaseOderBO;
import lk.ijse.pos.dto.OrdDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.util.ResponseUtil;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/pages/purchase-order")
public class PurchaseOrderServlet extends HttpServlet {
    private PurchaseOderBO orderBO;

    @Override
    public void init() throws ServletException {
        super.init();
      orderBO = (PurchaseOderBO) BOFactory.getBoFactory().getBoType(BOFactory.BoType.PurchaseOrder);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try (JsonReader reader = Json.createReader(req.getReader())) {
            JsonObject jsonObject = reader.readObject();

            String oid = jsonObject.getString("oid");
            String dateStr = jsonObject.getString("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateStr);
            Date date = new Date(utilDate.getTime());

            String cusId = jsonObject.getString("cusId");
            JsonArray odDetail = jsonObject.getJsonArray("odDetail");
            OrdDTO ordDTO = new OrdDTO(oid, date, cusId);

            List<OrderDetailDTO> ordDTOS = new ArrayList<>();
            for (JsonValue json : odDetail) {
                JsonObject jsonObject1 = json.asJsonObject();
                String code = jsonObject1.getString("code");
                String priceStr = jsonObject1.getString("price");
                String buyQtyStr = jsonObject1.getString("buyQty");
                String qtyOnHandStr = jsonObject1.getString("qtyOnHand");

                double price = Double.parseDouble(priceStr);
                double buyQty = Double.parseDouble(buyQtyStr);
                double qtyOnHand = Double.parseDouble(qtyOnHandStr);
                ordDTOS.add(new OrderDetailDTO(oid, code, qtyOnHand, price, buyQty));

            }

            boolean placeOrder = orderBO.placeOder(ordDTO, ordDTOS);
            if (placeOrder) {
                resp.getWriter().print(ResponseUtil.getJson("success", "Successful placeOrder"));
            } else {
                resp.getWriter().print(ResponseUtil.getJson("fail", "Failed placeOrder"));
            }
        } catch (ParseException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getJson("error", "Invalid date format."));
            e.printStackTrace();
        } catch (JsonException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getJson("error", "Invalid JSON data."));
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.getJson("error", e.getMessage()));
            e.printStackTrace();
        }
    }
}
