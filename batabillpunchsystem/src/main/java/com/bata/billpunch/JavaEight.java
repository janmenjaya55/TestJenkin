package com.bata.billpunch;
import java.awt.Button;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaEight {
	//function interface
	@FunctionalInterface
	interface MyFunctionalInterface {
		
		public int incrementByFive(int a);//method signature
	}
	@FunctionalInterface
	interface MyFunctionalInterfaceone {

		public int myMethod(int num);
	}
	
   //functional interface have mutliple methods max 1 abstarct
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
	}
	
	interface NormalInterface {

	    public String sconcat(String a, String b);
	    public String sconcatOne(String a, String b);
	    public String sconcatTwo(String a, String b);
	  
	}

	public static void main(String[] args) {
		Frame frame = new Frame("ActionListener Before Java8");

		Button b = new Button("Click Here");
		b.setBounds(50, 100, 80, 50);
		// (s ->) //like create object and its implementaion after {}
		b.addActionListener(s -> {
			System.out.println("java eight features start");
		});
		frame.add(b);

		frame.add(b);

		frame.setSize(200, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		//functional interface work logic and hold the ref by interface
	
		MyFunctionalInterface f = (num) ->{
		return num+5;
		};//logic part
        System.out.println("finctional interface work"+f.incrementByFive(22));//method call part
        
     // multi parameter lamda expression
    	
    	StringConcatinterface sx=(str1,str2)->{
    		return str1+ str2;
    	};
    	
        System.out.println(" interface work iwth lamda exp.."+sx.sconcat("JAVA","EIGHT"));
// for each loop with java 8
		
        List<String> list=new ArrayList<String>();  
        List<String> listnew=new ArrayList<String>(); 
        list.add("Rick");         
        list.add("Negan");       
        list.add("Daryl");         
        list.add("Glenn");         
        list.add("Carl");                
        list.forEach(          
            // lambda expression        
        	//add("Rick");
            (names)->System.out.println(names)         
        );  
        
        
        list.forEach(  s->       {
        	String ss=new String("JAVA");
        	//ss.concat("CODE");
        	System.out.println(">>>>>>>>>>>>>>>####"+s);
        	ss.concat(s.toString());
        	listnew.add(ss+s);
        }
                   
            );  
        
        System.out.println(">>>>>>>>>>>>>>>"+listnew.get(0));
            
       // pass the arg through interface method to concrete class 
        JavaEight obj = new JavaEight();   
    	// Method reference using the object of the class
        MyFunctionalInterfaceone reff = obj::myMethod;  
        reff.myMethod(6);
        
        System.out.println("get the value through method ref>>>>>>>>>>>>>>>>>>>>"+reff.myMethod(6));
    	// Calling the method of functional interface  
    	//ref.incrementByFive(5);  
    	
    	   MyFunctionalInterface reftest = (x)->{
    		   return x*x;
    	   };  //mehod logic
    	System.out.println("refttttttttttttttttttttt"+reftest.incrementByFive(6));//method call
    	//Stream api filter
    	
    	List<String> names = new ArrayList<String>();
    	names.add("Ajeet");
    	names.add("Negan");
    	names.add("Aditya");
    	names.add("Steve");
    		
    	//Using Stream and Lambda expression
    	long count = names.stream().filter(str->str.length()<6).count();
    	//names.stream().filter(str->str.length()<6).collect(Collectors.toList());
    	System.out.println("There are "+count+" strings with length less than 6");
        
    	//foreach loop
    	
    	List<String> namess = new ArrayList<String>();
    	namess.forEach(responseList->{
    		String resp=new String();
        	resp.concat("V");
        });
    	
    	//use Collectors.toList() to add in new list
    	
    	List<String> namesx = Arrays.asList("Melisandre","Sansa","Jon","Daenerys","Joffery");

        List<String> longnames = names.stream()    // converting the list to stream
                .filter(str -> str.length() > 6)   // filter the stream based on condition to create a new stream
                .collect(Collectors.toList());  // collect the final stream and convert it to a List

        longnames.forEach(System.out::println); //here () shifted to :: and replace the . value as method calling
       // System.out.println();
        
        
        
        // use map with java 8
        
        
        List<Integer> num = Arrays.asList(1,2,3,4,5,6);
        List<Integer> squares = num.stream() // converting the list to stream
        		.map(n -> n * n)   // map the stream based on condition to create a new stream
        		.collect(Collectors.toList()); // collect the final stream and convert it to a List
        System.out.println("Map work with java 8 features"+squares);        

       // num.stream().findFirst()
        System.out.println("internalmethods>>>>>>>>>>>"+ num.stream().findFirst().get());
        System.out.println("internalmethods>>"+ num.stream().count());
        System.out.println("internalmethods>>"+ num.stream().distinct());
	
	}
	public int myMethod(int num){  
		System.out.println("Instance Method");
		return 5;
	    }  
	//l is a arraylist of empty obj.
	//ecourtDao.findByAssignedType(type).iterator().forEachRemaining(l::add); 
	//Optional.ofNullable(m).orElseThrow(() -> new ResourceAccessException("Data Not Found"));
	//Collections.sort(dtolist, (o1, o2) -> (o2.getCompId().compareTo(o1.getCompId())));
	
	//no of java eight features
    //lamda exp,foreachloop,sort,optioalnull,iterate forEachRemaining,stream api,functional interface
	
	//List<BillPunchDetailsModel> cm = services.getAll();
		//List<BillPunchDetailsModel> l=new ArrayList<BillPunchDetailsModel>();
	//List<String> artnamelist=cm.stream().map(e->e.getArticleName()).collect(Collectors.toList());
	//List<String> artnamelist=cm.stream().map(BillPunchDetailsModel::getArticleName).collect(Collectors.toList());
	//String oldlist=cm.stream().map(e->e.getArticleName()).findFirst().get();
    //cm.sort((o1,o2)->o1.getDistcode().compareTo(o2.getDistcode()));//for sort
	//Collections.sort(cm,(m1,m2)->m1.getcPair().compareTo(m2.getcPair()));//for sort
     //services.getAll().iterator().forEachRemaining(l::add); for add the data into arraylist during query hit. directly.
	
	//Optional.ofNullable(cm).orElseThrow(() -> new ResourceAccessException("Data Not Found"));
	
	
}
