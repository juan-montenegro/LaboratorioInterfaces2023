import wss from "./controller/wSocketController.js";
import myConnect from "./controller/dbController.js";


const wsConnections = [];

const data = {
  valores: [0],
  tiempos: [0],
  flag: 0,
  nombre: ""
}

// Mensaje al inicio de la aplicación
console.log("¡Aplicación en ejecución!\nSeleccione un canal...");

// Manejadores del WebSocket
function wsHandler(client) {
  console.log("WS: Nueva conexión...");

  // Agregando la conexión al grupo de conexiones.
  wsConnections.push(client);

  // Cuando el cliente envía mensajes
  client.on("message", function (data) {
    console.log("Cliente: " + data);

    let canal = data; // Estrae el canal seleccionado
    let processVarsId = 0; //Analogos y digtales
    let processRefId = 0; // Salidas
    let valorsalida = 0; // estado de las salidas

    if (canal == "A0") {
      console.log("Canal Análogo 0 seleccionado");
      processVarsId = 10;
      actualizarVarsFlag(1,processVarsId);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,12);
      actualizarVarsFlag(0,13);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "A1") {
      console.log("Canal Análogo 1 seleccionado");
      processVarsId = 11;
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(1,processVarsId);
      actualizarVarsFlag(0,12);
      actualizarVarsFlag(0,13);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "A2") {
      console.log("Canal Análogo 2 seleccionado");
      processVarsId = 12;
      actualizarVarsFlag(1,processVarsId);
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,13);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "A3") {
      console.log("Canal Análogo 3 seleccionado");
      processVarsId = 13;
      actualizarVarsFlag(1,processVarsId);
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,12);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "D0") {
      console.log("Canal Digital 0 seleccionado");
      processVarsId = 18;
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,12);
      actualizarVarsFlag(0,13);
      actualizarVarsFlag(1,processVarsId);
      actualizarVarsFlag(1,19);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "D1") {
      console.log("Canal Digital 1 seleccionado");
      processVarsId = 19;
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,12);
      actualizarVarsFlag(0,13);
      actualizarVarsFlag(0,10);
      actualizarVarsFlag(0,11);
      actualizarVarsFlag(0,12);
      actualizarVarsFlag(0,18);
      actualizarVarsFlag(1,processVarsId);
      ejecutarConsultaYEnviar(client, processVarsId);
    } else if (canal == "DO0") {
      console.log("Canal Salida 0 seleccionado");
    } else if (canal == "DO1") {
      console.log("Canal Salida 1 seleccionado");
    } else if (canal == "DO00") {
      console.log("Salida 0 Desactivada");
      processRefId = 4;
      valorsalida = 0;
      actualizarSQL(valorsalida, processRefId);
    } else if (canal == "DO01") {
      console.log("Salida 0 Activada");
      processRefId = 4;
      valorsalida = 1;
      actualizarSQL(valorsalida, processRefId);
    } else if (canal == "DO10") {
      console.log("Salida 1 Desactivada");
      processRefId = 5;
      valorsalida = 0;
      actualizarSQL(valorsalida, processRefId);
    } else if (canal == "DO11") {
      console.log("Salida 1 Activada");
      processRefId = 5;
      valorsalida = 1;
      actualizarSQL(valorsalida, processRefId);
    } else {
      console.log("Identificador no reconocido");
    }
  });

  // Cuando el cliente cierra la conexión
  client.on("close", function () {
    console.log("WS: Conexión cerrada");
    const clientPosition = wsConnections.indexOf(client);
    wsConnections.splice(clientPosition, 1);
  });
}

// Eventos del WebSocket.
wss.on("connection", wsHandler);

// Función para ejecutar consulta SQL y enviar resultados al cliente
function ejecutarConsultaYEnviar(client, processVarId) {
  consultaSQL(processVarId, (error, valores) => {
    if (error) {
      console.error("Error durante la consulta:", error);
      return;
    } else if (error == 'NODATA'){
      return;
    }

    console.log("Valores a enviar:", valores);

    client.send(JSON.stringify(valores));
  });
}

// Función para realizar consulta SQL a la base de datos
function consultaSQL(processVarsId, callback) {
  console.log("La fk es:" + processVarsId);

  let myQuery = "SELECT * FROM int_proceso_vars_data ORDER BY Id DESC LIMIT 1";

  // Conectar a la base de datos
  myConnect.query(myQuery, function (error, results) {
    if (error) {
      callback(error, null); // Invoca el callback con el error
      return;
    }

    console.log("Selected data: ");
    console.log(JSON.stringify(results[0]));

    const {valor, tiempo} = results[0];

    if (data.tiempos.slice(-1) == tiempo){
      callback('NODATA', null); // Invoca el callback con el error
      return;
    }

    data.valores.push(valor);
    data.tiempos.push(tiempo);

    

    console.log("Array de números: ");
    console.log(JSON.stringify(data));

    let a = data.valores.length;
    console.log("Largo del vector: " + a);

    callback(null, data); // Invoca el callback con los resultados
  });
}

// Función para actualizar registros en la base de datos
function actualizarSQL(valorsalida, processRefsId) {
  console.log("La fk es:" + processRefsId);

  // Utilizar consultas parametrizadas para evitar inyección de SQL
  let myQuery = "UPDATE int_proceso_refs SET flag=? WHERE id=?";

  // Ejecutar la consulta con los parámetros correspondientes
  myConnect.query(
    myQuery,
    [valorsalida, processRefsId],
    function (error, results) {
      if (error) {
        console.error("Error al ejecutar la consulta:", error);
        return;
      }

      console.log("Número de filas afectadas:", results.affectedRows);
      console.log("Objeto de resultados:", results);

      console.log("Consulta ejecutada con éxito");
    }
  );
}

// Función para actualizar registros en la base de datos
function actualizarVarsFlag(valorsalida, processVarsId) {
  console.log("La fk es:" + processVarsId);

  // Utilizar consultas parametrizadas para evitar inyección de SQL
  let myQuery = "UPDATE int_proceso_vars SET flag=? WHERE id=?";

  // Ejecutar la consulta con los parámetros correspondientes
  myConnect.query(
    myQuery,
    [valorsalida, processVarsId],
    function (error, results) {
      if (error) {
        console.error("Error al ejecutar la consulta:", error);
        return;
      }

      console.log("Número de filas afectadas:", results.affectedRows);
      console.log("Objeto de resultados:", results);

      console.log("Consulta ejecutada con éxito");
    }
  );
}
