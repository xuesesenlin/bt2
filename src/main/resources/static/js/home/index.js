$(document).ready(function() {
    $('#side-menu').show();
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