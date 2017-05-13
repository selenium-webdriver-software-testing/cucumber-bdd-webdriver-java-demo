package com.softwaretestingboard.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHashMap {
	
	private Map mMap = new HashMap();
	private String keyValue;
	
	public void addItem(String key, String value) {
		mMap.put(key, value);
    }
	
	public String getValue(String key){
		Iterator iter = mMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			if (mEntry.getKey().toString().equals(key)){
				keyValue = mMap.get(key).toString();
				break;
			}
			//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		}
		return keyValue;
	}

}
