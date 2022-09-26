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
with Diagram('alarmrpiArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
     with Cluster('ctx_rpi', graph_attr=nodeattr):
          sonarlatestart=Custom('sonarlatestart','./qakicons/symActorSmall.png')
          sonardata=Custom('sonardata','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
     sonarlatestart >> Edge(color='blue', style='solid', xlabel='cmdSonar', fontcolor='blue') >> sonardata
     sonardata >> Edge( xlabel='sonardata', **eventedgeattr, fontcolor='red') >> sys
     sys >> Edge(color='red', style='dashed', xlabel='ledtrigger', fontcolor='red') >> led
diag
