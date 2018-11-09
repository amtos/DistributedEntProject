package com.ups.shipping;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class JaxrsApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ShippingInitiation.class);
		return classes;
	}

}
