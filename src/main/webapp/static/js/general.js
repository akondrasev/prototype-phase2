console.log("general.js");

var userId = null;

$(document).ready(function(){
    console.log("general.js - ready");

    var $navbarFixedTop = $(".navbar-fixed-top");
    var $intro = $(".intro");

    userId = $("#user-id-span").html();
    console.log(userId);

    if($intro.length == 0){
        $("html").addClass("sticky");
    }

    $(window).scroll(function() {
        console.log("transperent navbar on scroll binded");
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

function check(){
    var $inputs = $("input.required");
    $inputs.removeClass("error");
    var isOk = true;

    for(var i = 0; i < $inputs.length; i++){
        var $input = $($inputs[i]);

        if($input.val().length == 0){
            $input.addClass("error");
            isOk = false;
        }
    }
    return isOk;
}