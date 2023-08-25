# auto-framework
This is a Selenium Automation framework just in case I need to use one quick and I don't want to be setting up all from scratch.

## Technologies
- Java
- Selenium
- MSEdge Web Driver
- TestNG
- Extent Reports

## Installing this framework
- Clone the project
- Install your preferred JDK (17 or above)
- Under System Variable, create a new variable named "CLASSPATH" with route to the JDK binary. Example: "C:\Program Files\Java\jdk-17\bin"
- Under System Variables, edit "Path" and add a New one. Example: "C:\Program Files\Java\jdk-17\bin"
- Take into account this specific framework is only currently configured for MS Edge Browser so you might have to download and copy the latest `msedgedriver.exe` into the corresponding src/main/resources folder.

## Additional Notes
- I have tried to use _BoniGarcia WebDriverManager_ to auto-download the correct detected Web Driver. I tested thoroughly a full day using Edge Version 116 and found that it was downloading an incorrect driver version. In conclusion I will avoid depending on a 3rd party library which is maintained by only one person.
  
