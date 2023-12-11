byte buffer[7];
byte h1=0x7a;
byte h2=0x7b;
byte h3=0x7c;
String lectura;

int estadoFSM=0;
const int SensorPin0=A0;
const int SensorPin1=A1;
const int SensorPin2=A2;
const int SensorPin3=A3;
const int SensorPin4=A4;
const int SensorPin5=A5;
const int SensorPin6=A6;
const int SensorPin7=A7;
const int pin_S2 = 2;
const int pin_S3 = 3;
const int pin_S4 = 4;
const int pin_S5 = 5;
const int Led1 = 9;
const int Led2 = 10;
const int Led3 = 11;
const int Led4 = 12;

volatile unsigned muestreoActual=0;
volatile unsigned muestreoAnterior=0;
volatile unsigned deltaMuestreo=0;

const char START_DELIMITER = 'T';
const char END_DELIMITER = ',';
int ts=100; //tiempo de muestreo
const int ledPin = 13;


void leerTiempo(String trama) {
  String firstPart;
  String secondPart;
  
  if (trama.startsWith(String(START_DELIMITER))) {
    Serial.println("TRAMA: "+trama);
    // La trama tiene el formato correcto
    trama.remove(0, 1);  // Elimina el START_DELIMITER
    

    int delimiterIndex = trama.indexOf(END_DELIMITER); // Find the delimiter's position
  
//    if (delimiterIndex != -1) {
//      
//    } 
    firstPart = trama.substring(0, delimiterIndex);
    secondPart = trama.substring(delimiterIndex + 1);

    Serial.println("Trama: " + firstPart + ", " + secondPart);

    
    // Ahora, trama contiene solo el valor numérico
    int nuevoValor = firstPart.toInt();
    lectura = secondPart;

    // Actualiza el tiempo de muestreo
    ts = nuevoValor;
    digitalWrite(ledPin, HIGH);
    delay(1000);
    digitalWrite(ledPin, LOW);
  } else {
   // Serial.println("Trama inválida.");
  }
  
}

void setup() {
  // put your setup code here, to run once:
pinMode(13,OUTPUT);
pinMode(Led4,OUTPUT);
pinMode(Led3,OUTPUT);
pinMode(Led2,OUTPUT);
pinMode(Led1,OUTPUT);
pinMode(pin_S2, INPUT);
pinMode(pin_S3, INPUT);
pinMode(pin_S4, INPUT);
pinMode(pin_S5, INPUT);

Serial.begin(9600);
delay(1000);
}
void loop() {
  // put your main code here, to run repeatedly:
    //Serial.println("Leyendo: ");
    if(Serial.available()>0){
      
      lectura=Serial.readString();
      leerTiempo(lectura);
      delay(100);
    }
    Serial.println("PUERTO: " + lectura);
    
     
     if(lectura=="DO01"){
      digitalWrite(Led1,HIGH);
      }
     else if(lectura=="DO00"){
      digitalWrite(Led1,LOW);
      } 
      else if(lectura=="DO11"){
      digitalWrite(Led2,HIGH);
      }
     else if(lectura=="DO10"){
      digitalWrite(Led2,LOW);
      }  
      else if(lectura=="DO21"){
      digitalWrite(Led3,HIGH);
      }
      else if(lectura=="DO20"){
      digitalWrite(Led3,LOW);
      } 
      else if(lectura=="DO31"){
      digitalWrite(Led4,HIGH);
      }
      else if(lectura=="DO30"){
      digitalWrite(Led4,LOW);
      } 

      
      if(lectura=="A0"){
        Serial.println("LEYENDO: " + lectura);
        muestreoActual = millis();
        deltaMuestreo = (double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin0);
        muestreoAnterior=muestreoActual;
        }
      }

      else if(lectura=="A1"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin1);
        muestreoAnterior=muestreoActual;
        } 
      }

      else if(lectura=="A2"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin2);
        muestreoAnterior=muestreoActual;
        } 
      }

      else if(lectura=="A3"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin3);
        muestreoAnterior=muestreoActual;
        } 
      }

      else if(lectura=="A4"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin4);
        muestreoAnterior=muestreoActual;
        } 
      }

      else if(lectura=="A5"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin5);
        muestreoAnterior=muestreoActual;
        } 
      }
      else if(lectura=="A6"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin6);
        muestreoAnterior=muestreoActual;
        } 
      }
      else if(lectura=="A7"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
        potenciometro(SensorPin7);
        muestreoAnterior=muestreoActual;
        } 
      }
      else if(lectura=="D0"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
         enviarDatos(pin_S2);
         muestreoAnterior=muestreoActual;
        }
      }   
      else if(lectura=="D1"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
         enviarDatos(pin_S3);
         muestreoAnterior=muestreoActual;
        }
      }   
      else if(lectura=="D2"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
         enviarDatos(pin_S4);
         muestreoAnterior=muestreoActual;
        }
      }   
      else if(lectura=="D3"){
        muestreoActual=millis();
        deltaMuestreo=(double) muestreoActual-muestreoAnterior;
        if(deltaMuestreo>=ts){
         enviarDatos(pin_S5);
         muestreoAnterior=muestreoActual;
        }
      }
     



}

void potenciometro(const int pin){
    
          int sensorVal=analogRead(pin); 
          Serial.print("sensorVal: ");
          Serial.println(sensorVal);
          
          buffer[0]=h1;
          buffer[1]=h2;
          buffer[2]=sensorVal/256;
          buffer[3]=sensorVal%256;
          buffer[4]=h3;
          Serial.write(buffer,sizeof(buffer));
        
}
void enviarDatos(const int pin ) {
  int S2=digitalRead(pin);
  byte dato = constrain(S2,0,1);

  buffer[0] = h1;
  buffer[1] = h2;
  buffer[2] = dato;
  buffer[3] = h3;

  Serial.write(buffer, sizeof(buffer));
}
