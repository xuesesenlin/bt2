$(document).ready(function() {

    $('#side-menu').show();

    var doughnutData = [
        {
            value: 300,
            color: "#a3e1d4",
            highlight: "#1ab394",
            label: "服务器"
        },
        {
            value: 50,
            color: "#dedede",
            highlight: "#1ab394",
            label: "台式机"
        },
        {
            value: 100,
            color: "#A4CEE8",
            highlight: "#1ab394",
            label: "笔记本电脑"
        }
    ];

    var doughnutOptions = {
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 2,
        percentageInnerCutout: 45, // This is 0 for Pie charts
        animationSteps: 100,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false
    };

    var ctx = document.getElementById("doughnutChart").getContext("2d");
    var DoughnutChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);

    var polarData = [
        {
            value: 300,
            color: "#a3e1d4",
            highlight: "#1ab394",
            label: "服务器"
        },
        {
            value: 140,
            color: "#dedede",
            highlight: "#1ab394",
            label: "台式机"
        },
        {
            value: 200,
            color: "#A4CEE8",
            highlight: "#1ab394",
            label: "小型机"
        }
    ];

    var polarOptions = {
        scaleShowLabelBackdrop: true,
        scaleBackdropColor: "rgba(255,255,255,0.75)",
        scaleBeginAtZero: true,
        scaleBackdropPaddingY: 1,
        scaleBackdropPaddingX: 1,
        scaleShowLine: true,
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 2,
        animationSteps: 100,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false
    };
    var ctx = document.getElementById("polarChart").getContext("2d");
    var Polarchart = new Chart(ctx).PolarArea(polarData, polarOptions);

});
//<!--跳转页面-->
function cli(a){
    $.ajax({
        url:'../publics/'+a,
        cache:false,
        success:function(data){
            $('.ld').empty();
            $('.ld').html(data);
        }
    }).done(function(d){
    }).fail(function() {
        alert('维护中');
    })
    .always(function() {
    });
}