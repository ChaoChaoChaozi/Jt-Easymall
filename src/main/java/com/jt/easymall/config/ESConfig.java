package com.jt.easymall.config;

import org.springframework.beans.factory.annotation.Value;

public class ESConfig  {
	//implements
	@Value("{cluster-name}")
	private String name;
	@Value("{cluster-modes}")
	private String modes;
//	private TransportClient client;
	
	
}
