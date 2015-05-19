console.log("general.js");

var userId = null;

$(document).ready(function(){
    console.log("general.js - ready");

    var $navbarFixedTop = $(".navbar-fixed-top");
    var $intro = $(".intro");

    userId = $("#user-id-span").html();

    if($intro.length == 0){
        $("html").addClass("sticky");
    }

    console.log("transperent navbar on scroll binded");
    $(window).scroll(function() {
        if ($(".navbar").offset().top > 75) {
            $navbarFixedTop.addClass("transperent");
            $navbarFixedTop.removeClass("visible");
        } else {
            $navbarFixedTop.removeClass("transperent");
            $navbarFixedTop.addClass("visible");
        }
    });

    $(function() {
        $('a.page-scroll').bind('click', function(event) {
            console.log("scroll on click binded");
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top
            }, 1500, 'easeInOutExpo');
            event.preventDefault();
        });
    });

    if(userId != null && userId != "") {
        setInterval(getNewsCounts, 2000);
    }
});

function getNewsCounts() {
    $.ajax({
        url: "ajax/news.jsp",
        type: "POST",
        dataType: "json",
        data: {"personId": userId},
        success: function (response) {
            $("#newsCount").html(response.newsCount);
            $("#invitesCount").html(response.invitesCount);
            $("#requestsCount").html(response.requestsCount);
            $("#guestsCount").html(response.guestsCount);
        }
    });
}

function makeButtonForModal(button){
    //data-toggle="modal" data-target="#myModal"
    button.attr("data-toggle","modal");
    button.attr("data-target","#myModal");
}

function check(){
    var errorClass = "error";
    var $inputs = $("input.required");
    $inputs.removeClass(errorClass);
    var isOk = true;

    for(var i = 0; i < $inputs.length; i++){
        var $input = $($inputs[i]);

        if($input.val().length == 0){
            $input.addClass(errorClass);
            isOk = false;
        }

        if($input.attr("type") == 'integer'){
            var value = $input.val();
            if(!$.isNumeric(value)){
                $input.addClass(errorClass);
                $input.val(value + " (Enter an integer)");
                isOk = false;
            }
        }
    }
    return isOk;
}