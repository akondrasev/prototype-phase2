console.log("general.js");

var userId = null;

$(document).ready(function(){
    console.log("general.js - ready");

    var $navbarFixedTop = $(".navbar-fixed-top");
    var $intro = $(".intro");

    if(readonly){
        $("input").attr("disabled", "disabled");
        $(".btn").hide();
        $("input[type=checkbox]").hide();
    }

    userId = $("#user-id-span").html();

    if($intro.length == 0){
        $("html").addClass("sticky");
    }

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
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top
            }, 1500, 'easeInOutExpo');
            event.preventDefault();
        });
    });

    if(userId != null && userId != "") {
        setInterval(getRequestsAjax, 2000);
    }
    if(userId != null && userId != "") {
        setInterval(getInvitesAjax, 2000);
    }

    $(".present-link").click(function(e){
        presentModal($(this).attr("id"));
    });

    $(".user-link").click(function(e){
        userModal($(this).attr("id"));
    });

    makeButtonForModal($("#requests"));
    makeButtonForModal( $("#invites"));
    $("#requests").click(function(e){
        newsModal(userId);
    });

    $("#invites").click(function(e){
        invitesModal(userId);
    });
});


function removeInviteAjax(data, onSuccess){
    $.ajax({
        url: "ajax/removeInvite.jsp",
        type: "POST",
        data:data,
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}
function removeRequestAjax(data, onSuccess){
    $.ajax({
        url: "ajax/removeRequest.jsp",
        type: "POST",
        data:data,
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function getRequestsAjax(onSuccess){
    $.ajax({
        url: "ajax/getRequests.jsp",
        type: "POST",
        dataType:"json",
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
            global.requests = response.length;
            calculateNews();
        }
    });
}
function getInvitesAjax(onSuccess){
    $.ajax({
        url: "ajax/getInvites.jsp",
        type: "POST",
        dataType:"json",
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
            global.invites = response.length;
            calculateNews();
        }
    });
}


function calculateNews(){
    var newsCount =  global.requests + global.invites;

    $("#newsCount").html(newsCount);
    $("#invitesCount").html(global.invites);
    $("#requestsCount").html(global.requests);
}

function sendParticipateRequestAjax(partyId, onSuccess){
    $.ajax({
        url: "ajax/sendParticipateRequest.jsp",
        type: "POST",
        data: {
            "partyId": partyId,
            "personId": userId
        },
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function addGuestToParty(personId, onSuccess){
    $.ajax({
        url: "ajax/addGuest.jsp",
        type: "POST",
        data: {
            "partyId": partyId,
            "personId": personId
        },
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
}

function removePartyAjax(partyId, onSuccess){
    $.ajax({
        url: "ajax/removeParty.jsp",
        type: "POST",
        data: {"partyId": partyId},
        success: function (response) {
            if(onSuccess != null){
                onSuccess(response);
            }
        }
    });
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

function deleteGuestFromPartyAjax(personId, onSuccess){
    $.ajax({
        url:"ajax/removeGuest.jsp",
        type: "POST",
        data: {
            "personId":personId,
            "partyId" : partyId
        },
        success: function (response) {
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