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
        <s:form theme="xhtml" cssClass="form-login" action="processLogin" namespace="/">
            <h2 class="form-signin-heading"><s:text name="login"/></h2>
            <s:textfield key="email" cssClass="form-control"/>
            <s:password key="password" cssClass="form-control"/>
            <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
            <s:actionerror/>
            <s:a action="registration" namespace="/"><s:text name="registration"/></s:a>
        </s:form>
    </div>
</div>

</body>

</html>