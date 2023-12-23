import { SerialPort } from 'serialport';
import { ReadlineParser } from '@serialport/parser-readline';
import { Buffer as _Buffer } from 'buffer';

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
    ready = false;

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
            if(data == "<READY>\r"){
                console.log("PORT IS READY....");
                this.ready = true;
                return;
            }
            console.log('SERIAL PORT: Data received: ' + data);
    
            if(this.id > 0 && this.ready){
                data = +data.replace('\r','');
                this.time += this.timeMus;
                this.data = data;
                myEventEmitter.emit('data_arduino',this.id, this.data, this.time);
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
     * @param {number} procesoId
     */
    set id(procesoId){
        if (this.procesoId != procesoId) {
            this.time = 0;
            console.log("NEW PROCESS: "+ procesoId);
            this.procesoId = procesoId;
        }
    }

    get id(){
        return this.procesoId;
    }


    write(command) {
        if (this.isOpen){
            console.log('Data to write: ', command);
            this.serialPort.write(command + '\n'); 
        }       
    }

}

const mySerial = new SerialController(COM_PORT);
export default mySerial;
