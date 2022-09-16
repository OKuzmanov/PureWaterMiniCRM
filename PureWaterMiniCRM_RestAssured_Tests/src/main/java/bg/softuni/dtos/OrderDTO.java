package bg.softuni.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONPropertyName;

import java.math.BigDecimal;

public class OrderDTO {

    @SerializedName("id")
    public Long id;
    @SerializedName("name")
    public String name;
    @SerializedName("totalPrice")
    public BigDecimal totalPrice;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("prodCategory")
    public String prodCategory;
    @SerializedName("description")
    public String description;
    @SerializedName("expiryDate")
    public String expiryDate;
    @SerializedName("user")
    public UserDTO user;

    @SerializedName("customer")
    public CustomerDTO customer;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public String getDescription() {
        return description;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
