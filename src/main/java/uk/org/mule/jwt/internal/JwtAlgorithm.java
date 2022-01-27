package uk.org.mule.jwt.internal;

import io.jsonwebtoken.SignatureAlgorithm;

enum JwtAlgorithm {
    RS256(SignatureAlgorithm.RS256, "RSA"),
    RS512(SignatureAlgorithm.RS512, "RSA");

    private final SignatureAlgorithm algorithm;
    private final String family;

    SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }
    String getFamily() {
        return family;
    }

    JwtAlgorithm(SignatureAlgorithm algorithm, String family) {
        this.algorithm = algorithm;
        this.family = family;
    }
}
