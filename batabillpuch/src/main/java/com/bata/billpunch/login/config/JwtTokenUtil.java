package com.bata.billpunch.login.config;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public  JwtTokenUtil() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }
    
    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);

    }


    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);

    }


    private Claims getAllClaimsFromToken(String token) {
System.out.println("***********************>>>>>>>>>>>>"+token);
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }


    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());

    }


    public String generateToken(UserDetails userDetails,String fullName,String userRole) {

        Map<String, Object> claims = new HashMap<>();

        return doGenerateToken(claims, userDetails.getUsername(),fullName,userRole);

    }


    private String doGenerateToken(Map<String, Object> claims, String subject,String fullName,String userRole) {
    	//subject as username
    	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .claim("Role",userRole)
                .claim("FullName",fullName)
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }


    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
    
    public Boolean isTkExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);
        System.out.println("Expire=== "+expiration);
        return expiration.before(new Date());

    }
    
    public String getDecriptPassword(String token) throws Exception {

    	JwtTokenUtil td= new JwtTokenUtil();

        
        return td.decryptPassword(token);


    }
    
    
    public String getEncriptPassword(String token) throws Exception {
System.out.println("bbbbbbbbbbbbbbbbbbbbbbbnnnnnnnnnnnnnnnnnnnnnnnnnn"+ token);
    	JwtTokenUtil td= new JwtTokenUtil();
        
        return td.encryptPassword(token);


    }
    
    public String encryptPassword(String unencryptedString) {
        String encryptedString = null;
        System.out.println("String password = userReq.encryptedString();>>>>>>>>>>>>unencryptedString"+unencryptedString);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
            System.out.println("String password = userReq.encryptedString();>>>>>>>>>>>>inside55555"+encryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("String password = userReq.encryptedString();>>>>>>>>>>>>encryptedString"+encryptedString);
        return encryptedString;
    }
    
    public String decryptPassword(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

}