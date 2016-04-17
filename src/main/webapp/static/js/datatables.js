console.log("datatables.js");

$(document).ready(function(){
    console.log("datatables.js - ready");

    $.extend( $.fn.dataTable.defaults, {
        searching: false,
        ordering: false,
        paging: false,
        info:false,
        ajax:{
            dataSrc:"",
            type: "POST"
        },
        serverSide: true,
        oLanguage: {
            sEmptyTable: ""
        },
        colReorder: true,
        fixedColumns: {
            leftColumns: 2
        },
        dom: 'Zlfrtip',
        scrollX: true,
        "colResize": {
            "tableWidthFixed": false,
            "rtl": true,
            "resizeCallback": function(column) {
                console.log(column);
                return false;
            }
        }
    } );

    var partiesTable = $('#parties');
    var presentsTable = $('#presents');
    var guestsTable = $("#guests");
    var yourPartiesTable = $("#participating-parties");



    /*
     party table
     */
    if(yourPartiesTable != null){
        yourPartiesTable.dataTable({
            "ajax":{
                url:"ajax/partiesWithUser.jsp"
            },
            scrollX: "100%",
            "columns": [
                { "data": "partyName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name", render:renderTablePartyLink },
                { "data": "partyAddress", title:"<i class='glyphicon glyphicon-pencil'></i> Address" },
                { "data": "partyDate", title:"<i class='glyphicon glyphicon-calendar'></i> Date" },
                { "data": "partyId", title:"<i class='glyphicon glyphicon-remove-sign'></i> Delete?", "render": renderTableRemoveLink}
            ]
        } );

        yourPartiesTable.on("draw.dt", function(){
            var removePartyLink = $(".remove-party-link",yourPartiesTable);
            var userLink = $(".user-link",yourPartiesTable);

            makeButtonForModal(userLink);

            removePartyLink.click(function(e){
                var id = $(this).attr("id");

                function onSuccess(msg){
                    $.notify(msg);
                    yourPartiesTable.fnDraw();
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

        global.yourPartiesTable = yourPartiesTable;
    }


    /*
     guests
     */
    if(guestsTable != null){
        var columns = [];
        if(readonly){
            columns = [
                { "data": "userName", className:"btn btn-info user-link table-btn", render:renderTableUserLink}
            ]
        } else {
            columns = [
                { "data": "userName", className:"btn btn-info user-link table-btn", render:renderTableUserLink},
                { "data": "userId", className:"btn btn-danger remove-user-link", render:renderTableUserRemoveLink}
            ]
        }
        guestsTable.dataTable({
            "ajax":{
                url:"ajax/getGuests.jsp",
                "data": function ( data ) {
                    data.partyId = partyId;
                }
            },
            "columns": columns
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
        var columns = [];
        if(readonly){
            columns = [
                { "data": "presentName", className:"btn btn-info present-link table-btn", render:renderTablePresentLink},
            ]
        } else {
            columns = [
                { "data": "presentName", className:"btn btn-info present-link table-btn", render:renderTablePresentLink},
                { "data": "presentId", className:"btn btn-danger remove-present-link", render:renderTablePresentRemoveLink}
            ]
        }

        presentsTable.dataTable({
            "ajax":{
                url:"ajax/presentsForParty.jsp",
                "data": function ( data ) {
                    data.partyId = partyId;
                }
            },
            "columns": columns
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

function initInvitesTable(tableId){
    var table = $(tableId);
    if(table != null){
        table.dataTable({
            "ajax":{
                url:"ajax/getInvites.jsp"
            },
            //scrollX: "100%",
            "columns": [
                { "data": "partyId" , render: renderInviteRow}
            ]
        } );

        $('thead', table).hide();


        table.on("draw.dt", function(){
            var acceptBtn = $(".accept-btn",table);
            var declineBtn = $(".decline-btn",table);

            acceptBtn.click(function(e){
                var partyId = $(this).attr("partyId");
                console.log(userId,partyId);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    table.fnDraw();
                }

                var data = {
                    partyId: partyId,
                    isAccepted: true
                }

                removeInviteAjax(data, onSuccess);
            });
            declineBtn.click(function(e){
                var partyId = $(this).attr("partyId");
                console.log(userId,partyId);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    table.fnDraw();
                }

                var data = {
                    partyId: partyId,
                    isAccepted: false
                }

                removeInviteAjax(data, onSuccess);
            });

        });

        global.inviteTable = table;
    }
}

function initRequestsTable(tableId){
    var table = $(tableId);
    if(table != null){
        table.dataTable({
            "ajax":{
                url:"ajax/getRequests.jsp"
            },
            //scrollX: "100%",
            "columns": [
                { "data": "partyId" , render: renderRequestRow}
            ]
        } );

        $('thead', table).hide();


        table.on("draw.dt", function(){
            var acceptBtn = $(".accept-btn",table);
            var declineBtn = $(".decline-btn",table);

            acceptBtn.click(function(e){
                var userId = $(this).attr("userId");
                var partyId = $(this).attr("partyId");
                console.log(userId,partyId);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    table.fnDraw();
                }

                var data = {
                    partyId: partyId,
                    personId:userId,
                    isAccepted: true
                }

                removeRequestAjax(data, onSuccess);
            });
            declineBtn.click(function(e){
                var userId = $(this).attr("userId");
                var partyId = $(this).attr("partyId");
                console.log(userId,partyId);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    table.fnDraw();
                }

                var data = {
                    partyId: partyId,
                    personId:userId,
                    isAccepted: false
                }

                removeRequestAjax(data, onSuccess);
            });

        });

        global.requestsTable = table;
    }
}


function initOpenPartiesTable(tableId){
    var openPartiesTable = $(tableId);
    if(openPartiesTable != null){
        openPartiesTable.dataTable({
            "ajax":{
                url:"ajax/getOpenParties.jsp"
            },
            //scrollX: "100%",
            "columns": [
                { "data": "partyName", title:"<i class='glyphicon glyphicon-list-alt'></i> Name", render:renderTablePartyLink },
                { "data": "partyAddress", title:"<i class='glyphicon glyphicon-pencil'></i> Address" },
                { "data": "partyDate", title:"<i class='glyphicon glyphicon-calendar'></i> Date" },
                { "data": "partyId", title:"<i class='glyphicon glyphicon-plus'></i> Want participate?", "render": renderParticipateLink}
            ]
        } );


        openPartiesTable.on("draw.dt", function(){
            var participateBtn = $(".participate-btn",openPartiesTable);
            var userLink = $(".user-link",openPartiesTable);

            makeButtonForModal(userLink);

            participateBtn.click(function(e){
                var id = $(this).attr("id");
                var icon = $("i", this);
                var thisLink = $(this);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    icon.removeClass("glyphicon-plus");
                    icon.addClass("glyphicon-ok");
                    icon.addClass("green");
                    thisLink.unbind("click");
                    thisLink.attr("disabled", "disabled");
                    //openPartiesTable.fnDraw();
                }

                sendParticipateRequestAjax(id, onSuccess);
            });

            if(userLink != null){
                userLink.click(function(e){
                    e.preventDefault();
                    userModal($(this).attr("id"));
                });
            }
        });

        global.openPartiesTable = openPartiesTable;
    }
}

function initAllUsersTable(tableId){
    var allUsersTable = $(tableId);
    if(allUsersTable != null){
        allUsersTable.dataTable({
            "ajax":{
                url:"ajax/getUsers.jsp",
                data: {
                    partyId: partyId
                }
            },
            scrollX: "100%",
            "columns": [
                { "data": "userName", className:"btn btn-info table-btn add-guest-btn", render:renderTableUserLink },
            ]
        } );

        $("thead", allUsersTable).hide();

        allUsersTable.on("draw.dt", function(){
            var addGuestBtn = $(".add-guest-btn");
            addGuestBtn.click(function(e){

                var icon = $("i", this);
                icon.removeClass("glyphicon-user");
                var thisBtn = $(this);

                function onSuccess(msg){
                    $.notify(msg, "success");
                    icon.addClass("glyphicon-ok");
                    thisBtn.removeClass("btn-info");
                    thisBtn.addClass("btn-success");
                    global.guestsTable.fnDraw();

                    thisBtn.unbind("click");
                }

                addGuestToParty(icon.attr("id"), onSuccess);
            });
        });

        global.allUsersTable = allUsersTable;
    }
}

function renderParticipateLink(data, type, row){
    if(!readonly){
        return "<a id='"+row.partyId+"' class='participate-btn'><i class='glyphicon glyphicon-plus'></i> Participate</a>";
    } else {
        return "<a href='login.jsp'>Login</a>"
    }

}

function renderInviteRow(data, type, row){
    return "<button class='btn btn-info'>" + row.partyName + "</button><button userId='"+row.userId+"' partyId='"+row.partyId+"' class='btn btn-success accept-btn'>Accept</button><button userId='"+row.userId+"' partyId='"+row.partyId+"' class='btn btn-warning decline-btn'>Reject</button>";
}


function renderRequestRow(data, type, row){
    return "<button class='btn btn-info'>" +row.userName+" - " + row.partyName + "</button><button userId='"+row.userId+"' partyId='"+row.partyId+"' class='btn btn-success accept-btn'>Accept</button><button userId='"+row.userId+"' partyId='"+row.partyId+"' class='btn btn-warning decline-btn'>Reject</button>";
}

function renderTableUserRemoveLink(data, type, row){
    return "<i id='"+ row.userId + "' class='glyphicon glyphicon-remove'></i>";
}
function renderTableUserLink(data, type, row){
    return "<small><i id='"+row.userId+"' class='glyphicon glyphicon-user'></i></small>" + row.userName + " - " + row.userEmail;
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