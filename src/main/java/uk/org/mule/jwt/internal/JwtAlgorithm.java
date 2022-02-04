package uk.org.mule.jwt.internal;

import io.jsonwebtoken.SignatureAlgorithm;

enum JwtAlgorithm {
    ES256(SignatureAlgorithm.ES256),
    ES384(SignatureAlgorithm.ES384),
    ES512(SignatureAlgorithm.ES512),
    PS256(SignatureAlgorithm.PS256),
    PS384(SignatureAlgorithm.PS384),
    PS512(SignatureAlgorithm.PS512),
    RS256(SignatureAlgorithm.RS256),
    RS384(SignatureAlgorithm.RS384),
    RS512(SignatureAlgorithm.RS512);

    private final SignatureAlgorithm algorithm;

    SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }

    JwtAlgorithm(final SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
