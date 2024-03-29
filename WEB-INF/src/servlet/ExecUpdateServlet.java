package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;
import data.ProductDto;

// Servlet class to update the product data
public class ExecUpdateServlet extends HttpServlet {

    // Success page
    private static final String SUCCESS_PAGE = "/list";
    // Failure page
    private static final String FAILURE_PAGE = "/WEB-INF/jsp/editPage.jsp";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        /*
         * Check the request parameters
         */
        // Get the parameter values
        String id = request.getParameter("id");
        String productCode = request.getParameter("product_code");
        String productName = request.getParameter("product_name");
        String price = request.getParameter("price");
        String stockQuantity = request.getParameter("stock_quantity");
        String vendorCode = request.getParameter("vendor_code");

        // Convert the values to integers
        int iId, iProductCode, iPrice, iStockQuantity, iVendorCode;
        try {
            iId = Integer.parseInt(id);
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
         * Update the product data
         */
        // Instantiate the ProductDto class
        ProductDto data = new ProductDto();
        data.setId(iId);
        data.setProductCode(iProductCode);
        data.setProductName(productName);
        data.setPrice(iPrice);
        data.setStockQuantity(iStockQuantity);
        data.setVendorCode(iVendorCode);

        // Instantiate the ProductDao class
        ProductDao product = new ProductDao();

        // Update the product data
        int rowCnt = product.update(data);

        // Check the result
        if (rowCnt > 0) {
            forwardSuccess("Update is successful.", request, response);
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
