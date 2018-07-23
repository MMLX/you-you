package com.taotao.jedis;

public interface JedisClient {
	/**
	 * 存入string类型数据到redis中
	 * @param key  键
	 * @param value 值
	 * @return 存入的记录条数
	 */
	String set(String key, String value);
	/**
	 * 根据key取value redis中string类型的数据
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	/**
	 * 设置key的生存时间
	 * @param key
	 * @param seconds 秒为单位
	 * @return
	 */
	Long expire(String key, int seconds);
	/**
	 * 查看key的生存时间
	 * @param key
	 * @return
	 */
	Long ttl(String key);
	/**
	 * key递增
	 * @param key
	 * @return
	 */
	Long incr(String key);
	
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
}
