<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Home</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <s:form theme="xhtml" cssClass="form-login" action="processLogin" namespace="/" method="POST" onsubmit="return check()">
                <h2 class="form-signin-heading"><s:text name="login"/></h2>
                <s:textfield key="userEmail" cssClass="form-control required"/>
                <s:password key="userPassword" cssClass="form-control required"/>
                <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
                <s:actionerror cssClass="red"/>
                <s:a action="registration" namespace="/"><s:text name="registration"/></s:a>
            </s:form>
        </div>
    </div>
</div>

</body>

</html>