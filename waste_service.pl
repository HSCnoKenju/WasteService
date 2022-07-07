%====================================================================================
% waste_service description   
%====================================================================================
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( transporttrolley, ctxwaste, "it.unibo.transporttrolley.Transporttrolley").
  qactor( waste_truck_mock, ctxwaste, "it.unibo.waste_truck_mock.Waste_truck_mock").
  qactor( waste_service, ctxwaste, "it.unibo.waste_service.Waste_service").
