package br.com.project.consumers;

import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.project.dto.BudgetDTO;
import br.com.project.enums.EnumFolder;
import br.com.project.exceptions.BusinessException;

@Configuration
public class BudgetConsumer {

	private final String baseUrl = "http://localhost:8080/budgets/";
	private final RestTemplate restTemplate = new RestTemplate();

	public boolean hasAvailableResource(Long id, float value) throws BusinessException {

		String url = baseUrl + id + "/has-available-resource";
		try {
			return restTemplate.getForObject(url, Boolean.class, value);
		} catch (Exception e) {
			throw new BusinessException("Access denied to: " + baseUrl + e);
		}
	}

	public void updateExpenseValue(BudgetDTO budgetDTO) throws BusinessException, URISyntaxException {
		String url = baseUrl + budgetDTO.getId() + "/expense";

		HttpClient httpClient = HttpClientBuilder.create().build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
		HttpHeaders reqHeaders = new HttpHeaders();
		reqHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BudgetDTO> requestEntity = new HttpEntity<BudgetDTO>(budgetDTO, reqHeaders);
		ResponseEntity<BudgetDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity,
				BudgetDTO.class);

		System.out.println(responseEntity);

	}

	public BudgetDTO getByPossibleDestinationsWithAvailableResource(EnumFolder possibleDestinations, Float cost)
			throws BusinessException {

		String url = baseUrl + "has-available-resource-by-possible-destinations";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<BudgetDTO> requestEntity = new HttpEntity<>(requestHeaders);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("possibleDestinations", possibleDestinations).queryParam("cost", cost);
		ResponseEntity<BudgetDTO> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
				requestEntity, BudgetDTO.class);
		BudgetDTO budgetDTO = responseEntity.getBody();

		return budgetDTO;
	}

}
