package com.bata.billpunch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMind {
	
	//https://intellipaat.com/blog/interview-question/java-8-interview-questions/
	//@FunctionalInterface
	interface MyFunctionalInterface {
		
		public int incrementByFive(int a);//method signature // Abstract method    
		//public int incrementByFiveb(int b);// max one abstarct method automatically says functional interface 
		//@FunctionalInterface present or not
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		MyFunctionalInterface rf=(int a)->{
			return ++a;
		};
		
		System.out.println("the desire output for the above coding is >>>>"+rf.incrementByFive(5));
		
		
		//###############################################################
		//java.util.StringJoiner.StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
		StringJoiner sx = new StringJoiner(",", "#", "#");
	    sx.add("Questions");
	    sx.add("Answers");
	    System.out.println("String after adding # in suffix and prefix :");
	    System.out.println(sx);
	    
	  //################################################
		//use of() for adding as list data directly
		Integer max = Stream.of(1, 2, 3, 4, 5, 6,7)
	            .max(Comparator.comparing(Integer::valueOf))
	            .get();
		
	         System.out.println("The Maximum number is: " + max);
	         
	         
	   	  //################################################      
	         //directly foreachloop for iterate the data
	         List<String> stringList = Arrays.asList("Hello","Interview","Questions","Answers","Ram","for");
	         stringList.stream().filter(str -> str.length() > 3).forEach(System.out::println);
	         
	         //################################################     
	         //for get the map with i=i*3 conditions;
	         
	         List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7);
	         System.out.println(integerList.stream().map(i -> i*3).collect(Collectors.toList()));
	         
	         //################################################   
	         //concat between 2 stream
	         List<Integer> integerList1 = Arrays.asList(1,2,3,4);
	         List<Integer> integerList2 = Arrays.asList(5,6,7);
	         Stream<Integer> concatStream = Stream.concat(integerList1.stream(), integerList2.stream());
	         concatStream.forEach(System.out::print);
	         
	       //################################################  
	         //for remove the duplicate
	   	  List<Integer> integerLista = Arrays.asList(1,2,3,4,1,2,3);
	         System.out.println("After removing duplicates : ");
	         integerLista.stream().collect(Collectors.toSet()).forEach(System.out::print);	
	         //################################################  
	         //map(i -> i*i*i) for new value to map and then filter so as per req we have to use 
	         List<Integer> integerListd = Arrays.asList(4,5,6,7,1,2,3);
	         integerListd.stream().map(i -> i*i*i).filter(i -> i>50).forEach(System.out::println);
	         
	         //################################################ 
	         //local date
	         LocalDate currentDate = LocalDate.now();
	         System.out.println(currentDate);
	         //local time
	         LocalTime currentTime = LocalTime.now();
	         System.out.println(currentTime);
	         
	}
	
	  
  }

		
