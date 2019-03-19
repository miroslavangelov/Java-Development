<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="app.domain.models.AllTubesModel" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
</head>
<body>
<% List<AllTubesModel> tubes = (List<AllTubesModel>) request.getAttribute("tubes");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>All Tubes</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>Check our tubes below.</h3>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <% if(tubes.size() == 0) {%>
                    <p>No tubes - <a href="/tubes/create">Create Some</a>! </p>
                    <% } else{ %>
                    <ul>
                        <% for (AllTubesModel tube : tubes) { %>
                        <li><a href="/tubes/details?name=<%= tube.getTitle()%>"><%= tube.getTitle()%></a> </li>
                        <% } %>
                    </ul>
                    <% } %>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to Home.</a>
                </div>
            </div>
        </div>
    </main>
</div>
<footer>
    <c:import url="templates/footer.jsp" />
</footer>
</body>
</html>
