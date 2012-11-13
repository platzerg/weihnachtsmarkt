package com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt;

import android.database.Cursor;
import android.widget.FilterQueryProvider;

import com.platzerworld.weihnachtsmarkt.common.logging.LOG;

public class WeihnachtsmarktFilterQueryProvider implements FilterQueryProvider {

	private static final String TAG = "GeoKontaktFilterQueryProvider";

	private final WeihnachtsmarktSpeicher mSpeicher;

	public WeihnachtsmarktFilterQueryProvider(WeihnachtsmarktSpeicher speicher) {
		mSpeicher = speicher;
	}

	public Cursor runQuery(CharSequence nameFilter) {

		if (nameFilter != null && nameFilter.length() > 0) {
			LOG.v(TAG, "filter with " + nameFilter);
			return mSpeicher.ladeBiergartenListe(nameFilter);
		} else {
			return mSpeicher.ladeBiergartenListe(null);
		}
	}
}
