<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Home</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/home.js"></script>
</head>

<body>

<div class="intro">
    <div class="intro-body">
        <div class="container">
            <i class="glyphicon glyphicon-gift globus"></i>

            <h1>Start to organize your parties!<br/> <small>We have <s:property value="usersCount"/> users, who like parties!</small></h1>
            <a class="btn btn-primary btn-lg page-scroll" href="#try" role="button">
                <span class="h3">Start here</span>
                <br/>
                <i class="glyphicon glyphicon-triangle-bottom"></i>
            </a>
        </div>
    </div>
</div>

<div class="container">
    <div class="row" id="try">
        <div class="col-xs-12 col-md-6">
            <div class="well well-lg priority-border">
                <h1 class="h1 orange">Want to organize?</h1>
                <ul class="text-left">
                    <li>Create events</li>
                    <li>Manage guests</li>
                    <li>Send invites</li>
                    <li>Even more...</li>
                </ul>
                <a href="<s:url action="registration" namespace="/"/>" class="btn btn-info btn-lg">Create an Account<br><small class="grey">for free</small></a>
                <br/>
                <a href="<s:url action="login" namespace="/"/>" class="">or login</a>
                <i class="glyphicon glyphicon-user picture"></i>
            </div>
        </div>

        <div class="col-xs-12 col-md-6">
            <div class="well well-lg">
                <h1 class="h2">Want to participate?</h1>
                <ul class="text-left">
                    <li>Watch oncoming events</li>
                    <li>Send "want participate" requests</li>
                    <li>See guests</li>
                    <li>Vote for presents</li>
                </ul>
                <a href="<s:url action="parties" namespace="/"/>" class="btn btn-default">Start watching</a>
                <i class="glyphicon glyphicon-eye-open picture"></i>
            </div>
        </div>
    </div>
</div>

</body>

</html>