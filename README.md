# Assignment-1-DECIDE

## Building & Testing

Requires [**Gradle 6.8.1**](https://gradle.org/releases/) and project was developed using **Java SE 11**.

Most importantly, all implemented tests can be run using:
```
gradle clean test
```
from the main folder. Building the project can be done using:
```
gradle clean build
```

## Running program
Does not require gradle to be installed. Currently there is no interesting output, but could be run using:
```
./gradlew run
```
for Linux or Mac. For windows execute **gradlew.bat** located in the main directory.

## Project Structure

Source files can be found in directory: 

**app/src/main/java/decide/\***, 

along with corresponding tests in the directory: 

**app/src/test/java/decide/\***. 

The tests can be found in a mirrored folder with the filename **\*Test.java**. JUnit5 is the testing framework used by gradle in this project.

## Statement of Contributions

* Daniel Halvarsson: 
* Hannes Sundin: shouldLaunch(), calculateFUV(), LIC cond 4, 5 and all their corresponding tests.
* Niklas Wessman: LIC cond 1, 8 , 13 and corresponding tests, top level test 3.
* Yu Zhou: LIC cond 3, 6, 10, 14 and corresponding tests, top level test 2. 