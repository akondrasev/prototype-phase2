console.log("datatables.js");

$(document).ready(function(){
    console.log("datatables.js - ready");

    $.extend( $.fn.dataTable.defaults, {
        "searching": false,
        "ordering": false,
        paging: false,
        info:false
    } );

    var partiesTable = $('#parties');
    var presentsTable = $('#presents');

    presentsTable.dataTable({
        "ajax":{
            url:"ajax/presentsForParty.jsp",
            dataSrc:""
        },
        "serverSide": true,
        "columns": [
            { "data": "presentName", render:renderTablePresentLink},
            { "data": "presentId", render:renderTablePresentRemoveLink}
        ]
    });
    $("thead", presentsTable).hide();

    partiesTable.dataTable({
        "ajax":{
            url:"ajax/getUserParties.jsp",
            dataSrc:""
        },
        scrollX: "100%",
        "serverSide": true,
        "columns": [
            { "data": "partyName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name", render:renderTablePartyLink },
            { "data": "partyAddress", title:"<i class='glyphicon glyphicon-pencil'></i> Address" },
            { "data": "partyDate", title:"<i class='glyphicon glyphicon-calendar'></i> Date" },
            { "data": "partyId", title:"<i class='glyphicon glyphicon-remove-sign'></i> Delete?", "render": renderTableRemoveLink}
        ]
    } );

    presentsTable.on("draw.dt", function(){
        var removePresentLink = $(".remove-present-link",presentsTable);
        var presentLink = $(".present-link",presentsTable);

        makeButtonForModal(presentLink);

        removePresentLink.click(function(e){
            e.preventDefault();

            function onRemoveSuccess(msg){
                $.notify(msg);
                presentsTable.draw();
            }
            deletePresentAjax($(this).attr("id"), onRemoveSuccess);
        });

        presentLink.click(function(e){
            e.preventDefault();
            presentModal($(this).attr("id"));
        });
    });

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
});


function renderTablePresentRemoveLink(data, type, row){
    return "<a href='#' id=" + data + " class='remove-present-link'><i class='glyphicon glyphicon-remove'></i> remove</a>";
}

function renderTablePresentLink(data, type, row){
    return "<a href='#' id=" + row.presentId + " class='present-link'><i class='glyphicon glyphicon-eye-open'></i> "+row.presentName+"</a>";
}

function renderTableRemoveLink(data, type, row){
    return "<a href='#' id=" + data + " class='remove-party-link'><i class='glyphicon glyphicon-remove'></i> remove</a>";
}
function renderTablePartyLink(data, type, row){
    return "<a href='#' id=" + row.partyId + " class='present-link'><i class='glyphicon glyphicon-eye-open'></i> "+data+"</a>";
}