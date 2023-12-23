import {WebSocketServer}  from "ws";

const SERVER_PORT = 8080;

// Inicialización del WebSocket
const wss = new WebSocketServer({ port: SERVER_PORT });

export default wss;