package my.local.ys.mocktest.external.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FTPCredentialsDTO {

	@NotNull
	private String ftpuser;

	@NotNull
	private String ftppass;

	@NotNull
	private String ftproot;
}
