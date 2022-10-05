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
with Diagram('sprint_0Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
     with Cluster('ctxrpi', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
     with Cluster('ctxwasteservice', graph_attr=nodeattr):
          wasteservice=Custom('wasteservice','./qakicons/symActorSmall.png')
          trolley=Custom('trolley','./qakicons/symActorSmall.png')
     with Cluster('ctxgui', graph_attr=nodeattr):
          gui=Custom('gui','./qakicons/symActorSmall.png')
     with Cluster('ctxtruck', graph_attr=nodeattr):
          wastetruck=Custom('wastetruck','./qakicons/symActorSmall.png')
     wastetruck >> Edge(color='magenta', style='solid', xlabel='waste', fontcolor='magenta') >> wasteservice
     sys >> Edge(color='red', style='dashed', xlabel='robotState', fontcolor='red') >> wasteservice
     wasteservice >> Edge( xlabel='weights', **eventedgeattr, fontcolor='red') >> sys
     wasteservice >> Edge(color='magenta', style='solid', xlabel='goal', fontcolor='magenta') >> trolley
     wasteservice >> Edge(color='blue', style='solid', xlabel='stop', fontcolor='blue') >> trolley
     wasteservice >> Edge(color='blue', style='solid', xlabel='resume', fontcolor='blue') >> trolley
     wasteservice >> Edge(color='blue', style='solid', xlabel='ledtrigger', fontcolor='blue') >> led
     trolley >> Edge( xlabel='robotState', **eventedgeattr, fontcolor='red') >> sys
     sonar >> Edge(color='blue', style='solid', xlabel='sonardata', fontcolor='blue') >> wasteservice
     led >> Edge( xlabel='ledState', **eventedgeattr, fontcolor='red') >> sys
     sys >> Edge(color='red', style='dashed', xlabel='ledState', fontcolor='red') >> gui
     sys >> Edge(color='red', style='dashed', xlabel='robotState', fontcolor='red') >> gui
     sys >> Edge(color='red', style='dashed', xlabel='weights', fontcolor='red') >> gui
diag
