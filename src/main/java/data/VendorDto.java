package data;

// Data Transfer Object class for Vendor
public class VendorDto {
    private int vendorCode = 0;
    private String vendorName = "";

    /*
     * Getter and Setter
     */
    public int getVendorCode() {
        return this.vendorCode;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }
    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

