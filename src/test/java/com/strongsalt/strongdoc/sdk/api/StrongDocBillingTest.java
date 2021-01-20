package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.BillingDetailsResponse;
import com.strongsalt.strongdoc.sdk.api.responses.BillingFrequencyListResponse;
import com.strongsalt.strongdoc.sdk.api.responses.LargeTrafficResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocBillingTest {

    private final StrongDocBilling billing = new StrongDocBilling();
    private StrongDocServiceClient client;

    private TestOrg testOrg;
    private TestUser testOrgAdmin;

    @BeforeAll
    @DisplayName("Register organization and admin")
    void setUp() throws Exception {
        client = StrongDocTestSetupAndTearDown.initClient();

        TestData testData = StrongDocTestSetupAndTearDown.initData(1, 1);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);

        testOrg = testData.testOrgs[0];
        testOrgAdmin = testData.testUsers[0][0];

        client.login(testOrg.orgName, testOrgAdmin.userEmail, testOrgAdmin.password);
    }

    @AfterAll
    @DisplayName("Hard Remove organizations")
    void tearDown() throws Exception {
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

        assertEquals(res.getTrafficDetails().size(), 1);
    }
}
