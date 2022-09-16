package bg.softuni.dtos;

import com.google.gson.annotations.SerializedName;

public class SupplierDTO {
    @SerializedName("id")
    public Long id;

    @SerializedName("companyName")
    public String companyName;

    @SerializedName("email")
    public String email;

    @SerializedName("phoneNumber")
    public String phoneNumber;

    @SerializedName("address")
    public String address;

    @SerializedName("description")
    public String description;

    @SerializedName("rawMaterials")
    public RawMaterialDTO[] rawMaterials;

    public SupplierDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RawMaterialDTO[] getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(RawMaterialDTO[] rawMaterials) {
        this.rawMaterials = rawMaterials;
    }
}
