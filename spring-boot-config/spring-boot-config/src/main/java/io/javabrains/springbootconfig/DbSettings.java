package io.javabrains.springbootconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
// for Actuators - localhost:8080/actuator/configprops
//for configuraton proper. - localhost:8080/greeting
@Configuration
@ConfigurationProperties("db")
public class DbSettings 
{
	private String connection;
	private String host;
	private int port;
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
