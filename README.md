# placeholder
A placeholder image generator REST-ful API written in Java / Spring Boot.

usage:

<host>/placeholder/<format>/<width>/<height>

where format is either png or jpg, and width / height is any valid integer.

Clone this repository and use:

`./gradlew bootRun`

Now point your img src tags to:

http://localhost:8080/placeholder/png/500/500

![gif of example use](https://github.com/tordbjoe/placeholder/blob/master/examplepng.gif)