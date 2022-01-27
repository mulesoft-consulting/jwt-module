package uk.org.mule.jwt.internal;

import io.jsonwebtoken.SignatureAlgorithm;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(JwtOperations.class)
@DisplayName("jwt-config")
public class JwtConfiguration {

    @Parameter
    @DisplayName("Signature Algorithm")
    @Expression(ExpressionSupport.NOT_SUPPORTED)
    @Placement(order = 1)
    @Summary("The encryption algorithm to be used when signing the token")
    private JwtAlgorithm algorithm;

    @Parameter
    @Optional
    @DisplayName("Private Key File Path")
    @Summary("Location of the private key file")
    @Example("src/main/resources/certs/private-key.pem")
    @Placement(order = 2)
    private String privateKeyPath;

    @Parameter
    @Optional
    @DisplayName("Public Key File Path")
    @Summary("Location of the public key file")
    @Example("src/main/resources/certs/public-key.pem")
    @Placement(order = 3)
    private String publicKeyPath;

    public SignatureAlgorithm getAlgorithm() { return algorithm.getAlgorithm(); }
    public String getFamily() { return algorithm.getFamily(); }

    public String getPrivateKeyPath(){
        return privateKeyPath;
    }

    public String getPublicKeyPath(){
        return publicKeyPath;
    }
}
