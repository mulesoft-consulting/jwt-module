package uk.org.mule.jwt.internal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.exception.ModuleException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;
import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class JwtOperations {
    /**
     * Example of an operation that uses the configuration and a connection instance to perform some action.
     */
    @MediaType(value = TEXT_PLAIN, strict = false)
    @Throws(JwtErrorProvider.class)
    public String sign(@Content(primary = true) Map<String, Object> payload, @Config JwtConfiguration config) {
        String jws = null;
        try {
            Claims claims = Jwts.claims(payload);
            String pkcs8Key = read(config.getPrivateKeyPath());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pkcs8Key));
            KeyFactory keyFactory = KeyFactory.getInstance(config.getFamily());
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            jws = Jwts.builder().setClaims(claims).signWith(privateKey, config.getAlgorithm()).compact();
        }
        catch (FileNotFoundException fnfe) {
            throw new ModuleException(JwtError.FILE_NOT_FOUND, fnfe);
        }
        catch (IOException ioe) {
            throw new ModuleException(JwtError.IO_ERROR, ioe);
        }
        catch (InvalidKeySpecException ikse) {
            throw new ModuleException(JwtError.INVALID_KEY, ikse);
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new ModuleException(JwtError.NO_SUCH_ALGORITHM, nsae);
        }
        return jws;
    }

    private static String read(final String filePath) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();
        String line = null;
        String lineSeparator = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(lineSeparator);
        }
        reader.close();
        return removeHeaders(builder.toString());
    }

    private static String removeHeaders(final String keyContents) throws IllegalArgumentException {
        if (keyContents != null) {
            return keyContents.replace("-----BEGIN PRIVATE KEY-----", "").
                    replace("-----END PRIVATE KEY-----", "").
                    replace("-----BEGIN PUBLIC KEY-----", "").
                    replace("-----END PUBLIC KEY-----", "").
                    replace("\r", "").
                    replace("\n", "");
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
