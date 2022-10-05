from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
with Diagram('waste_serviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot','./qakicons/symActorSmall.png')
     with Cluster('ctxpathexec', graph_attr=nodeattr):
          pathexec=Custom('pathexec','./qakicons/symActorSmall.png')
     with Cluster('ctxpathplanner', graph_attr=nodeattr):
          pathplanner=Custom('pathplanner','./qakicons/symActorSmall.png')
          boundarymapbuilder=Custom('boundarymapbuilder','./qakicons/symActorSmall.png')
     with Cluster('ctxconfig', graph_attr=nodeattr):
          configurer=Custom('configurer','./qakicons/symActorSmall.png')
     with Cluster('ctxtest', graph_attr=nodeattr):
          tester=Custom('tester','./qakicons/symActorSmall.png')
     with Cluster('ctxwaste', graph_attr=nodeattr):
          trolleyanalyzer=Custom('trolleyanalyzer','./qakicons/symActorSmall.png')
          thresholdchecker=Custom('thresholdchecker','./qakicons/symActorSmall.png')
          transporttrolley=Custom('transporttrolley','./qakicons/symActorSmall.png')
          waste_service=Custom('waste_service','./qakicons/symActorSmall.png')
     with Cluster('ctxledqak22', graph_attr=nodeattr):
          led=Custom('led','./qakicons/symActorSmall.png')
     with Cluster('ctxgui', graph_attr=nodeattr):
          gui=Custom('gui','./qakicons/symActorSmall.png')
     with Cluster('ctxtruck', graph_attr=nodeattr):
          waste_truck_mock=Custom('waste_truck_mock','./qakicons/symActorSmall.png')
     with Cluster('ctxsonarqak22', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
     waste_service >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> gui
     transporttrolley >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> gui
     trolleyanalyzer >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> gui
     led >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> gui
     sys >> Edge(color='red', style='dashed', xlabel='robotState', fontcolor='red') >> trolleyanalyzer
     trolleyanalyzer >> Edge(color='blue', style='solid', xlabel='ledtrigger', fontcolor='blue') >> led
     sonar >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> thresholdchecker
     thresholdchecker >> Edge( xlabel='stop', **eventedgeattr, fontcolor='red') >> sys
     thresholdchecker >> Edge( xlabel='resume', **eventedgeattr, fontcolor='red') >> sys
     pathexec >> Edge(color='blue', style='solid', xlabel='cmd', fontcolor='blue') >> basicrobot
     pathexec >> Edge( xlabel='robotState', **eventedgeattr, fontcolor='red') >> sys
     pathexec >> Edge(color='magenta', style='solid', xlabel='step', fontcolor='magenta') >> basicrobot
     sys >> Edge(color='red', style='dashed', xlabel='alarm', fontcolor='red') >> pathexec
     sys >> Edge(color='red', style='dashed', xlabel='stop', fontcolor='red') >> pathexec
     sys >> Edge(color='red', style='dashed', xlabel='resume', fontcolor='red') >> pathexec
     basicrobot >> Edge( xlabel='info', **eventedgeattr, fontcolor='red') >> sys
     pathplanner >> Edge(color='magenta', style='solid', xlabel='buildMap', fontcolor='magenta') >> boundarymapbuilder
     pathplanner >> Edge(color='magenta', style='solid', xlabel='dopath', fontcolor='magenta') >> pathexec
     boundarymapbuilder >> Edge(color='magenta', style='solid', xlabel='step', fontcolor='magenta') >> basicrobot
     boundarymapbuilder >> Edge(color='blue', style='solid', xlabel='cmd', fontcolor='blue') >> basicrobot
     configurer >> Edge(color='blue', style='solid', xlabel='init_capacity', fontcolor='blue') >> waste_service
     configurer >> Edge(color='blue', style='solid', xlabel='start_position', fontcolor='blue') >> transporttrolley
     configurer >> Edge(color='blue', style='solid', xlabel='all_position', fontcolor='blue') >> transporttrolley
     tester >> Edge(color='blue', style='solid', xlabel='reset', fontcolor='blue') >> waste_service
     transporttrolley >> Edge(color='magenta', style='solid', xlabel='destination', fontcolor='magenta') >> pathplanner
     transporttrolley >> Edge( xlabel='pickup', **eventedgeattr, fontcolor='red') >> sys
     transporttrolley >> Edge( xlabel='robotState', **eventedgeattr, fontcolor='red') >> sys
     waste_truck_mock >> Edge(color='magenta', style='solid', xlabel='waste', fontcolor='magenta') >> waste_service
     waste_service >> Edge(color='magenta', style='solid', xlabel='goal', fontcolor='magenta') >> transporttrolley
     sys >> Edge(color='red', style='dashed', xlabel='pickup', fontcolor='red') >> waste_service
diag
