package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
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
    public void getUserListTest() {
        List<User> userEntityList = new ArrayList<>();
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();

        ResponseData data = response.getBody().as(ResponseData.class);
        String allJson = response.body().asString();
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
