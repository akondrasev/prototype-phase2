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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-cosmo.css"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/general.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/general.js"></script>


    <decorator:head/>
</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top visible">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<s:url action="index" namespace="/"/>"><i class="glyphicon glyphicon-gift"></i> <s:text name="logo"/></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<s:url action="index" namespace="/"/>"><i class="glyphicon glyphicon-home"></i> <s:text name="home"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span class="glyphicon glyphicon-user"></span> <s:property value="user.userName"/> <span class="badge"><s:property value="user.newsCount"/></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <s:if test="!user.isGuest">
                            <li><a href="#"><i class="glyphicon glyphicon-list-alt"></i> <s:text name="invites"/> <span class="badge"><s:property value="user.invitesCount"/></span></a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-list-alt"></i> <s:text name="requests"/> <span class="badge"><s:property value="user.requestsCount"/></span></a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-user"></i> <s:text name="guests"/> <span class="badge"><s:property value="user.guestsCount"/></span></a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="glyphicon glyphicon-cog"></i> <s:text name="edit.profile"/> </a></li>
                            <li><a href="<s:url action="logout" namespace="/"/>"><i class="glyphicon glyphicon-off"></i> <s:text name="logout"/></a></li>
                        </s:if>
                        <s:if test="user.isGuest">
                            <li><a href="<s:url action="login" namespace="/"/>"><i class="glyphicon glyphicon-user"></i> <s:text name="login"/></a></li>
                            <li><a href="<s:url action="registration" namespace="/"/>"><i class="glyphicon glyphicon-registration-mark"></i> <s:text name="registration"/></a></li>
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

</body>
</html>