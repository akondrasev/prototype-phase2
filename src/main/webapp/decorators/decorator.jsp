<?xml version="1.0" encoding="UTF-8" ?>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Event MANAGER - <decorator:title/></title>

    <%--bootstrap & jquery--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-cosmo.css"/>

    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <%--datepicker--%>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/d004434a5ff76e7b97c8b07c01f34ca69e635d97/src/js/bootstrap-datetimepicker.js"></script>

    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/d004434a5ff76e7b97c8b07c01f34ca69e635d97/build/css/bootstrap-datetimepicker.css" rel="stylesheet">

    <%--main--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/general.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/general.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modal.js"></script>

    <script type="text/javascript">
        var applicationContext = "${pageContext.request.contextPath}";
        var pictureBaseUrl = applicationContext + "/static/img/presents/";

        var dateFormat = "DD.MM.YYYY HH:mm";
    </script>

    <decorator:head/>
</head>
<body>

<span id="user-id-span" class="hidden"><s:property value="user.userId"/></span>
<nav class="navbar navbar-default navbar-fixed-top visible">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<s:url action="index" namespace="/"/>">
                <i class="glyphicon glyphicon-gift"></i> <s:text name="logo"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="home-link">
                    <a href="<s:url action="index" namespace="/"/>">
                        <i class="glyphicon glyphicon-home"></i> <s:text name="home"/>
                    </a>
                </li>
                <li id="parties-link">
                    <a href="<s:url action="parties" namespace="/"/>">
                        <i class="glyphicon glyphicon-th-list"></i> <s:text name="parties"/>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span class="glyphicon glyphicon-user"></span> <s:property value="user.userName"/> <span id="newsCount" class="badge"><s:property value="user.newsCount"/></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <s:if test="!user.isGuest">
                            <li>
                                <a href="#">
                                    <i class="glyphicon glyphicon-list-alt"></i> <s:text name="invites"/>
                                    <span id="invitesCount" class="badge">
                                        <s:property value="user.invitesCount"/>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="glyphicon glyphicon-list-alt"></i> <s:text name="requests"/>
                                    <span id="requestsCount" class="badge">
                                        <s:property value="user.requestsCount"/>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="glyphicon glyphicon-user"></i> <s:text name="guests"/>
                                    <span id="guestsCount" class="badge">
                                        <s:property value="user.guestsCount"/>
                                    </span>
                                </a>
                            </li>
                            <li class="divider"></li>

                            <li>
                                <a href="<s:url action="profile" namespace="/"/>">
                                    <i class="glyphicon glyphicon-cog"></i> <s:text name="edit.profile"/>
                                </a>
                            </li>
                            <li>
                                <a href="<s:url action="logout" namespace="/"/>">
                                    <i class="glyphicon glyphicon-off"></i> <s:text name="logout"/>
                                </a>
                            </li>
                        </s:if>
                        <s:if test="user.isGuest">
                            <li>
                                <a href="<s:url action="login" namespace="/"/>">
                                    <i class="glyphicon glyphicon-user"></i> <s:text name="login"/>
                                </a>
                            </li>
                            <li>
                                <a href="<s:url action="registration" namespace="/"/>">
                                    <i class="glyphicon glyphicon-registration-mark"></i> <s:text name="registration"/>
                                </a>
                            </li>
                        </s:if>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<decorator:body/>

<footer>
    <div class="container">
        <p class="text-center">
            <i class="glyphicon glyphicon-copyright-mark"></i> <s:text name="projectAuthors"/>
        </p>
    </div>
</footer>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                Content
            </div>
            <div class="modal-footer">
                <button id="close-btn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="submit-btn" type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>