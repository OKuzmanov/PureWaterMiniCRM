package bg.softuni.restassuredtests;

import bg.softuni.dtos.SupplierDTO;
import bg.softuni.dtos.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;

public class SupplierApiTests {
    final String url = "http://localhost:8080";
    final String apiGetAllSuppliers = "/api/suppliers";
    final String apiGetSupplierById = "/api/suppliers/";

    @Test
    public void test_getAllSuppliers() {
        when().request("GET", url + apiGetAllSuppliers).then().statusCode(200);

        String json = get(url + apiGetAllSuppliers).asString();

        Gson gson = new GsonBuilder()
                .create();

        SupplierDTO[] supplierDTOsList = gson.fromJson(json, SupplierDTO[].class);

        Assertions.assertTrue(supplierDTOsList.length > 0);
    }

    @Test
    public void test_getSupplierById() {
        when().request("GET", url + apiGetAllSuppliers).then().statusCode(200);

        String json = get(url + apiGetAllSuppliers).asString();

        Gson gson = new GsonBuilder()
                .create();

        SupplierDTO[] supplierDTOsList = gson.fromJson(json, SupplierDTO[].class);

        if (supplierDTOsList.length == 0) {
            Assertions.fail("There are no Suppliers in the DB.");
        }

        SupplierDTO firstSupplier = supplierDTOsList[0];
        Long firstSupplierId = firstSupplier.getId();

        when().request("GET", url + apiGetSupplierById + firstSupplierId).then().statusCode(200);

        get(url + apiGetSupplierById + firstSupplierId).then().body("id", equalTo(firstSupplier.getId().intValue()));
        get(url + apiGetSupplierById + firstSupplierId).then().body("companyName", equalTo(firstSupplier.getCompanyName()));
        get(url + apiGetSupplierById + firstSupplierId).then().body("email", equalTo(firstSupplier.getEmail()));
        get(url + apiGetSupplierById + firstSupplierId).then().body("phoneNumber", equalTo(firstSupplier.getPhoneNumber()));
        get(url + apiGetSupplierById + firstSupplierId).then().body("address", equalTo(firstSupplier.getAddress()));
        get(url + apiGetSupplierById + firstSupplierId).then().body("description", equalTo(firstSupplier.getDescription()));
    }
}
