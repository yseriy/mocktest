package my.local.ys.mocktest;

import my.local.ys.mocktest.external.FMService;
import my.local.ys.mocktest.external.dto.FTPCredentialsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestClientTest(components = FMService.class)
public class FMServiceTests {

	@Autowired
	private FMService fmService;

	@Autowired
	private MockRestServiceServer mockRestServiceServer;

	private FTPCredentialsDTO ftpCredentialsDTOExpected;

	private FileSystemResource responseExpectedFile;

	@Before
	public void setup() {
		ftpCredentialsDTOExpected = new FTPCredentialsDTO();
		ftpCredentialsDTOExpected.setFtpuser("testuser");
		ftpCredentialsDTOExpected.setFtppass("testpass");
		ftpCredentialsDTOExpected.setFtproot("testroot");

		responseExpectedFile = new FileSystemResource("src/test/resources/fm_ftp_credentials_response.json");
	}

	@Test
	public void successWay() throws Exception {
		this.mockRestServiceServer
				.expect(requestTo("/run"))
				.andRespond(withSuccess(responseExpectedFile, MediaType.APPLICATION_JSON));

		FTPCredentialsDTO ftpCredentialsDTO = this.fmService.getFTPCredentials("/run");

		assertNotNull(ftpCredentialsDTO);
		assertEquals(ftpCredentialsDTOExpected, ftpCredentialsDTO);
	}
}
