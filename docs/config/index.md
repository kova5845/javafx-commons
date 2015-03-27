---
layout: page
title: config
excerpt: "Project documentation."
search_omit: true
---

The purpose of **config** and **config-json** libraries is to provide an easy to use solution for application configuration persistence.

## Usage

* Import the libraries into your Maven project:

~~~ xml
<dependency>
    <groupId>com.github.dejv78.commons.config</groupId>
    <artifactId>config</artifactId>
    <version>1.1.0</version>
</dependency>

<dependency>
    <groupId>com.github.dejv78.commons.config</groupId>
    <artifactId>config-json</artifactId>
    <version>1.1.0</version>
</dependency>
~~~


* Code sample:

~~~ java
try {
	// Initialize the JSON backend to act as the config provider:
	final ConfigProvider configProvider = JSONConfigProvider(Paths.get("config.json"));
	
	// Load the configuration from the file: 
	final MyAppConfig configuration = configProvider.load(MyAppConfig.class);

	// Save the configuration into the file: 
	configProvider.store(configuration);
}
catch (ConfigException e) {
	// ConfigException gets thrown: 
	//
	// During construction of the config provider:
	//   - When it is not possible to create or open the file on the given path.
	//
	// During configuration load:
	// - When it is not possible to map the contents of the JSON file to the object.
	//
	// During configuration store:
	// - When it is not possible to map the object to the JSON format.
}
~~~


## API Documentation
Browse the API documentation:

* For **config**: [here](/apidocs/config/index.html).
* For **config-json**: [here](/apidocs/config-json/index.html).

Download the API documentation:

* For **config**: [here]().
* For **config-json**: [here]().