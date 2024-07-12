# Vulnerable Java application

This repository contains a sample application, the "Websites Tester Service", that's vulnerable to a [Command Injection](https://owasp.org/www-community/attacks/Command_Injection) vulnerability.

> **Warning!**
> This application is purposely vulnerable and can trivially be hacked. Don't expose it to the Internet, and don't run it in a production environment.
> Instead, you can run it locally on your machine, or in a cloud environment on a private VPC.

## Running locally

1. Build the image locally: 
   ```
   docker build -t vulnerable-java-application:latest .
   ```
2. Run:
    ```
    docker run --rm -p 8000:8000 vulnerable-java-application:latest
    ```
3. You can then access the web application at <http://127.0.0.1:8000>

## Exploitation

1. Browse to <http://127.0.0.1:8000/index.html>
2. Note how the input allows you to specify domain names such as `google.com` and ping them
3. Note that there is some level of input validation - entering `$(whoami)` returns `Invalid domain name: $(whoami) - don't try to hack us!`
4. However, the validation is buggy - notice how you can start the input with a domain name, and execute and command in the container!

![image](https://user-images.githubusercontent.com/136675/186954376-e3d82d03-7d9e-49b3-a106-6da080980dae.png)
