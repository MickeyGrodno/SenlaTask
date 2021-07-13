package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.senla.shabalin.entity.Resource;
import eu.senla.shabalin.entity.User;
import eu.senla.shabalin.entity.Worker;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredTest {
    SoftAssertions assertions = new SoftAssertions();

    @Test
    public void getUserListTest() throws JsonProcessingException {
        Response response = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();

        List<User> userList = (List<User>) Mapper.mapJsonFromResponseToDataList(response, User.class);
        assertions.assertThat(userList.size()).isEqualTo(6);
        assertions.assertThat(userList.get(1).getEmail()).isEqualTo("lindsay.ferguson@reqres.in");
        assertions.assertAll();
    }
    @Test
    public void getSingleUserTest() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .extract().response();

        System.out.println("getSingleUserTest body");
        response.getBody().print();
    }

    @Test
    public void userNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .extract();
    }

    @Test
    public void getResourceListTest() throws JsonProcessingException {
        Response response = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .extract().response();
        System.out.println("getResourceListTest body");
        response.getBody().print();
        List<Resource> userList = (List<Resource>) Mapper.mapJsonFromResponseToDataList(response, Resource.class);
        assertions.assertThat(userList.size()).isEqualTo(6);
        assertions.assertThat(userList.get(0).getPantoneValue()).isEqualTo("15-4020");
        assertions.assertAll();
    }

    @Test
    public void getSingleResourceTest() throws JsonProcessingException {
        Response response = given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .extract().response();
        Resource resource = (Resource) Mapper.mapJsonFromResponseToDataObject(response, Resource.class);
        System.out.println(resource.getColor());
        System.out.println("getSingleUserTest body");
        response.getBody().print();
    }

    @Test
    public void resourceNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404)
                .extract();
    }

    @Test
    public void createWorkerTest() throws JsonProcessingException {
        Worker worker = new Worker("morpheus", "leader");
        Response response = given()
                .contentType("application/json")
                .body(worker.getWorkerInJsonString())
                .when().post("https://reqres.in/api/users").then()
                .statusCode(201)
                .extract().response();
        worker = (Worker) Mapper.mapJsonFromResponseToDataObject(response, Worker.class);
        worker.toString();
        assertions.assertThat(worker.getName()).isEqualTo("morpheus");
        assertions.assertThat(worker.getJob()).isEqualTo("leader");
        assertions.assertThat(worker.getId()).hasNoNullFieldsOrProperties();
        assertions.assertThat(worker.getCreatedAt()).hasSizeGreaterThan(0);
        assertions.assertAll();
    }

    @Test
    public void updateWorkerTest() throws JsonProcessingException {
        Worker worker = new Worker("morpheus", "zion resident");
        Response response = given()
                .contentType("application/json")
                .body(worker.getWorkerInJsonString())
                .when()
                .put("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract().response();
        worker = (Worker) Mapper.mapJsonFromResponseToDataObject(response, Worker.class);
        worker.toString();
        assertions.assertThat(worker.getName()).isEqualTo("morpheus");
        assertions.assertThat(worker.getJob()).isEqualTo("zion resident");
        assertions.assertThat(worker.getUpdatedAt()).hasSizeGreaterThan(0);
        assertions.assertAll();
    }

    @Test
    public void deleteTest() {
        given()
                .when().delete("https://reqres.in/api/users")
                .then()
                .statusCode(204)
                .extract();
    }

    @Test
    public void registerSuccessfulTest() {
        String json = "{\"email\":\"eve.holt@reqres.in\", \"password\": \"pistol\"}";
        given()
                .contentType("application/json")
                .body(json)
                .when().post("https://reqres.in/api/register").then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract();
    }

    @Test
    public void registerUnsuccessfulTest() {
        String json = "{\"email\":\"eve.holt@reqres.in\"}";
        given()
                .contentType("application/json")
                .body(json)
                .when().post("https://reqres.in/api/register").then()
                .statusCode(400)
                .body("error", equalTo("Missing password"))
                .extract();
    }

    @Test
    public void loginSuccessfulTest() {
        String json = "{\"email\":\"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .contentType("application/json")
                .body(json)
                .when().post("https://reqres.in/api/register").then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract();
    }

    @Test
    public void delayedResponseTest() throws JsonProcessingException {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data[0].id", equalTo(1))
                .extract().response();
        List<User> userList = (List<User>) Mapper.mapJsonFromResponseToDataList(response, User.class);
        assertEquals(6, userList.size());
    }


        // Способ №1
//        String allJson = response.body().asString();
//        JSONObject jsonObject = new JSONObject(allJson);
//        JSONArray data = jsonObject.getJSONArray("data");
//        List<User> plannedMaintenanceRequest = objectMapper
//                .readValue(data.toString(), objectMapper.getTypeFactory()
//                        .constructCollectionType(List.class, User.class));

        // Способ №2
//        List allUsersMapInList = from(allJson).get("data");
//        allUsersMapInList.forEach(a -> {
//            try {
//                userEntityList.add(gson.fromJson(objectMapper.writeValueAsString(a), User.class));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        });
//        userEntityList.forEach(a -> System.out.println(a.getId()));
}
