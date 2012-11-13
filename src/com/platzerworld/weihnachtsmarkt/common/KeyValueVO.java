package com.platzerworld.weihnachtsmarkt.common;

import java.io.Serializable;

import com.platzerworld.weihnachtsmarkt.common.adapter.EqualsUtil;
import com.platzerworld.weihnachtsmarkt.common.adapter.HashCodeUtil;



public class KeyValueVO implements Serializable, Comparable<KeyValueVO>{
	private static final long serialVersionUID = 7652075763595799928L;

	public long key;
	
	public String value;
	
	public KeyValueVO(){
		
	}
	
	public KeyValueVO(long key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}
	
	@Override 
	public int hashCode() {
		 int result = HashCodeUtil.SEED;
		 result = HashCodeUtil.hash( result, value );
		 return result;
	}

	@Override
	public int compareTo(KeyValueVO otherKeyValueVO) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;
	    if ( this == otherKeyValueVO ) return EQUAL;
	    
	    int comparison = this.value.compareTo(otherKeyValueVO.value);
	    if ( comparison != EQUAL ) return comparison;
	    
	    return EQUAL;
	}
	
	@Override 
	public boolean equals(Object otherKeyValueVO) {
		if ( this == otherKeyValueVO ) return true;
	    KeyValueVO that = (KeyValueVO)otherKeyValueVO;

	    return EqualsUtil.areEqual(this.key, that.key);
	}

}
