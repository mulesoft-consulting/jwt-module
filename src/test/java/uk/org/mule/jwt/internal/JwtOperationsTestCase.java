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
    public void executeRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("rs256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.N--6HdA5ANMWTVKmcB7_x8AmaPyehVQahIYcB5tfYKnpOXCNXgQY-l6xGh_S2a61-FkBbawwFA5kxOK2OjOXk3xcqjkC9UznFXEreBjaPivddaeLY5Isya_0JCf_UsSoe7X5P-3rB1H0wa3AFxI6njAUxc0nCB3Ng1bV3x7-tdTvIMPOGaBP0WDahlMhotgLfwQf653pUea_MnlWXmrObW5xU1BUI5UlimV0yJRssO8BZGvUz64wH6_2NxP9yLiSoILjMmFCokB_K11gEFPU7HV_nTl3ZlYE-Nj7TcHOEK0dmcIzzdh6_3A5c0anxvlypYudjW0jyDDVYjWGusUXrw"));
    }

    @Test
    public void executeRS512Operation() throws Exception {
        String payloadValue = ((String) flowRunner("rs512Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJhbGciOiJSUzUxMiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.o9pfuTNY1a4znlEy3AGbC9GRI8OCLz8g5XcGg1I-z8jIqvC6noNJGv5ID56uI0IJ8AZ6ny7V4p8VMLE4SPKn88_xp_dSlhOvD8SgvKZi5Cu8B7H3G1Qdkou2_P0GfTAC3bk_iL40tKzzk3UEHDLRFU99C43w-geXh3fcIzmeneV3hwjrewnw70loV4btpIXohVRAB8H0sTYn2-LPxazMPbuOH2b7WUeYAc53NvJRj6YDqWZ3DETyobdFYq4aH4KWPXBs4Ws-qzvmCbJnnUkF3kDeryFlQS64LBgimcpldJ11mpFviJv-SXIYOEAwx6YYgbd7rxf4U4crSxiDnsxamQ"));
    }
}
