<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Objects"%>
<%@ page import="data.ProductDto"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product List</title>
<link rel="stylesheet"
    href="<%=request.getContextPath()%>/css/style.css">
<%-- Google Fonts --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
    href="https://fonts.googleapis.com/css2?family=NotoSans&display=swap"
    rel="stylesheet">
</head>
<body>
    <header>
        <nav>
            <a href="<%=request.getContextPath()%>/">Product Management App</a>
        </nav>
    </header>
    <main>
        <%
        String order = request.getParameter("order");
        order = Objects.toString(order, ""); // Convert NULL to empty string
        String keyword = request.getParameter("keyword");
        keyword = Objects.toString(keyword, ""); // Convert NULL to empty string
        %>
        <article class="products">
            <h1>Product List</h1>
            <%
            // If there is a success message 
            String successMessage = (String) request.getAttribute("successMessage");
            if( successMessage != null && !successMessage.isEmpty() ) {
                out.println("<p class='success'>" + successMessage + "</p>");
            }
            // If there is a failure message
            String failureMessage = (String) request.getAttribute("failureMessage");
            if (failureMessage != null && !failureMessage.isEmpty()) {
                out.println("<p class='failure'>" + failureMessage + "</p>");
            }
            %>
            <div class="products-ui">
                <div>
                    <a href="<%=request.getContextPath()%>/list?order=desc&keyword=<%=keyword%>"> <img
                        src="images/desc.png" alt="DESC" class="sort-img">
                    </a> <a href="<%=request.getContextPath()%>/list?order=asc&keyword=<%=keyword%>"> <img
                        src="images/asc.png" alt="ASC" class="sort-img">
                    </a>
                    <form action="<%=request.getContextPath()%>/list" method="get" class="search-form">
                        <input type="hidden" name="order" value="<%=order%>">
                        <input type="text" class="search-box" placeholder="Search" name="keyword" value="<%=keyword%>">
                    </form>
                </div>
                <a href="<%= request.getContextPath() %>/register" class="btn">Register</a>
            </div>
            <table class="products-table">
                <tr>
                    <th class="hidden-id">ID</th>
                    <th>Product Code</th>
                    <th>Product Name</th>
                    <th>Unit Price</th>
                    <th>Stock Quantity</th>
                    <th>Vendor Code</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <%
                // Get the product list from the request
                        ArrayList<data.ProductDto> productList = (ArrayList<data.ProductDto>) request.getAttribute("productList");

                        if (productList != null) {
                            // Display the product list line by line
                            for (data.ProductDto product : productList) {
                                out.println("<tr>");
                                out.println("<td class='hidden-id'>" + product.getId() + "</td>");
                                out.println("<td>" + product.getProductCode() + "</td>");
                                out.println("<td>" + product.getProductName() + "</td>");
                                out.println("<td>" + product.getPrice() + "</td>");
                                out.println("<td>" + product.getStockQuantity() + "</td>");
                                out.println("<td>" + product.getVendorCode() + "</td>");
                                out.println("<td><a href='" + request.getContextPath() + "/edit?id="
                                             + product.getId() + "'><img src='images/edit.png' alt='Edit' class='edit-icon'></a></td>");
                                out.println("<td><a href='" + request.getContextPath() + "/delete?id=" + product.getId()
                                                        + "' onclick=\"return confirm('Are you sure you want to delete the product: " + product.getProductName() + "?')\">"
                                                                + "<img src='images/delete.png' alt='Delete' class='delete-icon'></a></td>");
                                out.println("</tr>");
                            }
                        }
                %>
            </table>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; Product Management App</p>
    </footer>
</body>
</html>

