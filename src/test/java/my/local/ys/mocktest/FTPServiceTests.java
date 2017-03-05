package my.local.ys.mocktest;

import my.local.ys.mocktest.external.FTPClientBuilder;
import my.local.ys.mocktest.external.FTPService;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FTPServiceTests {

	@MockBean
	private FTPClientBuilder ftpClientBuilder;

	@Autowired
	private FTPService ftpService;

	private FTPClient mockedFtpClient;

	@Before
	public void setup() {
		mockedFtpClient = mock(FTPClient.class);
		when(ftpClientBuilder.build()).thenReturn(mockedFtpClient);
	}

	@Test
	public void successWay() throws IOException {
		FTPClient ftpClient = ftpService.getFTPClient("testhost", "testuser", "testpass");

		assertNotNull(ftpClient);
		verify(mockedFtpClient, times(1)).connect("testhost");
		verify(mockedFtpClient, times(1)).login("testuser", "testpass");
	}
}
