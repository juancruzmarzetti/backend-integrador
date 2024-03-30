window.addEventListener('load', function () {
     const url = '/pacientes/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
        for(paciente of data){

           var table = document.getElementById("peliculaTable");
           var peliculaRow =table.insertRow();
           let tr_id = 'tr_' + paciente.id;
           peliculaRow.id = tr_id;

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ paciente.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_'+paciente.id+'\"' +
                                     ' type="button" onclick="findBy('+paciente.id+')"' +
                                     ' class="btn btn-info btn_id">' +
                                     paciente.id +
                                     '</button>';

           peliculaRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                 '<td class=\"td_apellido\">' + paciente.apellido + '</td>' +
                 '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                 '<td class=\"td_fecha_de_alta\">' + paciente.fechaDeAlta + '</td>' +
                 '<td class=\"td_calle\">' + paciente.domicilio.calle + '</td>' +
                 '<td class=\"td_numero\">' + paciente.domicilio.numero + '</td>' +
                 '<td class=\"td_localidad\">' + paciente.domicilio.localidad + '</td>' +
                 '<td class=\"td_provincia\">' + paciente.domicilio.provincia + '</td>' +
                 '<td>' + deleteButton + '</td>';
       };
    })
})