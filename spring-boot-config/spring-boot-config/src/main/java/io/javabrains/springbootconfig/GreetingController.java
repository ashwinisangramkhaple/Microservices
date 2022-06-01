package io.javabrains.springbootconfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController 
{
	//@Value("${my.greeting}")
	//private String greetingMessage;
	
	@Value("${my.greeting: default value}")
	private String greetingMessage;
	
	@Value("some static message")
	private String staticMessage;
	
	@Value("${my.list.values}")
	private List<String> listvalues;
	
	//@Value("#{${dbValues}}")
	//private Map<String, String>map;
	
	@Autowired
	private DbSettings dbsettings;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greeting")
	public String greeting()
	{
		//return greetingMessage + staticMessage + listvalues + map;
		return dbsettings.getConnection() + dbsettings.getHost()+dbsettings.getPort();
	}
	@GetMapping("/envdetails")
	public String envDetails()
	{
		return env.toString();
	}
}
