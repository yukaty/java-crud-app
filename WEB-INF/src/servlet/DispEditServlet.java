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
import data.VendorDao;
import data.VendorDto;

// Servlet class to display the product edit page
public class DispEditServlet extends HttpServlet {

    // Success page
    private static final String SUCCESS_PAGE = "/WEB-INF/jsp/editPage.jsp";
    // Failure page
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/listPage.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Get the request parameter "id"
        String id = request.getParameter("id");
        id = Objects.toString(id, ""); // Convert null to empty string

        // Convert the ID to an integer
        int iId;
        try {
            iId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            request.setAttribute("failureMessage", "Internal Server Error. Please contact the system administrator.");
            request.getRequestDispatcher(FAILURE_PAGE).forward(request, response);
            return;
        }

        // Instantiate the DTO class
        ArrayList<ProductDto> productList = new ArrayList<ProductDto>();
        ArrayList<VendorDto> vendorList = new ArrayList<VendorDto>();

        // Instantiate the DAO class
        ProductDao product = new ProductDao();
        VendorDao vendor = new VendorDao();

        try {
            // Get the list of product data
            productList = product.read(iId, "", "");

            // Get the list of vendor data
            vendorList = vendor.read();

        } catch (SQLException e) {
            request.setAttribute("failureMessage", "Database access error occurred. Please contact the system administrator.");
            request.getRequestDispatcher(FAILURE_PAGE).forward(request, response);
            return;
        }

        if (productList.isEmpty()) {
            // If the product data list is empty
            request.setAttribute("failureMessage", "Data not found. Please check the product code.");
        } else {
            // If the product data list is found
            request.setAttribute("productList", productList);
        }

        if (vendorList.isEmpty()) {
            // If the vendor data list is empty
            request.setAttribute("failureMessage", "No vendor data is registered.");
        } else {
            // If the vendor data list is found
            request.setAttribute("vendorList", vendorList);
        }

        // Forward the request to the success page
        request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
    }
}

