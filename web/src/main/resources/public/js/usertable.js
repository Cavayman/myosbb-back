$(document).ready(show());
function show() {
    $('#tablecont').html("");
    $.ajax({
        url: "user/",
        type: 'GET',
        success: function (data) {
            for (var index in data) {
                var tr = $('<tr>');
                var tdId = $('<td>').text(data[index].userId);
                var tdfName = $('<td>').text(data[index].firstName).attr("class", "small");
                var tdlName = $('<td>').text(data[index].lastName).attr("class", "small");
                var tdbDate = $('<td>').text(new Date(data[index].birthDate).toLocaleDateString(['ban', 'id'])).attr("class", "small");
                var tdEmail = $('<td>').text(data[index].email).attr("class", "small");
                var tdPhoneN = $('<td>').text(data[index].phoneNumber).attr("class", "small");
                var tdPass = $('<td>').text(data[index].password).attr("class", "small");
                var tdGender = $('<td>').text(data[index].gender).attr("class", "small");
                var button = $('<button>')
                    .attr("class", "btn btn-success btn-sm")
                    .text("Edit").attr("bid", data[index].userId.toString())

                var sbutton = $('<button>')
                    .attr("class", "btn btn-warning btn-sm").text("Delete").attr("bid", data[index].userId.toString())
                    .attr('id', data[index].userId)
                    .click(deleteUserById);;

                var tdButton = $('<td>').append(button);
                var tdSButton = $('<td>').append(sbutton);
                tr.append(tdId).append(tdfName).append(tdlName).append(tdbDate)
                    .append(tdEmail).append(tdPhoneN).append(tdPass).append(tdGender).append(tdButton).append(tdSButton);
                $('#tablecont').append(tr);
            }
        }
    })
};

function deleteUserById() {
    $.ajax({
        url: "user/" + $(this).attr('id'),

        type: 'DELETE',
    })
    show();
};

$('#userAdd').click(addUser());

function addUser(){
    if($('#firstNameId').val().length>0) {
        var json = {
            "firstName": $('#firstNameId').val(),
            "lastName": $('#lastName').val(),
            "birthDate": new Date(),
            "email": $('#uemail').val(),
            "phoneNumber": $('#uNumber').val(),
            "password": $('#firstNameId').val(),
            "gender": $('#lastName').val(),
        };

        $.ajax({
            url: "user/",
            type: 'POST',
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function () {
                console.log("OK");
            }
        });
        show();
    }
}
