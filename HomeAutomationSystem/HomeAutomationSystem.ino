#include "wifi.h"
#include "webserver.h"
#include "dimmer.h"

//#define USE_SERIAL  SerialUSB //Serial for boards whith USB serial port
#define USE_SERIAL  Serial 

void setup() {
  USE_SERIAL.begin(115200); 
   
  USE_SERIAL.println("Dimmer Program is starting...");
  USE_SERIAL.println("Set value");
  wifiSetup();
  setupDimmer();
  setupWebserver();

}


void printSpace(int val){
  if ((val / 100) == 0) USE_SERIAL.print(" ");
  if ((val / 10) == 0) USE_SERIAL.print(" ");
}



void handleSerial(){
 
  if (USE_SERIAL.available())  {
    int curVal = USE_SERIAL.parseInt();
    if (curVal >=0 && curVal <= 100){
      setPower(curVal); 
      USE_SERIAL.printf("lampValue ->  %d %\n",getPower());
    }
  }
}

void loop() {
  handleSerial();
  handleWebserver();
}
