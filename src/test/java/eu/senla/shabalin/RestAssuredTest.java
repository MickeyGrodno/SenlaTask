package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class RestAssuredTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getUserListTest() {
    List<User> userEntityList = new ArrayList<>();
    List<String> userJsonList = new ArrayList<>();
    Response response = given()
            .when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .extract().response();
        response.getBody().print();
        userJsonList = response.body().;

        userJsonList.forEach(a -> {
            try {
                userEntityList.add(objectMapper.readValue(a, User.class));
            } catch (JsonProcessingException e) {
                System.err.println("Не удалось преобразовать json в объект.");
            }
        });

//    System.out.println(response.body().asString());

}
}
