package uk.org.mule.jwt.internal;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

public class JwtErrorProvider implements ErrorTypeProvider {
    @Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        Set<ErrorTypeDefinition> errors = new HashSet<ErrorTypeDefinition>();
        errors.add(JwtError.FILE_NOT_FOUND);
        errors.add(JwtError.INVALID_KEY);
        errors.add(JwtError.IO_ERROR);
        errors.add(JwtError.NO_SUCH_ALGORITHM);
        return errors;
    }
}
