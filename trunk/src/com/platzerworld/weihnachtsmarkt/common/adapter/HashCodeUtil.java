package com.platzerworld.weihnachtsmarkt.common.adapter;

import java.lang.reflect.Array;

public final class HashCodeUtil {

	  public static final int SEED = 23;

	  public static int hash( int aSeed, boolean aBoolean ) {
	    return firstTerm( aSeed ) + ( aBoolean ? 1 : 0 );
	  }

	  /**
	  * chars.
	  */
	  public static int hash( int aSeed, char aChar ) {
	    return firstTerm( aSeed ) + (int)aChar;
	  }

	  /**
	  * ints.
	  */
	  public static int hash( int aSeed , int aInt ) {
	    return firstTerm( aSeed ) + aInt;
	  }

	  /**
	  * longs.
	  */
	  public static int hash( int aSeed , long aLong ) {
	    return firstTerm(aSeed)  + (int)( aLong ^ (aLong >>> 32) );
	  }

	  /**
	  * floats.
	  */
	  public static int hash( int aSeed , float aFloat ) {
	    return hash( aSeed, Float.floatToIntBits(aFloat) );
	  }

	  /**
	  * doubles.
	  */
	  public static int hash( int aSeed , double aDouble ) {
	    return hash( aSeed, Double.doubleToLongBits(aDouble) );
	  }

	  public static int hash( int aSeed , Object aObject ) {
	    int result = aSeed;
	    if ( aObject == null) {
	      result = hash(result, 0);
	    }
	    else if ( ! isArray(aObject) ) {
	      result = hash(result, aObject.hashCode());
	    }
	    else {
	      int length = Array.getLength(aObject);
	      for ( int idx = 0; idx < length; ++idx ) {
	        Object item = Array.get(aObject, idx);
	        result = hash(result, item);
	      }
	    }
	    return result;
	  }

	  private static final int fODD_PRIME_NUMBER = 37;

	  private static int firstTerm( int aSeed ){
	    return fODD_PRIME_NUMBER * aSeed;
	  }

	  private static boolean isArray(Object aObject){
	    return aObject.getClass().isArray();
	  }
	} 