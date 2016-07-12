$(function(){
    $('#tablecont').html("");
    $.ajax({
        url : "user/",
        type : 'GET',
        success : function(data) {
            for ( var index in data) {
                var tr = $('<tr>');
                var tdId = $('<td>').text(data[index].userId);
                var tdfName = $('<td>').text(data[index].firstName).attr("class","small");
                var tdlName = $('<td>').text(data[index].lastName).attr("class","small");
                var tdbDate = $('<td>').text(data[index].birthDate).attr("class","small");
                var tdEmail = $('<td>').text(data[index].email).attr("class","small");
                var tdPhoneN = $('<td>').text(data[index].phoneNumber).attr("class","small");
                var tdPass = $('<td>').text(data[index].password).attr("class","small");
                var tdGender = $('<td>').text(data[index].gender).attr("class","small");
                var button = $('<button>')
                    .attr("class","btn btn-success btn-sm").text("Edit").val("bId",data[index].toString());
                var sbutton = $('<button>')
                    .attr("class","btn btn-warning btn-sm").text("Delete",data[index].toString());

                var tdButton=$('<td>').append(button);
                var tdSButton=$('<td>').append(sbutton);
                tr.append(tdId).append(tdfName).append(tdlName).append(tdbDate)
                    .append(tdEmail).append(tdPhoneN).append(tdPass).append(tdGender).append(tdButton).append(tdSButton);
                $('#tablecont').append(tr);
            }
        }
    });
});

$("#userAdd").click();

function show() {
    var input=$('#focusedInput').val();
    $('#tablecont').html("");
    $.ajax({
        url : "Allcity/getAll-"+input,

        type : 'GET',
        success : function(data) {

            for ( var index in data) {
                var tr = $('<tr>');
                var tdId = $('<td>').text(data[index].id);
                var tdName = $('<td>').text(data[index].name);
                var tdEmail = $('<td>').text(data[index].email);
                var button = $('<button>').attr("class","btn btn-info transfed")
                    .attr("data-toggle","modal")
                    .attr("data-target","#myModal")
                    .attr('id', data[index].id).text("transer");
                button.click(modal);
                var tdButton=$('<td>').append(button);
                tr.append(tdId).append(tdName).append(tdEmail).append(tdButton);
                $('#tablecont').append(tr);
            }
        }
    });
alert('asd');
};