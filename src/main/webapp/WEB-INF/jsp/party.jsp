<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Party</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/party.js"></script>
</head>
<body>
<br/>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <div class="well bs-component">
                <s:form cssClass="form-login" action="processParty" namespace="/" method="POST" onsubmit="return check()">

                    <legend><h2><s:text name="create.party"/></h2></legend>
                    <s:textfield key="partyName" cssClass="form-control required"/>
                    <s:textfield key="partyAddress" cssClass="form-control required"/>
                    <s:textfield key="partyDate" cssClass="form-control required"/>
                    <s:textfield key="partyDefaultMoney" cssClass="form-control required"/>
                    <s:checkbox key="partyIsOpen"/>

                    <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
                    <s:actionerror cssClass="red"/>
                    <s:actionmessage cssClass="green"/>
                    <s:a action="index" namespace="/">
                        <i class="glyphicon glyphicon-arrow-left"></i> <s:text name="back.to.welcome"/>
                    </s:a>
                </s:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
