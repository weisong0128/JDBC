package com.fiberhome.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;

/**
 * @description: jdbc连接mongo库示例
 * @author: ws
 * @time: 2020/9/15 15:42
 */
public class Demo {
    private static final String host = "172.16.49.33";
    private static final int port = 27024;

    private static final String user = "fhmgdb";
    private static final String authDatabase = "admin";
    private static final String password = "wzmgdb";

    public static void main(String[] args) {
        try {
            //1.host信息
            ServerAddress sa = new ServerAddress(host, port);
            //2.鉴权信息（用户、密码、库名）
            MongoCredential mongoCredential = MongoCredential.createCredential(user, authDatabase, password.toCharArray());
            ArrayList<MongoCredential> credentialList = new ArrayList<>();
            credentialList.add(mongoCredential);
            //3.连接池信息配置

            //4.连接到mongodb服务
            MongoClient mongoClient = new MongoClient(sa, credentialList);
            //5.连接数据库,输入库名
            MongoDatabase db = mongoClient.getDatabase("uqbing");

            //获取集合（表）
            MongoCollection<Document> collection = db.getCollection("uqbing_history");

            //操作集合
            //1.遍历集合中数据
            FindIterable<Document> documents = collection.find();
            for (Document document : documents) {
                System.out.println(document);
            }
            //2.插入数据...

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
