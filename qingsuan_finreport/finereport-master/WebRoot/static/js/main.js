/*自定义工具*/
function rdm(a, b) {
    if (arguments.length === 2) {
        return Math.random() * (b - a) + a
    }
    return Math.random() * a
}
$(".btnGroup>button").click(function () {
    $(this).addClass("active").siblings().removeClass("active");
})