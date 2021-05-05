package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Component
public class JwtUtil {

    /**
     * This method will decrypt a JWT auth token into a user object.
     * @param token encrypted JWT token
     * */
    public static boolean decrypt(String token) throws UnsupportedEncodingException {

        /*
         *
         * token expires in 7 days
         * get all claims in token and add them to user object to return to frontend
         *
         * */
        System.out.println("We are filtering stuff");
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(System.getenv("JWT_SECRET").getBytes("UTF-8"))
                .parseClaimsJws(token);

        if(claims!= null) return true;

        return false;
    }

}
