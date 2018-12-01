package com.jt.easymall.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class ClusterConfig {
	
	@Value("${spring.redis.cluster.nodes}")
	private String nodes;
	@Value("${spring.redis.maxTotal}")
	private Integer maxTotal;
	@Value("${spring.redis.minIdle}")
	private Integer minIdle;
	@Value("${spring.redis.maxIdle}")
	private Integer maxIdle;
	@Bean
	public JedisCluster getInstance(){
		//收集信息
		Set<HostAndPort> infoSet=new HashSet<HostAndPort>();
		String[] node=nodes.split(",");//192.168.60.131:8000数组
		for (String hostAndPort : node) {
			//每次获取192.168.60.131:8000
			String host=hostAndPort.split(":")[0];
			Integer port=Integer.parseInt(hostAndPort.split(":")[1]);
			infoSet.add(new HostAndPort(host, port));}
		//配置对象
		GenericObjectPoolConfig config=new GenericObjectPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setMinIdle(minIdle);
		JedisCluster cluster=new JedisCluster(infoSet,config);
		return cluster;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
