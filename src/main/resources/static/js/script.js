$(document).ready(function() {
    $(".top_img").mouseover(function() {
        $(".message_min").css("transform","translateX(413px)");
    });
    $(".top_img").mouseout(function() {
        $(".message_min").css("transform","translateX(-413px)");
    });
    $(".box_out").css("height",$("html").height());
});