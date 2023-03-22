package com.bata.billpunch;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;

public class TestMap {

	public static void main(String[] args) {

		Map<String, String> mapdata = new HashedMap<String, String>();
		mapdata.put("123", "Z");
		mapdata.put("11", "K");
		mapdata.put("111", "L");

		// to get both keys and values from a map.
		List<String> list = mapdata.entrySet().stream().filter(e -> (e.getKey().equalsIgnoreCase("11")))
				.map(Map.Entry::getValue).collect(Collectors.toList());

		System.out.println("Map output>>>>>>>>>>>>>>>list are ...." + list);
		// Describe each part
		// List<String> list= for return store in list
		// mapdata.entrySet() to get all the key value pair from Map
		// .stream() for get the stream as list for further work on in
		// filter(e->(e.getKey().equalsIgnoreCase("11"))) for condition wise data
		// selection
		// map(Map.Entry::getValue) for get the value and set a map as per the required.
		// collect(Collectors.toList()) for final collection after all the work and
		// assign to the list.
		// BillPunchDetailsModel::getArticleName (leftpart::rightpart leftpart for
		// object and right part the method need to call present inside the object
		// entity.)

		// to get both keys and values from a map.
		List<String> newlist = mapdata.entrySet().stream().filter(e -> (e.getValue()).equalsIgnoreCase("L"))
				.map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>newlist...." + newlist);

		// only for get the values from map
		List<String> values = mapdata.values().stream().filter(e -> e.equalsIgnoreCase("J"))
				.collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>values...." + values);

		// only for get the keys values from map
		List<String> keys = mapdata.keySet().stream().filter(e -> e.equalsIgnoreCase("11"))
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>keys...." + keys);

		// only for natural reverse order sorted
		List<String> keysort = mapdata.keySet().stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		//by using the values reverse order
		List<Entry<String, String>> keysorts=mapdata.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>keys keysorts...." + keysorts);
		
		//by using the keys reverse order
		List<Entry<String, String>> keysortsKeys=mapdata.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toList());
				
		//keysort.sort((o1,o2)->o1.getDistcode().compareTo(o2.getDistcode())); for real entity data
		System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsKeys...." + keysortsKeys);
		
		Map<Object, Object> keysortsKeysort=mapdata.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(
			    Map.Entry::getKey, 
			    Map.Entry::getValue, 
			    (oldValue, newValue) -> newValue, LinkedHashMap::new));
		
		System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsKeysort...." + keysortsKeysort);
		
		Map<Object, Object> keysortsKeysortobj=mapdata.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(
						Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(),
		                        (entry1, entry2) -> entry2, LinkedHashMap::new));
		
		System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsKeysort...." + keysortsKeysortobj);
		
		Map<Object, Object> keysortsKeysortobkj=mapdata.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
							    (entry1, entry2) -> entry2, LinkedHashMap::new));//by method ref ::
		
		System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsKeysort...." + keysortsKeysortobkj);
		
		
		//by using the keys
				List<Entry<String, String>> keysortskey=mapdata.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
				System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsvalkeysortsvalkeysortskykey...." + keysortskey);
				
				
				//by using the values
						List<Entry<String, String>> keysortsval=mapdata.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
						System.out.println("Map output>>>>>>>>>>>>>>>keys keysortsvalkeysortsvalkeysortsval...." + keysortsval);
		
		
		
	}

}
