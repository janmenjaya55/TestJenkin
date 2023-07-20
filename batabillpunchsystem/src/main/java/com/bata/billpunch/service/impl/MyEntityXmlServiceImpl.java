/*
 * package com.bata.billpunch.service.impl;
 * 
 * import javax.transaction.Transactional; import javax.xml.bind.Element;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.bata.billpunch.dao.MyEntityDao; import
 * com.bata.billpunch.model.MyEntityXml;
 * 
 * @Service
 * 
 * @Transactional public class MyEntityXmlServiceImpl {
 * 
 * @Autowired private MyEntityDao xmldao;
 * 
 * public void parseSdnFile(String fileName) {
 * 
 * //...
 * 
 * for (int temp = 0; temp < list.getLength(); temp++) { Node node =
 * list.item(temp); if (node.getNodeType() == Node.ELEMENT_NODE) { Element
 * element = (Element) node; String Document =
 * element.getElementsByTagName("Document").item(0).getTextContent(); String
 * Name = element.getElementsByTagName("Name").item(0).getTextContent(); String
 * Address = element.getElementsByTagName("snType").item(0).getTextContent();
 * String Profession =
 * element.getElementsByTagName("Profession").item(0).getTextContent();
 * 
 * MyEntityXml entity = new MyEntityXml(Document, Name, Address, Profession);
 * //serealize your data into entity xmldao.save(entity); //saving to database
 * System.out.println(Document + " " + Name + " Address " + Address +
 * " Profession " + Profession);
 * 
 * } } }
 * 
 * }
 */