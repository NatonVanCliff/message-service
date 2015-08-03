<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Message Service</title>
	<link type="text/css" href="resources/css/style.css" rel="stylesheet"/>
	<link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet"/>
	<link type="text/css" href="resources/css/bootstrap-theme.min.css" rel="stylesheet"/>
	<link type="text/css" href="resources/css/tablesorter-dark.css" rel="stylesheet"/>
</head>
<body class="body">
<div class="container">
	<%--header panel--%>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active">
						<c:url value="/messages" var="messages"/>
						<a href="${messages}">Home</a>
					</li>
					<li>
						<c:if test="${user.roleId == 1}">
							<c:url value="/admin" var="admin"/>
							<a href="${admin}">Admin</a>
						</c:if>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right" style="padding-right: 55px">
					<li>
						<c:url value="/j_spring_security_logout" var="logoutUrl"/>
						<a href="${logoutUrl}"><i class="glyphicon glyphicon-off"></i></a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="page-header">
		<h3>Home</h3>
	</div>

	<%--main panel--%>
	<div class="row">
		<%--Messages--%>
		<div class="col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Messages</h3>
				</div>


				<div clas="panel-body">
					<table id="messages" class="table table-striped tablesorter-dark">
						<thead>
						<tr>
							<th>Sender</th>
							<th>Date</th>
							<th>Subject</th>
							<th>Action</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${messageList}" var="index">
							<tr>
								<td>${index.sender.username}</td>
								<td><fmt:formatDate type="time" value="${index.date}" pattern="dd/MM/yyyy HH:mm"/></td>
								<td>${index.subject}</td>
								<td>
									<a href="#" data-toggle="modal" data-target="#showMessage"
									   data-sender="${index.sender.username}"
									   data-date="<fmt:formatDate type="time" value="${index.date}" pattern="dd/MM/yyyy HH:mm"/>"
									   data-subject="${index.subject}"
									   data-text="${index.text}">
										<i class="glyphicon glyphicon-hand-left"></i>
									</a>
									<c:url value="/deleteMsg?msgId=${index.id}" var="deleteMsgUrl"/>
									<a href="${deleteMsgUrl}"><i class="glyphicon glyphicon-trash"></i>
									</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<%--Contacs--%>
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Contacts</h3>
				</div>


				<div clas="panel-body">
					<div class="toolbar">
						<a href="#" data-toggle="modal" data-target="#addContact"><i class="glyphicon glyphicon-plus"></i></a>
					</div>

					<table class="table table-striped" dadata-show-header="false">
						<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${contactList}" var="index">
							<tr>
								<td>${index.contact.firstName}</td>
								<td>${index.contact.lastName}</td>
								<td>
								<td>
									<a href="#" data-toggle="modal" data-target="#sendMessage"
									   data-userid="${user.id}"
									   data-contactid="${index.contact.id}"><i class="glyphicon glyphicon-envelope"></i>
									</a>
									<c:url value="/deleteContact?userId=${user.id}&contactId=${index.contact.id}" var="removeContactUrl"/>
									<a href="${removeContactUrl}"><i class="glyphicon glyphicon-trash"></i></a>
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

<%-- show message window--%>
<div class="modal fade" id="showMessage" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content custom-modal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="showMessageLabel">Message details</h4>
			</div>
			<div class="modal-body">
				<form>
					<fieldset disabled>
						<div class="form-group">
							<label for="sender">Sender</label>
							<input type="text" class="form-control" id="sender">
						</div>
						<div class="form-group">
							<label for="date">Date</label>
							<input class="form-control" id="date">
						</div>
						<div class="form-group">
							<label for="subject">Subject</label>
							<input class="form-control" id="subject">
						</div>
						<div class="form-group">
							<label for="text">Text</label>
                                <textarea type="text" class="form-control" id="text"
										  name='message'
										  path="message" rows="4"
										  value=''></textarea>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- add contact window--%>
<div class="modal fade" id="addContact" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="addContactLabel">Add contact</h4>
			</div>
			<div class="modal-body">
				<form action="<c:url value='/addContact'/>" method='POST'>
					<div class="form-group">
						<select class="form-control" id="selectContact" name="contactId">
							<c:forEach var="index" items="${userList}">
								<option value="${index.id}">${index.username}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="userId" value="${user.id}">
					</div>
					<button type="submit" class="btn btn-default" value="submit">Add</button>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- send message window--%>
<div class="modal fade" id="sendMessage" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Send message</h4>
			</div>
			<div class="modal-body">
				<form action="<c:url value='/sendMessage'/>" method='POST'>
					<input type="hidden" class="form-control" id="userid", name="senderId">
					<input type="hidden" class="form-control" id="contactid", name="recipientId">
					<div class="form-group">
						<input type="text" class="form-control" name="subject">
					</div>
					<div class="form-group">
                        <textarea type="text" class="form-control" name='text' value='' rows="4">
						</textarea>
					</div>
					<button type="submit" class="btn btn-default" value="submit">Send</button>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="resources/js/jquery-2.1.4.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.tablesorter.min.js"></script>
<script src="resources/js/popup.js"></script>

<script>
	$(document).ready(function(){
		$("#messages").tablesorter();
	});
</script>
</body>
</html>