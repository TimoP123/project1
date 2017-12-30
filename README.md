
# Cyber Security Base with F-Secure - Course Project 1

### Introduction

This is the first project on Cyber Security Base with F-Secure course. The task was to create a web application that has at least five different vulnerabilities from the OWASP top ten list (2013). The application repository can be found from the address https://github.com/TimoP123/project1. The application is based on the starter code on Github at https://github.com/cybersecuritybase/cybersecuritybase-project.

The application itself is very simple. It has a sign in form for a fictional event and after the user has provided his name and address information the application shows a list of all participants. There's also a login page for getting into the supermember page. Currently there is only one valid username/password combination for the supermember page and it is username: supermember & password: itsMeeee. The application has been programmed only for the purpose of examining the vulnerabilities and that's why there hasn't been any effort to make the application look nice or has any more functionalities than the bare minimum.

### A3 - Cross-Site Scripting (XSS)

Issue: Cross-Site Scripting (XSS)
Steps to reproduce:
1. Open the page http://localhost:8080 in a browser
2. Type in the name field: <script> alert('Buhahahahaaa!'); </script>
3. Type in the password field whatevery you want
4. Press the submit button
5. You get redirected to the participant list page and the script you typed in the name field gets executed there everytime you (or someone else) lands there.

The application has a problem in the sign in form for the event. The name field is rendered to the browser using Thymeleaf's utext-attribute instead of text-attribute. This way the text that user has typed ends up to the event participants list unescaped. If a malicious user types some script in the name field he gets the other users' browsers to execute the script everytime they land on the participant list page.

To fix the issue one needs to change the th:utext-attribute to th:text. This prevents the script codes ending to the participant list page in an executable form. It would also be good idea to do some filtering for special characters so that anything the user types doesn't end up to publicly available page.


### A6 - Sensitive Data Exposure

Issue: Sensitive data exposure
Steps to reproduce: Examine the file ./sql/createAccountTable.sql

This application has only one valid username and password combination. The password is stored in the H2 database as it is without any hashing or encryption. This makes the database leaking a serious risk. Sensitive data like this deserves extra protection such as encryption as well as special precaution when exchanged with the browser. Adding 'salt' before hashing the password makes exploiting leaked passwords even harder. The file SecurityConfiguration.java has already a PasswordEncoder method which uses BCrypt to encrypt passwords.

### A7 - Missing Function Level Access Control

Issue: Missing function level access control
Steps to reproduce:
1. Open the page http://localhost:8080/memberpage in a browser
2. You end up in the supermember page

The application has a page for supermembers which is meant for their access only. The problem is in the way the page is implemented. If a non-logged-in user knows (or guesses) the url for the page he can get there because the permissions for the page are not checked on the server. One way to fix the issue is to use http.authorizeRequests() method in SecurityConfiguration.java file's configure function. Another option could be implementing the session login check in the controller.

### A8 - Cross-Site Request Forgery (CSRF)

Issue: Cross-Site Request Forgery (CSRF)

The application has not implemented anything to protect from CSRF-attacks. In this kind of situations there's a potential risk that a user can send requests on behalf of a malicious hacker if user has an active session on this application while he visits a bad website which contains code that uses users credentials in sending requests on the server.

Protection from CSRF-attack can be activated by commenting the line `http.csrf().disable();` in the file SecurityConfiguration.java. Of course this is very artificial way of implementing the vulnerability but it also shows how little is required to introduce a major vulnerability in an application.

### A9 - Using Components with Known Vulnerabilities

Issue: Using components with known vulnerabilities
Steps to reproduce:
1. Open the terminal and go to the root folder of the application
2. Run maven dependency-check wh command mvn dependency-check:check
3. From the folder ./target you'll find the dependency-test report file which can be viewed with the browser.

One of the vulnerabilities is about an outdated version of the Spring Boot Web Starter (1.4.2). The current version is 1.5.9. The new version can be changed in the pom.xml file. Using old vulnerable components makes the application a potential target for attacks exploiting these vulnerabilities.

If dependency check doesn't work correctly it can be worth checking if you have an old version of maven.

