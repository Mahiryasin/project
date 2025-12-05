package com.mahir.JWT;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {
private final  String API_KEY="rV7/lD2jMqBpqBKySj+5IeTvt46/AC2Kiz5l26lFKiw=";
public String GenerateToken(UserDetails userDetails){
    return Jwts.builder()
    .setSubject(userDetails.getUsername())
    .setIssuedAt(new Date())
    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
    .signWith(CreateKey(),SignatureAlgorithm.HS256)
    
    .compact();
}
private SecretKey CreateKey(){
  byte[] decoder_key=Base64.getDecoder().decode(API_KEY);
  return Keys.hmacShaKeyFor(decoder_key);

}
private Claims ExportClaims(String Token){
  return Jwts.parserBuilder()
  .setSigningKey(CreateKey())
  .build()
  .parseClaimsJws(Token)
  .getBody();
}

public <T> T ExportToken(String Token,Function<Claims,T> Claims){
  Claims claims=ExportClaims(Token);
 return Claims.apply(claims);
}

public String GetUsername(String Token){
   return  ExportToken(Token, Claims::getSubject);
}
public boolean  isExpireDate(String Token){
 Date date= ExportToken(Token, Claims::getExpiration);
  return new Date().after(date);
}



}
