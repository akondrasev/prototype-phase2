<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Party</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/party.js"></script>
</head>
<body>

<span id="partyId" class="hidden">${partyId}</span>

<s:div class="container">

    <s:div class="row">
        <s:div class="col-md-3 col-md-offset-0 col-xs-12">
            <s:a action="index" namespace="/" class="btn btn-default btn-lg btn-block">
                <i class="glyphicon glyphicon-arrow-left"></i> <s:text name="back.to.welcome"/>
            </s:a>
        </s:div>
    </s:div>

    <s:div class="row">
        <s:div class="col-md-4 col-md-offset-3 col-xs-12">
            <s:form cssClass="form-login" action="processParty" namespace="/" onsubmit="return check()">

                <legend><h2><s:text name="create.party"/></h2></legend>
                <s:textfield key="partyName" cssClass="form-control required"/>
                <s:textfield key="partyAddress" cssClass="form-control required"/>
                <s:textfield key="partyDate" cssClass="form-control required"/>
                <s:textfield key="partyDefaultMoney" cssClass="form-control required" type="integer"/>
                <s:checkbox key="partyIsOpen"/>

                <s:submit key="submit" class="btn btn-lg btn-primary btn-block"/>
                <s:actionerror cssClass="red"/>
                <s:actionmessage cssClass="green"/>
            </s:form>
        </s:div>

        <div class="col-md-3 col-md-offset-1 col-xs-12">
            <h1><s:text name="manage.presents"/></h1>

            <table class="table table-bordered" id="presents">
            </table>

            <button class="btn btn-default btn-lg btn-block" id="addPresent">
                <i class="glyphicon glyphicon-plus"></i>
            </button>
        </div>
    </s:div>
</s:div>

</body>
</html>
