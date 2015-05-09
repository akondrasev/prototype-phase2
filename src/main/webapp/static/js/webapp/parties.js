console.log("parties.js");

$(document).ready(function(){
    console.log("parties.js - ready");

    $("#parties-link").addClass("active");

    $(".party-link").click(function(e){
        presentModal($(this).attr("id"));
    });

    $(".user-link").click(function(e){
        userModal($(this).attr("id"));
    });
});