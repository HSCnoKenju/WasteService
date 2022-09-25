%====================================================================================
% waste_service description   
%====================================================================================
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( trolley_analyzer, ctxwaste, "it.unibo.trolley_analyzer.Trolley_analyzer").
