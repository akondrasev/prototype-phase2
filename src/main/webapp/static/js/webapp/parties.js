console.log("parties.js");

$(document).ready(function(){
    console.log("parties.js - ready");

    $("#parties-link").addClass("active");

    initOpenPartiesTable("#open-parties");
});