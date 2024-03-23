window.addEventListener('load', function () {
   //Buscamos y obtenemos el formulario donde están
   //los datos que el usuario pudo haber modificado de la película
   const formulario = document.querySelector('#update_pelicula_form');
   formulario.addEventListener('submit', function (event) {

       let odontologoId = document.querySelector('#odontologo_id').value;
       //creamos un JSON que tendrá los datos de la película
       //a diferencia de una película nueva en este caso enviamos el ID
       //para poder identificarla y modificarla para no cargarla como nueva

       const formData = {
           id: document.querySelector('#odontologo_id').value,
           matricula: document.querySelector('#matricula').value,
           nombre: document.querySelector('#nombre').value,
           apellido: document.querySelector('#apellido').value,
       };
       //invocamos utilizando la función fetch la API películas con el método PUT
       //que modificará la película que enviaremos en formato JSON
       const url = '/odontologos/actualizar';
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
         const url = '/odontologos'+"/"+id;
         const settings = {
             method: 'GET'
         }
         fetch(url,settings)
         .then(response => response.json())
         .then(data => {
             let odontologo = data;
             document.querySelector('#odontologo_id').value = odontologo.id;
             document.querySelector('#matricula').value = odontologo.matricula;
             document.querySelector('#nombre').value = odontologo.nombre;
             document.querySelector('#apellido').value = odontologo.apellido;
             //el formulario por default está oculto y al editar se habilita
             document.querySelector('#div_pelicula_updating').style.display = "block";
         }).catch(error => {
             alert("Error: " + error);
             console.log(error);
         })
   }