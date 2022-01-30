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
    public void signRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("sign256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.mhFRuLSTY-XPJCUpTEuwl-xsiecF07iYLCU1lM-Xqac2N39I-da8kn9csz2dTmdrDxYVyjNXivNuRbJJy41WjG_rSOsiNHhXbU4NNRB9fCc7dZlVHWltHuqBb-MUkbN1yEwiOOvCTsRu2y0mwGC18jHuf8-a8fw7OYvAcELKnNlaBMkwv-CFf6e5iYmxhhbtQvR9VETtN1qfjZH-oNWG9PD-MGr2-eadrII_wWgWcb58egJj0lGsHEC4MEM8qU545BwwzyiikUaqcuLjoZvAI-FvHePc_c_kJmAyem2BhuUnxMGDHOpCFe_FGoW8ryV3g9B703yhzIbMtzf1EetbfGYJ8MOnDlZf_0lhrGSjvjXF6EH9POempCKJ0Y_wZsIWGSrgUiemiuMOS10-FF0YvRMyHXp5g9eF0kKuvImKSVDydlb-NJQXeYd3YWMZpd_ppKF6rGW_jHMROeK-GzAUYJCKAwkf6uo2wen-p3-h7n3hUvnqKA5wpKJokLJxp4o52FMWHaOR3CK46mdMAxMg6Oe7NE0XxXBbZZ8fXpak8o7ZfNnaxROtg0DH2cAQR_od2vSFZDAwl64qcL8f6XoUP8Wjiru4rh1VmxLDnyKDM6jDd9gQVRajskdwr9g1Yjn-WJqQGM-nTaIaPB84kc0ztjWVoIGirFQNxZyg0lkPOmQ"));
    }

    @Test
    public void signWithVariableRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("sign256Flow-withVariable")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.mhFRuLSTY-XPJCUpTEuwl-xsiecF07iYLCU1lM-Xqac2N39I-da8kn9csz2dTmdrDxYVyjNXivNuRbJJy41WjG_rSOsiNHhXbU4NNRB9fCc7dZlVHWltHuqBb-MUkbN1yEwiOOvCTsRu2y0mwGC18jHuf8-a8fw7OYvAcELKnNlaBMkwv-CFf6e5iYmxhhbtQvR9VETtN1qfjZH-oNWG9PD-MGr2-eadrII_wWgWcb58egJj0lGsHEC4MEM8qU545BwwzyiikUaqcuLjoZvAI-FvHePc_c_kJmAyem2BhuUnxMGDHOpCFe_FGoW8ryV3g9B703yhzIbMtzf1EetbfGYJ8MOnDlZf_0lhrGSjvjXF6EH9POempCKJ0Y_wZsIWGSrgUiemiuMOS10-FF0YvRMyHXp5g9eF0kKuvImKSVDydlb-NJQXeYd3YWMZpd_ppKF6rGW_jHMROeK-GzAUYJCKAwkf6uo2wen-p3-h7n3hUvnqKA5wpKJokLJxp4o52FMWHaOR3CK46mdMAxMg6Oe7NE0XxXBbZZ8fXpak8o7ZfNnaxROtg0DH2cAQR_od2vSFZDAwl64qcL8f6XoUP8Wjiru4rh1VmxLDnyKDM6jDd9gQVRajskdwr9g1Yjn-WJqQGM-nTaIaPB84kc0ztjWVoIGirFQNxZyg0lkPOmQ"));
    }

    @Test
    public void signWithHeaderRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("sign256Flow-withHeader")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.GlMyJYVmG8-s_HXeyKegJ18Ru5oTPGn0ZU-UGvq72R3I51ui0jFV-5O3uyHv5IXgBRJtXj0reDwgNTUUsv5nd1fDLmjDg2FMuvChIUmYNZozWyEa86k8PsxsTGnhmt5Lt-TxbwjtYIjUI5LEEOOMj81DS5edbj0FQ_o6Dcb5mayvFZlDJD0KyUWGO1Gonmn5-gwF_geLREtV4y89nbfjSjPncY76wN_vRIRAa7Q6i_NgYg6Lbio7Fm4CahmWspht_xjE4z1ZUyijbJHKbHj2RF__yPjpdCf_i3rwCSSUiyfEt-HKWBDxFc5YxsJq588ng4mX9OM-dV8g9dhGpHnyG7faBlD5RTLF7YiZjo-9Cx9ngIdVi7hgz2ZOVsuxYvCkFsH0UhR_1O5wdWQOLLE4bo7XOccIQC8Qkzupctkgs1HebLF3Hz8fqvJbvIUiiVWheATgWSenfAZ_34e16IjlVmOlipR99hVwCAo91Cy_ICOfL-FV9OFc-Zvo3QeS_d0MC4uuMp2gbZ6YEmq_CGUGawsxIz1HKuyadlbHifsGyKeJ8h2RPZvvlRFWVncxTAlAax1Yd2jA93FsXZE7hNS2toe4ZlIhGaYdLgQhCdit70ZSTdsd-Hb1NALu7Ir3EZMgyccbTWRBK5la9laSTv8-YJrxH1Pb_NOXxLjjHZndXkQ"));
    }

    @Test
    public void invalidFileRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("invalidFile256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("FILE_NOT_FOUND Exception raised and handled"));
    }

    @Test
    public void invalidKeyRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("invalidKey256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("INVALID_KEY Exception raised and handled"));
    }
}
