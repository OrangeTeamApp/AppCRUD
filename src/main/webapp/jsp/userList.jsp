<%@ page import="model.User" %>
<%@ taglib prefix = "adminInfo" uri = "/WEB-INF/library.tld"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Table</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/3.2.0/css/font-awesome.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style><%@include file="/css/style.css"%></style>
</head>

<body>

<div id="message">
</div>

<div class = "addNewUser">
    <a href="/jsp/addUser.jsp" class="btn btn-success" ><span>Add new user</span></a>
</div>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Users</b></h2>
                    </div>
                </div>
            </div>
            <adminInfo:PrintUsersTable/>
        </div>
    </div>
</div>


<<div id="deleteUserModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Delete User</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" onclick="deleteThis()" value="Delete">
                </div>
        </div>
    </div>
</div>


<script>
    function deleteThis(){
        var userId = $('#deleteUserModal').data('id');

        $.ajax({
            type: "DELETE",
            url: '/deleteUser',
            contentType: "application/json",
            data: JSON.stringify({ id: userId }),
            success: function() {
                window.location.reload();
            }
        });
    }

    $(document).on("click", ".open-DeleteUserModal", function () {
        var id = $(this).data('id');
        $('#deleteUserModal').data('id', id);
    });

</script>
</body>
</html>