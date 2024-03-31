<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Management App</title>
<link rel="stylesheet"
    href="<%=request.getContextPath()%>/css/style.css">

<%-- Google Font --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
    href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
    rel="stylesheet">
</head>

<body>
    <header>
        <nav>
            <a href="<%=request.getContextPath()%>/">Product Management App</a>
        </nav>
    </header>
    <main>
        <article class="home">
            <h1>Product Management App</h1>
            <p>Java CRUD application</p>
            <a href="<%=request.getContextPath()%>/list" class="btn">Product List</a>
        </article>
    </main>
    <footer>
        <p class="copyright">&copy; Product Management App
        </p>
    </footer>
</body>

</html>

