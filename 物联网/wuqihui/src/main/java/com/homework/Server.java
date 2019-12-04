package com.homework;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.*;


public class Server {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://192.168.1.112:1883";
    //定义一个主题
    public static final String TOPIC = "wqhTopic";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "wuqihui";
    private String passWord = "123456";

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public Server() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallBack());
            client.connect(options);

            topic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException, FileNotFoundException, IOException {
        Server server = new Server();
        FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\" +
                "软院学习资料\\物联网\\qch\\send.txt");
        //FileInputStream fis = new FileInputStream("./要发送的文件.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        int n ;
        char[] str = new char[10];
        StringBuilder sb = new StringBuilder("");
        while((n=isr.read(str)) != -1) {
            sb.append(str,0,n);
        }
        isr.close();
        fis.close();
        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        server.message.setPayload(sb.toString().getBytes());
        server.publish(server.topic , server.message);
        System.out.println(server.message.isRetained() + "------ratained状态");
    }
}
