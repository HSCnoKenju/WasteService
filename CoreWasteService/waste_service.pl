%====================================================================================
% waste_service description   
%====================================================================================
mqttBroker("broker.hivemq.com", "1883", "unibo/hu/waste").
context(ctxpathexec, "127.0.0.1",  "TCP", "8049").
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( pathexec, ctxpathexec, "external").
  qactor( thresholdchecker, ctxwaste, "it.unibo.thresholdchecker.Thresholdchecker").
  qactor( mocksystem, ctxwaste, "it.unibo.mocksystem.Mocksystem").
  qactor( trolleyanalyzer, ctxwaste, "it.unibo.trolleyanalyzer.Trolleyanalyzer").
