package com.common.handler;

import com.C2S.C2SPtl.C2SLogin;
import com.S2C.S2CPtl;
import com.S2C.S2CPtl.S2CLogin;
import com.common.net.Cmd;
import com.common.net.Request;
import com.common.net.Response;
import org.springframework.stereotype.Component;

@Component
public class UserHandler extends AbstractHandler {
	public UserHandler() {
		// System.out.print(color + "\n");
	}

	@Cmd(id = 1001, requestProtoClass = C2SLogin.class, responseProtoClass = S2CLogin.class)
	public void login(Request request, Response response) {
		System.out.print("loginloginlogin\n");
	}
//
	@Cmd(id = 1000, requestProtoClass = C2SLogin.class, responseProtoClass = S2CLogin.class)
	public void val(Request request, Response response) {
		System.out.print("valvalval\n");
		S2CPtl.S2CLoginOrBuilder builder= S2CLogin.newBuilder();
//		S2CLogin login = builder.
//		builder.
	}

	@SuppressWarnings("unused")
	private void sendSms() throws Exception {
		

	}

}
