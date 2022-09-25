%====================================================================================
% sonarqak22 description   
%====================================================================================
mqttBroker("broker.hivemq.com", "1883", "unibo/hu/radar").
context(ctxwaste, "127.0.0.1",  "TCP", "8033").
context(ctxsonarqak22, "localhost",  "TCP", "8061").
 qactor( sonarsimulator, ctxsonarqak22, "sonarSimulator").
  qactor( sonardatasource, ctxsonarqak22, "sonarHCSR04Support2021").
  qactor( datacleaner, ctxsonarqak22, "dataCleaner").
  qactor( sonarqak22, ctxsonarqak22, "it.unibo.sonarqak22.Sonarqak22").
  qactor( sonarmastermock, ctxsonarqak22, "it.unibo.sonarmastermock.Sonarmastermock").
