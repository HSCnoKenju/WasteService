%====================================================================================
% ledqak22 description   
%====================================================================================
context(ctxledqak22, "localhost",  "TCP", "8065").
 qactor( ledsimulator, ctxledqak22, "Ledsimulatorfsm").
  qactor( ledconcrete, ctxledqak22, "Ledconcretefsm").
  qactor( ledqak22, ctxledqak22, "it.unibo.ledqak22.Ledqak22").
