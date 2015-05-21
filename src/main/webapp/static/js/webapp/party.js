console.log("party.js");

$(document).ready(function(){
    console.log("party.js - ready");

    var $dateInput = $('input[type=date]');
    var $addPresentBtn = $("#addPresent");
    var $addGuestBtn = $("#addGuest");
    makeButtonForModal($addPresentBtn);
    makeButtonForModal($addGuestBtn);

    $dateInput.datetimepicker({
        format: dateFormat,
        sideBySide: true
    });

    $addPresentBtn.click(function (e) {
        addPresentModal();
    });

    $addGuestBtn.click(function (e) {
        addGuestsModal();
    });

});