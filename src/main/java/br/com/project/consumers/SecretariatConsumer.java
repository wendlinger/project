package br.com.project.consumers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.project.dto.SecretariatDTO;
import br.com.project.exceptions.BusinessException;

@Configuration
public class SecretariatConsumer {

	private final String baseUrl = "http://localhost:8081/secretariats/";
	private final RestTemplate restTemplate = new RestTemplate();

	public SecretariatDTO getById(Long idSecretariat) throws BusinessException {

		SecretariatDTO secretariatDTO = new SecretariatDTO(); 
//		RestTemplate restTemplate = new RestTemplate();
		
		try {
			secretariatDTO = restTemplate.getForEntity(baseUrl + idSecretariat, SecretariatDTO.class).getBody();
		} catch (Exception e) {
			throw new BusinessException("Access denied to: " + baseUrl + e);
		}
		return secretariatDTO;
	}

}
