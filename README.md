# Cyber Security Base with F-Secure - Course Project 1

### Introduction

This is the first project on Cyber Security Base with F-Secure course. The task was to create a web application that has at least five different flaws from the OWASP top ten list (2013).

The application itself is very simple. It has a sign in form for an event and after the user has provided his name and address information the application show a list of all participants. There's also a login page for getting into the supermember page.

### A3 - Cross-Site Scripting (XSS)

The application has a problem in the sign in form for the event. The name field is rendered to the browser using Thymeleaf's utext-attribute instead of text-attribute. This way the text that user has typed ends up to the event participants list unescaped.

### A6 - Sensitive Data Exposure

This application has only one valid username and password combination. The password is stored in the H2 database as it is without any hashing or encryption. This makes the database leaking a serious risk. Sensitive data like this deserves extra protection such as encryption as well as special precaution when exchanged with the browser.


### A8 - Cross-Site Request Forgery (CSRF)


### A9 - Using Components with Known Vulnerabilities

By checking pom.xml file one notices that the application uses outdated version of the Spring Boot Web Starter (1.4.2). The current version is 1.5.9. The vulnerability can be found by running dependency-check with maven.

