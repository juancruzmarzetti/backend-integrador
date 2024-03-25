window.addEventListener('load', function () {
   //Buscamos y obtenemos el formulario donde están
   //los datos que el usuario pudo haber modificado de la película
   const formulario = document.querySelector('#update_pelicula_form');
   formulario.addEventListener('submit', function (event) {

       let pacienteId = document.querySelector('#paciente_id').value;
       //creamos un JSON que tendrá los datos de la película
       //a diferencia de una película nueva en este caso enviamos el ID
       //para poder identificarla y modificarla para no cargarla como nueva

       const formData = {
           id: document.querySelector('#paciente_id').value,
           nombre: document.querySelector('#nombre').value,
           apellido: document.querySelector('#apellido').value,
           domicilio: {
                 id: document.querySelector('#paciente_id').value,
                 calle: document.querySelector('#calle').value,
                 numero: document.querySelector('#numero').value,
                 localidad: document.querySelector('#localidad').value,
                 provincia: document.querySelector('#provincia').value,
           },
           dni: document.querySelector('#dni').value,
           fechaDeAlta: document.querySelector('#fecha-de-alta').value,
           usuario: document.querySelector('#usuario').value,
           password: document.querySelector('#password').value
       };
       //invocamos utilizando la función fetch la API películas con el método PUT
       //que modificará la película que enviaremos en formato JSON
       const url = '/pacientes/actualizar';
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
         const url = '/pacientes'+"/"+id;
         const settings = {
             method: 'GET'
         }
         fetch(url,settings)
         .then(response => response.json())
         .then(data => {
             let paciente = data;
             document.querySelector('#paciente_id').value = paciente.id;
             document.querySelector('#nombre').value = paciente.nombre;
             document.querySelector('#apellido').value = paciente.apellido;
             document.querySelector('#calle').value = paciente.domicilio.calle;
             document.querySelector('#numero').value = paciente.domicilio.numero;
             document.querySelector('#localidad').value = paciente.domicilio.localidad;
             document.querySelector('#provincia').value = paciente.domicilio.provincia;
             document.querySelector('#dni').value = paciente.dni;
             document.querySelector('#fecha-de-alta').value = paciente.fechaDeAlta;
             document.querySelector('#usuario').value = paciente.usuario;
             document.querySelector('#password').value = paciente.password;

             //el formulario por default está oculto y al editar se habilita
             document.querySelector('#div_pelicula_updating').style.display = "block";
         }).catch(error => {
             alert("Error: " + error);
             console.log(error);
         })
   }