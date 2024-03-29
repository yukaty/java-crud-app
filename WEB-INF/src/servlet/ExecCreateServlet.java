package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import data.ProductDto;

// Servlet class to execute product registration
public class ExecCreateServlet extends HttpServlet {

    // Success page after registration
    private static final String SUCCESS_PAGE = "/list";
    // Failure page after registration
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/registerPage.jsp";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        /*
         * Check the request parameters 
         */
        // Get the parameter values 
        String productCode = request.getParameter("product_code");
        String productName = request.getParameter("product_name");
        String price = request.getParameter("price");
        String stockQuantity = request.getParameter("stock_quantity");
        String vendorCode = request.getParameter("vendor_code");

        // Convert the values to integers
        int iProductCode, iPrice, iStockQuantity, iVendorCode;
        try {
            iProductCode = Integer.parseInt(productCode);
            iPrice = Integer.parseInt(price);
            iStockQuantity = Integer.parseInt(stockQuantity);
            iVendorCode = Integer.parseInt(vendorCode);
        } catch (NumberFormatException e) {
            forwardFailure("Input valid number for except of product name.", request, response);
            return;
        }

        // Check if the product name is empty
        if (productName == null || productName.isEmpty()) {
            forwardFailure("Input valid product name.", request, response);
            return;
        }

        /*
         * Register the product data
         */
        // Instantiate the DTO class
        ProductDto data = new ProductDto();
        data.setProductCode(iProductCode);
        data.setProductName(productName);
        data.setPrice(iPrice);
        data.setStockQuantity(iStockQuantity);
        data.setVendorCode(iVendorCode);

        // Instantiate the DAO class
        ProductDao product = new ProductDao();

        // Register the product data
        int rowCnt = product.create(data);

        // Check the registration result
        if (rowCnt > 0) {
            forwardSuccess("Registration is successful.", request, response);
        } else {
            forwardFailure("Database processing error. Contact the system administrator.", request, response);
        }
    }

    /*
     * Forward to the success/failure page
     */
    private void forwardSuccess(String message, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("successMessage", message);
        request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
    }

    private void forwardFailure(String message, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("failureMessage", message);
        request.getRequestDispatcher(FAILURE_PAGE).forward(request, response);
    }
}

