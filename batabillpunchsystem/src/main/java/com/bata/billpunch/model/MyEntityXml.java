package com.bata.billpunch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEntityXml {
	
	@Id
	@Column(name = "xml_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long userId;

     @Column(name = "Document")
     private String Document;

     @Column(name = "Name")
     private String Name;

     @Column(name = "Address")
     private String Address;
     

     @Column(name = "Profession")
     private String Profession;


	public MyEntityXml(String Profession, String Address, String Name, String Document) {
		this.Document = Document;
        this.Name = Name;
        this.Address = Address;
        this.Profession = Profession;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getDocument() {
		return Document;
	}


	public void setDocument(String document) {
		Document = document;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getProfession() {
		return Profession;
	}


	public void setProfession(String profession) {
		Profession = profession;
	}
     
     

}
