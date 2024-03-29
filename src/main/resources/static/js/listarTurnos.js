//El evento load se ejecuta al cargar la página que muestra la lista de películas
window.addEventListener('load', function () {
     //con fetch invocamos a la API de peliculas con el método GET
     //nos devolverá un JSON con una colección de películas
     const url = '/turnos/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
     //recorremos la colección de odontologos del JSON
        for(turno of data){

           //por cada película armaremos una fila de la tabla
           //cada fila tendrá un ID que luego nos permitirá
           //borrar la fila si eliminamos la película

           var table = document.getElementById("peliculaTable");
           var peliculaRow = table.insertRow();
           let tr_id = 'tr_' + turno.id;
           peliculaRow.id = tr_id;



           //por cada película creamos un botón delete que
           //agregaremos en cada fila para poder eliminar la misma
           //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
           //de llamar a la API para eliminar una película

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ turno.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           //por cada película creamos un botón que muestra el ID
           //y que al hacerle clic invocará a la función de JavaScript findBy
           //que se encargará de buscar la película que queremos modificar
           //y mostrar los datos de la misma en un formulario.

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_'+turno.id+'\"' +
                                     ' type="button" onclick="findBy('+turno.id+')"' +
                                     ' class="btn btn-info btn_id">' +
                                     turno.id +
                                     '</button>';



           //armamos cada columna de la fila
           //como primera columna pondremos el botón modificar
           //luego los datos de la película
           //como última columna, el botón eliminar
           peliculaRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_odontologo\">' + turno.odontologo + '</td>' +
                 '<td class=\"td_paciente\">' + turno.paciente + '</td>' +
                 '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                 '<td class=\"td_hora\">' + turno.hora + '</td>' +
                 '<td>' + deleteButton + '</td>';
       };
    })
})