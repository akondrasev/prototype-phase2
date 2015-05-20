function presentModal(presentId){

    $.ajax({
        url:"ajax/presentInfoAjax.jsp",
        type: "POST",
        dataType: 'json',
        data: {"presentId":presentId},
        success:function(response){
            var present = response;
            var modalTitle = "Present " + present.presentName;

            var pictureUrl = pictureBaseUrl + present.pictureUrl;

            var modalContent = "";
            modalContent += "<img src='" + pictureUrl + "' class='thumbnail present-img'/>";
            modalContent += "<h1><small>Cost: </small> $" + present.presentCost + "</h1>";
            modalContent += "<button class='btn btn-success' id='vote'><span class='badge' id='votes'><b>0</b></span><br/> <i class='glyphicon glyphicon-heart'></i> Vote</button>";

            makeModal(modalTitle, modalContent);

            var voteBtn = $(".btn#vote");

            function redrawVotes(votes){
                $("#votes b").html(votes);
            }

            function afterVoteSuccess(msg){
                $.notify(msg, "success");
                getVotesForPresentAjax(present.presentId, redrawVotes);
            }

            getVotesForPresentAjax(present.presentId, redrawVotes);

            voteBtn.click(function (e) {
                voteForPresentAjax(present.presentId, afterVoteSuccess);
            });

        }
    });

}

function userModal(personId) {

    function drawUserModal(userJson){
        var modalTitle = "User data";
        var modalContent = "<h2><small>Name: </small>" + userJson.userName + "</h2>";
        modalContent += "<h2><small>Email: </small>" + userJson.userEmail + "</h2>";
        makeModal(modalTitle, modalContent);
    }

    getUserInfoAjax(personId, drawUserModal);

}


function addPresentModal(){
    var modalTitle = "Add Present";
    var modalContent = "";

    modalContent += "<form class='form-horizontal'><fieldset>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentName' class='control-label col-lg-4'>Name:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='text' name='presentName' id='presentName' class='form-control required' placeholder='Present #1'/>";
    modalContent += "</div></div>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentCost' class='control-label col-lg-4'>Cost:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='integer' name='presentCost' id='presentCost' class='form-control required' placeholder='$1000'/>";
    modalContent += "</div></div>";

    modalContent += "<div class='form-group'>";
    modalContent += "<label for='presentPictureUrl' class='control-label col-lg-4'>Picture Url:</label>";
    modalContent += "<div class='col-lg-6'>";
    modalContent += "<input type='text' name='presentPictureUrl' id='presentPictureUrl' class='form-control' placeholder='ex.com/image.png'/>";
    modalContent += "</div></div>";

    modalContent += "</fieldset></form>";


    function savePresent(){

        if(!check($("#myModal"))){
            return false;
        }

        var data = {
            "presentName":$("#presentName").val(),
            "presentCost":$("#presentCost").val(),
            "presentPictureUrl":$("#presentPictureUrl").val()
        };

        function afterPresentSavedSuccess(msg){
            $.notify(msg, "success");
            $("#close-btn", "#myModal").click();
            getPartyPresentsAjax(partyId, $.parsePresent);
        }

        savePresentAjax(data, afterPresentSavedSuccess);
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
            var isOk = defaultBtnFunction();
            if(isOk){
                $primaryBtn.unbind("click");
            }
        });
    }

    $title.html(title);
    $content.html(content);

}