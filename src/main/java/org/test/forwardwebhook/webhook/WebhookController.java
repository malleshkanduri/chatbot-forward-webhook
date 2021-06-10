package org.test.forwardwebhook.webhook;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebhookController {

 	@PostMapping(path = "/forwardwebhook")
	public String webhook(@RequestBody String req) {
		
		System.out.println("chatbot-forward-webhook : Message received: " + req);
		
		String url="http://localhost:8080/webhook";
		
		RestTemplate restTemplate = new RestTemplate();
	  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	  
		HttpEntity<String> entity = new HttpEntity<>(req);
	  
		restTemplate.postForObject(url, entity, String.class);
				
		return "PostForwardwebhook"; 
	}
	
 	@GetMapping(path = "/forwardwebhook")
 	public String getwebhook() {
		System.out.println("forwardwebhook");
		return "GetForwardwebhook";
	}
	
}
