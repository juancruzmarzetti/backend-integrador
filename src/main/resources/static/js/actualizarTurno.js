window.addEventListener('load', function () {
   //Buscamos y obtenemos el formulario donde están
   //los datos que el usuario pudo haber modificado de la película
   const formulario = document.querySelector('#update_pelicula_form');
   formulario.addEventListener('submit', function (event) {

       let turnoId = document.querySelector('#turno_id').value;
       //creamos un JSON que tendrá los datos de la película
       //a diferencia de una película nueva en este caso enviamos el ID
       //para poder identificarla y modificarla para no cargarla como nueva

       const formData = {
           id: document.querySelector('#turno_id').value,
           odontologo: {
                id: document.querySelector('#odontologo_id').value
           },
           paciente: {
                id: document.querySelector('#paciente_id').value
           },
           fecha: document.querySelector('#fecha').value,
           hora: document.querySelector('#horario').value,
       };
       //invocamos utilizando la función fetch la API películas con el método PUT
       //que modificará la película que enviaremos en formato JSON
       const url = '/turnos/actualizar';
       const settings = {
           method: 'PUT',
           headers: {
               'Content-Type': 'application/json',
           },
           body: JSON.stringify(formData)
       }
       fetch(url,settings)
       .then(response => response.json())
   })
})



   //Es la función que se invoca cuando se hace clic
   //sobre el ID de una película del listado
   //se encarga de llenar el formulario con los datos de la película
   //que se desea modificar

   function findBy(id) {
         const url = '/turnos'+"/"+id;
         const settings = {
             method: 'GET'
         }
         fetch(url,settings)
         .then(response => response.json())
         .then(data => {
             let turno = data;
             document.querySelector('#turno_id').value = turno.id;
             document.querySelector('#odontologo_id').value = turno.odontologo;
             document.querySelector('#paciente_id').value = turno.paciente;
             document.querySelector('#fecha').value = turno.fecha;
             document.querySelector('#horario').value = turno.hora;
             //el formulario por default está oculto y al editar se habilita
             document.querySelector('#div_pelicula_updating').style.display = "block";
         }).catch(error => {
             alert("Error: " + error);
             console.log(error);
         })
   }