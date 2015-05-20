console.log("general.js");

var userId = null;

$(document).ready(function(){
    console.log("general.js - ready");

    $.extend( $.fn.dataTable.defaults, {
        "searching": false,
        "ordering": false,
        paging: false,
        info:false
    } );

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

    var partiesTable = $('#parties');
    var presentsTable = $('#presents');

    presentsTable.dataTable({
        "ajax":{
            url:"ajax/presentsForParty.jsp",
            dataSrc:""
        },
        "serverSide": true,
        "columns": [
            { "data": "presentName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name" },
            { "data": "presentId", title:"<i class='glyphicon glyphicon-remove-sign'></i> Delete?"}
        ]
    });

    partiesTable.dataTable({
        "ajax":{
            url:"ajax/getUserParties.jsp",
            dataSrc:""
        },
        scrollX: "100%",
        "serverSide": true,
        "columns": [
            { "data": "partyName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name", render:renderTablePartyLink },
            { "data": "partyAddress", title:"<i class='glyphicon glyphicon-pencil'></i> Address" },
            { "data": "partyDate", title:"<i class='glyphicon glyphicon-calendar'></i> Date" },
            { "data": "partyId", title:"<i class='glyphicon glyphicon-remove-sign'></i> Delete?", "render": renderTableRemoveLink}
        ]
    } );

    partiesTable.on("draw.dt", function(){
        var removePartyLink = $(".remove-party-link",partiesTable);
        var presentLink = $(".present-link",partiesTable);
        var userLink = $(".user-link",partiesTable);

        makeButtonForModal(presentLink);
        makeButtonForModal(userLink);

        removePartyLink.click(function(e){
            //TODO ajax method
            console.log("remove action");
        });

        presentLink.click(function(e){
            presentModal($(this).attr("id"));
        });

        userLink.click(function(e){
            userModal($(this).attr("id"));
        });
    });

    $(".present-link").click(function(e){
        presentModal($(this).attr("id"));
    });

    $(".user-link").click(function(e){
        userModal($(this).attr("id"));
    });
});

function renderTableRemoveLink(data, type, row){
    return "<a href='#' id=" + data + " class='remove-party-link'><i class='glyphicon glyphicon-remove'></i> remove</a>";
}
function renderTablePartyLink(data, type, row){
    return "<a href='#' id=" + row.partyId + " class='present-link'><i class='glyphicon glyphicon-eye-open'></i> "+data+"</a>";
}

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