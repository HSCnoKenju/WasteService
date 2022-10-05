%====================================================================================
% sprint_0 description   
%====================================================================================
context(ctxrpi, "localhost",  "TCP", "8081").
context(ctxwasteservice, "localhost",  "TCP", "8080").
context(ctxgui, "localhost",  "TCP", "8082").
context(ctxtruck, "localhsot",  "TCP", "8083").
 qactor( wastetruck, ctxtruck, "it.unibo.wastetruck.Wastetruck").
  qactor( wasteservice, ctxwasteservice, "it.unibo.wasteservice.Wasteservice").
  qactor( trolley, ctxwasteservice, "it.unibo.trolley.Trolley").
  qactor( sonar, ctxrpi, "it.unibo.sonar.Sonar").
  qactor( led, ctxrpi, "it.unibo.led.Led").
  qactor( gui, ctxgui, "it.unibo.gui.Gui").
