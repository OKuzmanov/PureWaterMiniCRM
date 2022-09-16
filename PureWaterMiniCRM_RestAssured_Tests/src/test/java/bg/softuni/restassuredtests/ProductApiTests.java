package bg.softuni.restassuredtests;

import bg.softuni.dtos.ProductDTO;
import bg.softuni.dtos.RawMaterialDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductApiTests {
    final String url = "http://localhost:8080";
    final String apiGetAllProducts = "/api/products";
    final String apiGetProductById = "/api/products/";

    @Test
    public void test_getAllProducts() {
        when().request("GET", url + apiGetAllProducts).then().statusCode(200);

        String json = get(url + apiGetAllProducts).asString();

        Gson gson = new GsonBuilder()
                .create();

        ProductDTO[] productDTOsList = gson.fromJson(json, ProductDTO[].class);

        Assertions.assertTrue(productDTOsList.length > 0);
    }

    @Test
    public void test_getProductById() {
        when().request("GET", url + apiGetAllProducts).then().statusCode(200);

        String json = get(url + apiGetAllProducts).asString();

        Gson gson = new GsonBuilder()
                .create();

        ProductDTO[] productDTOsList = gson.fromJson(json, ProductDTO[].class);

        if (productDTOsList.length == 0) {
            Assertions.fail("There are no Products in the DB.");
        }

        ProductDTO firstProduct = productDTOsList[0];
        Long firstProductId = firstProduct.getId();

        when().request("GET", url + apiGetProductById + firstProductId).then().statusCode(200);

        get(url + apiGetProductById + firstProductId).then().body("id", equalTo(firstProduct.getId().intValue()));
        get(url + apiGetProductById + firstProductId).then().body("quantity", equalTo(firstProduct.getQuantity()));
        get(url + apiGetProductById + firstProductId).then().body("type", equalTo(firstProduct.getType()));
        get(url + apiGetProductById + firstProductId).then().body("productionDate", equalTo(firstProduct.getProductionDate()));
    }
}
