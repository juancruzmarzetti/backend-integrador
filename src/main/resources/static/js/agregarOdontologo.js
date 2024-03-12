window.addEventListener('load', function () {
    const url = '/odontologos/agregar';
    const formulario = document.querySelector('#form-agregar-odontologo');
    formulario.addEventListener('submit', function (event){
        event.preventDefault();
        const formData = {
                   matricula: document.querySelector(
                   '#matricula-odontologo').value,
                   nombre: document.querySelector(
                   '#nombre-odontologo').value,
                   apellido: document.querySelector(
                   '#apellido-odontologo').value,
        };

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                document.querySelector("#response-div").innerHTML = '<p>¡Odontólogo agregado!</p>';
                document.querySelector("#response-div").style.display = "block";
                console.log(data);
            })
            .catch(error => {
                document.querySelector('#response-div').innerHTML = '<p>Error, la película no se pudo agregar</p>';
                document.querySelector("#response-div").style.display = "block";
                resetUploadForm();
                console.log(error);
            })
    })
})
    function resetUploadForm(){
           document.querySelector('#matricula-odontologo').value = "";
           document.querySelector('#nombre-odontologo').value = "";
           document.querySelector('#apellido-odontologo').value = "";
    };