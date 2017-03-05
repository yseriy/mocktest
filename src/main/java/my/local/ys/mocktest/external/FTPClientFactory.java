package my.local.ys.mocktest.external;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

@Component
public class FTPClientFactory {

	public FTPClient createClient() {
		return new FTPClient();
	}
}
