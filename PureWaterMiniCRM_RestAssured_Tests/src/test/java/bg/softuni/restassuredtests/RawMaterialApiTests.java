package bg.softuni.restassuredtests;

import bg.softuni.dtos.RawMaterialDTO;
import bg.softuni.dtos.SupplierDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class RawMaterialApiTests {
    final String url = "http://localhost:8080";
    final String apiGetAllRawMaterials = "/api/raw/materials";
    final String apiGetRawMaterialById = "/api/raw/materials/";

    @Test
    public void test_getAllRawMaterials() {
        when().request("GET", url + apiGetAllRawMaterials).then().statusCode(200);

        String json = get(url + apiGetAllRawMaterials).asString();

        Gson gson = new GsonBuilder()
                .create();

        RawMaterialDTO[] rawMaterialDTOsList = gson.fromJson(json, RawMaterialDTO[].class);

        Assertions.assertTrue(rawMaterialDTOsList.length > 0);
    }

    @Test
    public void test_getRawMaterialById() {
        when().request("GET", url + apiGetAllRawMaterials).then().statusCode(200);

        String json = get(url + apiGetAllRawMaterials).asString();

        Gson gson = new GsonBuilder()
                .create();

        RawMaterialDTO[] rawMaterialDTOsList = gson.fromJson(json, RawMaterialDTO[].class);

        if (rawMaterialDTOsList.length == 0) {
            Assertions.fail("There are no Raw Materials in the DB.");
        }

        RawMaterialDTO firstRawMaterial = rawMaterialDTOsList[0];
        Long firstRawMaterialId = firstRawMaterial.getId();

        when().request("GET", url + apiGetRawMaterialById + firstRawMaterialId).then().statusCode(200);

        get(url + apiGetRawMaterialById + firstRawMaterialId).then().body("id", equalTo(firstRawMaterial.getId().intValue()));
        get(url + apiGetRawMaterialById + firstRawMaterialId).then().body("quantity", equalTo(firstRawMaterial.getQuantity()));
        get(url + apiGetRawMaterialById + firstRawMaterialId).then().body("type", equalTo(firstRawMaterial.getType()));
        get(url + apiGetRawMaterialById + firstRawMaterialId).then().body("deliveredAt", equalTo(firstRawMaterial.getDeliveredAt()));
    }
}
