package com.yxlg.webservice.security;

import com.yxlg.base.sso.entity.SSOUser;
import com.yxlg.base.util.TokenHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;


@Service
public class TokenAuthenticationService {
	//token的名称
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final TokenHandler tokenHandler;

	/**初始化token的处理类，解密token时用的密钥
	 * @param secret 经过加密的密钥
	 */
	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		//传入的参数是密钥，不过要进行解密
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	//从用户的request里面获取token，从token里面获取user
	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final SSOUser user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
