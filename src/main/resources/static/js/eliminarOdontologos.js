function deleteBy(id){
         //con fetch invocamos a la API de películas con el método DELETE
         //pasándole el ID en la URL
         const url = '/odontologos/'+id;
         const settings = {
             method: 'DELETE'
         }
         fetch(url,settings)
         .then(response => response.json())
         //borrar la fila de la película eliminada
         let row_id = "#tr_" + id;
         document.querySelector(row_id).remove();
}