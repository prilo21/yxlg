package com.yxlg.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Order(1)
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.exceptionHandling().and()
				.anonymous().and()
				.servletApi().and()
//				.headers().cacheControl().and()
				.authorizeRequests()
				
				//测试阶段开放所有权限(真实环境应该去掉)
//				.antMatchers("/api/v1/**").permitAll()
		    	//.antMatchers("/**").permitAll()
				//商城相关
				.antMatchers("/store/**").permitAll()
				//Henry Permit 量体师
				.antMatchers("/tailor/**").permitAll()
				.antMatchers("/api/v1/login").permitAll()
				.antMatchers("/api/v1/members").permitAll()
				.antMatchers("/api/v1/trade/testAutoOrderFactoryCallback").permitAll()
				
				//2015.7.2Jetty  放开银联支付宝回调接口
				.antMatchers("/api/v1/payFinished/receivedBackResponMessage").permitAll()//银联支付回调
				.antMatchers("/api/v1/payFinished/receivedMulOrder").permitAll()//银联多订单支付回调

				.antMatchers("/api/v1/pay/payRefundResp").permitAll()//银联退款回调
				.antMatchers("/api/v1/pay/payAlipayFinished").permitAll()//阿里支付多订单完成回调
				.antMatchers("/api/v1/pay/payMulOrderFinished").permitAll()//阿里多订单支付完成回调
				.antMatchers("/api/v1/pay/payAgentResp").permitAll() //代付回调
			    .antMatchers("/api/v1/pay/payAliPayRefundResponse*").permitAll()//阿里退款回调
			    .antMatchers("/api/v1/pay/aliRefund*").permitAll()//阿里退款回调
				.antMatchers("/api/v1/pay/weixinpay/scanQRGetGift").permitAll()//微信回调
				.antMatchers("/api/v1/pay/weixinpay/unifiedToTrade").permitAll()//微信同一支付回调
				.antMatchers("/api/v1/pay/weixinpay/callbackMessage").permitAll()//微信app支付回调
				.antMatchers("/api/v1/pay/weixinpay/callbackMessageMulOrder").permitAll()//微信app支付回调
				.antMatchers ("/api/v1/pay/weixinpay/callbackMessageMulOrderByJSAPI").permitAll()//微信JSAPI支付
				.antMatchers("/api/v1/pay/weixinpay/jsAPIcallbackMessage").permitAll() // 微信JSAPI单个订单支付回调
				.antMatchers("/api/v1/pay/repairOrder/payUnionpayFinished").permitAll() // 返修单银联支付回调
				.antMatchers("/api/v1/pay/repairOrder/payAlipayFinished").permitAll() // 返修单支付宝支付回调
				.antMatchers("/offline/pay/queryInfo").permitAll()  //线下pos请求信息
				.antMatchers("/api/v1/pay/offline/backMessage").permitAll()  //线下pos回调信息
				.antMatchers ("/api/v1/activity/receivedRedPacketByGame").permitAll()
				//redis手动初始化权限
				.antMatchers("/api/v1/admin/redis/*").permitAll()
				// 工厂端订单更新接口权限 
				// dirk 2015-06-09
				.antMatchers("/api/v1/orders/orderUpdate").permitAll()
				
				// 订单提交成功回调接口权限
				// dirk 2015-06-25
				.antMatchers("/api/v1/orders/orderResultCallBack").permitAll()
				
				// 物流更新接口权限放开
				// dirk 2015-06-10
				.antMatchers("/api/v1/express").permitAll()
				//公共接口
				.antMatchers("/api/v1/public/**").permitAll()
				.antMatchers("/api/v2/public/**").permitAll()
				//资源文件 js等
				.antMatchers("/resources/**").permitAll()
				//静态页面 jsp html 等
				.antMatchers("/mobileweb/**").permitAll()
				
				//匿名用户   ANONYMOUS (公共资源和公共接口)
				.antMatchers("/favicon.ico").permitAll()
//				.antMatchers("/login.html").permitAll()
				.antMatchers("/index.jsp").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/configuration/ui/**").permitAll()
				
				.antMatchers("/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				//支持跨域OPTIONS请求
				//Michael.Sun 2016-03-24
				.antMatchers(HttpMethod.OPTIONS, "/api/v1/**").permitAll()
				//Michael.Sun 2016-06-18
				.antMatchers(HttpMethod.OPTIONS, "/api/v2/**").permitAll()
				//webservice管理员用户的配置  WS_ADMIN
				.antMatchers("/admin/**").hasRole("WS_ADMIN")
				.antMatchers("/user/**").hasRole("WS_ADMIN")
				.antMatchers("/api/v1/**").hasRole("WS_USER")

				//WS_USER 的访问接口权限
				.antMatchers("/self/**").hasRole("WS_USER")
				
				//其他系统角色的配置   MG_USER
				
				//其余未配置的接口的访问权限
				.anyRequest().hasRole("WS_USER").and()
		
				//登录拦截器
				.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService,  authenticationManager()), AnonymousAuthenticationFilter.class)
					
				//token解析拦截器
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), AnonymousAuthenticationFilter.class);
	}

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public StatelessAuthenticationSecurityConfig() {

		super(true);
	}
}
