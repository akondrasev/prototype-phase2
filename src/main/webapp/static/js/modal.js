function presentModal(partyId){

    $.ajax({
        url:"ajax/mostWantedPresentForParty.jsp",
        type: "POST",
        dataType: 'json',
        data: {"partyId":partyId},
        success:function(response){
            console.log(response);

            var modalTitle = "Current present";

            var pictureUrl = pictureBaseUrl + response.pictureUrl;
            console.log(pictureUrl);

            var modalContent = "";
            //modalContent += "<div class'row'><div class='col-xs-6 col-md-3'>";
            modalContent += "<img src='" + pictureUrl + "' class='thumbnail present-img'/>";
            //modalContent += "</div></div>";

            makeModal(modalTitle, modalContent);

        }
    });

}

function userModal(personId) {
    $.ajax({
       url:"ajax/userData.jsp",
        type: "POST",
        dataType: "json",
        data: {"personId":personId},
        success: function (response) {
            var modalTitle = "User data";
            var modalContent = "Name: " + response.userName;
            makeModal(modalTitle, modalContent);
        }
    });

}

function makeModal(title, content, defaultBtnFunction){
    var $primaryBtn = $(".modal .btn-primary");
    var $title = $(".modal .modal-title");
    var $content = $(".modal .modal-body");

    $content.html("");
    $title.html("");

    $primaryBtn.show();

    if(defaultBtnFunction == null){
        $primaryBtn.hide();
    }

    $title.html(title);
    $content.html(content);

}