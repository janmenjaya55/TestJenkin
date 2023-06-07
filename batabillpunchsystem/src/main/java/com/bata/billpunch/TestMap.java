package com.bata.billpunch;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;

public class TestMap {
	
	public static void main(String[] args) {
		
		
		Map<String, String> mapdata=new HashedMap<String, String>();
		mapdata.put("1", "J");
		mapdata.put("11", "K");
		mapdata.put("111", "L");
		
		List<String> list=mapdata.entrySet().stream().filter(e->(e.getKey().equalsIgnoreCase("11"))).
				map(Map.Entry::getValue).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>...."+list);
		//Describe each part
		//List<String> list= for return store in list
		//mapdata.entrySet() to get all the key value pair from Map
		//.stream() for get the stream as list for further work on in
		//filter(e->(e.getKey().equalsIgnoreCase("11"))) for condition wise data selection
		//map(Map.Entry::getValue) for get the value /key as per the required.
		//collect(Collectors.toList()) for final collection after all the work and assign to the list. 
		//BillPunchDetailsModel::getArticleName (leftpart::rightpart leftpart for object and right part the method need to call present inside the object entity.)
		List<String> newlist=mapdata.entrySet().stream().filter(e->(e.getValue()).equalsIgnoreCase("L")).map(Map.Entry::getKey).
				collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>newlist...."+newlist);
		
		List<String> values=mapdata.values().stream().filter(e->e.equalsIgnoreCase("J")).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>values...."+values);
		
		List<String> keys=mapdata.keySet().stream().filter(e->e.equalsIgnoreCase("11")).collect(Collectors.toList());
		System.out.println("Map output>>>>>>>>>>>>>>>keys...."+keys);
		
		
		
		
	}

}
