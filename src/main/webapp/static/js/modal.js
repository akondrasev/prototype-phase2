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

            var modalContent = "<img src='" + pictureUrl + "'/>";

            makeModal(modalTitle, modalContent);

        }
    });

}

function userModal(userId) {
    $.ajax({
       url:"ajax/userData.jsp",
        type: "POST",
        dataType: "json",
        data: {"userId":userId},
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