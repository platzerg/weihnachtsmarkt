package com.platzerworld.weihnachtsmarkt.common.style;

import android.content.Context;
import android.graphics.Typeface;

public class StyleManager {
	private static Context mContext;
	static private StyleManager _instance;
	private static Typeface font;
	
	private static final String droidsan_bold = "fonts/DroidSans-Bold.ttf";
	private static final String droidsan = "fonts/DroidSans.ttf";
	private static final String droidsan_mono = "fonts/DroidSansMono.ttf";
	
	private static final String droidserif_bold = "fonts/DroidSerif-Bold.ttf";	
	private static final String droidserif_bold_italic = "fonts/DroidSerif-BoldItalic.ttf";
	private static final String droidserif_serif_italic = "fonts/DroidSerif-Italic.ttf";
	private static final String droidserif_serif_regular = "fonts/DroidSerif-Regular.ttf";
	
	
	private static final String roboto_bold_condensed = "fonts/Roboto-BoldCondensed.ttf";
	private static final String roboto_bold_condensed_italic = "fonts/Roboto-BoldCondensedItalic.ttf";	
	private static final String roboto_condensed = "fonts/Roboto-Condensed.ttf";
	private static final String roboto_condensed_italic = "fonts/Roboto-CondensedItalic.ttf";
	
	private static final String chantelle_antiqua = "fonts/Chantelli_Antiqua.ttf";

	private StyleManager() {

	}

	/**
	 * Requests the instance of the Sound Manager and creates it if it does not
	 * exist.
	 * 
	 * @return Returns the single instance of the SoundManager
	 */
	static synchronized public StyleManager getInstance() {
		if (_instance == null)
			_instance = new StyleManager();
		return _instance;
	}

	/**
	 * Initialises the storage for the sounds
	 * 
	 * @param theContext
	 *            The Application context
	 */
	public StyleManager init(Context theContext) {
		mContext = theContext;
		font = Typeface.createFromAsset(mContext.getAssets(), roboto_bold_condensed);
		return this;
	}

	public static void cleanup() {
		_instance = null;

	}

	public Typeface getTypeface() {
		return font;
	}
	
	public Typeface getTypefaceDroidsanBold() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidsan_bold);
		return font;
	}
	
	public Typeface getTypefaceDroidsan() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidsan);
		return font;
	}
	
	public Typeface getTypefaceDroidsanMono() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidsan_mono);
		return font;
	}
	
	public Typeface getTypefaceDroidserifBold() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidserif_bold);
		return font;
	}
	
	public Typeface getTypefaceDroidserifBold_idItalic() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidserif_bold_italic);
		return font;
	}
	
	public Typeface getTypefaceDroidserifSerifItalic() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidserif_serif_italic);
		return font;
	}
	
	public Typeface getTypefaceDroidserifSerifRegular() {
		font = Typeface.createFromAsset(mContext.getAssets(), droidserif_serif_regular);
		return font;
	}
	
	public Typeface getTypefaceRobotoBoldCondensed() {
		font = Typeface.createFromAsset(mContext.getAssets(), roboto_bold_condensed);
		return font;
	}
	
	public Typeface getTypefaceRobotoBoldCondensedItalic() {
		font = Typeface.createFromAsset(mContext.getAssets(), roboto_bold_condensed_italic);
		return font;
	}
	
	public Typeface getTypefaceRobotoCondensed() {
		font = Typeface.createFromAsset(mContext.getAssets(), roboto_condensed);
		return font;
	}
	
	public Typeface getTypefaceRobotoCondensedItalic() {
		font = Typeface.createFromAsset(mContext.getAssets(), roboto_condensed_italic);
		return font;
	}
	
	public Typeface getTypefaceChantelleAntiqua() {
		font = Typeface.createFromAsset(mContext.getAssets(), chantelle_antiqua);
		return font;
	}

}