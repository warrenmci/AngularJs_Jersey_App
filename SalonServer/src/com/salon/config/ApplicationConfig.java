package com.salon.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		packages("com.salon.services");
	}

}
