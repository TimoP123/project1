# Cyber Security Base with F-Secure - Course Project 1

### Introduction

This is the first project on Cyber Security Base with F-Secure course. The task was to create a web application that has at least five different flaws from the OWASP top ten list (2013).

The application itself is very simple. It has a sign in form for an event and after the user has provided his name and address information the application show a list of all participants.


### A8 - Cross-Site Request Forgery (CSRF)


### A9 - Using Components with Known Vulnerabilities

By checking pom.xml file one notices that the application uses outdated version of the Spring Boot Web Starter (1.4.2). The current version is 1.5.9. The vulnerability can be found by running dependency-check with maven.

