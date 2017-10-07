package com.yxlg.member.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.jerry.entity.Students;
import com.yxlg.base.jerry.entity.StudentsDto;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IStudentsService;

@Service
public class StudentServiceImpl implements IStudentsService{

	@Override
	public ResponseEntity<Result> findAllStudent() {
		//String sql = "select * from jer_students";
		String sql = "select * from jer_students where city = '菏泽'";
		//List<Object[]> studentList = baseDao.queryBySQL(sql);
		List<Students> studentList = baseDao.queryBySQL(sql, Students.class);
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		for (Students student : studentList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("姓名", student.getName());
			map.put("学号", student.getNo());
			map.put("城市",student.getCity());
			returnList.add(map);
		}
		return new ResponseEntity<Result>(new Result(returnList),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Result> findAllStudentByDet() {

		List<Students> list = baseDao.findAll(Students.class);
		
		return new ResponseEntity<Result>(new Result(list),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Result> addStudent(StudentsDto stu) {
		Students s = new Students();
		s.setName(stu.getName());
		s.setNo(stu.getNo());
		s.setCity(stu.getCity());
		s.setProvice(stu.getProvice());
		s.setIdCard(stu.getIdCard());
		s.setAge(stu.getAge());
		
		
		Date b = null;
		String dateString = stu.getBithday();  
		try  
		{  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    b = sdf.parse(dateString);  
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		}
		
		
		
		s.setBithday(b);
		baseDao.save(s);
		return new ResponseEntity<Result>(new Result("保存成功"),HttpStatus.OK);
	}
	@Resource 
	private IBaseDAO baseDao;
}
