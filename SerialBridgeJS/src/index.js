import { setTimeout } from "timers/promises";
import  sqlDB   from './controllers/sqlController.js';
import  mySerial  from './controllers/serialController.js';

await setTimeout(500);

console.log('Connected as ' + sqlDB.conn.threadId);
console.log('Connection state: ' + sqlDB.conn.state);

async function checkData(){
    if(!sqlDB.status) return;
    if(!mySerial.isOpen) {
        mySerial.open();
        return;   
    }
    sqlDB.getRefsProcess(3);
    sqlDB.getVarsProcess(1);

}

setInterval(checkData,100);