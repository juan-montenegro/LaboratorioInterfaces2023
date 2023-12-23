import { EventEmitter } from "events";

import  sqlDB   from './sqlController.js';
import  mySerial  from './serialController.js';

// Create event emitter
const myEventEmitter = new EventEmitter();

// Attaching the connection event with the handler
myEventEmitter.on('data_arduino', (id,data,time) => {
    console.log('NEW DATA: ' + id + ', ' + data + ', ' + time);
    sqlDB.varsDataProcess(id, data, time);    
});

// Attaching Data_received event with an anonymous function
myEventEmitter.on('data_db', (varsId) => {
    console.log('NEW PROCESS VARS ID: ', varsId);
    mySerial.id = varsId;
});



export default myEventEmitter;
