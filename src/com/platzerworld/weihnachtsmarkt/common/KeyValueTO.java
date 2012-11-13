package com.platzerworld.weihnachtsmarkt.common;

import java.io.Serializable;

public class KeyValueTO implements Serializable{
	private static final long serialVersionUID = 7652075763595799928L;

	public long key;
	
	public String value;
	
	public KeyValueTO(){
		
	}
	
	public KeyValueTO(long key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}

}
