import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {


    @Test
    public void getTest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .log().all()
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .statusCode(200)
                .log().all();
    }


    @Test
    public void postTest(){
        String requestBody = "This is expected to be sent back as part of response body.";
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when().post("/post")
                .then()
                .statusCode(200)
                .body("data",equalTo("This is expected to be sent back as part of response body."));
    }



    @Test
    public void postDataTest(){
        String requestBody = "{\n" +
                "        \"foo1\": \"bar1\",\n" +
                "        \"foo2\": \"bar2\"\n" +
                "    }";
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .header("Content-Type","application/json")
                .body(requestBody)
               .when().post("/post")
                .then()
                .statusCode(200);
    }

@Test
public void putTest(){
    String requestBody = "This is expected to be sent back as part of response body.";
    RestAssured.baseURI = "https://postman-echo.com";
    given()
            .header("Content-Type","application/json")
            .body(requestBody)
            .when().put("/put")
            .then()
            .statusCode(200)
            .body("data",equalTo("This is expected to be sent back as part of response body."));
}
    @Test
    public void patchTest(){
        String requestBody = "This is expected to be sent back as part of response body.";
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when().patch("/patch")
                .then()
                .statusCode(200)
                .body("data",equalTo("This is expected to be sent back as part of response body."));
    }
    @Test
    public void deleteTest(){
        String requestBody = "This is expected to be sent back as part of response body.";
        RestAssured.baseURI = "https://postman-echo.com";
        given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when().delete("/delete")
                .then()
                .statusCode(200)
                .body("data",equalTo("This is expected to be sent back as part of response body."));
    }
}
