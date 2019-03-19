<%@ page import="app.domain.models.CreateTubeModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
</head>
<body>
<% CreateTubeModel tube = (CreateTubeModel) request.getAttribute("tube");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1><%= tube.getTitle()%></h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3><%= tube.getDescription()%></h3>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%= tube.getYouTubeLink()%>"><%= tube.getYouTubeLink()%></a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <div><%= tube.getUploader()%></div>
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
