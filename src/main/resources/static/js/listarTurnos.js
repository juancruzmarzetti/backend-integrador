window.addEventListener('load', function () {
     const url = '/turnos/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
        for(turno of data){

           var table = document.getElementById("peliculaTable");
           var peliculaRow = table.insertRow();
           let tr_id = 'tr_' + turno.id;
           peliculaRow.id = tr_id;

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ turno.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_'+turno.id+'\"' +
                                     ' type="button" onclick="findBy('+turno.id+')"' +
                                     ' class="btn btn-info btn_id">' +
                                     turno.id +
                                     '</button>';

           peliculaRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_odontologo\">' + turno.odontologo + '</td>' +
                 '<td class=\"td_paciente\">' + turno.paciente + '</td>' +
                 '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                 '<td class=\"td_hora\">' + turno.hora + '</td>' +
                 '<td>' + deleteButton + '</td>';
       };
    })
})