package com.iranLabs.assignment.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Hanieh Moafi
 * @Date : 10/7/2022
 */
public class JWTUtil {


    private static String SECRET = "S3cr3tKiiForMaWall3tApplication";
    private static String SUBJECT = "UserDetails";
    private static String ISSUER = "IranLabs-assignment";

    public static String generateToken(String username, String password) throws IllegalArgumentException, JWTCreationException {
        return JWT.create().withSubject(SUBJECT)
                .withClaim("username", username)
                .withClaim("password", password)
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, String> validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, String> claims = new HashMap<>();
        claims.put("username", jwt.getClaim("username").asString());
        claims.put("password", jwt.getClaim("password").asString());
        return claims;
    }

    public static String validateTokenAndFetchUsername(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("username").asString();

    }
}
