<%@ page import="com.hanna.model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style><%@include file="/css/style.css"%></style>
</head>
<body>



<div style="text-align: center">
  <h1>Add user</h1>
</div>
<div class="container">
  <div class="card">
    <div class="card-body">
      <form method="POST" action="${pageContext.request.contextPath}/addUser">

        <div class=" form-group row">
          <label for="login" class="col-sm-2 col-form-label">Login</label>
          <div class="col-sm-7">
            <input type="text" id="login" class="form-control" name="login" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="password"  id="password" class="col-sm-2 col-form-label">Password</label>
          <div class="col-sm-7">
            <input id = "pass" type="password" class="form-control password" name="password" required >
          </div>
        </div>

        <div class="form-group row">
          <label for="confirmPassword"  id="confirmPassword" class="col-sm-2 col-form-label">Password Again</label>
          <div class="col-sm-7">
            <input id = "confPass" type="password" class="form-control confirmPassword" name="confirmPassword" >
            <span id='message'></span>
          </div>
        </div>


        <div class=" form-group row">
          <label for="email" id="email" class="col-sm-2 col-form-label">Email</label>
          <div class="col-sm-7">
            <input type="email" class="form-control" name="email" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="firstName" id="firstName" class="col-sm-2 col-form-label">First name</label>
          <div class="col-sm-7">
            <input type="text" class="form-control" name="firstName" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="lastName" id="lastName" class="col-sm-2 col-form-label">Last name</label>
          <div class="col-sm-7">
            <input type="text" class="form-control" name="lastName" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="dateOfBirth" id="dateOfBirth" class="col-sm-2 col-form-label">Birthday</label>
          <div class="col-sm-7">
            <input type="date" class="form-control" name="dateOfBirth" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="role" class="col-sm-2 col-form-label"></label>
          <div class="col-sm-7">
            <button type="submit" class="pull-right btn btn-primary">Submit</button>
          </div>
        </div>

      </form>
    </div>
  </div>
</div>
<script>
  $(document).on("keyup", "#confirmPassword", function () {
    if ($('#password').val() == $('#confirmPassword').val()) {
      $('#message').html('Matching').css('color', 'green');
    } else
      $('#message').html('Not Matching').css('color', 'red');
  });

  $(document).ready(function(){
    $('body').on("keyup",'#confirmPassword', function(){
      console.log('keyed');
    });
  });

  $('#pass, #confPass').on('keyup', function () {
    if ($('#pass').val() == $('#confPass').val()) {
      $('#message').html('Matching').css('color', 'green');
    } else
      $('#message').html('Not Matching').css('color', 'red');
  });

</script>
</body>
</html>

