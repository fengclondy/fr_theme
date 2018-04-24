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
function canvasAll(obj,val){
	
	var canvas = document.getElementById(obj);
	var ctx  = canvas.getContext("2d");
	var oRange = Number(val);
	
	if(oRange < 3){
		$("#"+obj).siblings(".chartBodyNumber").find("div").addClass("colorLow");
		$("#"+obj).siblings(".chartBodyNumber").find("div").text("低");
	}
	else if(oRange >= 3 && oRange < 7){
		$("#"+obj).siblings(".chartBodyNumber").find("div").addClass("colorMiddle");
		$("#"+obj).siblings(".chartBodyNumber").find("div").text("中");
	}
	else{
		$("#"+obj).siblings(".chartBodyNumber").find("div").addClass("colorHigh");
		$("#"+obj).siblings(".chartBodyNumber").find("div").text("高");
	}
	var M = Math;
	var Sin = M.sin;
	var Cos = M.cos;
	var Sqrt = M.sqrt;
	var Pow = M.pow;
	var PI = M.PI;
	var Round = M.round;
	
	var oW = 0;
	var oH = 0;
	if(screen.width == 1920){
		oW = canvas.width = 160;
		oH = canvas.height = 160;
	}
	else{
		oW = canvas.width = 130;
		oH = canvas.height = 130;	
	}
	
	// 线宽
	var lineWidth = 0.8
	
	// 大半径
	var r = (oW / 2);
	var cR = r - 8*lineWidth;
	
	ctx.beginPath();
	
	ctx.lineWidth = lineWidth;
	
	// 水波动画初始参数
	var axisLength = 2*r - 16*lineWidth;  // Sin 图形长度
	var unit = axisLength / 8; // 波浪宽
	var range = .4 // 浪幅
	var nowrange = range;  
	var xoffset = 8*lineWidth; // x 轴偏移量
	var data = ~~(oRange) / 10;   // 数据量
	var sp = 0; // 周期偏移量
	var nowdata = 0;
	var waveupsp = 0.007; // 水波上涨速度
	
	// 圆动画初始参数
	var arcStack = [];  // 圆栈
	var bR = r-8*lineWidth;
	var soffset = -(PI/2); // 圆动画起始位置
	var circleLock = true; // 起始动画锁
	
	// 获取圆动画轨迹点集
	for(var i = soffset; i< soffset + 2*PI; i+=1/(8*PI)) {
	  arcStack.push([
	    r + bR * Cos(i),
	    r + bR * Sin(i)
	  ])
	}
	
	var cStartPoint = arcStack.shift();  // 圆起始点
	
	ctx.strokeStyle = "#1c86d1";
	ctx.moveTo(cStartPoint[0],cStartPoint[1]);
	render();  // 开始渲染
	
	function drawSine () {
	  ctx.beginPath();
	  ctx.save();
	  var Stack = []; // 记录起始点和终点坐标
	  for (var i = xoffset; i<=xoffset + axisLength; i+=20/axisLength) {
	    var x = sp + (xoffset + i) / unit;
	    var y = Sin(x) * nowrange;
	
	    var dx = i;
	
	    var dy = 2*cR*(1-nowdata) + (r - cR) - (unit * y);
	    
	    ctx.lineTo(dx, dy);
	    Stack.push([dx,dy])
	  }
	
	  // 获取初始点和结束点
	  var startP = Stack[0]
	  var endP = Stack[Stack.length - 1]
	
	  ctx.lineTo(xoffset + axisLength,oW);
	  ctx.lineTo(xoffset,oW);
	  ctx.lineTo(startP[0], startP[1])
	  ctx.fillStyle = "#1c86d1";
	  ctx.fill()
	  ctx.restore();
	}
	
	function drawText () {
	  ctx.globalCompositeOperation = 'source-over';
	  
	  var size = 0.6*cR;
	  ctx.font = size + 'px Microsoft Yahei';
	  //var txt = (nowdata.toFixed(2)*10).toFixed(1);
	  var ex = /^\d+$/;//整数
	  if(ex.test(val)){
	  	val = val + ".0";
	  }
	  var fonty = r + size/50;
	  var fontx = r - size * 0.8; 
	  ctx.fillStyle = "#FFEC3D";
	  ctx.fillText(val, fontx, fonty);
	}

	function render () {
	  ctx.clearRect(0,0,oW,oH);
	
	  if (circleLock) {
	    if (arcStack.length) {
	      var temp = arcStack.shift();
	      ctx.lineTo(temp[0],temp[1])
	      ctx.stroke();
	    } else {
	      circleLock = false;
	      ctx.lineTo(cStartPoint[0],cStartPoint[1])
	      ctx.stroke();
	      arcStack = null;
	
	      ctx.globalCompositeOperation = 'destination-over';
	      ctx.beginPath();
	      ctx.lineWidth = lineWidth;
	      ctx.arc(r,r, bR, 0, 2*PI, 1);
	
	      ctx.beginPath();  
	      ctx.save();
	      ctx.arc(r,r, r-16*lineWidth, 0, 2*PI, 1);
	      ctx.restore()
	      ctx.clip();
	
	      ctx.fillStyle = "#1c86d1";
	    }
	  } else {
	    // 开始水波动画
	    // 控制波幅
	    if (data >= 0.85) {
	      if (nowrange > range/4) {
	        var t = range * 0.01;
	        nowrange -= t;   
	      }
	    } else if (data <= 0.1) {
	      if (nowrange < range*1.5) {
	        var t = range * 0.01;
	        nowrange += t;   
	      }
	    } else {
	      if (nowrange <= range) {
	        var t = range * 0.01;
	        nowrange += t;   
	      }      
	
	      if (nowrange >= range) {
	        var t = range * 0.01;
	        nowrange -= t;
	      }
	    }
	
	    if((data - nowdata) > 0) {
	      nowdata += waveupsp;      
	    }
	
	    if((data - nowdata) < 0){
	      nowdata -= waveupsp
	    }
	
	    sp += 0.07;
	    drawSine();
	    drawText();  
	  }
	  requestAnimationFrame(render);
	}
}
