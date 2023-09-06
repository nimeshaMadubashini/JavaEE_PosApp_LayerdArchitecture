package lk.ijse.pos.util;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ResponseUtil {
    public static JsonObject getJson(String state, String message, JsonArray...data){
        JsonObjectBuilder objectBuilder= Json.createObjectBuilder();
        objectBuilder.add("State",state);
        objectBuilder.add("Message",message);
        if(data.length>0){
            objectBuilder.add("data",data[0]);
        }else {
            objectBuilder.add("data","");
        }
        return objectBuilder.build();
    }
}
