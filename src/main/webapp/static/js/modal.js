function presentModal(presentId){

    $.ajax({
        url:"ajax/presentInfoAjax.jsp",
        type: "POST",
        dataType: 'json',
        data: {"presentId":presentId},
        success:function(response){
            console.log(response);

            var modalTitle = "Present " + response.presentName;

            var pictureUrl = pictureBaseUrl + response.pictureUrl;

            var modalContent = "";
            modalContent += "<img src='" + pictureUrl + "' class='thumbnail present-img'/>";
            modalContent += " <h1><small>Cost: </small> $" + response.presentCost + "</h1>";

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


function addPresentModal(){
    var modalTitle = "Add Present";
    var modalContent = "";

    modalContent += "<form class='form-horizontal'><fieldset>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentName' class='control-label col-lg-4'>Name:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='text' name='presentName' id='presentName' class='form-control required'/>";
    modalContent += "</div></div>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentCost' class='control-label col-lg-4'>Cost:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='integer' name='presentCost' id='presentCost' class='form-control required'/>";
    modalContent += "</div></div>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentPictureUrl' class='control-label col-lg-4'>Picture Url:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='text' name='presentPictureUrl' id='presentPictureUrl' class='form-control'/>";
    modalContent += "</div></div>";

    modalContent += "</fieldset></form>";


    function savePresent(){

        if(!check($("#myModal"))){
            return false;
        }

        $.ajax({
            url:"ajax/savePresent.jsp",
            data:{
                "presentName":$("#presentName").val(),
                "presentCost":$("#presentCost").val(),
                "presentPictureUrl":$("#presentPictureUrl").val()
            },
            type:"POST",
            success: function (response) {
                $.notify(response, "success");
                $("#close-btn", "#myModal").click();

                $.loadPresentsForParty(partyId);
                return true;
            }
        });
    }

    makeModal(modalTitle, modalContent, savePresent);
}

function makeModal(title, content, defaultBtnFunction){
    console.log("makeModal called");
    var $primaryBtn = $(".modal .btn-primary");
    var $title = $(".modal .modal-title");
    var $content = $(".modal .modal-body");

    $content.html("");
    $title.html("");

    $primaryBtn.show();

    if(defaultBtnFunction == null){
        $primaryBtn.hide();
    } else {
        $primaryBtn.click(function(e){
            var isOk = defaultBtnFunction.call();
            if(isOk){
                $primaryBtn.unbind("click");
            }
        });
    }

    $title.html(title);
    $content.html(content);

}