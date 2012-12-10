Contributing to phonetik
========

Phonetik is a nice little project. It's perfect to practice some Java and C.

It works (tm). The test coverage is decent. You may even learn one or two tricks. You should probably be able to understand some German.  (Don't ask me why I write this in English, I have no idea.)

So much for the good part, the bad part is:

 - The C code is quite hard to read. Frankly, I get only chunks of it in my head.
 - The Java code is basicly the same as the C code.
 - The code is not idiomatic Java. It's not C in Java or Java in Java. It's an unfortunate mix of APIs and style.
 - Performance is not optimal.


Getting Started
-------

You have to install:

 - OpenJDK 7
 - Maven 2
 - GCC 4.7

To build and test run `JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-i386 mvn clean install`. 

The build is tested with Ubuntu 12.10.

Making Changes
-------

All changes should be done without sacrificing the possibility to port changes of the C version to Java. 

So here's what you could do: write more idiomatic Java where appropriate, write C-ish code in Java where appropriate and improve the performance. If you're interested can contact me: thomas.andreas.jung@gmail.com or do a pull request.