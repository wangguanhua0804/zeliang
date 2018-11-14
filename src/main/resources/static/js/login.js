$("#loginForm").submit(function () {
    var user = $("#user").val();
    var password = $("#password").val();
    var obj={
        "user":user,
        "password":password
    };
    //obj=$("#loginForm").serialize();
    $.ajax({
        async:false,
        type: "post",
        url:"/api/sys/login",
        contentType : "application/json; charset=UTF-8",
        dataType: "json",
        data:JSON.stringify(obj),
        success:function (result) {
            if(result.rspCode==="200"){
                /*  //本来想用一个对象存放所有信息,存放失败
                var sysUser={
                    "id":result.body.id,
                    "user":result.body.user,
                    "userName":result.body.userName,
                    "password":result.body.password,
                    "idCard":result.body.idCard,
                    "token":result.body.token,
                    "mobile":result.body.mobile,
                    "role":result.body.role.role_name,
                }
                localStorage.setItem("sysUser",sysUser);
                alert(user.token);*/
                localStorage.setItem("user",user);
                localStorage.setItem("userId",result.body.id);
                localStorage.setItem("token",result.body.token);
                localStorage.setItem("mobile",result.body.mobile);
                localStorage.setItem("idCard",result.body.idCard);
                localStorage.setItem("role_name",result.body.role.role_name);
                localStorage.setItem("userName",result.body.userName);
                window.location.href ="home.html";
            }else {
                $("#msg").html(result.msg)
            }
        }
    });
    return false;
});