%====================================================================================
% waste_service description   
%====================================================================================
mqttBroker("broker.hivemq.com", "1883", "unibo/hu/waste").
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( trolleyanalyzer, ctxwaste, "it.unibo.trolleyanalyzer.Trolleyanalyzer").
  qactor( trolleymock, ctxwaste, "it.unibo.trolleymock.Trolleymock").
