import { EventEmitter } from "events";

import  sqlDB   from './sqlController.js';
import  mySerial  from './serialController.js';

// Create event emitter
const myEventEmitter = new EventEmitter();

// Create event handler
let myConnectHandler = function connectHandler() {
    console.log('Ok - Connection succesful !');
}

// Attaching the connection event with the handler
myEventEmitter.on('data_arduino', myConnectHandler);

// Attaching Data_received event with an anonymous function
myEventEmitter.on('data_db', (varsId) => {
    console.log('Data received successfully !', varsId);
    mySerial.id = varsId;
});

// // Fire connection event
// myEventEmitter.emit('data_arduino');

// // Fire data received by event.
// myEventEmitter.emit('data_db');

export default myEventEmitter;
