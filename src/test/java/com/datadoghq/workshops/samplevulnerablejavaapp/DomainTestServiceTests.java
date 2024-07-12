package com.datadoghq.workshops.samplevulnerablejavaapp;

import com.datadoghq.workshops.samplevulnerablejavaapp.exception.DomainTestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DomainTestServiceTests {
    @Test
    void testValidDomain() {
        String domain = "google.com";
        DomainTestService testService = new DomainTestService();

        try {
            testService.testDomain(domain);
        } catch (DomainTestException e) {
            Assertions.fail();
        }
    }

    @Test
    void testInvalidDomain() {
        String domain = "exec script.sh";
        DomainTestService testService = new DomainTestService();

        try {
            testService.testDomain(domain);
            Assertions.fail();
        } catch (DomainTestException ignored) { }
    }
}
