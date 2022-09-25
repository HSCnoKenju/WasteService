%====================================================================================
% waste_service description   
%====================================================================================
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( thresholdchecker, ctxwaste, "it.unibo.thresholdchecker.Thresholdchecker").
  qactor( mocktrolleylistener, ctxwaste, "it.unibo.mocktrolleylistener.Mocktrolleylistener").
