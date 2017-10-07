/*
 * FileController.java
 *
 * Created Date: 2015年5月26日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.service.IFileService;
import com.yxlg.base.upload.dto.Base64File;
import com.yxlg.base.util.NewResult;
import com.yxlg.base.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author alison.liu
 * @version  <br>
 * <p>文件控制器</p>
 */
@Controller("fileController")
@Api(tags = "文件控制类")
public class FileController {
	
	/**
	 * client上传图片
	 * @param id
	 * @param base64File
	 * @return
	 */
	@ApiOperation(value="client上传图片", notes = "client上传图片", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "base64String", value = "base64编码后的字符串", required = true, dataType = "string", paramType = "query", defaultValue=""),
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/images/{id}", method = RequestMethod.POST)
	public ResponseEntity<Result> uploadClientImg(@PathVariable String id, @RequestBody Base64File base64File){
		return fileService.uploadIMG(id,base64File);
	}
	
	/**
	 * 上传七牛，自定义七牛得路径
	 * @param key 存放到七牛上的路径，也是path，唯一标准
	 * @param base64File
	 * @return
	 */
	@ApiOperation(value="上传至七牛", notes = "上传文件至七牛，可自定义存放路径", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "key", value = "自定义存放路径", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "base64String", value = "base64编码后的字符串", required = true, dataType = "string", paramType = "query", defaultValue="")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/file/private/{key}", method = RequestMethod.POST)
	public ResponseEntity<Result> uploadPrivateFile(@PathVariable String key, @RequestBody Base64File base64File){
		return fileService.uploadPrivateFile(key,base64File);
	} 
	
	/**
	 * 获得私有空间的token
	 * @return
	 */
	@ApiOperation(value="获得私有空间的token", notes = "获得私有空间的token")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/file/private/token", method = RequestMethod.GET)
	public ResponseEntity<Result> getPrivateToken(){
		return fileService.getPrivateToken();
	} 
	
	/**
	 * 获得resources空间上传token
	 * @return
	 */
	@ApiOperation(value="获得resources空间上传token", notes = "获得resources空间上传token")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/file/public/token", method = RequestMethod.GET)
	public ResponseEntity<Result> getPublicToken(){
		return fileService.getPublicToken();
	}
	

	/**
	 * 2017-1-12 alisa.yang 测试微信小程序上传图片的功能,之后会修改相应
	 * @param dto
	 * @param request
	 * @return
	 */
	@ApiOperation(value="微信小程序上传图片的功能，图片列表", notes = "微信小程序上传图片", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_2 + "/test/WXSmartProgram",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public @ResponseBody ResponseEntity<NewResult<List<String>>> testWXSmartProgram(@ModelAttribute TestWXDto dto, HttpServletRequest request){
		//只需要打印日志，查看有哪些参数
		if(StringUtils.isNotBlank(dto.getId()) && dto.getPicFile() != null && dto.getPicFile().length > 0){
			List<String> urlsList = new ArrayList<String>();
			for (MultipartFile file : dto.getPicFile()) {
				//String fileName = file.getOriginalFilename();
				//上传文件到七牛，然后将url拿到
				String url = Constants.uploadImgFile(file, "test/WXSmartProgram", dto.getId(), null);
				urlsList.add(url);
			}
			return new ResponseEntity<NewResult<List<String>>>(new NewResult<List<String>>(HttpStatus.OK.value(), Constants.ReturnMsg.SC_SUCCESS, urlsList), HttpStatus.OK);
		}
		return new ResponseEntity<NewResult<List<String>>>(new NewResult<List<String>>(HttpStatus.OK.value(), Constants.ReturnMsg.SC_SUCCESS), HttpStatus.OK);
		
	}
	
	@Resource
	private IFileService fileService;
}
