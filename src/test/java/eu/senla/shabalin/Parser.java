package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Parser {
    static ObjectMapper objectMapper;
    public static List<User> parseJsonFromResponseToUserList(Response response) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        String allJson = response.body().asString();
        JSONObject jsonObject = new JSONObject(allJson);
        JSONArray data = jsonObject.getJSONArray("data");
        List<User> userList = objectMapper
                .readValue(data.toString(), objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, User.class));
        return userList;
    }
}
