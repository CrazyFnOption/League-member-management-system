
function refreshCode() {
    //document.getElementById("Code").src = "checkCodeServlet?" + new Date().getTime();
    $("#Code")[0].src = "administer/checkCode?" + new Date().getTime();
}

$(function () {
    var flag = false,flag1 = false,flag2 = false,flag3 = false,flag4 = false,flag5 = false;
    $("#name").blur(function () {
        var nickname = $(this).val();
        var tmp = $("#errorUser");
        // 下面这个就是关于中文的正则表达式
        var patten = /^([\u4E00-\u9FA5]{2,10})|\w{3,10}$/;
        flag1 = patten.test(nickname);
        tmp.addClass("has-feedback");

        if (!flag1){
            $("#s1").text("用户名非法字符，允许3-20个字符");
            $("#id1").hide();
            $("#id2").show();
            tmp.removeClass("has-success");
            tmp.addClass("has-error");
            refreshCode();
            $("#checkCode").val("");
            $("#errorCheckcode").removeClass("has-error").addClass("has-success")
            flag5 = false;

            $("#id9").hide();
            $("#id10").show();
            //$(this).focus();
            //alert("昵称输入错误，要么2个以上的中文，要么3个以上的字母，不能出现非法字符");
            $("#name").val("");
        }
        else {
            $("#s1").text("");
            $("#id2").hide();
            $("#id1").show()
            tmp.removeClass("has-error");
            tmp.addClass("has-success");
        }
    });

    $("#Sdept").blur(function () {
        var patten = /^([\u4E00-\u9FA5]{2,8})|\w{3,10}$/;
        var tmp = $("#errorMajor");
        var major = $(this).val();
        flag2 = patten.test(major);
        tmp.addClass("has-feedback");
        if (!flag2) {
            $("#id3").hide();
            $("#id4").show();
            tmp.removeClass("has-success");
            tmp.addClass("has-error");
            $("#s2").text("格式错误,不要非法字符,2个以上的字符!");
            refreshCode();
            $("#checkCode").val("");
            $("#id9").hide();
            $("#id10").show();
            $("#errorCheckcode").removeClass("has-error").addClass("has-success")
            flag5 = false;
            //$(this).focus();
            //alert("专业输入错误，要么2个以上的中文，要么3个以上的字母，不能出现非法字符");
            $("#Sdept").val("");
        }
        else {
            $("#s2").text("");
            $("#id4").hide();
            $("#id3").show()
            tmp.removeClass("has-error");
            tmp.addClass("has-success");
        }
    });

    $("#Email").blur(function () {
        var patten = /^\w{3,20}\@\w{3,20}\.\w+$/
        var val = $(this).val();
        flag3 = patten.test(val);
        var tmp = $("#errorEmail");
        tmp.addClass("has-feedback");
        if (!flag3) {
            $("#id5").hide();
            $("#id6").show();
            tmp.removeClass("has-success");
            tmp.addClass("has-error");
            $("#s3").text("请按照邮箱的格式来输入！");
            refreshCode();
            $("#checkCode").val("");
            $("#id9").hide();
            $("#id10").show();
            $("#errorCheckcode").removeClass("has-error").addClass("has-success")
            flag5 = false;
            //$(this).focus();
            //alert("邮箱格式错误，请再次输入，如若再有疑问，请联系管理员");
            $("#Email").val("");

        }
        else {
            $("#s3").text("");
            $("#id6").hide();
            $("#id5").show()
            tmp.removeClass("has-error");
            tmp.addClass("has-success");
            $.post("administer/checkEmail",{email:val},function (data) {
                if(data.exist) {
                    flag = false;
                    $("#id5").hide();
                    $("#id6").show();
                    tmp.removeClass("has-success");
                    tmp.addClass("has-error");
                    $("#s3").text("该邮箱已经被注册过，请更换");
                }
                else {
                    flag = true;
                    $("#s3").text("");
                    $("#id6").hide();
                    $("#id5").show()
                    tmp.removeClass("has-error");
                    tmp.addClass("has-success");
                }
            })
        }
    });

    $("#password").blur(function () {
        var patten = /^\w{6,20}$/
        var val = $(this).val();
        flag4 = patten.test(val);
        var tmp = $("#errorPassword");
        tmp.addClass("has-feedback");
        if (!flag4) {
            $("#id7").hide();
            $("#id8").show();
            tmp.removeClass("has-success");
            tmp.addClass("has-error");
            $("#s4").text("密码要6个以上的字符");
            $("#id9").hide();
            $("#id10").show();
            $("#errorCheckcode").removeClass("has-error").addClass("has-success")
            flag5 = false;
            refreshCode();
            $("#checkCode").val("");
            //$(this).focus();
            //alert("密码格式错误，请再次输入，不允许非法字符如若再有疑问，请联系管理员");
            $("#password").val("");

        }
        else {
            $("#s4").text("");
            $("#id8").hide();
            $("#id7").show()
            tmp.removeClass("has-error");
            tmp.addClass("has-success");
        }
    });

    $("#checkCode").blur(function () {
        var val = $(this).val();
        var tmp = $("#errorCheckcode");
        tmp.addClass("has-feedback");
        $.post("administer/checkcc",{Code:val},function (data) {
            if (data.res) {
                $("#s5").text("");
                flag5 = true;
                $("#id10").hide();
                $("#id9").show()
                tmp.removeClass("has-error");
                tmp.addClass("has-success");
            }
            else {

                $("#s5").text("验证码输入错误哦!");
                flag5 = false;
                $("#id9").hide();
                $("#id10").show();
                tmp.removeClass("has-success");
                tmp.addClass("has-error");

                //alert(data.msg);
                $(this).focus();
                refreshCode();
                $("#checkCode").val("");
            }
        })
    });

    $("#form").submit(function () {
        alert("点进来")
        if (flag1 && flag2 && flag && flag3 && flag4 && flag5) {
            alert("开始注册了...")
            $("#btn").attr("disabled", "disabled");
            $("#btn_c").attr("disabled", "disabled");
            $.post("administer/register",$(this).serialize(),function (data) {
                if (data.res == true) {
                    location.href = "register_ok.html";
                }
                return true;
            });
            return false;
        }
    })

    $("#cancel").click(function () {
        location.href = "login.html";
    })
})
