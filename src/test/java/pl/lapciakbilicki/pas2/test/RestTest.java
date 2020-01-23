package pl.lapciakbilicki.pas2.test;


import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import io.restassured.response.Response;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class RestTest {
    private static final String PATH = "/facilities";
    private static RequestSpecification specification;

    @BeforeClass
    public static void init() {
        specification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/pas2/api")
                .build();
    }

    @Test
    public void getSportsFacilityError() {
        given()
                .spec(specification)
                .when()
                .get(PATH + "/1")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteSportsFacilityError() {
        given()
                .spec(specification)
                .when()
                .delete(PATH + "/1")
                .then()
                .statusCode(404)
                .extract()
                .asString();
    }

    @Test
    public void createSportsFacilityPricePerHoursValidationError() {
        JSONObject body = createFootballFacility();
        body.remove("pricePerHours");
        testRequestWithBody(body.toString(), "football", 400);
        body.put("pricePerHours", 2);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilityNullFieldError(){
        JSONObject body = createFootballFacility();
        body.remove("field");
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilityNameError(){
        JSONObject body = createFootballFacility();
        body.remove("name");
        testRequestWithBody(body.toString(), "football", 400);
        body.put("name", "a");
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilityNullFieldValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("field");
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilitySurfaceAreaFieldValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("field");
        JSONObject field = new JSONObject();
        field.put("typeOfGround", "name");
        field.put("maxAmountOfPeople", 5);
        field.put("surfaceArea", 9);
        body.put("field", field);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilityMaxAmountOfPeopleFieldValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("field");
        JSONObject field = new JSONObject();
        field.put("typeOfGround", "name");
        field.put("maxAmountOfPeople", 1);
        field.put("surfaceArea", 20);
        body.put("field", field);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createSportsFacilityTypeOfGroundFieldValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("field");
        JSONObject field = new JSONObject();
        field.put("typeOfGround", "I am the best tester in the world");
        field.put("maxAmountOfPeople", 30);
        field.put("surfaceArea", 20);
        body.put("field", field);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createFootballFacilityWidthOfGoalValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("widthOfGoal");
        testRequestWithBody(body.toString(), "football", 400);
        body.put("widthOfGoal", 1);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createFootballFacilityHeightOfGoalValidationError(){
        JSONObject body = createFootballFacility();
        body.remove("heightOfGoal");
        testRequestWithBody(body.toString(), "football", 400);
        body.put("heightOfGoal", 1);
        testRequestWithBody(body.toString(), "football", 400);
    }

    @Test
    public void createBasketballFacilityNumberOfBasketValidationError(){
        JSONObject body = createBasketballFacility();
        body.remove("numberOfBasket");
        testRequestWithBody(body.toString(), "basketball", 400);
        body.put("numberOfBasket", 0);
        testRequestWithBody(body.toString(), "basketball", 400);
    }

    @Test
    public void createBasketballFacilityMinHeightOfBasketValidationError(){
        JSONObject body = createBasketballFacility();
        body.remove("minHeightOfBasket");
        testRequestWithBody(body.toString(), "basketball", 400);
        body.put("minHeightOfBasket", 1);
        testRequestWithBody(body.toString(), "basketball", 400);
    }

    @Test
    public void createBasketballFacilityMaxHeightOfBasketValidationError(){
        JSONObject body = createBasketballFacility();
        body.remove("maxHeightOfBasket");
        testRequestWithBody(body.toString(), "basketball", 400);
        body.put("maxHeightOfBasket", 1);
        testRequestWithBody(body.toString(), "basketball", 400);
    }

    @Test
    public void createBasketballFacilityMinHeightOfBasketGreaterThanMaxHeightOfBasketValidationError(){
        JSONObject body = createBasketballFacility();
        body.remove("minHeightOfBasket");
        body.put("minHeightOfBasket", 20);
        testRequestWithBody(body.toString(), "basketball", 400);
    }

    @Test
    public void crudTest(){
        int initialSportsFacilitiesSize = 20;
        JSONArray sportsFacilities = getAllSportsFacilities();
        Assert.assertEquals(initialSportsFacilitiesSize, sportsFacilities.length());
        JSONObject basketballBody = createBasketballFacility();
        JSONObject footballBody = createFootballFacility();
        footballBody.remove("name");
        footballBody.put("name", "testName2");

        testRequestWithBody(basketballBody.toString(),"basketball", 200);
        testRequestWithBody(footballBody.toString(), "football", 200);

        sportsFacilities = getAllSportsFacilities();
        Assert.assertEquals(initialSportsFacilitiesSize + 2, sportsFacilities.length());
        JSONObject testNameSportsFacility = sportsFacilities.getJSONObject(sportsFacilities.length()-2);
        JSONObject testName2SportsFacility = sportsFacilities.getJSONObject(sportsFacilities.length()-1);
        getTest(testNameSportsFacility.getString("id"));
        getTest(testName2SportsFacility.getString("id"));

        testNameSportsFacility.put("name", "changed");
        testName2SportsFacility.put("name", "changed2");

        putTest("basketball/update", testNameSportsFacility.toString(), 200);
        putTest("football/update", testName2SportsFacility.toString(), 200);

        sportsFacilities = getAllSportsFacilities();
        testNameSportsFacility = sportsFacilities.getJSONObject(sportsFacilities.length()-2);
        testName2SportsFacility = sportsFacilities.getJSONObject(sportsFacilities.length()-1);

        Assert.assertEquals("changed", testNameSportsFacility.get("name"));
        Assert.assertEquals("changed2", testName2SportsFacility.get("name"));

        testName2SportsFacility.put("name", "changed");

        testRequestWithBody(testName2SportsFacility.toString(), "football", 400);

        deleteTest(testNameSportsFacility.getString("id"));
        deleteTest(testName2SportsFacility.getString("id"));

        sportsFacilities = getAllSportsFacilities();
        Assert.assertEquals(initialSportsFacilitiesSize, sportsFacilities.length());


    }

    private JSONObject createFootballFacility() {
        return new JSONObject()
                .put("id", "")
                .put("pricePerHours", 20)
                .put("access", true)
                .put("type", "FootballFacility")
                .put("name", "testName")
                .put("field", new JSONObject()
                        .put("surfaceArea", 20)
                        .put("maxAmountOfPeople", 10)
                        .put("typeOfGround", "trawa"))
                .put("fullsize", true)
                .put("widthOfGoal", 2)
                .put("heightOfGoal", 2);
    }

    private JSONObject createBasketballFacility(){
        return new JSONObject()
                .put("id", "")
                .put("pricePerHours", 20)
                .put("access", true)
                .put("type", "FootballFacility")
                .put("name", "testName")
                .put("field", new JSONObject()
                        .put("surfaceArea", 20)
                        .put("maxAmountOfPeople", 10)
                        .put("typeOfGround", "trawa"))
                .put("minHeightOfBasket", 2)
                .put("maxHeightOfBasket", 2.5)
                .put("numberOfBasket", 2);
    }

    private void testRequestWithBody(String body, String resourcePath, int expectedStatusCode) {
        given()
                .spec(specification)
                .body(body)
                .when()
                .post(PATH + "/" + resourcePath)
                .then()
                .statusCode(expectedStatusCode);
    }

    private void getTest(String id){
         given()
                .spec(specification)
                .when()
                .get(PATH + "/" + id)
                .then()
                .statusCode(200);
    }

    private JSONArray getAllSportsFacilities(){
        Response response = with().request("GET", "http://localhost:8080/pas2/api/facilities");
        return new JSONArray(response.asString());
    }

    private void putTest(String path, String body, int code){
        given()
                .spec(specification)
                .body(body)
                .when()
                .put(PATH + "/" + path)
                .then()
                .statusCode(code);
    }

    private void deleteTest(String id){
        given()
                .spec(specification)
                .when()
                .delete(PATH + "/" + id)
                .then()
                .statusCode(200);
    }
}
