package com.jt.redis.bin;

import org.junit.Test;

public class BinTest {
	@Test
	public void test01(){
		byte a=55;
		System.out.println(Integer.toBinaryString(a));
		for (int i = 0; i < 8; i++) {
			int move=8-i-1;
			int result=(a>>move)&1;
			System.out.println();
		}
	}
	
}
