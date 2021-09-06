<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf" name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Insert title here</title>
    <script>
        function login() {
            var username = $("#memberName").val();
            var password = $("#password").val();

            //csrf
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");


            $.ajax( {
                url:"/user/login",
                type:'POST',
                dataType: "json",
                data: {
                    username : username,
                    password : password
                },
                beforeSend:function(xhr) {
                    xhr.setRequestHeader(header, token);
                }
            }).done(function( data ) {
                if(!data.response.error){
                    alert("로그인 하였습니다.");
                    window.location = "/";
                } else if(data.response.message == 'wrongid'){
                    $("#errMsg").text("잘못된 아이디입니다.");
                    $("#id").val('');
                    $("#password").val('');
                } else if(data.response.message == 'wrongpw'){
                    $("#errMsg").text("잘못된 비밀번호입니다.");
                    $("#password").val('');
                }
            })
                .fail( function( textStatus ) {
                    alert( "Request failed: " + textStatus );
                });

        }
    </script>
</head>
<body>
<form action="/user/login" method="post">
Email address : <input type="text" id="memberName"><br>
Password : <Input type ="password" id="password"><br>
<input type="submit" onclick="login()" value = "login">
</form>

</body>
</html>