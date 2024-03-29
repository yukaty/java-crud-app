<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Objects"%>
<%@ page import="data.ProductDto" %>
<%@ page import="data.VendorDto" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Product</title>
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
            <a href="<%=request.getContextPath()%>">Product Management App</a>
        </nav>
    </header>
    <main>
        <%
        // Get request parameters
        String id = request.getParameter("id");
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
        
        // Get the Product List
        ArrayList<ProductDto> productList = (ArrayList<ProductDto>) request.getAttribute("productList");

        if( productList != null && !productList.isEmpty() ) {
            // Get the first record
            ProductDto productData = productList.get(0);
            
            id = Integer.toString( productData.getId() );
            productCode = Integer.toString( productData.getProductCode() );
            productName = productData.getProductName();
            price = Integer.toString( productData.getPrice() );
            stockQuantity = Integer.toString( productData.getStockQuantity() );
            vendorCode = Integer.toString( productData.getVendorCode() );
        }
        %>
        <article class="registration">
            <%
            // If there is a failure message
            String failureMessage = (String) request.getAttribute("failureMessage");
            if( failureMessage != null && !failureMessage.isEmpty() ) {
                out.println("<p class='failure'>" + failureMessage + "</p>");
            }
            %>

            <h1>Edit Product</h1>
            <div class="back">
                <a href="<%=request.getContextPath()%>/list" class="btn">&lt; Back</a>
            </div>
            <form action="<%=request.getContextPath()%>/update?id=<%= id %>" method="post" class="registration-form">
                <div>
                    <label for="product_code">Product Code</label>
                    <input type="number" name="product_code" min="0" max="100000000" value="<%= productCode %>" required>
                    <label for="product_name">Product Name</label>
                    <input type="text" name="product_name" maxlength="50" value="<%= productName %>" required>
                    <label for="price">Unit Price</label>
                    <input type="number" name="price" min="0" max="100000000" value="<%= price %>" required>
                    <label for="stock_quantity">Stock Quantity</label>
                    <input type="number" name="stock_quantity" min="0" max="100000000" value="<%= stockQuantity %>" required
                    <label for="vendor_code">Vendor Code</label>
                    <select name="vendor_code" required>
                        <option disabled selected value>Select</option>
                        <%
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
                        %>
                    </select>
                </div>
                <button type="submit" class="submit-btn" name="submit" value="update">Update</button>
            </form>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; Product Management App</p>
    </footer>
</body>
</html>

