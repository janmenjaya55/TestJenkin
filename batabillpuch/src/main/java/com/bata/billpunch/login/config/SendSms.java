/*
 * package com.bata.billpunch.login.config;
 * 
 * 
 * import java.io.IOException; import java.net.URI; import
 * java.net.URISyntaxException; import java.util.LinkedHashMap; import
 * java.util.Random;
 * 
 * import org.apache.http.client.HttpClient; import
 * org.springframework.stereotype.Component;
 * 
 * 
 * @Component public class SendSms {
 * 
 * public Long sendSMS(String mobileNumber) throws URISyntaxException,
 * IOException, InterruptedException { Long otp1 = 0L; Random random = new
 * Random(); otp1 = (long) (100000 + random.nextInt(900000)); HttpClient client
 * = HttpClient.newHttpClient(); // MediaType mediaType =
 * MediaType.parse("text/plain"); Map<Object, Object> builder = new
 * LinkedHashMap(); builder.put("authkey", authkey); builder.put("mobiles",
 * mobileNumber); builder.put("message",
 * "Radhe Radhe\nYour OTP for login into BGSM portal is " + otp1 +
 * ". The OTP is valid for 2 Mins. PLEASE DO NOT SHARE WITH OTHERS.\nThank You"
 * ); builder.put("route", route); builder.put("DLT_TE_ID", tempId);
 * builder.put("sender", sender);
 * 
 * String boundary = new BigInteger(256, new Random()).toString();
 * 
 * HttpRequest request = HttpRequest.newBuilder().uri(URI.create(msguri))
 * .header("Content-Type", "multipart/form-data;boundary=" + boundary)
 * .POST(ofMimeMultipartData(builder, boundary)).build();
 * 
 * HttpResponse<String> response = client.send(request,
 * HttpResponse.BodyHandlers.ofString()); System.out.println("response===" +
 * response.body()); return otp1; } }
 */