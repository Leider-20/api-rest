// Variables.
let campoTexto = document.querySelector(".input-text");
let btnRegistro = document.querySelector(".boton");
let tabla = document.querySelector(".tabla");

let departamento;
let arregloDatos = []




// Función para agregar los datos de los departamentos a la tabla del front.
function agregarEnTablaFront() {

    arregloDatos.push(campoTexto.value)
    // console.log(arregloDatos.length)

    let filas = document.createElement("tr");
    let idFilas = document.createElement("td");
    idFilas.classList = "columna-id";
    let datoFilas = document.createElement("td");

    idFilas.textContent = arregloDatos.length
    datoFilas.textContent = campoTexto.value

    filas.appendChild(idFilas);
    filas.appendChild(datoFilas);
    tabla.appendChild(filas);;

}



// Función para agregar los datos de los departamentos en la base de datos.
function agregarEnBaseDeDatos() {

    departamento = campoTexto.value

    fetch("http://localhost:8080/departamento", {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ nombre: departamento })
    })
        .then(function (resultado) {
            console.log(resultado)
            console.log("Se registró el departamento");
            return resultado.json();
        })
        .then(function (datos) {
            console.log(datos)
            // datos.forEach(function(departamento) {
            //     console.log(`ID del departamento: ${departamento.id}, nombre del departamento: ${departamento.nombre}`)
            // });
        })
        .catch(function (error) {
            console.error('Error al guardar el departamento:', error);
        });
}




// función para borrar los datos de la base de datos
function borrarTodosLosDatos() {
    fetch("http://localhost:8080/departamento", {
        method: 'DELETE'
    })
        .catch(function (error) {
            console.error('Error al borrar los departamentos:', error);
        });
}




// Botón para agregar los datos
btnRegistro.addEventListener("click", function (evento) {
    
    if (campoTexto.value.trim() === "") {
        console.log("Ingrese el nombre del departamento")

    } else {
        agregarEnBaseDeDatos()
        agregarEnTablaFront()
        campoTexto.value = " "  // Esto es para limpiar el campo de texto depués de agregar el departamento

    }
})


// Con esto se borran todos los datos de la base de datos cuando la página se recargue
window.addEventListener("load", function () {
    borrarTodosLosDatos();
});