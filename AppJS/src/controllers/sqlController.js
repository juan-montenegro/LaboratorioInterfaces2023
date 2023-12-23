import mysql from 'mysql';
import date from 'date-and-time';
import mySerial  from "./serialController.js";
import myEventEmitter from './eventController.js';

class SqlController {
    stateDo0 = 0;
    stateDo1 = 0;
    stateDo2 = 0;
    stateDo3 = 0;

    prevStateDo0 = 0;
    prevStateDo1 = 0;
    prevStateDo2 = 0;
    prevStateDo3 = 0;

    signal = "";
    signalTemp = "";

    conn;

    status = false;

    constructor(user, password) {
        this.conn = mysql.createConnection({
            host: 'localhost',
            user: user,
            password: password,
            database: 'db2023-2'
        });

        this.conn.connect((err) => {
            if (err){
                this.status = false;
                console.log('ERROR: Cannot connect to database: ', err.stack);
            } else {
                this.status = true;
                console.log('Connection succesful!');
            }
        });

        this.signalTemp = "\0";
        this.signal = '\0';        
    }

    
    varsDataProcess(processId, value, time){
        let insertQuery = "INSERT INTO int_proceso_vars_data (int_proceso_vars_id, valor, tiempo, fecha, hora) VALUES (?, ?, ?, ?, ?)";
        let now = new Date();
        let today = date.format(now, 'YYYY-MM-DD');  
        let clock = date.format(now, 'HH:mm:ss');
        
        let myQueryValues = [processId,value,time,today,clock];
        
        console.log("INSERT int_proceso_vars_data: " + JSON.stringify(myQueryValues));

        if(this.conn == null) return;

        this.conn.query(insertQuery, myQueryValues , (error, result) => {
            if (error) throw error;
    
            console.log('Affected rows: '+result.affectedRows);
            console.log('Insert ID: '+result.insertId);
        });
    }


    getVarsProcess(flagg){
        let getQuery =  "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag FROM int_proceso_vars WHERE flag=?";
        let result = {};   
        let myQueryValues = [flagg];
        if (this.conn == null) return;
        if (!mySerial.ready) return;

        this.conn.query(getQuery, myQueryValues, (error, results, fields) => {
            if (error) throw error;

            
            result = results[0];
  
            if(!result){
                this.signal = "\0";
                if(this.signalTemp !== this.signal){
                    mySerial.write(this.signal);
                }
                this.signalTemp = this.signal;
                return;
            }
            const {id, nombre} = result;

            this.signal = nombre;

            if (this.signalTemp !== this.signal){
                console.log('SELECT FROM int_proceso_vars: '+ JSON.stringify(result));
                console.log("SENDING DATA FROM " + nombre + " TO processId " + id);
                mySerial.write("T500,"+this.signal);
                myEventEmitter.emit('data_db', id);
                this.signalTemp = this.signal;
            }
        });
    }

    getRefsProcess(processId){
        let getQuery =  "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag FROM int_proceso_refs WHERE int_proceso_id=?";

        let myQueryValues = [processId];

        if(this.conn == null) return;
        if(!mySerial.ready) return;
        this.conn.query(getQuery, myQueryValues, (error, results, fields) => {
            if (error) throw error;

            for(let i = 0; i < 4; i++){
                const {nombre, flag} = results[i];
                this.#updateLedStatus(nombre, flag);
            }
            
            if(this.stateDo0 != this.prevStateDo0){
                console.log("int_proceso_refs: " + JSON.stringify(results[0]));
                mySerial.write("DO0"+this.stateDo0);
                this.prevStateDo0 = this.stateDo0;
                return;
            } 

            if(this.stateDo1 != this.prevStateDo1){
                console.log("int_proceso_refs: " + JSON.stringify(results[1]));
                mySerial.write("DO1"+this.stateDo1); 
                this.prevStateDo1 = this.stateDo1;
                return;               
            } 

            if(this.stateDo2 != this.prevStateDo2){
                console.log("int_proceso_refs: " + JSON.stringify(results[2]));
                mySerial.write("DO2"+this.stateDo2);   
                this.prevStateDo2 = this.stateDo2;  
                return;           
            } 

            if(this.stateDo3 != this.prevStateDo3){
                console.log("int_proceso_refs: " + JSON.stringify(results[3]));
                mySerial.write("DO3"+this.stateDo3);
                this.prevStateDo3 = this.stateDo3;                
            }                     

        });
    }     



    #updateLedStatus(nombre, flag) {
        switch (nombre) {
            case "DO0":
                this.stateDo0 = flag;
                break;
            case "DO1":
                this.stateDo1 = flag;
                break;
            case "DO2":
                this.stateDo2 = flag;
                break;
            case "DO3":
                this.stateDo3 = flag;
                break;
            default:
                break;
        }
    }
}

const sqlDB = new SqlController('camilo','docWHn9LCLk7N98@');
export default sqlDB;