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
        <div class="col-md-12">
            <h1>Hi, <s:property value="user.userName"/>!</h1>
            <ul>
                <li>You can manage your profile <a href="<s:url action="profile" namespace="/"/>">here</a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-md-offset-1 col-xs-12">
            <div class="text-center">
                'create' party
            </div>
        </div>
        <div class="col-md-3 col-md-offset-1 col-xs-12">
            <div class="text-center">
                'see' parties
            </div>
        </div>
        <div class="col-md-3 col-md-offset-1 col-xs-12">
            <div class="text-center">
                '???'
            </div>
        </div>
    </div>
</div>

</body>

</html>