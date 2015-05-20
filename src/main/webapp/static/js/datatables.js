console.log("datatables.js");

$(document).ready(function(){
    console.log("datatables.js - ready");

    $.extend( $.fn.dataTable.defaults, {
        "searching": false,
        "ordering": false,
        paging: false,
        info:false,
        ajax:{
            dataSrc:"",
            type: "POST"
        },
        serverSide: true
    } );

    var partiesTable = $('#parties');
    var presentsTable = $('#presents');

    /*
    present table
     */
    if(presentsTable != null){
        presentsTable.dataTable({
            "ajax":{
                url:"ajax/presentsForParty.jsp",
                "data": function ( data ) {
                    data.partyId = partyId;
                }
            },
            "columns": [
                { "data": "presentName", className:"btn btn-info present-link", render:renderTablePresentLink},
                { "data": "presentId", className:"btn btn-danger remove-present-link", render:renderTablePresentRemoveLink}
            ]
        });
        $("thead", presentsTable).hide();

        presentsTable.on("draw.dt", function(){
            var removePresentLink = $(".remove-present-link",presentsTable);
            var presentLink = $(".present-link",presentsTable);

            makeButtonForModal(presentLink);

            removePresentLink.click(function(e){
                e.preventDefault();

                function onRemoveSuccess(msg){
                    $.notify(msg);
                    presentsTable.fnDraw();
                }
                deletePresentAjax($("i", this).attr("id"), onRemoveSuccess);
            });

            presentLink.click(function(e){
                e.preventDefault();
                presentModal($(this).attr("id"));
            });
        });
        global.presentsTable = presentsTable;
    }


    /*
     party table
     */
    if(partiesTable != null){
        partiesTable.dataTable({
            "ajax":{
                url:"ajax/getUserParties.jsp"
            },
            scrollX: "100%",
            "columns": [
                { "data": "partyName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name", render:renderTablePartyLink },
                { "data": "partyAddress", title:"<i class='glyphicon glyphicon-pencil'></i> Address" },
                { "data": "partyDate", title:"<i class='glyphicon glyphicon-calendar'></i> Date" },
                { "data": "partyId", title:"<i class='glyphicon glyphicon-remove-sign'></i> Delete?", "render": renderTableRemoveLink}
            ]
        } );

        partiesTable.on("draw.dt", function(){
            var removePartyLink = $(".remove-party-link",partiesTable);
            var presentLink = $(".present-link",partiesTable);
            var userLink = $(".user-link",partiesTable);

            makeButtonForModal(presentLink);
            makeButtonForModal(userLink);

            removePartyLink.click(function(e){
                //TODO ajax method
                console.log("remove action");
            });

            presentLink.click(function(e){
                e.preventDefault();
                presentModal($(this).attr("id"));
            });

            userLink.click(function(e){
                e.preventDefault();
                userModal($(this).attr("id"));
            });
        });

        global.partiesTable = partiesTable;
    }

});


function renderTablePresentRemoveLink(data, type, row){
    return "<i id='"+ row.presentId + "' class='glyphicon glyphicon-remove'></i>";
}

function renderTablePresentLink(data, type, row){
    return "<small><i class='glyphicon glyphicon-eur'></i></small>" + row.presentCost + " - " + row.presentName;
}

function renderTableRemoveLink(data, type, row){
    return "<a href='#' id=" + data + " class='remove-party-link'><i class='glyphicon glyphicon-remove'></i> remove</a>";
}
function renderTablePartyLink(data, type, row){
    return "<a href='#' id=" + row.partyId + " class='present-link'><i class='glyphicon glyphicon-eye-open'></i> "+data+"</a>";
}