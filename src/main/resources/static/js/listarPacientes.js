//El evento load se ejecuta al cargar la página que muestra la lista de películas
window.addEventListener('load', function () {
     //con fetch invocamos a la API de peliculas con el método GET
     //nos devolverá un JSON con una colección de películas
     const url = '/pacientes/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
     //recorremos la colección de odontologos del JSON
        for(paciente of data){

           //por cada película armaremos una fila de la tabla
           //cada fila tendrá un ID que luego nos permitirá
           //borrar la fila si eliminamos la película

           var table = document.getElementById("peliculaTable");
           var peliculaRow =table.insertRow();
           let tr_id = 'tr_' + paciente.id;
           peliculaRow.id = tr_id;



           //por cada película creamos un botón delete que
           //agregaremos en cada fila para poder eliminar la misma
           //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
           //de llamar a la API para eliminar una película

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ paciente.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           //por cada película creamos un botón que muestra el ID
           //y que al hacerle clic invocará a la función de JavaScript findBy
           //que se encargará de buscar la película que queremos modificar
           //y mostrar los datos de la misma en un formulario.

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_'+paciente.id+'\"' +
                                     ' type="button" onclick="findBy('+paciente.id+')"' +
                                     ' class="btn btn-info btn_id">' +
                                     paciente.id +
                                     '</button>';



           //armamos cada columna de la fila
           //como primera columna pondremos el botón modificar
           //luego los datos de la película
           //como última columna, el botón eliminar
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