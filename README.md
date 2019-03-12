<h1>Steps to configure and run the project:</h1>
	
Install and configure maven in your system:

	https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/
 
Download "chromedriver.exe" from: 

	http://chromedriver.chromium.org/downloads

Create a "lib" folder. Copy "chromedriver.exe" file inside "lib" folder.

Inside "src\main\resources" folder, there is a file named as "application.properties". Open that file and change the following values:

	username_value=YOUR_EMAIL_ID

	password_value=YOUR_PASSWORD

Open command prompt. Go to the project folder where "pom.xml" file exist. Type following command:
	
	mvn clean install
	
Test script should run successfully.
