package data;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Data Access Object class for Vendor
public class VendorDao {

    // Database connection
//    private static final String URL = "jdbc:mariadb://localhost:8889/java_db_app";
//    private static final String USER_NAME = "root";
//    private static final String PASSWORD = "root"; 
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String dburl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        return DriverManager.getConnection(dburl, username, password);
    }
    
    /*
     * SELECT
     */
    public ArrayList<VendorDto> read()
            throws SQLException, URISyntaxException {

        // SQL
        String sql = "SELECT vendor_code FROM vendors;";

        // List of retrieved data
        ArrayList<VendorDto> dataList = new ArrayList<VendorDto>();

        // Connect to database
//        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        try (Connection con = getConnection();        
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

