package bg.softuni.restassuredtests;

import bg.softuni.dtos.CustomerDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CustomerApiTests {
    final String url = "http://localhost:8080";
    final String apiGetAllCustomers = "/api/customers";
    final String apiGetCustomerById = "/api/customers/";

    @Test
    public void test_getAllCustomers() {
        when().request("GET", url + apiGetAllCustomers).then().statusCode(200);

        String json = get(url + apiGetAllCustomers).asString();

        Gson gson = new GsonBuilder()
                .create();

        CustomerDTO[] customersList = gson.fromJson(json, CustomerDTO[].class);

        Assertions.assertTrue(customersList.length > 0);
    }

    @Test
    public void test_getCustomersById() {
        when().request("GET", url + apiGetAllCustomers).then().statusCode(200);

        String json = get(url + apiGetAllCustomers).asString();

        Gson gson = new GsonBuilder()
                .create();

        CustomerDTO[] customersList = gson.fromJson(json, CustomerDTO[].class);

        if (customersList.length == 0) {
            Assertions.fail("There are no Customers in the DB.");
        }

        CustomerDTO firstCustomer = customersList[0];
        Long firstCustomerId = firstCustomer.getId();

        when().request("GET", url + apiGetCustomerById + firstCustomerId).then().statusCode(200);

        get(url + apiGetCustomerById + firstCustomerId).then().body("id", equalTo(firstCustomer.getId().intValue()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("companyName", equalTo(firstCustomer.getCompanyName()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("email", equalTo(firstCustomer.getEmail()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("phoneNumber", equalTo(firstCustomer.getPhoneNumber()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("address", equalTo(firstCustomer.getAddress()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("description", equalTo(firstCustomer.getDescription()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("user.username", equalTo(firstCustomer.getUser().getUsername()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("user.firstName", equalTo(firstCustomer.getUser().getFirstName()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("user.lastName", equalTo(firstCustomer.getUser().getLastName()));
        get(url + apiGetCustomerById + firstCustomerId).then().body("user.email", equalTo(firstCustomer.getUser().getEmail()));
    }
}
