package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.BillingDetailsResponse;
import com.strongsalt.strongdoc.sdk.api.responses.BillingFrequencyListResponse;
import com.strongsalt.strongdoc.sdk.api.responses.LargeTrafficResponse;
//import com.strongsalt.strongdoc.sdk.api.responses.NextBillingFrequencyResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.util.Date;
import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocBillingTest {

    private final StrongDocBilling billing = new StrongDocBilling();
    private StrongDocServiceClient client;

    @BeforeAll
    @DisplayName("Register organizations and obtain token")
    void setUp() throws Exception {
        client = StrongDocTestSetup.init();
        StrongDocTestSetup.registerOrganization(
                client, ORG3_NAME, ORG3_ADMIN_EMAIL, ORG3_ADDRESS, ORG3_ADMIN_NAME,
                ORG3_ADMIN_PASSWORD, ORG3_ADMIN_EMAIL, new String[]{},
                false, SOURCE, SOURCE_DATA);
        client.login(ORG3_NAME, ORG3_ADMIN_EMAIL, ORG3_ADMIN_PASSWORD);
    }

    @AfterAll
    @DisplayName("Remove organizations")
    void tearDown() throws Exception, InterruptedException {
        StrongDocTestSetup.removeOrganization(client);
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Get Billing Details")
    void getBillingDetails() throws Exception {
        final BillingDetailsResponse res = billing.getBillingDetails(client, new Date());

        assertNotNull(res);
        assertNotNull(res.getDocumentCosts());
        assertNotNull(res.getSearchCosts());
        assertNotNull(res.getTrafficCosts());
    }

    @Test
    @Order(2)
    @DisplayName("Get Billing Frequency List")
    void getBillingFrequencyList() throws Exception {
        final BillingFrequencyListResponse res = billing.getBillingFrequencyList(client);

        assertNotNull(res.getBillingFrequencyList().get(0));
    }

    /*
    @Test
    @Order(3)
    @DisplayName("Set Next Billing Frequency")
    void setNextBillingFrequency() throws Exception {
        Calendar validFrom = Calendar.getInstance();
        validFrom.add(Calendar.MONTH, 1);
        validFrom.set(Calendar.DAY_OF_MONTH, 1);
        validFrom.set(Calendar.HOUR_OF_DAY, 0);
        validFrom.set(Calendar.MINUTE, 0);
        validFrom.set(Calendar.SECOND, 0);
        validFrom.set(Calendar.MILLISECOND, 0);
        validFrom.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        //System.out.printf("Calendar: %s\n", validFrom.toString());
        //System.out.printf("Date: %s\n\n", validFrom.getTime().toGMTString());
        final NextBillingFrequencyResponse res = billing.setNextBillingFrequency(
            client, StrongDocBilling.TimeInterval.YEARLY, validFrom.getTime());

        assertEquals(res.getNextBillingFrequency().getFrequency(), StrongDocBilling.TimeInterval.YEARLY);
    }*/

    @Test
    @Order(4)
    @DisplayName("Get Large Traffic")
    void getLargeTraffic() throws Exception {
        final LargeTrafficResponse res = billing.getLargeTraffic(client, new Date());

        assertEquals(res.getTrafficDetails().size(), 0);
    }
}
