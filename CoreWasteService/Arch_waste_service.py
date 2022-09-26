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
     with Cluster('ctxpathplanner', graph_attr=nodeattr):
          pathplanner=Custom('pathplanner(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxsonarqak22', graph_attr=nodeattr):
          sonarqak22=Custom('sonarqak22(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxwaste', graph_attr=nodeattr):
          trolleyanalyzer=Custom('trolleyanalyzer','./qakicons/symActorSmall.png')
          thresholdchecker=Custom('thresholdchecker','./qakicons/symActorSmall.png')
          transporttrolley=Custom('transporttrolley','./qakicons/symActorSmall.png')
          waste_service=Custom('waste_service','./qakicons/symActorSmall.png')
     sys >> Edge(color='red', style='dashed', xlabel='robotState', fontcolor='red') >> trolleyanalyzer
     trolleyanalyzer >> Edge( xlabel='ledtrigger', **eventedgeattr, fontcolor='red') >> sys
     thresholdchecker >> Edge(color='blue', style='solid', xlabel='coapUpdate', fontcolor='blue') >> thresholdchecker
     thresholdchecker >> Edge( xlabel='stop', **eventedgeattr, fontcolor='red') >> sys
     thresholdchecker >> Edge( xlabel='resume', **eventedgeattr, fontcolor='red') >> sys
     transporttrolley >> Edge(color='magenta', style='solid', xlabel='destination', fontcolor='magenta') >> pathplanner
     transporttrolley >> Edge( xlabel='pickup', **eventedgeattr, fontcolor='red') >> sys
     transporttrolley >> Edge( xlabel='robotState', **eventedgeattr, fontcolor='red') >> sys
     sys >> Edge(color='red', style='dashed', xlabel='pickup', fontcolor='red') >> waste_service
     waste_service >> Edge(color='magenta', style='solid', xlabel='goal', fontcolor='magenta') >> transporttrolley
diag
