package com.jt.easymall.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisConfig {
	@Value("${spring.redis.nodes}")
	private String nodes;
	@Value("${spring.redis.maxTotal}")
	private Integer maxTotal;
	@Value("${spring.redis.minIdle}")
	private Integer minIdle;
	@Value("${spring.redis.maxIdle}")
	private Integer maxIdle;
	//构造连接池对象
		@Bean
		public ShardedJedisPool getPool(){
			//收集节点信息
			List<JedisShardInfo> infoList=new ArrayList<JedisShardInfo>();
			//截取,将nodes利用"," 截取成单个的连接和端口
			String[] node = nodes.split(",");//{"10.9.9.9:6379","",""}
			for (String hostAndPort : node) {
				//"10.9.9.9:6379"
				String host=hostAndPort.split(":")[0];
				Integer port=Integer.parseInt(hostAndPort.split(":")[1]);
				infoList.add(new JedisShardInfo(host,port));
			}
			//配置config
			JedisPoolConfig config =new JedisPoolConfig();
			config.setMaxTotal(maxTotal);
			config.setMaxIdle(maxIdle);
			config.setMinIdle(minIdle);
			ShardedJedisPool pool=new ShardedJedisPool(config,infoList);
			return pool;
		}
}
