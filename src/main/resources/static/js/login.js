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
                localStorage.setItem("userAll",result.body);
                localStorage.setItem("user",user);
                localStorage.setItem("userId",result.body.id);
                localStorage.setItem("token",result.body.token);
                localStorage.setItem("userName",result.body.userName);
                window.location.href ="home.html";
            }else {
                $("#msg").html(result.msg)
            }
        }
    });
    return false;
});