function refreshCode1() {
    $("#Code")[0].src = "administer/checkCode?" + new Date().getTime();
}

function findBack() {
    let email = prompt("please input your Email ?");
    if (email == null) return;
    var newPassWord;
    $.post("administer/findEmail",{email:email},function (data) {
        if (data.res){
            newPassWord = prompt("please input your new password...");
            $.post("Member/changePassword",{password:newPassWord},function (data) {
                alert("password has been changed...")
                location.href = "login.html";
            });

        }
        else {
            let b = confirm("the email is not exist, do you want to try it again...");
            if (b) {
                findBack();
            }
        }
    });
}

$(function () {
    var flag5 = false;

    // var cookies = document.cookie;
    //
    // var c_start = cookies.indexOf("msg=\"");
    // if (c_start != -1) {
    //     c_start += 4;
    //     c_end = cookies.indexOf(";\"",c_start);
    //     if (c_end != 1) c_end = cookies.length
    //     alert(cookies.substring(c_start,c_end));
    // }



    $("#checkCode").blur(function () {
        var val = $(this).val();
        var tmp = $("#errorCheckcode");
        tmp.addClass("has-feedback");
        $.post("administer/check",{Code:val},function (data) {
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

    $("#form_login").submit(function () {
        if (flag5) {
            $.post("administer/login",$(this).serialize(),function (data) {
                if (data.res == true) {
                    location.href = "home.html";
                }
                else {
                    $("#msg").text(data.msg);
                }
            })
        }
        return false;
    })
});