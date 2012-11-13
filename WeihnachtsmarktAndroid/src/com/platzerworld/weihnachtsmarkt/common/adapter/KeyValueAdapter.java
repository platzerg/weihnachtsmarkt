package com.platzerworld.weihnachtsmarkt.common.adapter;

import java.util.Enumeration;
import java.util.Hashtable;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.platzerworld.weihnachtsmarkt.common.KeyValueVO;

public class KeyValueAdapter extends ArrayAdapter<String> {
	private final Hashtable<String, KeyValueVO> _data;
	private final String[] _keys;

	public KeyValueAdapter(Context context, Hashtable<String, KeyValueVO> objects) {
		// To make the drop down a simple text box
		super(context, android.R.layout.simple_spinner_item);
		_data = objects;

		// To make the drop down view a radio button list
		setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// TODO; Do your own data massaging here to present the data in a
		// ordered list.

		// Assign hash keys with a position so that we can present and retrieve
		// them
		int i = 0;
		_keys = new String[_data.size()];

		for (final Enumeration<String> e = _data.keys(); e.hasMoreElements();) {
			_keys[i++] = e.nextElement().toString();
		}
	}

	public int getCount() {
		return _data.size();
	}

	/**
	 * Returns the camera name for the camera in the specified position
	 */
	@Override
	public String getItem(int position) {
		// TODO: return the value based on the position. This is displayed in
		// the list.
		KeyValueVO k = _data.get(String.valueOf(position));
		return _keys[position];
	}

	/**
	 * Returns the cameraKey for the camera in the specified position
	 */
	public long getItemId(int position) {
		// TODO: Return an id to represent the item.

		return position; // if this function is of no use to you simply return
							// the position.
							// if your identifier is a different return type
							// then you can make
							// your own method eg: public String
							// getPromaryKey(int position) and
							// call it where you need your identifier.
	}
	

	public int getIndexFromElement(ArrayAdapter adapter, String element) {
	      for(int i = 0; i < adapter.getCount(); i++) {
	            if(adapter.getItem(i).equals(element)) {
	                  return i;
	            }
	      }
	      return 0;
	}

}
