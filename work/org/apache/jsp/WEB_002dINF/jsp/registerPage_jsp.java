/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.85
 * Generated at: 2024-03-28 23:52:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.util.Objects;
import data.VendorDto;

public final class registerPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Objects");
    _jspx_imports_classes.add("data.VendorDto");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ja\">\n");
      out.write("<head>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("<title>Register Product</title>\n");
      out.write("<link rel=\"stylesheet\"\n");
      out.write("    href=\"");
      out.print(request.getContextPath());
      out.write("/css/style.css\">\n");
      out.write("\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
      out.write("<link\n");
      out.write("    href=\"https://fonts.googleapis.com/css2?family=NotoSans&display=swap\"\n");
      out.write("    rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <nav>\n");
      out.write("            <a href=\"");
      out.print(request.getContextPath());
      out.write("\">Product Management App</a>\n");
      out.write("        </nav>\n");
      out.write("    </header>\n");
      out.write("    <main>\n");
      out.write("        ");

        // Get request parameters
        String productCode = request.getParameter("product_code");
        String productName = request.getParameter("product_name");
        String price = request.getParameter("price");
        String stockQuantity = request.getParameter("stock_quantity");
        String vendorCode = request.getParameter("vendor_code");

        // Convert NULL to empty string
        productCode = Objects.toString(productCode, "");
        productName = Objects.toString(productName, "");
        price = Objects.toString(price, "");
        stockQuantity = Objects.toString(stockQuantity, "");
        vendorCode = Objects.toString(vendorCode, "");
        
      out.write("\n");
      out.write("        <article class=\"registration\">\n");
      out.write("            ");

            // If there is a failure message
            String failureMessage = (String) request.getAttribute("failureMessage");
            if( failureMessage != null && !failureMessage.isEmpty() ) {
                out.println("<p class='failure'>" + failureMessage + "</p>");
            }
            
      out.write("\n");
      out.write("\n");
      out.write("            <h1>Register New Product</h1>\n");
      out.write("            <div class=\"back\">\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/list\" class=\"btn\">&lt; Back</a>\n");
      out.write("            </div>\n");
      out.write("            <form action=\"");
      out.print(request.getContextPath());
      out.write("/create\" method=\"post\" class=\"registration-form\">\n");
      out.write("                <div>\n");
      out.write("                    <label for=\"product_code\">Product Code</label>\n");
      out.write("                    <input type=\"number\" name=\"product_code\" min=\"0\" max=\"100000000\" value=\"");
      out.print( productCode );
      out.write("\" required>\n");
      out.write("                    <label for=\"product_name\">Product Name</label>\n");
      out.write("                    <input type=\"text\" name=\"product_name\" maxlength=\"50\" value=\"");
      out.print( productName );
      out.write("\" required>\n");
      out.write("                    <label for=\"price\">Unit Price</label>\n");
      out.write("                    <input type=\"number\" name=\"price\" min=\"0\" max=\"100000000\" value=\"");
      out.print( price );
      out.write("\" required>\n");
      out.write("                    <label for=\"stock_quantity\">Stock Quantity</label>\n");
      out.write("                    <input type=\"number\" name=\"stock_quantity\" min=\"0\" max=\"100000000\" value=\"");
      out.print( stockQuantity );
      out.write("\" required\n");
      out.write("                    <label for=\"vendor_code\">Vendor Code</label>\n");
      out.write("                    <select name=\"vendor_code\" required>\n");
      out.write("                        <option disabled selected value>Select</option>\n");
      out.write("                        ");

                        // Get the vendor list
                        ArrayList<VendorDto> vendorList = (ArrayList<VendorDto>) request.getAttribute("vendorList");

                        if (vendorList != null) {
                            // Output each vendor data as an option in the select box
                            for (VendorDto vendor : vendorList) {
                                // Convert vendor code from Int to String
                                String nowVendorCode = Integer.toString( vendor.getVendorCode() );
                                // If the previous vendor data matches, select it
                                String selected = Objects.equals(vendorCode, nowVendorCode) ? "selected" : "";
                                out.println("<option value='" + vendor.getVendorCode() + "' " + selected + ">" + vendor.getVendorCode() + "</option>");
                            }
                        }
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"submit-btn\" name=\"submit\" value=\"create\">Register</button>\n");
      out.write("            </form>\n");
      out.write("        </article>\n");
      out.write("    </main>\n");
      out.write("    <footer>\n");
      out.write("        <p class=\"copyright\">&copy; Product Management App</p>\n");
      out.write("    </footer>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
