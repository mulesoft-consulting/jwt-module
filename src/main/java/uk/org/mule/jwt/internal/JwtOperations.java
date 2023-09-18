package uk.org.mule.jwt.internal;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.exception.ModuleException;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

/**
 * This class provides the component-level implementation(s)
 */
public class JwtOperations {
    /**
     * Implementation of the "Sign" component
     */
    @DisplayName("Sign")
    @MediaType(value = TEXT_PLAIN)
    @Throws(JwtErrorProvider.class)
    public String sign(@Optional @Content Map<String, Object> header,
                       @Content(primary = true) Map<String, Object> payload,
                       @Config JwtConfiguration config) {
        String jws;
        PEMParser parser = null;
        try {
            parser = new PEMParser(
                         new InputStreamReader(new FileInputStream(config.getKeyPath()), StandardCharsets.UTF_8));
            Object object = parser.readObject();
            PrivateKeyInfo keyInfo;
            if (object instanceof PrivateKeyInfo) {
                keyInfo = ((PrivateKeyInfo)object);
            }
            else if (object instanceof PEMKeyPair) {
                PEMKeyPair keyPair = ((PEMKeyPair)object);
                keyInfo = keyPair.getPrivateKeyInfo();
            }
            else if (object instanceof PEMEncryptedKeyPair) {
				Security.addProvider(new BouncyCastleProvider());
                PEMEncryptedKeyPair encryptedKeyPair = ((PEMEncryptedKeyPair)object);
                PEMDecryptorProvider provider =
                        new JcePEMDecryptorProviderBuilder().build(config.getPassphrase().toCharArray());
                PEMKeyPair keyPair = encryptedKeyPair.decryptKeyPair(provider);
                keyInfo = keyPair.getPrivateKeyInfo();
            }
            else {
                throw new InvalidKeyException(config.getKeyPath() + " is not a PrivateKey, but " + object.getClass());
            }
            jws = getJWS(header, payload, config, keyInfo);
        }
        catch (FileNotFoundException fnfe) {
            throw new ModuleException(JwtError.FILE_NOT_FOUND, fnfe);
        }
        catch (InvalidKeyException | PEMException ike) {
            throw new ModuleException(JwtError.INVALID_KEY, ike);
        }
        catch (IOException ioe) {
            throw new ModuleException(JwtError.IO_ERROR, ioe);
        }
        finally {
            try {
                if (parser != null) {
                    parser.close();
                }
            }
            catch (IOException ioe) {
                throw new ModuleException(JwtError.IO_ERROR, ioe);
            }
        }
        return jws;
    }

    private String getJWS(Map<String, Object> header,
                          Map<String, Object> payload,
                          JwtConfiguration config,
                          PrivateKeyInfo privateKeyInfo) throws PEMException {
        String jws = null;
        if (privateKeyInfo != null) {
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            PrivateKey privateKey = converter.getPrivateKey(privateKeyInfo);
            JwtBuilder builder = Jwts.builder().setClaims(Jwts.claims(payload));
            if (header != null) {
                builder = builder.setHeader(Jwts.claims(header));
            }
            jws = builder.signWith(privateKey, config.getAlgorithm()).compact();
        }
        return jws;
    }
}
