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

    $.loadPresentsForParty = function(partyId, onSuccess){
        $.ajax({
            url:"ajax/presentsForParty.jsp",
            data:{
                "partyId":partyId
            },
            type:"POST",
            dataType:"json",
            success:function(response){
                $.parsePresent(response);
                if(onSuccess != null){
                    onSuccess.call();
                }
            }
        });
    }


    $.parsePresent = function (presentsJson){
        $("#presents").html("");
        if(presentsJson.length > 0){
            for(var i=0; i<presentsJson.length; i++){
                var presentId = presentsJson[i].presentId;


                var row = "";
                row += "<tr id="+presentsJson[i].presentId+">";
                row += "<td class='btn btn-lg btn-info watch'><i class='glyphicon glyphicon-eye-open'></i> "+presentsJson[i].presentName+"</td>";
                row += "<td class='btn btn-danger remove'><i class='glyphicon glyphicon-remove-circle'></i></td>";
                row += "</tr>";
                $("#presents").append(row);

                var watchButton = $($("tr > td.btn.watch"));
                var removeBtn = $($("tr > td.btn.remove"));

                makeButtonForModal(watchButton);

                watchButton.click(function (e){
                    presentModal(presentId);
                });

                removeBtn.click(function (e) {
                    $.ajax({
                        url:"ajax/deletePresent.jsp",
                        type:"POST",
                        data:{
                            "presentId":presentId
                        },
                        success:function(response){
                            $.notify(response);
                            $.loadPresentsForParty(partyId);
                        }
                    });
                });
            }
        }

    }

});