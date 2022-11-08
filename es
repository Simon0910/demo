docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.7
docker pull docker.elastic.co/kibana/kibana:7.17.7

docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -t xxx_image_Id

##############################################
#
# ** THIS IS AN AUTO-GENERATED FILE **
#

# Default Kibana configuration for docker target
server.name: kibana
server.host: "0"
elasticsearch.hosts: [ "http://xxx:9200" ]
xpack.monitoring.ui.container.elasticsearch.enabled: true
##############################################


  docker run -d \
    --name=kibana \
    -p 5601:5601 \
    -v /data/kibana/config/kibana.yml:/usr/share/kibana/config/kibana.yml \
    47c5b6ca1535
    
    
