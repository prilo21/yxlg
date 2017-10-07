/*
 * SpringfoxDocConfig.java
 * 
 * Created Date: 2016年4月18日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.yxlg.base.constant.Constants;

/**
 * @author yuandian
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
@Configuration
@EnableSwagger2 //Enable swagger 2.0 spec
@EnableWebMvc
public class SpringfoxDocConfig {
	
	/*@Bean
	public Docket petApi() {
	
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-petstore").apiInfo(apiInfo()).forCodeGeneration(true).select().paths(petstorePaths()).build();
	}
	
	@SuppressWarnings("unchecked")
	private Predicate<String> petstorePaths() {
	
		return or(regex("/api/pet.*"), regex("/api/user.*"), regex("/api/store.*"));
	}
	
	@Bean
	public Docket adminApi() {
	
		return new Docket(DocumentationType.SWAGGER_2).groupName("admins").apiInfo(apiInfo()).forCodeGeneration(true).select().paths(regex("/admins.*")).build();
	}
	
	@Bean
	public Docket userApi() {
	
		AuthorizationScope[] authScopes = new AuthorizationScope[1];
		authScopes[0] = new AuthorizationScopeBuilder().scope("read").description("read access").build();
		SecurityReference securityReference = SecurityReference.builder().reference("test").scopes(authScopes).build();
		
		ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences(newArrayList(securityReference)).build());
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(newArrayList(new BasicAuth("test"))).securityContexts(securityContexts).groupName("user").apiInfo(apiInfo())
				.select().paths(userOnlyEndpoints()).build();
	}
	
	private Predicate<String> userOnlyEndpoints() {
	
		return new Predicate<String>() {
			
			@Override
			public boolean apply(String input) {
			
				return input.contains("user");
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
	
		return new ApiInfoBuilder().title("Springfox REST API").description("Descriptions.").termsOfServiceUrl("http://springfox.io").contact("springfox")
				.license("Apache License Version 2.0").licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}
	
	@Bean
	public Docket configSpringfoxDocket_all() {
	
		return new Docket(DocumentationType.SWAGGER_2).produces(Sets.newHashSet("application/json")).consumes(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http", "https")).forCodeGeneration(true).select().paths(regex(".*")).build();
	}
	
	@Bean
	public Docket configSpringfoxDocket_foo() {
	
		return new Docket(DocumentationType.SWAGGER_2).groupName("foo").produces(Sets.newHashSet("application/json")).consumes(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http", "https")).forCodeGeneration(true).select().paths(regex(".*foo.*")).build();
	}*/
	/**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     *
     * @return SwaggerSpringMvcPlugin
     */
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths())
                .build()
                .apiInfo(getApiInfo())
                .pathMapping("/");
    }

    private Predicate<String> paths() {
    	if (Constants.isOfficialServer()) {
    		return PathSelectors.none();
    	}
        return PathSelectors.any();
    }

    private ApiInfo getApiInfo() {
    	
		return new ApiInfoBuilder()
				   .title("酷特 REST API")
				   .description("酷特API文档")
				   .termsOfServiceUrl("http://www.magicmanufactory.com")
				   .license("Apache License Version 2.0")
				   .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				   .version("2.0").build();
	}
}
