#!/bin/sh

echo ""
echo "starting..................... nginx"
#sudo redis-cli shutdown
#sudo nginx -s stop;
sudo nginx -s quit;
sudo nginx -s reload;
sleep 1;


echo ""
echo "starting..................... zookeeper"
ZOOKEEPER_HOME=/usr/local/zookeeper-3.4.6;
sudo cp -rfv zoo.cfg ${ZOOKEEPER_HOME}/conf/;

sudo bash ${ZOOKEEPER_HOME}/bin/zkServer.sh stop;
sleep 1;
echo '';
sudo bash ${ZOOKEEPER_HOME}/bin/zkServer.sh start;
sleep 1;
echo '';
sudo bash ${ZOOKEEPER_HOME}/bin/zkServer.sh status;
sleep 1;



echo -e "\n\n\n"
echo "starting..................... redis"
sudo redis-cli -p 6379 shutdown;
sleep 1;
sudo redis-cli -p 6380 shutdown;
sleep 1;
sudo redis-cli -p 6381 shutdown;
sleep 1;

sudo cp -rfv redis/*.conf /opt/;

sudo redis-server /opt/redis_6379.conf;
sudo redis-server /opt/redis_6380.conf;
sudo redis-server /opt/redis_6381.conf;

sleep 1;
echo '';

