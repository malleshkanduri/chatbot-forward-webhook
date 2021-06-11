package org.test.forwardwebhook.webhook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TransitWebhook {
	
	static Logger log = LogManager.getLogger(TransitWebhook.class);
	@Value("${webhook.endpoint.url}")
	String webhookUrl;
	
	@Value("${webhook.access.token}")
	String webhookAccessToken;
	
	
 	@PostMapping(path = "/transitwebhook")
	public String webhook(@RequestBody String req) {
		log.info("Webhook message received: " + req);
		
		RestTemplate restTemplate = new RestTemplate();
	  
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", webhookAccessToken);
		
		HttpEntity<String> entity = new HttpEntity<>(req, headers);
	  
		restTemplate.postForObject(webhookUrl, entity, String.class);
				
		return "PostForwardwebhook"; 
	}
	
 	@GetMapping(path = "/forwardwebhook")
 	public String getwebhook() {
		log.info("forwardwebhook");
		return "GetForwardwebhook";
	}
	
}
