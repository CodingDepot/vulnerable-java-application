package com.datadoghq.workshops.samplevulnerablejavaapp;

import com.datadoghq.workshops.samplevulnerablejavaapp.exception.DomainTestException;
import com.datadoghq.workshops.samplevulnerablejavaapp.exception.InvalidDomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class DomainTestServiceTests {
    @Test
    void testValidDomain() {
        DomainTestService testService = new DomainTestService();

        String domain = "google.com";
        try {
            testService.testDomain(domain);
        } catch (DomainTestException e) {
            fail();
        }
    }

    @Test
    void testInvalidDomain() {
        DomainTestService testService = new DomainTestService();

        String domain = "exec script.sh";
        try {
            testService.testDomain(domain);
            fail();
        } catch (DomainTestException e) {
            assertInstanceOf(InvalidDomainException.class, e);
        }
    }
}
