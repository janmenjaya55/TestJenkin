package com.bata.billpunch.constant;

public class ResponseModel {

	private String message;
	private String status;
	private Object data;
	
	
	
	public ResponseModel(){
		
	}
	
	public ResponseModel(int status,String message){
		this.status=status+"";
		this.message=message;
	}
	public ResponseModel(int status,String message,Object ent){
		this.status=status+"";
		this.message=message;
		this.data=ent;
	}
	
	public static ResponseModel ok(){
		return new ResponseModel(200,"Success");
	}
	public static ResponseModel ok(Object message){
		return new ResponseModel(200,"Success",message);
	}
	public static ResponseModel ok(String message,Object ent){
		return new ResponseModel(200,message,ent);
	}
	public static ResponseModel error(String message){
		return new ResponseModel(500,message);
	}
	public static ResponseModel error(int staus,String message){
		return new ResponseModel(staus,message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
