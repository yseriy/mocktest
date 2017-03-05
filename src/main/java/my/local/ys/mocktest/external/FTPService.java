package my.local.ys.mocktest.external;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FTPService {

	private final FTPClientBuilder ftpClientBuilder;

	public FTPService(FTPClientBuilder ftpClientBuilder) {
		this.ftpClientBuilder = ftpClientBuilder;
	}

	public FTPClient getFTPClient(String hostName, String userName, String userPass) throws IOException {
		FTPClient ftpClient = ftpClientBuilder.build();
		ftpClient.connect(hostName);
		ftpClient.login(userName, userPass);

		return ftpClient;
	}
}
