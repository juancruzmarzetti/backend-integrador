//El evento load se ejecuta al cargar la página que muestra la lista de películas
window.addEventListener('load', function () {
     //con fetch invocamos a la API de peliculas con el método GET
     //nos devolverá un JSON con una colección de películas
     const url = '/odontologos/all';
     const settings = {
       method: 'GET'
     }
     fetch(url,settings)
     .then(response => response.json())
     .then(data => {
     //recorremos la colección de odontologos del JSON
        for(odontologo of data){

           //por cada película armaremos una fila de la tabla
           //cada fila tendrá un ID que luego nos permitirá
           //borrar la fila si eliminamos la película

           var table = document.getElementById("peliculaTable");
           var peliculaRow =table.insertRow();
           let tr_id = 'tr_' + odontologo.id;
           peliculaRow.id = tr_id;



           //por cada película creamos un botón delete que
           //agregaremos en cada fila para poder eliminar la misma
           //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
           //de llamar a la API para eliminar una película

           let deleteButton = '<button' +
                                     ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                     ' type="button" onclick="deleteBy('+ odontologo.id +')"' +
                                     'class="btn btn-danger btn_delete">' +
                                     '&times' +
                                     '</button>';

           //por cada película creamos un botón que muestra el ID
           //y que al hacerle clic invocará a la función de JavaScript findBy
           //que se encargará de buscar la película que queremos modificar
           //y mostrar los datos de la misma en un formulario.

           let updateButton = '<button' +
                                     ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                     ' type="button" onclick="findBy('+ odontologo.id +')"' +
                                     ' class="btn btn-info btn_id">' +
                                     odontologo.id +
                                     '</button>';



           //armamos cada columna de la fila
           //como primera columna pondremos el botón modificar
           //luego los datos de la película
           //como última columna, el botón eliminar
           peliculaRow.innerHTML = '<td>' + updateButton + '</td>' +
                 '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                 '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' +
                 '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' +
                 '<td>' + deleteButton + '</td>';
       };
    })
})
