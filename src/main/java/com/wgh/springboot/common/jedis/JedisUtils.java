package com.wgh.springboot.common.jedis;

import groovy.beans.DefaultPropertyReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static final Logger log = Logger.getLogger(JedisUtils.class);
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 15 * 1000;
    //超时时间
    private static int TIMEOUT = 10 * 1000;
    private static String HOST="47.105.89.125";
    private static int PORT=6379;
    private static String AUTH="wgh5449723.";

    private static JedisPool jedisPool = null;

    /**
     * Jedis实例获取返回码
     *
     * @author jqlin
     *
     */
    public static class JedisStatus{
        /**Jedis实例获取失败*/
        public static final long FAIL_LONG = -5L;
        /**Jedis实例获取失败*/
        public static final int FAIL_INT = -5;
        /**Jedis实例获取失败*/
        public static final String FAIL_STRING = "-5";
    }


    private static void initialPool() {
       /* //Redis服务器IP
        String HOST = (String) DefaultPropertyReader.INSTANCE.read("redis.host","redis.properties");
        //Redis的端口号
        int PORT = (int) DefaultPropertyReader.INSTANCE.read("redis.port","redis.properties");
        //访问密码
        String AUTH = (String) DefaultPropertyReader.INSTANCE.read("redis.password","redis.properties");*/

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数，如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(300);
            //最大空闲数，控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
            config.setMaxIdle(8);
            //最小空闲数
            config.setMinIdle(2);
            //是否在从池中取出连接前进行检验，如果检验失败，则从池中去除连接并尝试取出另一个
            config.setTestOnBorrow(true);
            //在return给pool时，是否提前进行validate操作
            config.setTestOnReturn(true);
            //在空闲时检查有效性，默认false
            config.setTestWhileIdle(true);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
            //这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(30000);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(60000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(1000);
            //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(MAX_WAIT);

            if (StringUtils.isNotBlank(AUTH)) {
                jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, AUTH);
            } else {
                jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
            }
        } catch (Exception e) {
            if(jedisPool != null){
                jedisPool.close();
            }
            log.error("初始化Redis连接池失败", e);
        }
    }

    /**
     * 初始化Redis连接池
     */
    static {
        initialPool();
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }

        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            log.error("同步获取Jedis实例失败" + e.getMessage(), e);
            returnBrokenResource(jedis);
        }

        return jedis;
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    @SuppressWarnings("deprecation")
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }

    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }
    public void setKeyValueWithTimeOut(String key, String value, Long timeOutOfSecond) {
        Jedis jedis = getJedis();
        boolean keyExist = jedis.exists(key);
        // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
        if (keyExist) {
            jedis.del(key);
        }
        jedis.set(key, value, "NX", "EX", timeOutOfSecond);
        returnResource(jedis);
    }
    public String getValueByKey(String key){
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        returnResource(jedis);
        return  value;
    }
    public void delKey(String key){
        Jedis jedis = getJedis();
        Long value = jedis.del(key);
        returnResource(jedis);
    }
}
