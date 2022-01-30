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

    @DisplayName("Signature Algorithm")
    @Expression(ExpressionSupport.NOT_SUPPORTED)
    @Parameter
    @Placement(order = 1)
    @Summary("The encryption algorithm to be used when signing/verifying the token")
    private JwtAlgorithm algorithm;

    @DisplayName("PKCS#8 Key File")
    @Example("${mule.home}/apps/${app.name}/certificates/private-key.pem")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional
    @Parameter
    @Placement(order = 2)
    @Summary("Hint: Use ${mule.home} and ${app.name} to avoid hardcoding")
    private String privateKeyPath;

    public SignatureAlgorithm getAlgorithm() { return algorithm.getAlgorithm(); }

    public String getPrivateKeyPath(){
        return privateKeyPath;
    }
}
