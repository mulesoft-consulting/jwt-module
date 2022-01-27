package uk.org.mule.jwt.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class JwtOperationsTestCase extends MuleArtifactFunctionalTestCase {

    /**
     * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
     */
    @Override
    protected String getConfigFile() {
        return "test-mule-config.xml";
    }

    @Test
    public void executeJWTOperation() throws Exception {
        String payloadValue = ((String) flowRunner("jwtFlow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.N--6HdA5ANMWTVKmcB7_x8AmaPyehVQahIYcB5tfYKnpOXCNXgQY-l6xGh_S2a61-FkBbawwFA5kxOK2OjOXk3xcqjkC9UznFXEreBjaPivddaeLY5Isya_0JCf_UsSoe7X5P-3rB1H0wa3AFxI6njAUxc0nCB3Ng1bV3x7-tdTvIMPOGaBP0WDahlMhotgLfwQf653pUea_MnlWXmrObW5xU1BUI5UlimV0yJRssO8BZGvUz64wH6_2NxP9yLiSoILjMmFCokB_K11gEFPU7HV_nTl3ZlYE-Nj7TcHOEK0dmcIzzdh6_3A5c0anxvlypYudjW0jyDDVYjWGusUXrw"));
    }
}
