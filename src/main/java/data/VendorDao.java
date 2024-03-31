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
    
    /*
     * DB connection
     */
    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbEnvUrl = System.getenv("JAWSDB_URL");
        
        // Heroku　(JawsDB)
        if (dbEnvUrl != null && !dbEnvUrl.isEmpty()) {
            URI jdbUri = new URI(dbEnvUrl);

            String username = jdbUri.getUserInfo().split(":")[0];
            String password = jdbUri.getUserInfo().split(":")[1];
            String port = String.valueOf(jdbUri.getPort());
            String dbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } else {
            // Local
            String dbUrl = "jdbc:mariadb://localhost:8889/java_db_app";
            String username = "root";
            String password = "root";
            return DriverManager.getConnection(dbUrl, username, password);
        }
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

