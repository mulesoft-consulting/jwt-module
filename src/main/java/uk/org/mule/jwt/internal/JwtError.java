package uk.org.mule.jwt.internal;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum JwtError implements ErrorTypeDefinition<JwtError> {
    FILE_NOT_FOUND,
    INVALID_KEY,
    IO_ERROR,
    NO_SUCH_ALGORITHM
}
