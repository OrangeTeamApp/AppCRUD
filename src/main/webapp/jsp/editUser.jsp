<%@ page import="model.User" %>
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
   <h1>Edit user</h1>
</div>
<div class="container">
   <div class="card">
      <div class="card-body">
         <form method="POST" action="${pageContext.request.contextPath}/editUser" >

            <div class=" form-group row">
               <label for="email" id="email" class="col-sm-2 col-form-label">Email</label>
               <div class="col-sm-7">
                  <input type="text" class="form-control" name="email" value=${userToEdit.email} required>
               </div>
            </div>

            <div class="form-group row">
               <label for="firstName" id="firstName" class="col-sm-2 col-form-label">First name</label>
               <div class="col-sm-7">
                  <input type="text" class="form-control" name="firstName" value=${userToEdit.firstName} required>
               </div>
            </div>

            <div class="form-group row">
               <label for="lastName" id="lastName" class="col-sm-2 col-form-label">Last name</label>
               <div class="col-sm-7">
                  <input type="text" class="form-control" name="lastName" value=${userToEdit.lastName} required>
               </div>
            </div>

            <div class="form-group row">
               <label for="dateOfBirth" id="dateOfBirth" class="col-sm-2 col-form-label">Birthday</label>
               <div class="col-sm-7">
                  <input type="date" class="form-control" name="dateOfBirth" value=${userToEdit.birthDate} required>
               </div>
            </div>


            <div class="form-group row">
               <label for="dateOfBirth" class="col-sm-2 col-form-label"></label>
               <div class="col-sm-7">
                  <button type="submit" class="pull-right btn btn-primary">Submit</button>
               </div>
            </div>

         </form>
      </div>
   </div>
</div>
</body>
</html>

