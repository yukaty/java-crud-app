package data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

// Data Access Object class for Product
public class ProductDao {

    // Database connection
//    private static final String URL = "jdbc:mariadb://localhost:8889/java_db_app";
//    private static final String USER_NAME = "root";
//    private static final String PASSWORD = "root"; 
    private static final String URL = "jdbc://rtzsaka6vivj2zp1.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/mffj6f4zbk9hazrl/java_db_app";
    private static final String USER_NAME = "r6sxv63pde8ljs1u";
    private static final String PASSWORD = "x4ecppm06ii1lmbs"; 

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
        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = con.prepareStatement(sql)) {

            // Placeholders
            statement.setInt(1, data.getProductCode());
            statement.setString(2, data.getProductName());
            statement.setInt(3, data.getPrice());
            statement.setInt(4, data.getStockQuantity());
            statement.setInt(5, data.getVendorCode());

            // Execute SQL 
            rowCnt = statement.executeUpdate();
            
        } catch (SQLException e) {
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
        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {

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
        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
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
            
        } catch (SQLException e) {
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
        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = con.prepareStatement(sql)) {

            // Placeholders
            statement.setInt(1, id);

            // Execute SQL
            rowCnt = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error occerred：" + e.getMessage());
        }

        return rowCnt; 
    }
}

