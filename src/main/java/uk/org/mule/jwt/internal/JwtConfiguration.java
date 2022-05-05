package uk.org.mule.jwt.internal;

import io.jsonwebtoken.SignatureAlgorithm;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(JwtOperations.class)
@DisplayName("Configuration")
public class JwtConfiguration {

    @DisplayName("Signature Algorithm")
    @Expression(ExpressionSupport.NOT_SUPPORTED)
    @Parameter
    @Placement(order = 1)
    @Summary("The encryption algorithm to be used when signing the token")
    private JwtAlgorithm algorithm;

    @DisplayName("Private Key File")
    @Example("${mule.home}/apps/${app.name}/certs/private-key.pem")
    @Expression(ExpressionSupport.SUPPORTED)
    @Parameter
    @Placement(order = 2)
    @Summary("Hint: Use ${mule.home} and ${app.name} to avoid hardcoding")
    private String keyPath;

    @DisplayName("Passphrase")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional
    @Parameter
    @Password
    @Placement(order = 4)
    @Summary("Passphrase is used for encrypted keys only")
    private String passphrase;

    SignatureAlgorithm getAlgorithm() { return algorithm.getAlgorithm(); }

    String getKeyPath(){
        return keyPath;
    }

    String getPassphrase() { return passphrase; }
}
