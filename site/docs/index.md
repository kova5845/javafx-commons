---
layout: page
title: Docs
excerpt: "Project documentation."
search_omit: true
---

## API Documentation
* Browse the API documentation [here](/apidocs/).
* Download it [here](https://bintray.com/artifact/download/dejv78/maven/com/github/dejv78/jfx/commons/commons/1.0.1/commons-1.0.1-javadoc.jar).

## Maven

~~~ xml
<dependency>
    <groupId>com.github.dejv78.jfx.commons</groupId>
    <artifactId>commons</artifactId>
    <version>X.Y.Z</version>
    <type>jar</type>
</dependency>
~~~

__Note:__ It is required to include following bintray repository in your Maven POM:

~~~ xml
<distributionManagement>
    <repository>
        <id>bintray</id>
        <url>https://api.bintray.com/maven/dejv78/maven/jfx.commons</url>
    </repository>
</distributionManagement>
~~~
