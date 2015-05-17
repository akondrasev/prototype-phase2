<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Profile</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/profile.js"></script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <div class="well bs-component">
                <s:form cssClass="form-login" action="processEditProfile" namespace="/" method="POST" onsubmit="return check()">

                    <legend><h2><s:text name="edit.profile"/></h2></legend>
                    <s:textfield key="userId" cssClass="form-control required" disabled="true"/>
                    <s:textfield key="userEmail" cssClass="form-control required"/>
                    <s:password key="userPassword" cssClass="form-control required"/>

                    <s:textfield key="userName" cssClass="form-control required"/>
                    <s:textfield key="userBank" cssClass="form-control required"/>

                    <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
                    <s:actionerror cssClass="red"/>
                    <s:actionmessage cssClass="green"/>
                    <s:a action="index" namespace="/">
                        <i class=" glyphicon glyphicon-arrow-left"></i> <s:text name="back.to.welcome"/>
                    </s:a>
                </s:form>
            </div>
        </div>
    </div>
</div>

</body>

</html>