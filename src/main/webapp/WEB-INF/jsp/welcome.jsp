<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Welcome</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/welcome.js"></script>
</head>

<body>

<div class="container">

    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <div class="row">
                <div class="col-lg-12">
                    <h1><i class="glyphicon glyphicon-info-sign"></i> Hi, <s:property value="user.userName"/>!</h1>
                    <ul>
                        <li>Welcome to <label class="label label-default">Start Screen</label></li>
                    </ul>
                </div>
            </div>

            <div class="row welcome">
                <div class="col-lg-6">
                    <img class="img-circle" src="${pageContext.request.contextPath}/static/img/party.jpg" alt="img" height="140" width="140"/>
                    <h1>Parties</h1>
                    <p>
                        <a class="btn btn-lg btn-warning" href="<s:url action="party" namespace="/"/>" role="button">Create party »</a>
                    </p>
                </div>

                <div class="col-lg-6">
                    <img class="img-circle" src="${pageContext.request.contextPath}/static/img/anketa.jpg" alt="img" height="140" width="140"/>
                    <h1>Profile</h1>
                    <p>
                        <a class="btn btn-lg btn-default" href="<s:url action="profile" namespace="/"/>" role="button">Edit »</a>
                    </p>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h1><i class="glyphicon glyphicon-th-list"></i> Your Parties</h1>

                    <s:if test="userParties.size == 0">
                        <small>you have not activities</small>
                    </s:if>

                    <s:if test="userParties.size > 0">
                        <table id="parties" class="table table-hover">
                        </table>
                    </s:if>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h1><i class="glyphicon glyphicon-th-list"></i> You will go</h1>

                        <table id="participating-parties" class="table table-hover">
                        </table>
                </div>
            </div>

        </div>

    </div>
</div>

</body>

</html>