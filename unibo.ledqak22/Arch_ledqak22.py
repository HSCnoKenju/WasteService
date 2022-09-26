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
with Diagram('ledqak22Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
     with Cluster('ctxledqak22', graph_attr=nodeattr):
          ledqak22=Custom('ledqak22','./qakicons/symActorSmall.png')
          ledsimulator=Custom('ledsimulator(coded)','./qakicons/codedQActor.png')
          ledconcrete=Custom('ledconcrete(coded)','./qakicons/codedQActor.png')
     sys >> Edge(color='red', style='dashed', xlabel='ledtrigger', fontcolor='red') >> ledqak22
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledon', fontcolor='blue') >> ledsimulator
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledon', fontcolor='blue') >> ledconcrete
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledblink', fontcolor='blue') >> ledsimulator
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledblink', fontcolor='blue') >> ledconcrete
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledoff', fontcolor='blue') >> ledsimulator
     ledqak22 >> Edge(color='blue', style='solid', xlabel='ledoff', fontcolor='blue') >> ledconcrete
diag
