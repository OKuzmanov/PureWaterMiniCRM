package bg.softuni.restassuredtests;

import bg.softuni.dtos.OrderDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.JsonPath.from;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class OrderApiTests {

    final String url = "http://localhost:8080";
    final String apiGetAllOrders = "/api/orders";
    final String apiGetOrderById = "/api/orders/";

    @Test
    public void test_getAllOrders() {
        when().request("GET", url + apiGetAllOrders).then().statusCode(200);

        String json = get(url + apiGetAllOrders).asString();

        Gson gson = new GsonBuilder()
                .create();

        OrderDTO[] orderDTOsList = gson.fromJson(json, OrderDTO[].class);

        Assertions.assertTrue(orderDTOsList.length > 0);
    }

    @Test
    public void test_getOrderById() {
        when().request("GET", url + apiGetAllOrders).then().statusCode(200);

        String json = get(url + apiGetAllOrders).asString();

        Gson gson = new GsonBuilder()
                .create();

        OrderDTO[] orderDTOsList = gson.fromJson(json, OrderDTO[].class);

        if (orderDTOsList.length == 0) {
            Assertions.fail("There are no Orders in the DB.");
        }

        OrderDTO firstOrder = orderDTOsList[0];
        Long firstOrderId = firstOrder.getId();

        when().request("GET", url + apiGetOrderById + firstOrderId).then().statusCode(200);

        get(url + apiGetOrderById + firstOrderId).then().body("id", equalTo(firstOrder.getId().intValue()));
        get(url + apiGetOrderById + firstOrderId).then().body("name", equalTo(firstOrder.getName()));
        given().config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .when().get(url + apiGetOrderById + firstOrderId).then().body("totalPrice", equalTo(firstOrder.getTotalPrice()));
        get(url + apiGetOrderById + firstOrderId).then().body("quantity", equalTo(firstOrder.getQuantity()));
        get(url + apiGetOrderById + firstOrderId).then().body("prodCategory", equalTo(firstOrder.getProdCategory()));
        get(url + apiGetOrderById + firstOrderId).then().body("description", equalTo(firstOrder.getDescription()));
        get(url + apiGetOrderById + firstOrderId).then().body("expiryDate", equalTo(firstOrder.getExpiryDate()));
        get(url + apiGetOrderById + firstOrderId).then().body("user.username", equalTo(firstOrder.getUser().getUsername()));
        get(url + apiGetOrderById + firstOrderId).then().body("user.firstName", equalTo(firstOrder.getUser().getFirstName()));
        get(url + apiGetOrderById + firstOrderId).then().body("user.lastName", equalTo(firstOrder.getUser().getLastName()));
        get(url + apiGetOrderById + firstOrderId).then().body("user.email", equalTo(firstOrder.getUser().getEmail()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.id", equalTo(firstOrder.getCustomer().getId().intValue()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.companyName", equalTo(firstOrder.getCustomer().getCompanyName()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.email", equalTo(firstOrder.getCustomer().getEmail()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.phoneNumber", equalTo(firstOrder.getCustomer().getPhoneNumber()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.address", equalTo(firstOrder.getCustomer().getAddress()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.description", equalTo(firstOrder.getCustomer().getDescription()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.user.username", equalTo(firstOrder.getCustomer().getUser().getUsername()));
        get(url + apiGetOrderById + firstOrderId).then().body("customer.user.email", equalTo(firstOrder.getCustomer().getUser().getEmail()));
    }
}
