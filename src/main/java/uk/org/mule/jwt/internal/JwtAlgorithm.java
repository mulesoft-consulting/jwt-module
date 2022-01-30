package uk.org.mule.jwt.internal;

import io.jsonwebtoken.SignatureAlgorithm;

enum JwtAlgorithm {
    HS256(SignatureAlgorithm.HS256),
    HS384(SignatureAlgorithm.HS384),
    HS512(SignatureAlgorithm.HS512),
    ES256(SignatureAlgorithm.ES256),
    ES384(SignatureAlgorithm.ES384),
    ES512(SignatureAlgorithm.ES512),
    RS256(SignatureAlgorithm.RS256),
    RS384(SignatureAlgorithm.RS384),
    RS512(SignatureAlgorithm.RS512);

    private final SignatureAlgorithm algorithm;

    SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }

    JwtAlgorithm(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
