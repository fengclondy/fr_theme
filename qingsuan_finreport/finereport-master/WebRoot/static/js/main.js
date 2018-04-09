/*自定义工具*/
function rdm(a, b) {
    if (arguments.length === 2) {
        return Math.random() * (b - a) + a
    }
    return Math.random() * a
}

// 按钮组切换
$(".btnGroup>button").click(function () {
    $(this).addClass("active").siblings().removeClass("active");
});

// 自定义下拉框功能
// 默认显示第一个
$(".mySelect>.myOption>.text").text(function () {
    return $(this).parent().next(".myOptionBox").children(".myOption:eq(0)").text()
});
// 展开折叠
$(".mySelect>.myOption").click(function () {
    var $mySelect = $(this).parent();
    if($mySelect.hasClass("open")){
        $mySelect.removeClass("open");
    }else{
        $mySelect.addClass("open").children(".myOptionBox").slideDown(180);
    }
    $(".mySelect").not($(this).parent()).removeClass("open")
    return false;
});
// 选中选项
$(".myOptionBox>.myOption").click(function () {
    $(this).addClass("selected").siblings().removeClass("selected")
        .parent().slideUp()
        .prev().children(".text").text($(this).text())
        .parents(".mySelect").removeClass("open")
});

$(".output>.dropDown").click(function () {
    $(this).next().toggle();
    return false;
});

// 点击其他部位折叠下拉框以及打开的列表
$("body").click(function () {
    $(".mySelect").removeClass("open");
    /*白底页面的输出菜单折叠*/
    $(".output").children("ul").hide();
});








