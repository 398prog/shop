package jp.co.sss.shop.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.co.sss.shop.util.Login;

@Login
public class LoginFormWithAnnotation{

	@Max(value = 999)
	private int userId;

	@NotEmpty
	@Size(max = 16)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
