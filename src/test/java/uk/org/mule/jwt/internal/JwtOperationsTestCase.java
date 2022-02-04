package uk.org.mule.jwt.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
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
    public void fileNotFoundOperation() throws Exception {
        String payloadValue = ((String) flowRunner("fileNotFoundFlow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("FILE_NOT_FOUND error raised and handled"));
    }

    @Test
    public void invalidKeyOperation() throws Exception {
        String payloadValue = ((String) flowRunner("invalidKeyFlow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("INVALID_KEY error raised and handled"));
    }

    @Test
    public void signES256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("signES256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, startsWith("eyJraWQiOiIyMWZlMTgyNi1jYmJkLTQ4ZGMtYTU3ZS1jODk2MDc0ZTI1OTIiLCJhbGciOiJFUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ"));
    }

    @Test
    public void signPS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("signPS256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, startsWith("eyJraWQiOiI2ZTlmMDA3Ni0xOTBlLTQxMmUtODY4MC1kZGJiMTEyZTMyY2UiLCJhbGciOiJQUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ"));
    }

    @Test
    public void signRS256Operation() throws Exception {
        String payloadValue = ((String) flowRunner("signRS256Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJraWQiOiIyYWJjNmNmOS04NjJmLTQwZjYtODEwNS1iZTU5NDNlZGRlZDMiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.T0A0BiGqgzsO8-FunObxtDwkbozKnpngkLHuQ0SF9R8jJNMHulHsZ-eG4ms3IEuOfX8L5xZgrHG42pynSnGyBl0tHhO_XP_IBz17YFX390mV23oPcmqmGg2atn8oTraHvbjfg23sgPMAKXPLa7KoULXH_govx_DW27kaSllohpWXo59djnUAD65H0XYeJENFoehWIQuGPLFg83_SE3e4ADYtEf98tLgGSau2Wc-wh0YatM8UfiFJGVty9rJPJAcGdaNbyEl6xE-BOIOZdP0qO9w-MMz75hZjSUvZ4VIkCvgWA9_iFs3fZGH6eFcT-a01HksJ_szZdqwuSREo18RF3g"));
    }

    @Test
    public void signRS384Operation() throws Exception {
        String payloadValue = ((String) flowRunner("signRS384Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJraWQiOiI0YjZlMjg4Yi0yMTNmLTQ0OGYtODU5Mi02MmQ2NDI2NTY2NTEiLCJhbGciOiJSUzM4NCJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.s_xD2ieItMiVyGE8slx2-Cks1beCJNh3T_CxzXG6MGGuPpDtCo-L5tOjYHlzXne_lkDvGGSGdkWeHJwBMwGjqiz5ZEGZ58bZeagjT0bA0LQcfzgBwMfFx3XgplydxqUhWWLMkQhuvnoLVLAttShF86SDCNj9QK21t2BjRCAOEaqkTEQF-IGL8mKG7zhZZPnDX1YmVTncDJ8OfnvLUt-yzasfs_GydyvkFXy3JOgl3imcFLyR4fQyIDOZkg4UCmDSFyLf3wr3xjM7vgwx0b8Mt2Dvr3NJj_1QnPZ6M-pdYwyV6OkO5SCmmZVpSWbOEZz6WDpAtth39BSA0MvpQmiLNA"));
    }

    @Test
    public void signRS512Operation() throws Exception {
        String payloadValue = ((String) flowRunner("signRS512Flow")
                .run()
                .getMessage()
                .getPayload()
                .getValue());
        assertThat(payloadValue, is("eyJraWQiOiJlZTBmZjIyYi0yZWQ1LTRmNzQtYTRlMS1hNDc0OWZhZmY3MWEiLCJhbGciOiJSUzUxMiJ9.eyJhdWQiOiJzZXJ2aWNlLmdvdi51ayIsImlzcyI6IjEyMzQ1IiwiaWF0IjoxNTE2MjM5MDIyfQ.H9ZyrdWaMrPhEmmPGE4WDpg-2fC2KNyg5bkxJW6cEezMVjyObFwJcIJb1Z5xS6PnMG_-azhElsnzINrgSJZD36wd_dW7FAlnHiKDLLExKv4uq5AYJc_7T7GvW9CuztWLkwDSaeC9any7w0z_VHD9K1elrT5yXYm1uIfGaO8mRCtFWM7O0ua1oXHfbMe7YUWvTdU6NnkTP4UYyBZe8zf5ANPQl9B5wO8Q0x0hsX7OPx9EdTxMIlFdEszNAJbXSLbZylBXb-Fnl5ucwoWtz6pBbbWrgLUmO_JVPONqju_4sXHouKSJKhEi8tL3vMCmwzG_Nsqs7jeLTDHa_qXCHhUTIg"));
    }
}
