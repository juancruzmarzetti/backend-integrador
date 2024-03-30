window.addEventListener('load', function () {
     const url = '/odontologos/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
        for(odontologo of data){
           var table = document.getElementById("peliculaTable");
           var peliculaRow =table.insertRow();
           let tr_id = 'tr_' + odontologo.id;
           peliculaRow.id = tr_id;

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ odontologo.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';
           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_'+odontologo.id+'\"' +
                                     ' type="button" onclick="findBy('+odontologo.id+')"' +
                                     ' class="btn btn-info btn_id">' +
                                     odontologo.id +
                                     '</button>';
           peliculaRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                 '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' +
                 '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' +
                 '<td>' + deleteButton + '</td>';
       };
    })
})