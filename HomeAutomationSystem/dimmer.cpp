  /**************
 *  RobotDyn
 *  Dimmer Library
 *  **************
 * 
 * The following sketch is meant to define the dimming value through the serial port of the controller, 
 * using USE_SERIAL.begin 
 *    void printSpace() function is used for adding of space after functional data
 *    void loop()  serial port evaluator, used to register and define values in dimmer.setPower(outVal);
 *    
 * 
 *  ---------------------- OUTPUT & INPUT Pin table ---------------------
 *  +---------------+-------------------------+-------------------------+
 *  |   Board       | INPUT Pin               | OUTPUT Pin              |
 *  |               | Zero-Cross              |                         |
 *  +---------------+-------------------------+-------------------------+
 *  | Lenardo       | D7 (NOT CHANGABLE)      | D0-D6, D8-D13           |
 *  +---------------+-------------------------+-------------------------+
 *  | Mega          | D2 (NOT CHANGABLE)      | D0-D1, D3-D70           |
 *  +---------------+-------------------------+-------------------------+
 *  | Uno           | D2 (NOT CHANGABLE)      | D0-D1, D3-D20           |
 *  +---------------+-------------------------+-------------------------+
 *  | ESP8266       | D1(IO5),    D2(IO4),    | D0(IO16),   D1(IO5),    |
 *  |               | D5(IO14),   D6(IO12),   | D2(IO4),    D5(IO14),   |
 *  |               | D7(IO13),   D8(IO15),   | D6(IO12),   D7(IO13),   |
 *  |               |                         | D8(IO15)                |
 *  +---------------+-------------------------+-------------------------+
 *  | ESP32         | 4(GPI36),   6(GPI34),   | 8(GPO32),   9(GP033),   |
 *  |               | 5(GPI39),   7(GPI35),   | 10(GPIO25), 11(GPIO26), |
 *  |               | 8(GPO32),   9(GP033),   | 12(GPIO27), 13(GPIO14), |
 *  |               | 10(GPI025), 11(GPIO26), | 14(GPIO12), 16(GPIO13), |
 *  |               | 12(GPIO27), 13(GPIO14), | 23(GPIO15), 24(GPIO2),  |
 *  |               | 14(GPIO12), 16(GPIO13), | 25(GPIO0),  26(GPIO4),  |
 *  |               | 21(GPIO7),  23(GPIO15), | 27(GPIO16), 28(GPIO17), |
 *  |               | 24(GPIO2),  25(GPIO0),  | 29(GPIO5),  30(GPIO18), |
 *  |               | 26(GPIO4),  27(GPIO16), | 31(GPIO19), 33(GPIO21), |
 *  |               | 28(GPIO17), 29(GPIO5),  | 34(GPIO3),  35(GPIO1),  |
 *  |               | 30(GPIO18), 31(GPIO19), | 36(GPIO22), 37(GPIO23), |
 *  |               | 33(GPIO21), 35(GPIO1),  |                         |
 *  |               | 36(GPIO22), 37(GPIO23), |                         |
 *  +---------------+-------------------------+-------------------------+
 *  | Arduino M0    | D7 (NOT CHANGABLE)      | D0-D6, D8-D13           |
 *  | Arduino Zero  |                         |                         |
 *  +---------------+-------------------------+-------------------------+
 *  | Arduino Due   | D0-D53                  | D0-D53                  |
 *  +---------------+-------------------------+-------------------------+
 *  | STM32         | PA0-PA15,PB0-PB15       | PA0-PA15,PB0-PB15       |
 *  | Black Pill    | PC13-PC15               | PC13-PC15               |
 *  | BluePill      |                         |                         |
 *  | Etc...        |                         |                         |
 *  +---------------+-------------------------+-------------------------+
 */

//Library provided by manufacturer of RobotDyn AC Light Dimmer (solid state relay) used in our circuit (source github.com/RobotDynOfficial/RBDDimmer

#include "dimmer.h"
#include <RBDdimmer.h>

     
#define zerocross  12 // for boards with CHANGEBLE input pins

dimmerLamp  dimmer(outputPin, zerocross); //initialase port for dimmer for ESP8266, ESP32, Arduino due boards
//dimmerLamp dimmer(outputPin); //initialase port for dimmer for MEGA, Leonardo, UNO, Arduino M0, Arduino Zero

int outVal = 0;


void setupDimmer(){
 dimmer.begin(NORMAL_MODE, ON); //dimmer initialisation: name.begin(MODE, STATE) 
}
void setPower(int power){
  Serial.printf("setting pwm at %d\n",power);
  dimmer.setPower(power);
}

int getOldValue(){
  return outVal;
}

int getPower(){
  return dimmer.getPower();
}
