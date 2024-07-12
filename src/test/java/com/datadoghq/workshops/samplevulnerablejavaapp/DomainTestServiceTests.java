package com.datadoghq.workshops.samplevulnerablejavaapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DomainTestServiceTests {
    @Test
    void testValidDomain() {
        String domain = "google.com";
        Assertions.assertTrue(DomainTestService.isValidDomainName(domain));
    }

    @Test
    void testInvalidDomain() {
        String domain = "exec script.sh";
        Assertions.assertFalse(DomainTestService.isValidDomainName(domain));
    }
}
