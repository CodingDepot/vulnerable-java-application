package com.datadoghq.workshops.samplevulnerablejavaapp;

import com.datadoghq.workshops.samplevulnerablejavaapp.exception.DomainTestException;
import com.datadoghq.workshops.samplevulnerablejavaapp.exception.InvalidDomainException;
import com.datadoghq.workshops.samplevulnerablejavaapp.exception.UnableToTestDomainException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class DomainTestService {

  final static int timeoutMs = 10_000;

  public String testDomain(String domainName) throws DomainTestException {
    InetAddress address;
    try {
      address = InetAddress.getByName(domainName);
    } catch (UnknownHostException e) {
      throw new InvalidDomainException("Invalid domain name: " + domainName + " - don't try to hack us!");
    }

    try {
      boolean reachable = address.isReachable(timeoutMs);
      if (!reachable) {
        throw new UnableToTestDomainException("The domain " + domainName + "is not reachable!");
      }
      return "The domain " + domainName + "is reachable!";
    } catch (IOException e) {
      throw new UnableToTestDomainException("Internal error while testing domain: " + e.getMessage());
    }
  }
}
