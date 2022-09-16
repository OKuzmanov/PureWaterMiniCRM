package bg.softuni.dtos;

import com.google.gson.annotations.SerializedName;

public class RawMaterialDTO {
    @SerializedName("id")
    public Long id;

    @SerializedName("quantity")
    public int quantity;

    @SerializedName("type")
    public String type;

    @SerializedName("deliveredAt")
    public String deliveredAt;

    public RawMaterialDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
