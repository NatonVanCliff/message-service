<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Message Service</title>
    <link type="text/css" href="resources/css/style.css" rel="stylesheet"/>
    <link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="resources/css/bootstrap-theme.min.css" rel="stylesheet"/>
</head>
<body class="body">
<div class="container">
    <%--header panel--%>
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <c:url value="/messages" var="messages"/>
                        <a href="${messages}">Home</a>
                    </li>
                    <li class="active">
                        <c:url value="/admin" var="admin"/>
                        <a href="${admin}">Admin</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right logout">
                    <li>
                        <c:url value="/j_spring_security_logout" var="logoutUrl"/>
                        <a href="${logoutUrl}"><i class="glyphicon glyphicon-off"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="page-header">
        <h3>User managment</h3>
    </div>

    <div class="errorMessage">${message}</div>

    <%--main panel--%>
    <div class="row">
        <%--Users--%>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Users</h3>
                </div>

                <div clas="panel-body">
                    <div class="toolbar">
                        <a href="#" data-toggle="modal" data-target="#adUser"><i class="glyphicon glyphicon-plus"></i></a>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>E-mail</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="index">
                            <tr>
                                <td>${index.firstName}</td>
                                <td>${index.lastName}</td>
                                <td>${index.email}</td>
                                <td>${index.username}</td>
                                <c:if test="${index.roleId == 0}">
                                    <td>User</td>
                                </c:if>

                                <c:if test="${index.roleId == 1}">
                                    <td>Admin</td>
                                </c:if>
                                <td>
                                    <c:if test="${index.id != 0}">
                                        <c:if test="${index.roleId == 0}">
                                            <c:url value="/addAdminRole?userId=${index.id}" var="addAdminRoleUrl"/>
                                            <a href="${addAdminRoleUrl}"><i class="glyphicon glyphicon-king"></i></a>
                                        </c:if>

                                        <c:if test="${index.roleId == 1}">
                                            <c:url value="/deleteAdminRole?userId=${index.id}" var="deleteAdminRoleUrl"/>
                                            <a href="${deleteAdminRoleUrl}"><i class="glyphicon glyphicon-pawn"></i></a>
                                        </c:if>

                                        <c:url value="/deleteUser?userId=${index.id}" var="deleteUserUrl"/>
                                        <a href="${deleteUserUrl}"><i class="glyphicon glyphicon-trash"></i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- add user window--%>
<div class="modal fade" id="adUser" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content custom-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addUserLabel">Add user</h4>
            </div>
            <div class="modal-body">
                <form method="POST" action="<c:url value='/addUser'/>">
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Login" name='username'>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" placeholder="Password" name='password'>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="First name" name='firstName'>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Last name" name='lastName'>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Email" name='email'>
                    </div>
                    <div class="form-group" >
                        <button class="btn btn-default" type="submit" value="submit">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="resources/js/jquery-2.1.4.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>