package com.fiberhome.redis;

import redis.clients.jedis.Jedis;

/**
 * @description: 描述
 * @author: ws
 * @time: 2020/9/17 14:14
 */
public class Demo {
    public static void main(String[] args) {
        String host = "27.1.23.31";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.auth("fhredisdb");

        System.out.println(jedis.ping());

    }

}
