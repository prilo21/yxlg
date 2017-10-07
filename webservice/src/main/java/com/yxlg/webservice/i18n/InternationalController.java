package com.yxlg.webservice.i18n;

import io.swagger.annotations.Api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@Api(tags = "国际化测试类")
public class InternationalController {
	
	@RequestMapping(value = "/test/international", method = RequestMethod.GET)
	private @ResponseBody Map<String, Object> getMessage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		RequestContext requestContext = new RequestContext(request);
		
		//用来取出国际化信息
		String resultString = requestContext.getMessage("money");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value_content", resultString);
		
		return map;
	}
}
