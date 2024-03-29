package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.VendorDao;
import data.VendorDto;

// Servlet class to display the registration page
public class DispRegisterServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configure the request and response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // List of vendors
        ArrayList<VendorDto> vendorList = new ArrayList<VendorDto>();

        // DAO class for vendor data
        VendorDao vendor = new VendorDao();

        try {
            // Get the list of vendors
            vendorList = vendor.read();

            if (vendorList.isEmpty()) {
                // If no vendor data is registered
                request.setAttribute("failureMessage", "No vendor data is registered.");
            } else {
                // If there is vendor data
                request.setAttribute("vendorList", vendorList);
            }
        } catch (SQLException e) {
            request.setAttribute("failureMessage", "Failed to read vendor data. Contact the administrator.");
        }

        // Forward the request to the registration page
        request.getRequestDispatcher("/WEB-INF/jsp/registerPage.jsp").forward(request, response);
    }
}

