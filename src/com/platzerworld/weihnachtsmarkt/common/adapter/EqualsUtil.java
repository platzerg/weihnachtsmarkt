package com.platzerworld.weihnachtsmarkt.common.adapter;

public final class EqualsUtil {

	  static public boolean areEqual(boolean value, boolean valueToCompare){
	    return value == valueToCompare;
	  }

	  static public boolean areEqual(char value, char valueToCompare){
	    return value == valueToCompare;
	  }

	  static public boolean areEqual(long value, long valueToCompare){
	    return value == valueToCompare;
	  }

	  static public boolean areEqual(float value, float valueToCompare){
	    return Float.floatToIntBits(value) == Float.floatToIntBits(valueToCompare);
	  }

	  static public boolean areEqual(double value, double valueToCompare){
	    return Double.doubleToLongBits(value) == Double.doubleToLongBits(valueToCompare);
	  }

	  static public boolean areEqual(Object value, Object valueToCompare){
	    return value == null ? valueToCompare == null : value.equals(valueToCompare);
	  }
	}
