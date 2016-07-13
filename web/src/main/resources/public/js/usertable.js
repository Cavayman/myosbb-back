$(document).ready(show);
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
                    .attr('id', data[index].userId)
                    .click(deleteById);
                var sbutton = $('<button>')
                    .attr("class", "btn btn-warning btn-sm").text("Delete").attr("bid", data[index].userId.toString());

                var tdButton = $('<td>').append(button);
                var tdSButton = $('<td>').append(sbutton);
                tr.append(tdId).append(tdfName).append(tdlName).append(tdbDate)
                    .append(tdEmail).append(tdPhoneN).append(tdPass).append(tdGender).append(tdButton).append(tdSButton);
                $('#tablecont').append(tr);
            }
        }
    })
};

function deleteById() {
    $.ajax({
        url: "user/" + $(this).attr('id'),

        type: 'DELETE',
    })
    show();
};