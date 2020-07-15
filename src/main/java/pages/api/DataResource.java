package pages.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DataResource {
    private static final String RESOURCE_ENDPOINT = "/resource/";

    public static Response getJSONResource(String fileName) {

        return given()
                .contentType(ContentType.JSON)
                .when().get(RESOURCE_ENDPOINT + fileName + ".json")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .extract().response();
    }
}
