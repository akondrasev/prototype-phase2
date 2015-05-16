console.log("party.js");

$(document).ready(function(){
    console.log("party.js - ready");

    var $dateInput = $('input[name=partyDate]');

    $dateInput.datetimepicker({
        format: dateFormat
    });

});