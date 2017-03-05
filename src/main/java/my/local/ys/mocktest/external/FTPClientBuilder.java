package my.local.ys.mocktest.external;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

@Component
public class FTPClientBuilder {

	public FTPClient build() {
		return new FTPClient();
	}
}
