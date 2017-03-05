package my.local.ys.mocktest.external;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FTPService {

	private final FTPClientFactory ftpClientFactory;

	public FTPService(FTPClientFactory ftpClientFactory) {
		this.ftpClientFactory = ftpClientFactory;
	}

	public FTPClient getFTPClient(String hostName, String userName, String userPass) throws IOException {
		FTPClient ftpClient = ftpClientFactory.createClient();
		ftpClient.connect(hostName);
		ftpClient.login(userName, userPass);

		return ftpClient;
	}
}
