//
//  Android PDF Writer
//  http://coderesearchlabs.com/androidpdfwriter
//
//  by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com)
//

package com.platzerworld.weihnachtsmarkt.common.pdf;

public class Stream extends EnclosedContent {

	public Stream() {
		super();
		setBeginKeyword("stream",false,true);
		setEndKeyword("endstream",false,true);
	}

}