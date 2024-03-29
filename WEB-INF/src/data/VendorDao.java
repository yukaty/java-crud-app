package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Data Access Object class for Vendor
public class VendorDao {

    // Database connection
    private static final String URL = "jdbc:mariadb://localhost:8889/java_db_app";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root"; 
    
    /*
     * SELECT
     */
    public ArrayList<VendorDto> read()
            throws SQLException {

        // SQL
        String sql = "SELECT vendor_code FROM vendors;";

        // List of retrieved data
        ArrayList<VendorDto> dataList = new ArrayList<VendorDto>();

        // Connect to database
        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                Statement statement = con.createStatement();) {

            // Execute SQL
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                // Fill DTO instance with column data
                VendorDto vendorData = new VendorDto();
                vendorData.setVendorCode(result.getInt("vendor_code"));

                // Add data to list
                dataList.add(vendorData);
            }
        }

        return dataList;
    }
}

