var bodyScale = parent.bodyScale;
var colors = [
    '#00adef',
    '#faad14',
    '#73d13d',
    '#b37feb',
    '#ff7875',
    '#5cdbd3',
    '#ff85c0',
    '#ffd591',
    '#eaff8f',
    '#d3adf7',
    '#91d5ff',
    '#ffa39e',
    '#87e8de',
    '#ffadd2',
    '#adc6ff',
    '#fffb8f',
    '#b7eb8f',
    '#ffbb96',
    '#d9f7be',
    '#ffd6e7'
];
var barWidth = 18 * bodyScale;
var pieLabelOut = {
    color: '#fff',
    normal: {
        position: 'outer'
    }
};
//所有图表的公共属性
var opt_com = {
    color: colors,
    textStyle: {
        fontFamily: 'PingFang SC, microsoft yahei,微软雅黑, sans-serif',
        color: '#fff',
        fontSize: 12 * bodyScale
        // width:'120%'
    },
    legend: {
        textStyle: {
            fontSize: 10 * bodyScale,
            color: '#fff' //图例白色,全局样式不能影响到
        },
        itemWidth: 11 * bodyScale,
        itemHeight: 11 * bodyScale,
        top: '3%'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'line'      // 默认为直线，可选为：'line' | 'shadow'
        }

    },
    grid: {
        bottom: "15%" //底边留空
    }
};

//直角坐标系坐标轴
var axis_com = {
    axisLabel: { //标签名称
        fontSize: 11 * bodyScale
    },
    nameTextStyle: { //坐标轴名称
        fontSize: 11 * bodyScale
    },
    nameGap: 5 * bodyScale, //坐标轴名称距离
    axisTick: { //小刻度线
        show: false
    },
    axisLine: { //坐标轴
        lineStyle: {
            color: "#369"
        }
    },
    splitLine: { //分割线
        lineStyle: {
            color: "#369"
        }
    }
};

//条形图(水平)公共属性
var opt_bar_horizon = {};
//opt_bar_horizon写在里面是为了不让后面的对象覆盖opt_com
$.extend(true, opt_bar_horizon, opt_com, {
    xAxis: $.extend({
        type: 'value'
    }, axis_com),
    yAxis: $.extend({
        type: 'category'
    }, axis_com)
    //这里写此类图表其他属性
});

//条形图(竖直)公共属性
var opt_bar_vertical = {};
$.extend(true, opt_bar_vertical, opt_com, {
    xAxis: $.extend({
        type: 'category'
    }, axis_com),
    yAxis: $.extend({
        type: 'value'
    }, axis_com)
    //这里写此类图表其他属性
});

//折线图公共属性
var opt_line = {};
$.extend(true, opt_line, opt_com, {
    xAxis: $.extend({
        type: 'category'
    }, axis_com),
    yAxis: $.extend({
        type: 'value'
    }, axis_com),
    //这里写此类图表其他属性

});

//饼图公共属性
var opt_pie = {};
$.extend(true, opt_pie, opt_com,
    {
        legend: {
            orient: 'vertical',
            right: '3%',
            top: 'middle'
        },
        tooltip: {
            show: true,
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        animationType: 'scale',
        animationEasing: 'elasticOut',
        animationDelay: function (idx) {
            return Math.random() * 200;
        }

    });

//散点图公共属性
var opt_scatter = {};
$.extend(true, opt_scatter, opt_com, {
    xAxis: $.extend({
        type: 'category'
    }, axis_com),
    yAxis: $.extend({
        type: 'value'
    }, axis_com),

    //这里写此类图表其他属性

});

function scatterSymbolSize(data) {
    return Math.sqrt(data[2]) * bodyScale / 2;
}

function lineAreaStyle(index) {
    return {
        color: {
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
                offset: 0, color: colors[index] // 0% 处的颜色
            }, {
                offset: 1, color: 'rgba(0,173,239,.06)' // 100% 处的颜色
            }]
        }
    }

}

