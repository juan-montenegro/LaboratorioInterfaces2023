import { SerialPort } from 'serialport';
import { ReadlineParser } from '@serialport/parser-readline';
import { Buffer as _Buffer } from 'buffer';
import { setTimeout } from "timers/promises";

import sqlDB  from "./sqlController.js";
import myEventEmitter from './eventController.js';

const { Buffer } = _Buffer;

const COM_PORT = 'COM3';

class SerialController {
    serialPort;
    signal = "";
    signalTemp = "";
    timeMus = 500;
    time = 0;
    data = 0;
    procesoId = 0;

    constructor(COM) {
        console.log('Creating serialPort');
        this.serialPort = new SerialPort({
            path: COM,
            baudRate: 9600,
            autoOpen: false,
            flowControl: false
        });

        this.serialPort.on('error', (err) => {
            return console.log("ERROR: " + err.message);
        })

        this.serialPort.on('open', (error) =>{
            if (error) {
                return console.log('Error openning Serial Port: ' + error.message);
            } else {
                console.log("Serial port open!");
            }        
        });

        this.open();
        
        this.myParserSP = new ReadlineParser();
        this.serialPort.pipe(this.myParserSP);

        this.myParserSP.on('data', (data) => {
            console.log('SERIAL PORT: Data received: ' + data);
    
            if(this.id > 0){
                this.time += this.timeMus;
                this.data = data;
                
                sqlDB.varsDataProcess(this.id, this.data, this.time);    
            }
            
        });

        this.serialPort.on('data', (error) => {
            if (error.message != null) {
                return console.log('Error writing Serial Port: ', error.message);
            } else{            
                console.log('Message writen on serial port...');
            }
        });
    }

    open() {
        this.serialPort.open();
    }

    get isOpen() {
        return  this.serialPort.isOpen;
    }

    /**
     * @param {string} signal
     */
    set cmd(signal){
        this.signal = signal;
    }

    get cmd(){
        return this.signal;
    }

    /**
     * @param {number} procesoId
     */
    set id(procesoId){
        if (this.procesoId != procesoId) {
            console.log("NEW PROCESS: "+ procesoId);
            this.procesoId = procesoId;
        }
    }

    get id(){
        return this.procesoId;
    }


    async write(command) {
        if (this.isOpen){
            this.time = 0;
            console.log('Data to write: ', command);
            this.serialPort.write(command +'\n'); 
            await setTimeout(200);
        }       
    }

}

const mySerial = new SerialController(COM_PORT);
export default mySerial;
