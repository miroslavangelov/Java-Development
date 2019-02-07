<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>Create Tube!</h1>
                </div>
            </div>
            <hr/>
            <form action="/tubes/create" method="post">
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="title">Title</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="title" name="title">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="description">Description</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="description" name="description">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="youTubeLink">YouTube Link</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="youTubeLink" name="youTubeLink">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="uploader">Uploader</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="uploader" name="uploader">
                        </div>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Create Tube</button>
                    </div>
                </div>
            </form>
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
