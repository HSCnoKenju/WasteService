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
with Diagram('pathplannerArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxpathexec', graph_attr=nodeattr):
          pathexec=Custom('pathexec(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxpathplanner', graph_attr=nodeattr):
          pathplanner=Custom('pathplanner','./qakicons/symActorSmall.png')
          boundarymapbuilder=Custom('boundarymapbuilder','./qakicons/symActorSmall.png')
     pathplanner >> Edge(color='magenta', style='solid', xlabel='buildMap', fontcolor='magenta') >> boundarymapbuilder
     pathplanner >> Edge(color='magenta', style='solid', xlabel='dopath', fontcolor='magenta') >> pathexec
     boundarymapbuilder >> Edge(color='magenta', style='solid', xlabel='step', fontcolor='magenta') >> basicrobot
     boundarymapbuilder >> Edge(color='blue', style='solid', xlabel='cmd', fontcolor='blue') >> basicrobot
diag
