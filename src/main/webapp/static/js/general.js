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

    $(".party-link").click(function(e){
        presentModal($(this).attr("id"));
    });

    $(".user-link").click(function(e){
        userModal($(this).attr("id"));
    });
});

function getUserPartiesAjax(userId, onSuccess){
    //TODO ajax call
}

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

function deletePresentAjax(presentId, onSuccess){
    $.ajax({
        url:"ajax/deletePresent.jsp",
        type:"POST",
        data:{
            "presentId":presentId
        },
        success:function(response){
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function savePresentAjax(data, onSuccess){
    $.ajax({
        url:"ajax/savePresent.jsp",
        data: data,
        type:"POST",
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function getPartyPresentsAjax(partyId, onSuccess){
    $.ajax({
        url:"ajax/presentsForParty.jsp",
        data:{
            "partyId":partyId
        },
        type:"POST",
        dataType:"json",
        success:function(response){
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function getUserInfoAjax(personId, onSuccess){
    $.ajax({
        url:"ajax/userData.jsp",
        type: "POST",
        dataType: "json",
        data: {"personId":personId},
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function voteForPresentAjax(presentId, onSuccess){
    $.ajax({
        url:"ajax/vote.jsp",
        type:"POST",
        data:{
            "presentId":presentId
        },
        success:function(response){
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function getVotesForPresentAjax(presentId, onSuccess){
    $.ajax({
        url:"ajax/getVotes.jsp",
        type:"POST",
        data:{
            "presentId":presentId
        },
        success:function(response){
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function makeButtonForModal(button){
    //data-toggle="modal" data-target="#myModal"
    button.attr("data-toggle","modal");
    button.attr("data-target","#myModal");
}

function check(context){
    var errorClass = "error";
    var $inputs = null;

    if(context == null){
        $inputs = $("input");
    } else {
        $inputs = $("input", context);
    }

    $inputs.removeClass(errorClass);
    var isOk = true;

    for(var i = 0; i < $inputs.length; i++){
        var $input = $($inputs[i]);
        var alarm = "";

        if($input.hasClass("required") && $input.val().length == 0){
            $input.addClass(errorClass);
            alarm += "Must be filled. ";
            isOk = false;
        }

        if($input.attr("type") == 'integer'){
            var value = $input.val();
            if(!$.isNumeric(value)){
                $input.addClass(errorClass);
                alarm += "Must be an Integer. ";
                isOk = false;
            }
        }

        if(alarm != ""){
            $input.notify(alarm);
        }
    }
    return isOk;
}