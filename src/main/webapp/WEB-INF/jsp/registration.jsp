<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<br/>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <div class="well bs-component">
                <s:form cssClass="form-login" action="processRegistration" namespace="/" method="POST" onsubmit="return check()">

                    <legend><h2><s:text name="registration"/></h2></legend>
                    <s:textfield key="userEmail" cssClass="form-control required"/>
                    <s:password key="userPassword" cssClass="form-control required"/>

                    <s:textfield key="userName" cssClass="form-control required"/>
                    <s:textfield key="userBank" cssClass="form-control required"/>

                    <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
                    <s:actionerror cssClass="red"/>
                    <s:actionmessage cssClass="green"/>
                    <s:a action="login" namespace="/"><s:text name="login"/></s:a>
                </s:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
