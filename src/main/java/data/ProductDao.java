package data;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

// Data Access Object class for Product
public class ProductDao {

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
     *  INSERT
     */
    public int create(ProductDto data) {
        int rowCnt = 0; 

        // SQL
        String sql = "INSERT INTO products(" +
                "  product_code, product_name, price, stock_quantity, vendor_code" +
                ") VALUES(?, ?, ?, ?, ?);";

        // Connect to database
        try (Connection con = getConnection();        
                PreparedStatement statement = con.prepareStatement(sql)) {

            // Placeholders
            statement.setInt(1, data.getProductCode());
            statement.setString(2, data.getProductName());
            statement.setInt(3, data.getPrice());
            statement.setInt(4, data.getStockQuantity());
            statement.setInt(5, data.getVendorCode());

            // Execute SQL 
            rowCnt = statement.executeUpdate();
            
        } catch (SQLException | URISyntaxException e) {
            System.out.println("Error occurred：" + e.getMessage());
        }

        // Return the number of updated records
        return rowCnt;
    }

    /*
     *  SELECT
     */
    public ArrayList<ProductDto> read(int id, String order, String keyword)
            throws SQLException {

        // SQL
        String sql = "SELECT * FROM products";

        // List of retrieved data
        ArrayList<ProductDto> dataList = new ArrayList<ProductDto>();

        // Replace NULL with empty string
        order = Objects.toString(order, "");
        keyword = Objects.toString(keyword, "");

        // Connect to database
          try (Connection con = getConnection()) {

            if (id > 0) {
                // If an ID is specified
                sql += " WHERE id = ?;";
            } else {
                // If a search keyword exists
                if (!keyword.isEmpty()) {
                    sql += " WHERE product_name LIKE ?";
                }

                // Apped ORDER BY clause
                if ("asc".equals(order)) {
                    sql += " ORDER BY updated_at ASC";
                } else if ("desc".equals(order)) {
                    sql += " ORDER BY updated_at DESC";
                }
                sql += ";";
            }

            // Prepare SQL statement
            try (PreparedStatement statement = con.prepareStatement(sql)) {

                if (id > 0) {
                    // If an ID is specified
                    statement.setInt(1, id); 
                } else if (!keyword.isEmpty()) {
                    // If a search keyword exists
                    statement.setString(1, "%" + keyword + "%");
                }

                // Execute SQL
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    // Fill DTO instance with column data
                    ProductDto productData = new ProductDto();
                    productData.setId(result.getInt("id"));
                    productData.setProductCode(result.getInt("product_code"));
                    productData.setProductName(result.getString("product_name"));
                    productData.setPrice(result.getInt("price"));
                    productData.setStockQuantity(result.getInt("stock_quantity"));
                    productData.setVendorCode(result.getInt("vendor_code"));

                    // Add data to list
                    dataList.add(productData);
                }
            }
        } catch ( URISyntaxException e) {
            System.out.println("Error occerred：" + e.getMessage());
        }

        return dataList; 
    }

    /*
     *  UPDATE
     */
    public int update(ProductDto data) {
        int rowCnt = 0;

        // SQL
        String sql = "UPDATE products " +
                "SET product_code = ?, " +
                "  product_name = ?, " +
                "  price = ?, " +
                "  stock_quantity = ?, " +
                "  vendor_code = ? " +
                "WHERE id = ?;";

        // Connect to database
        try (Connection con = getConnection();        
                PreparedStatement statement = con.prepareStatement(sql)) {

            // Placeholders
            statement.setInt(1, data.getProductCode());
            statement.setString(2, data.getProductName());
            statement.setInt(3, data.getPrice());
            statement.setInt(4, data.getStockQuantity());
            statement.setInt(5, data.getVendorCode());
            statement.setInt(6, data.getId());

            // Execute SQL
            rowCnt = statement.executeUpdate();
            
        } catch (SQLException | URISyntaxException e) {
            System.out.println("Error occurred：" + e.getMessage());
        }

        return rowCnt;
    }

    /*
     * DELETE
     */
    public int delete(int id) {
        int rowCnt = 0; 

        // SQL
        String sql = "DELETE FROM products WHERE id = ?;";

        // Connect to database
        try (Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement(sql)) {

            // Placeholders
            statement.setInt(1, id);

            // Execute SQL
            rowCnt = statement.executeUpdate();
        } catch (SQLException | URISyntaxException e) {
            System.out.println("Error occerred：" + e.getMessage());
        }

        return rowCnt; 
    }
}

