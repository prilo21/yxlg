package com.yxlg.member.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.util.NewResult;
import com.yxlg.member.service.IMemberSuperPWService;

@Service
public class MemberSuperPWServiceImpl implements IMemberSuperPWService{
	
	@Override
	public ResponseEntity<NewResult<String>> getSuperPWStatus() {
		/*ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
		String enableFlag = (String) valueOps.get(RedisBaseKeys.SUPER_PASSWORD_ENABLE);*/
		String enableFlag = "";
		
		return new ResponseEntity<NewResult<String>>(new NewResult<String>("enableFlag is: " + enableFlag), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NewResult<String>> setSuperStatus(String enableFlag){
		/*ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
		valueOps.set(RedisBaseKeys.SUPER_PASSWORD_ENABLE, enableFlag);*/
		
        return new ResponseEntity<NewResult<String>>(new NewResult<String>("操作成功,设置为" + ""), 
        		HttpStatus.OK);
	}
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
}
