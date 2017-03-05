package my.local.ys.mocktest.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.local.ys.mocktest.external.dto.FMResponseDTO;
import my.local.ys.mocktest.external.dto.FTPCredentialsDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;

@Service
@Validated
public class FMService {

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private static class FMResponseDTOTypeReference extends TypeReference<FMResponseDTO<FTPCredentialsDTO>> {
	}

	public FMService(RestTemplateBuilder restTemplateBuilder, Jackson2ObjectMapperBuilder objectMapperBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.objectMapper = objectMapperBuilder.build();
	}

	@Valid
	public FTPCredentialsDTO getFTPCredentials(String url) throws IOException {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String body = responseEntity.getBody();
		System.out.print(body);
		FMResponseDTO<FTPCredentialsDTO> fmResponseDTO = objectMapper.readValue(body, new FMResponseDTOTypeReference());

		return fmResponseDTO.getResult();
	}
}
