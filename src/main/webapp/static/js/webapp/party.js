console.log("party.js");

$(document).ready(function(){
    console.log("party.js - ready");

    var $dateInput = $('input[name=partyDate]');
    var $addPresentBtn = $("#addPresent");
    makeButtonForModal($addPresentBtn);

    $dateInput.datetimepicker({
        format: dateFormat,
        sideBySide: true
    });

    $addPresentBtn.click(function (e) {
        addPresentModal();
    });

});