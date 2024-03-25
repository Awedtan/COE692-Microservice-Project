package frontend;

import java.security.Key;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map.Entry;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

public class Authenticate {

    static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    static String superSecretString = "ifyouarereadingthisthenstopreadingthis87490123786329";
    static String superSecretCode = Encoders.BASE64.encode(superSecretString.getBytes());
    static byte[] superSecretBytes = DatatypeConverter.parseBase64Binary(superSecretCode);

    public static String createToken(String issuer, String subject) {
        long nowMillis = System.currentTimeMillis();
        Key signingKey = new SecretKeySpec(superSecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder b = Jwts.builder()
                .setIssuedAt(new Date(nowMillis))
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signingKey);

        String token = b.compact();
        return token;
    }

    public static Entry<Boolean, String> verifyToken(String token) {
        Entry<Boolean, String> entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");

        if (token.equals(""))
            return entry;

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(superSecretCode)
                    .build()
                    .parseClaimsJws(token);

            String subject = jws.getBody().getSubject();
            entry = new AbstractMap.SimpleEntry<Boolean, String>(true, subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entry;
    }
}