package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kainos.ea.exception.EncryptionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.kainos.ea.model.Encryption.encodeKey;

@Getter
@Setter
public class Token {
    protected String token;

    public void generateToken(User user) throws EncryptionException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String secret = System.getenv("JWT_SECRET_TOKEN");

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
        this.token = encryptToken(jwtToken);
    }

    public String encryptToken(String toEncrypt) throws EncryptionException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String secretKey = System.getenv("JWT_SECRET_KEY");

        return Encryption.encrypt(toEncrypt, encodeKey(secretKey));
    }
    public String viewToken() {
        String secret = System.getenv("JWT_SECRET_TOKEN");


        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(this.token);
        return jwt.toString();
    }

    @JsonCreator
    public Token(@JsonProperty("token") String token) {
        this.setToken(token);
    }
}
