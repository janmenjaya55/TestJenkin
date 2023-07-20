
package com.bata.billpunch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) throws ParseException {

		System.out.println("new string buffer>>>>>>>>>>>>>>>final");

		Map<String, Integer> maptest = new HashMap<>();
		maptest.put("x", 1);
		maptest.put("y", 2);
		maptest.put("z", 3);
		// Stream<Integer> valuesStream = maptest.stream();
		// Stream<String> keysStream = maptest.stream();
		//maptest.entrySet() We can obtain a set of key-value pairs:
		// someMap.keySet(); We can also get the key set associated with the Map
		//someMap.values(); we could work directly with the set of values:
		Optional<String> vn=maptest.entrySet().stream().filter(e->(e.getValue()>=1)).map(Map.Entry::getKey).findFirst();
		System.out.println("kkkkkkkkkkkkkkkkkkkkkk"+vn.get());

		// with filter
		/*
		 * maptest.entrySet().stream().filter(e ->
		 * "Effective Java".equals(e.getValue())) .map(Map.Entry::getKey) .findFirst();
		 */
		// without filter
		// maptest.entrySet().stream()
		// .map(Map.Entry::getKey)
		// .findFirst();
		List<Integer> longnames = maptest.values().stream().filter(e -> (e.intValue() > 1))
				.collect(Collectors.toList());
		System.out.println("new string buffer>>>>>>>>>>>>>>>maptest" + maptest.get("x"));
		System.out.println("new string buffer>>>>>>>>>>>>>>>maptest" + longnames);

		List<String> namesx = new ArrayList<String>();
		namesx.add("x");
		namesx.add("y");
		List<String> newlist = new ArrayList<String>();
		namesx.forEach(e -> {

			newlist.add(e);

		});
		System.out.println(">>>>>>arraylist>>>>>>>>>>>>>>>>." + newlist);
		List<Integer> newintlist = new ArrayList<Integer>();
		List<Integer> newintlistone = new ArrayList<Integer>();
		List<Integer> intlist = Arrays.asList(1, 2, 3);
		List<Integer> intlistone = Arrays.asList(11, 22, 33);

		System.out.println("newintlist data>>>>>>>" + newintlist);
		intlist.forEach(e -> {
			intlistone.forEach(e1 -> {
				newintlist.add(e1);
			});
			if (newintlist.contains(e)) {
				newintlistone.add(e);
			}
			System.out.println("datatatatattttaa"+e);

		});

		System.out.println(
				">>>>>>arraylist>>>>>>>>>>>>>>>>.test" + newintlistone + ">>>>>>>>>>><<<<<<<<<<<" + newintlist);

		StringConcatinterface sx = (x, y) -> {
			return x + y;
		};
		//StringConcatinterface sy = obj::say();
		//StringConcatinterface sy = obj::sayLouder();
		
		StringConcatinterface sz = (x, y) -> {
			return x + y;
		};

		System.out.println(">>>>>>>>>>>>>>>>>>>ts" + sx.sconcat("1", "2"));
		
		
		
		
	}

	StringConcatinterface mx = (x, y) -> {
		return x + y;
	};

	@FunctionalInterface
	interface StringConcatinterface {


		  // Abstract method    
	    public String sconcat(String a, String b);
	    
	    // default method    
	    default void say(){    
	        System.out.println("Hello, this is default method");    
	    }    
	    
	    // static method    
	    static void sayLouder(String msg){    
	        System.out.println(msg);    
	    }    
	  //  public String sconcatnew(String a, String b); only one abstract method is possible. 
	    //and more than one default method  with body possible.
	    //and static methods  also used
	    //no of java eight features
	    //lamda exp,foreachloop,sort,optioalnull,iterate forEachRemaining,stream api,functional interface
	}
	//A a1 = Test::getInfo; // refering to pre-existing getInfo(String info) of class Test as arguments are same as getName(String name)
	//a1.getName("getInfo() of Test class is executing"); 
	//Test as object for that entity and getInfo() is a method present inside that Entity class

}