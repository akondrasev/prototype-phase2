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
        serverSide: true,
        oLanguage: {
            sEmptyTable: ""
        }
    } );

    var partiesTable = $('#parties');
    var presentsTable = $('#presents');
    var guestsTable = $("#guests");


    /*
     guests
     */
    if(guestsTable != null){
        guestsTable.dataTable({
            "ajax":{
                url:"ajax/getGuests.jsp",
                "data": function ( data ) {
                    data.partyId = partyId;
                }
            },
            "columns": [
                { "data": "userName", className:"btn btn-info user-link table-btn", render:renderTableUserLink},
                { "data": "userId", className:"btn btn-danger remove-user-link", render:renderTableUserRemoveLink}
            ]
        });
        $("thead", guestsTable).hide();

        guestsTable.on("draw.dt", function(){
            var removeUserLink = $(".remove-user-link",guestsTable);
            var userLink = $(".user-link" ,guestsTable);

            makeButtonForModal(userLink);

            removeUserLink.click(function(e){
                e.preventDefault();

                function onRemoveSuccess(msg){
                    $.notify(msg);
                    guestsTable.fnDraw();
                }
                deleteGuestFromPartyAjax($("i", $(this)).attr("id"), onRemoveSuccess);
            });

            userLink.click(function(e){
                e.preventDefault();
                userModal($("i", this).attr("id"));
            });
        });

        global.guestsTable = guestsTable;
    }


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
                { "data": "presentName", className:"btn btn-info present-link table-btn", render:renderTablePresentLink},
                { "data": "presentId", className:"btn btn-danger remove-present-link", render:renderTablePresentRemoveLink}
            ]
        });
        $("thead", presentsTable).hide();

        presentsTable.on("draw.dt", function(){
            var removePresentLink = $(".remove-present-link",presentsTable);
            var presentLink = $(".present-link", presentsTable);

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
                presentModal($("i", this).attr("id"));
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
            var partyLink = $(".party-link",partiesTable);
            var userLink = $(".user-link",partiesTable);

            makeButtonForModal(userLink);

            removePartyLink.click(function(e){
                var id = $(this).attr("id");

                function onSuccess(msg){
                    $.notify(msg);
                    partiesTable.fnDraw();
                }

                removePartyAjax(id, onSuccess);
            });

            if(userLink != null){
                userLink.click(function(e){
                    e.preventDefault();
                    userModal($(this).attr("id"));
                });
            }
        });

        global.partiesTable = partiesTable;
    }

});

function renderTableUserRemoveLink(data, type, row){
    return "<i id='"+ row.userId + "' class='glyphicon glyphicon-remove'></i>";
}
function renderTableUserLink(data, type, row){
    return "<small><i id='"+row.userId+"' class='glyphicon glyphicon-user'></i></small>" + row.userName;
}

function renderTablePresentRemoveLink(data, type, row){
    return "<i id='"+ row.presentId + "' class='glyphicon glyphicon-remove'></i>";
}

function renderTablePresentLink(data, type, row){
    return "<small><i id='"+ row.presentId +"' class='glyphicon glyphicon-eur'></i></small>" + row.presentCost + " - " + row.presentName;
}

function renderTableRemoveLink(data, type, row){
    return "<a id=" + data + " class='remove-party-link'><i class='glyphicon glyphicon-remove'></i> remove</a>";
}
function renderTablePartyLink(data, type, row){
    return "<a href='party.jsp?id=" + row.partyId + "' id=" + row.partyId + " class='party-link'><i class='glyphicon glyphicon-eye-open'></i> "+data+"</a>";
}