package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductDao;

// Servlet class to delete data
public class ExecDeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Get the request parameter
        String id = request.getParameter("id");

        // Convert the ID to integer
        int iId;
        try {
            iId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            request.setAttribute("failureMessage", "Error occurred. Please contact the system administrator.");
            request.getRequestDispatcher("/list").forward(request, response);
            return;
        }
        /*
         * Delete the product data
         */
        ProductDao product = new ProductDao();

        // Delete the product data
        int rowCnt = product.delete(iId);

        // Check the result
        if (rowCnt > 0) {
            request.setAttribute("successMessage", rowCnt + " product(s) have been successfully deleted.");
        } else {
            request.setAttribute("failureMessage", "Database processing error occurred. Please contact the system administrator.");
        }

        // Forward the request to the list page
        request.getRequestDispatcher("/list").forward(request, response);
    }
}

