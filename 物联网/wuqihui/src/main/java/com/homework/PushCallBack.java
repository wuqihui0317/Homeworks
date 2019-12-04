package com.homework;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class PushCallBack implements MqttCallback {
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + "qchTopic");
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收到的文件存储在根目录的received.txt文件");
        System.out.println("接收消息内容 : \n" + new String(message.getPayload()));
        FileOutputStream fis = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\" +
                "软院学习资料\\物联网\\qch\\received.txt");
        OutputStreamWriter isr = new OutputStreamWriter(fis);
        isr.write(new String(message.getPayload()));
        isr.flush();
        isr.close();
    }
}
