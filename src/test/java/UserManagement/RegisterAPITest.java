package UserManagement;

import static io.restassured.RestAssured.*;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class RegisterAPITest {

    @Test
    public void testRegisterAPI() {

        // Given: Precondition (API endpoint and request body)
                given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"email\": \"Shubhamnakade976990@gmail.com\",\n" +
                        "    \"password\": \"LeeCopper@12345\",\n" +
                        "    \"confirmpassword\": \"LeeCopper@12345\"\n" +
                        "}").log().all()

                // When: Action (the request is made)
                .when()
                .post("https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1/register")

                // Then: Expected result (response validation)
                .then().assertThat()
                .statusCode(201).body("success",org.hamcrest.Matchers.is(true));;// Assuming 201 is the success status code for registration
//                .body("success", equalTo("<true>")); // Assuming this is the message returned on success
    }




    @Test
    public void testCitiesAPI() {

        // Given: Precondition (API endpoint and headers)
       Response response  = given()
                .header("Cookie", "ARRAffinity=6f722d9ba8c6f7ac8a05d991523ce95c10a2df43a9a9f2a9d215eed82ec16bb7; ARRAffinitySameSite=6f722d9ba8c6f7ac8a05d991523ce95c10a2df43a9a9f2a9d215eed82ec16bb7")

                // Then: Expected result (response validation)
                .when()
                .get("https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1/cities")
                .then().extract().response().prettyPeek();
//                .statusCode(200) // Assuming a successful response returns 200
//                .body("size()", greaterThan(0)) // Validate that the response contains some cities
//                .body("[0].city", notNullValue()); // Validate that the first city's name is not null
        List<String> actualIds = response.jsonPath().getList("id");
        List<String> expectedIds = Arrays.asList("bengaluru", "goa", "kolkata", "singapore", "malaysia", "bangkok", "new-york","paris");
        //assertThat(response.jsonPath().getList("id")).containsExactlyInAnyOrder(expectedList.toArray(new String[0]));

        Assert.assertEquals(actualIds,expectedIds);


    }
}
