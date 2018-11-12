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
        url:"/sys/login",
        contentType : "application/json; charset=UTF-8",
        dataType: "json",
        data:JSON.stringify(obj),
        success:function (result) {
            if(result.rspCode==="200"){
                localStorage.setItem("userAll",result.body);
                localStorage.setItem("user",user);
                localStorage.setItem("token",result.body.token);
                localStorage.setItem("userName",result.body.userName);
             //   alert("登录成功,获取到token准备跳转"+localStorage.getItem("token"));
                //window.location.href ="home";
               /* $.ajax({
                    async:false,
                    headers:{
                        "token":localStorage.getItem("token"),
                       "Accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*!/!*;q=0.8",
                        "Cache-Control":"max-age=0",
                        "Upgrade-Insecure-Requests": "1"
                    },
                    type: "get",
                    url:"/to/home",
                    //contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                    //dataType: "text/html",
                    success:function (msg) {

                    }
                });*/
                window.location.href ="home";
            }else {
                $("#msg").html(result.msg)
            }
        }
    });
    return false;
});