package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Mapper {
    private static ObjectMapper objectMapper = new ObjectMapper();;
    public static List<?> mapJsonFromResponseToDataList(Response response, Class<?> clazz) throws JsonProcessingException {
        String allJson = response.body().asString();
        JSONObject jsonObject = new JSONObject(allJson);
        JSONArray data = jsonObject.getJSONArray("data");
        List list = objectMapper
                .readValue(data.toString(), objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, clazz));
        return list;
    }

    public static Object mapJsonFromResponseToDataObject(Response response, Class<?> clazz) throws JsonProcessingException {
        String allJson = response.body().asString();
        JSONObject jsonObject = new JSONObject(allJson);
        if(jsonObject.has("data")){
            return objectMapper.readValue(jsonObject.getJSONObject("data").toString(), clazz);
        } else {
            return objectMapper.readValue(jsonObject.toString(), clazz);
        }
    }
}
