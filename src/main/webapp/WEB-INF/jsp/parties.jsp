<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Parties</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webapp/parties.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th><i class="glyphicon glyphicon-list-alt"></i> Name</th>
                        <th><i class="glyphicon glyphicon-pencil"></i> Address</th>
                        <th><i class="glyphicon glyphicon-calendar"></i> Date</th>
                        <th><i class="glyphicon glyphicon-user"></i> Organizer</th>
                        <th><i class="glyphicon glyphicon-ok"></i>  Want participate?</th>
                    </tr>
                    </thead>

                    <tbody>
                        <s:iterator value="openParties">
                            <tr class="small">
                                <td><a href="#" id="<s:property value="partyId"/>"><i class="glyphicon glyphicon-eye-open"></i> <s:property value="partyName"/></a></td>
                                <td><s:property value="partyAddress"/></td>
                                <td><s:property value="partyDate"/></td>
                                <td><a href="#" id="<s:property value="partyOrganizerId"/>"><i class="glyphicon glyphicon-eye-open"></i> <s:property value="partyOrganizerName"/></a></td>
                                <td><a href="#" id="<s:property value="partyId"/>"><i class="glyphicon glyphicon-plus"></i> Participate</a></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
