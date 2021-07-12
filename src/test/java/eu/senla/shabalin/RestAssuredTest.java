package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class RestAssuredTest {
    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    @Test
    public void getUserListTest() throws JsonProcessingException {
        List<User> userEntityList = new ArrayList<>();
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();

        // Способ №1
        String allJson = response.body().asString();
        JSONObject jsonObject = new JSONObject(allJson);
        JSONArray data = jsonObject.getJSONArray("data");
        List<User> plannedMaintenanceRequest = objectMapper
                .readValue(data.toString(), objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, User.class));

        // Способ №2
        List allUsersMapInList = from(allJson).get("data");
        allUsersMapInList.forEach(a -> {
            try {
                userEntityList.add(gson.fromJson(objectMapper.writeValueAsString(a), User.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        userEntityList.forEach(a -> System.out.println(a.getId()));
    }
}
