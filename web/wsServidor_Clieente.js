// Inicialización del WebSocket
const SERVER_PORT = 8080;
const WebSocketServer = require("ws").Server;
const wss = new WebSocketServer({ port: SERVER_PORT });
const wsConnections = [];

var mysql = require("mysql");

var myConnect = mysql.createConnection({
  host: "localhost",
  user: "camilo",
  password: "docWHn9LCLk7N98@",
  database: "db2023-2",
});

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

    var canal = data; // Estrae el canal seleccionado
    var int_p_v_id_fk = 0; //Analogos y digtales
    var int_p_r_id_fk = 0; // Salidas
    var valorsalida = 0; // estado de las salidas

    if (canal == "A0") {
      console.log("Canal Análogo 0 seleccionado");
      int_p_v_id_fk = 10;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "A1") {
      console.log("Canal Análogo 1 seleccionado");
      int_p_v_id_fk = 11;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "A2") {
      console.log("Canal Análogo 2 seleccionado");
      int_p_v_id_fk = 12;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "A3") {
      console.log("Canal Análogo 3 seleccionado");
      int_p_v_id_fk = 13;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "D0") {
      console.log("Canal Digital 0 seleccionado");
      int_p_v_id_fk = 18;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "D1") {
      console.log("Canal Digital 1 seleccionado");
      int_p_v_id_fk = 19;
      ejecutarConsultaYEnviar(client, int_p_v_id_fk);
    } else if (canal == "DO0") {
      console.log("Canal Salida 0 seleccionado");
    } else if (canal == "DO1") {
      console.log("Canal Salida 1 seleccionado");
    } else if (canal == "DO00") {
      console.log("Salida 0 Desactivada");
      int_p_r_id_fk = 4;
      valorsalida = 0;
      actualizarSQL(valorsalida, int_p_r_id_fk);
    } else if (canal == "DO01") {
      console.log("Salida 0 Activada");
      int_p_r_id_fk = 4;
      valorsalida = 1;
      actualizarSQL(valorsalida, int_p_r_id_fk);
    } else if (canal == "DO10") {
      console.log("Salida 1 Desactivada");
      int_p_r_id_fk = 5;
      valorsalida = 0;
      actualizarSQL(valorsalida, int_p_r_id_fk);
    } else if (canal == "DO11") {
      console.log("Salida 1 Activada");
      int_p_r_id_fk = 5;
      valorsalida = 1;
      actualizarSQL(valorsalida, int_p_r_id_fk);
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
function ejecutarConsultaYEnviar(client, int_p_v_id_fk) {
  consultaSQL(int_p_v_id_fk, (error, valores) => {
    if (error) {
      console.error("Error durante la consulta:", error);
      return;
    }

    console.log("Valores a enviar:", valores);
    // client.send(valores);
    client.send(JSON.stringify(valores));
  });
}

// Función para realizar consulta SQL a la base de datos
function consultaSQL(int_p_v_id_fk, callback) {
  console.log("La fk es:" + int_p_v_id_fk);

  var myQuery =
    "SELECT valor FROM int_proceso_vars_data WHERE int_proceso_vars_id = " +
    int_p_v_id_fk;

  // Conectar a la base de datos
  myConnect.query(myQuery, function (error, results) {
    if (error) {
      callback(error, null); // Invoca el callback con el error
      return;
    }

    console.log("Selected data: ");
    console.log(results);

    const valores = results.map((row) => row.valor);

    console.log("Array de números: ");
    console.log(valores);

    let a = valores.length;
    console.log("Largo del vector: " + a);

    callback(null, valores); // Invoca el callback con los resultados
  });
}

// Función para actualizar registros en la base de datos
function actualizarSQL(valorsalida, int_p_r_id_fk) {
  console.log("La fk es:" + int_p_r_id_fk);

  // Utilizar consultas parametrizadas para evitar inyección de SQL
  var myQuery =
    "UPDATE int_proceso_refs SET flag = ? WHERE id = ?";

  // Ejecutar la consulta con los parámetros correspondientes
  myConnect.query(
    myQuery,
    [valorsalida, int_p_r_id_fk],
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
