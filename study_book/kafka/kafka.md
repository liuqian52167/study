kafka:消息系统、存储系统、流式处理平台
# 第一章
## 1.1 基本概念
kafka体系框架包括若干Producer、Broker、Consumer以及一个Zookeeper集群，zookeeper是kafka用来管理集群数据管理、控制器选举
等操作的。Producer将消息发送到Broker，broker 将收到的信息存储到磁盘中，而consumer负责从Broker订阅并消费信息。

生产者：Producer，发送消息的一方。生产者负责创建消息，然后投递到kafka中
消费者：Consumer，接收消息的一方。消费者连接到kafka上接收信息，进行业务逻辑处理。
服务代理节点：Broker，对kafka来说Broker 可以看作独立的服务节点，或者kafka服务实例。一个或者多个Broker组成集群。

Topic 主题 ，kafka的消息按topic 归类，生产者将消息发送到待定的主题，消费者订阅主题来进行消费

Partion 分区，主题是一个逻辑概念。它可以细分多个分区，有一个分区属于单个主题，有时候叫主题分区。同一主题的不同分区包含
信息不同，分区在存储界面可以看作一个追加的日志文件LOG，消息在被追加到分区的日志文件时候都会分配一个偏移量offset。唯一标识
用它来区分分区的顺序性。

kafka问分区引入了多副本机制，通过增加副本数量可以提升容灾能力。统一分区的不同副本保存相同的信息（同一时刻并非完全一样），
副本之间
# 第二章
##2.1客户端开发
    正常的生产逻辑：
    1配置生产者客户端参数创建相应的生产者实例
    2构建待发送的消息
    3发送消息
    4关闭生产者实例

生产者代码
   
    public class kafkaProducerAnalysis{
        public static final String BrokerList="localhost:9092";
        public static final String topic="topic-demo";
        public static Properties initConfig(){
            Properties props = new Properties();
            props.put("bootstrap.servers",brokerList);
            props.put("key.servers" ,"org.apache.kafka.Common.srialization.StringSerializer");
            props.put("value.serializer","org.apache.kafka.Common.srialization.StringSerializer");
            properties.put("client.id","producer.client.id.demo");
            return props;
         }
         
    public static void main (String[] args){
        Properties props = initConfig();
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        ProducerRecord<String,String> record
    }
    }
# 第三章
# 第四章
# 第五章
# 第六章
# 第七章
# 第八章
# 第九章
# 第十章
# 第十一章
# 第十二章

