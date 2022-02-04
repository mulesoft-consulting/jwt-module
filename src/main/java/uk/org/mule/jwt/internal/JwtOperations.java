package uk.org.mule.jwt.internal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.exception.ModuleException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class JwtOperations {
    /**
     * Example of an operation that uses the configuration and a connection instance to perform some action.
     */
    @DisplayName("Sign")
    @MediaType(value = TEXT_PLAIN, strict = false)
    @Throws(JwtErrorProvider.class)
    public String sign(@Optional @Content Map<String, Object> header,
                       @Content(primary = true) Map<String, Object> payload,
                       @Config JwtConfiguration config) {
        String jws;
        try {
            Claims bodyClaims = Jwts.claims(payload);
            PEMParser parser = new PEMParser(new FileReader(config.getKeyPath()));
            Object object = parser.readObject();
            if (object instanceof PrivateKeyInfo) {
                JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
                PrivateKey privateKey = converter.getPrivateKey((PrivateKeyInfo) object);
                if (header != null) {
                    Claims headerClaims = Jwts.claims(header);
                    jws = Jwts.builder().setHeader(headerClaims).setClaims(bodyClaims).signWith(privateKey, config.getAlgorithm()).compact();
                } else {
                    jws = Jwts.builder().setClaims(bodyClaims).signWith(privateKey, config.getAlgorithm()).compact();
                }
            }
            else {
                throw new InvalidKeyException(config.getKeyPath() + " identified as " + object.getClass());
            }
        }
        catch (FileNotFoundException fnfe) {
            throw new ModuleException(JwtError.FILE_NOT_FOUND, fnfe);
        }
        catch (IOException ioe) {
            throw new ModuleException(JwtError.IO_ERROR, ioe);
        }
        catch (InvalidKeyException ke) {
            throw new ModuleException(JwtError.INVALID_KEY, ke);
        }
        return jws;
    }
}
