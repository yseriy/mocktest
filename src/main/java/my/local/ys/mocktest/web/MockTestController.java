package my.local.ys.mocktest.web;

import my.local.ys.mocktest.external.FMService;
import my.local.ys.mocktest.external.dto.FTPCredentialsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MockTestController {

	private final FMService fmService;

	public MockTestController(FMService fmService) {
		this.fmService = fmService;
	}

	@PostMapping("/run")
	public FTPCredentialsDTO run(@RequestBody String url) throws IOException {
		return fmService.getFTPCredentials(url);
	}
}
