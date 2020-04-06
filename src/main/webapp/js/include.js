
$(function () {
    $.post("header.html",{},function (data) {
        $("#_header").html = data;
    })
})
