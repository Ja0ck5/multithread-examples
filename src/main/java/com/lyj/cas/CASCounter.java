package com.lyj.cas;

public class CASCounter {
	
	private SimulatedCAS value;
	
	public int getValue() {
		return value.get();
	}

	public int increment(){
		int v;
		do{
			v = getValue();
		}while(v != value.compareAndSwap(v, v + 1));
		return v +1;
	}
}
