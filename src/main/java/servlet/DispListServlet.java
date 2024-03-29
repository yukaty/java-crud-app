package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import data.ProductDto;

/*
 * Servlet class to display the list of products
 */
public class DispListServlet extends HttpServlet {

    /*
     * doGet() method
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Get the parameters from JSP
        String order = request.getParameter("order");
        order = Objects.toString(order, ""); // Convert NULL to empty string
        String keyword = request.getParameter("keyword");
        keyword = Objects.toString(keyword, ""); // Convert NULL to empty string

        // List of products
        ArrayList<ProductDto> productList = new ArrayList<ProductDto>();

        // DAO class for product data
        ProductDao product = new ProductDao();

        try {
            // Get the list of products
            productList = product.read(0, order, keyword);

            if (productList.isEmpty()) {
                // If no data is found
                request.setAttribute("failureMessage", "No data found.");
            } else {
                // If data is found
                request.setAttribute("productList", productList);
            }
        } catch (SQLException e) {
            request.setAttribute("failureMessage", "Failed to get data. Contact the administrator.");
//            request.setAttribute("failureMessage", e.getMessage());
        }

        // Forward to the list page
        request.getRequestDispatcher("/WEB-INF/jsp/listPage.jsp").forward(request, response);
    }
    /*
     * doPost() method
     *
     * When a doPost() method of a Servlet transitions to another Servlet,
     * its doPost() method is called.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Get success message from request
        String successMessage = (String) request.getAttribute("successMessage");

        if (successMessage != null && !successMessage.isEmpty()) {
            // Success message to pass it to listPage.jsp
            request.setAttribute("successMessage", successMessage);
        }

        // Call doGet() to reacquire the updated list
        doGet(request, response);
    }

}
