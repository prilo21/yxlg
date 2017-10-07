package com.yxlg.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.LockOptions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * 基于Hibernate的Service通用实现类
 * 
 * @author Marvin
 *
 */
@Service
public class BaseServiceImpl implements IBaseService {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            待保存的实体
	 * @return 序列化id
	 */
	@Override
	public <T> Serializable save(T entity) {
	return	baseDAO.save(entity);
		//throw new RuntimeException();
	}

	/**
	 * 批量保存数据,每20条数据提交一次
	 * 
	 * @param entityList
	 *            要持久化的临时实体对象集合
	 */
	@Override
	public <T> void saveBatch(List<T> entityList) {
		baseDAO.saveBatch(entityList);
	}

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param entity
	 *            待保存或更新的对象
	 */
	@Override
	public <T> void saveOrUpdate(T entity) {
		baseDAO.saveOrUpdate(entity);
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 * 			待更新的实体
	 */
	@Override
	public <T> void update(T entity) {
		baseDAO.update(entity);
	}

	/**
	 * 删除实体
	 * 
	 * @param entity
	 * 			待删除的实体
	 */
	@Override
	public <T> void delete(T entity) {
		baseDAO.delete(entity);

	}

	/**
	 * 根据主键删除实体
	 * @param entityClass 待删除的实体Class
	 * @param id 待删除的实体主键
	 */
	@Override
	public <T> void deleteById(Class<T> entityClass,Serializable id) {
		baseDAO.delete(baseDAO.findById(entityClass, id));
	}

	/**
	 * 删除指定的实体集合
	 * 
	 * @param entityList
	 * 			待删除的实体集合
	 */
	@Override
	public <T> void deleteAllEntity(Collection<T> entityList) {
		baseDAO.deleteAllEntity(entityList);
	}

	/**
	 * 根据主键查询实体
	 * @param entityClass
	 * 			待查询实体的Class
	 * @param id
	 * 			待查询实体的主键
	 * @return T
	 * 			查询到的实体
	 */
	@Override
	public <T> T findById(Class<T> entityClass, Serializable id) {
		return baseDAO.findById(entityClass, id);
	}
	/**
	 * 根据主键查询实体
	 * @param entityClass
	 * 			待查询实体的Class
	 * @param id
	 * 			待查询实体的主键
	 * @return T
	 * 			查询到的实体
	 */
	@Override
	public <T> T findById(Class<T> entityClass, Serializable id,LockOptions lockOptions) {
		return baseDAO.findById(entityClass, id,lockOptions);
	}
	/**
	 * 根据实体属性查询唯一记录
	 * @param entityClass
	 * 			待查询实体Class
	 * @param propertyName
	 * 			待查询实体属性
	 * @param value
	 * 			待查询实体属性值
	 * @return T
	 * 			查询到的实体
	 */
	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return baseDAO.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * 根据实体属性查询所有记录
	 * @param entityClass
	 * 			待查询的实体Class
	 * @param propertyName
	 * 			待查询实体属性
	 * @param value
	 * 			待查询实体属性值
	 * @return List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return baseDAO.findListByProperty(entityClass, propertyName, value);
	}

	/**
	 * 根据实体属性查询所有记录带排序
	 * @param entityClass
	 * 			待查询的实体Class
	 * @param propertyName
	 * 			待查询实体属性
	 * @param value
	 * 			待查询实体属性值
	 * @param isAsc
	 * 			是否升序 true：升序 false：降序
	 * @return List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		return baseDAO.findByPropertyisOrder(entityClass, propertyName, value, isAsc);
	}

	/**
	 * 根据实体属性模糊查询所有记录
	 * @param entityClass
	 * 			待查询的实体Class
	 * @param propertyName
	 * 			待查询实体属性
	 * @param value
	 * 			待查询实体属性值（部分）
	 * @param matchMode
	 * 			匹配模式
	 *  		   MatchMode.EXACT 完全匹配（like 'value'）
	 *  		   MatchMode.START 前端匹配（like 'value%'）
	 *   		   MatchMode.END 后端匹配（like '%value'）
	 *   		   MatchMode.ANYWHERE 两端匹配（like '%value%'）
	 * @return List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findListByPropertyLike(Class<T> entityClass,
			String propertyName, Object value, MatchMode matchMode) {
		return baseDAO.findListByPropertyLike(entityClass, propertyName, value, matchMode);
	}

	/**
	 * 根据实体属性模糊查询所有记录带排序
	 * @param entityClass
	 * 			待查询的实体Class
	 * @param propertyName
	 * 			待查询实体属性
	 * @param value
	 * 			待查询实体属性值（部分）
	 * @param isAsc
	 * 			是否升序 true：升序 false：降序
	 * @param matchMode
	 * 			匹配模式
	 *  		   MatchMode.EXACT 完全匹配（like 'value'）
	 *  		   MatchMode.START 前端匹配（like 'value%'）
	 *   		   MatchMode.END 后端匹配（like '%value'）
	 *   		   MatchMode.ANYWHERE 两端匹配（like '%value%'）
	 * @return List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findListByPropertyLikeIsOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc,
			MatchMode matchMode) {
		return baseDAO.findListByPropertyLikeIsOrder(entityClass, propertyName, value, isAsc, matchMode);
	}

	/**
	 * 查询全部实体
	 * @param entityClass
	 * 			待查询的实体Class
	 * @return	List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		return baseDAO.findAll(entityClass);
	}

	/**
	 * 查询全部实体，支持按属性排序
	 * @param entityClass
	 * 			待查询的实体Class
	 * @param orderbyProperty
	 * 			排序属性
	 * @param isAsc
	 * 			是否升序 true：升序 false：降序
	 * @return List<T>
	 * 			查询到的实体集合
	 */
	@Override
	public <T> List<T> findAllIsOrder(Class<T> entityClass,
			String orderbyProperty, boolean isAsc) {
		return baseDAO.findAllIsOrder(entityClass, orderbyProperty, isAsc);
	}

	@Override
	public <T> List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return baseDAO.findByDetachedCriteria(detachedCriteria);
	}

	/**
	 * 分页查询实体
	 * @param page
	 * 			分页条件
	 * @param entityClass
	 * 			待查询的实体
	 * @return	查询到的实体集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> Pagination findAllWithPage(PageBean pageBean, Class<T> entityClass) {
		Pagination pagination = new Pagination();
		Long count = baseDAO.findAllCount(entityClass);
		List rows = baseDAO.findAllWithPage(pageBean, entityClass);
		pagination.setRows(rows);
		pagination.setTotal(count);
		
		return pagination;
	}

	/**
	 * 根据条件查询结果条数
	 * @param entityClass
	 * 			待查询的实体Class
	 * @return 查询到的记录条数
	 */
	@Override
	public <T> Long findAllCount(Class<T> entityClass) {
		return baseDAO.findAllCount(entityClass);
	}
	
	/**
	 * 根据条件分页查询实体
	 * @param pageBean
	 * 			分页条件
	 * @param detachedCriteria
	 * 			查询条件
	 * @return 查询到的实体集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Pagination findAllWithPageCriteria(PageBean pageBean,DetachedCriteria detachedCriteria) {
		Pagination pagination = new Pagination();
		Long count = baseDAO.findAllCountCriteria(detachedCriteria);
		List rows = baseDAO.findAllWithPageCriteria(pageBean, detachedCriteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}

	/**
     * 根据条件查询结果条数
     * @param detachedCriteria
     * 			查询条件
     * @return 查询到的记录总数
     */
	@Override
	public <T> Long findCountCriteria(DetachedCriteria detachedCriteria) {
		return baseDAO.findAllCountCriteria(detachedCriteria);
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.base.service.IBaseService#deleteByValueList(java.lang.Class, java.lang.String, java.util.List)
	 */
	@Override
	public <T> int deleteByValueList(Class<T> entityClass, String propertyName,List<Serializable> valueList) {
	
		return baseDAO.deleteByValueList(entityClass, propertyName, valueList);
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.service.IBaseService#updateBatchByPropertyName(java.lang.Class, java.util.Map, java.lang.String, java.io.Serializable)
	 */
	@Override
	public <T> int updateBatchByPropertyName(Class<T> entityClass,
			Map<String, Object> updateMap, String propertyName,
			Serializable propertyValue) {
		
		return baseDAO.updateBatchByPropertyName(entityClass, updateMap, propertyName, propertyValue);
	}
	
	/**
     * 根据属性值批量更新实体
     * update 表名 set propertyName1=propertyValue1,propertyName2=propertyValue2... where propertyName in ()
     * @param entityClass
     * 			待更新的实体
     * @param updateMap
     * 			待更新属性值集合
     * @param propertyName
     * 			更新条件属性
     * @param propertyValueList
     * 			更新条件属性值集合
     */
    public <T> int updateBatchByPropertyName(Class<T> entityClass,Map<String, Object> updateMap,String propertyName,List<Serializable> propertyValueList) {
    	return baseDAO.updateBatchByPropertyName(entityClass, updateMap, propertyName, propertyValueList);
    }

	/* (non-Javadoc)
	 * @see com.yxlg.base.service.IBaseService#findByHql(java.lang.String, java.util.List)
	 */
	@Override
	public <T> List<T> findListByNamedQuery(String queryName, Map<String, Object> paramMap) {
		
		return baseDAO.findListByNamedQuery(queryName, paramMap);
	}
	
	@Resource
	private IBaseDAO baseDAO;

	@Override
	public int updateByHql(String hql, List<Object> valueList, LockOptions lockOptions) {
	
		return baseDAO.updateByHql(hql, valueList, lockOptions);
	}

	@Override
	public int updateByHql(String hql, List<Object> valueList) {
	
		return baseDAO.updateByHql(hql, valueList);
	}

	@Override
	public <T> List<T> createSQLQuery(Class<T> entityClass, String sql) {
		return baseDAO.createSQLQuery(entityClass, sql);
	}

	@Override
	public List<Object[]> queryBySQL(String sql) {
		return baseDAO.queryBySQL(sql);
	}

	@Override
	public List<Object[]> queryBySql(String sql, Map<String, Object> paramMap) {
		return baseDAO.queryBySql(sql, paramMap);
	}

	@Override
	public <T> List<T> queryBySql(Class<T> entityClass, String sql, Map<String, Object> paramMap) {
		return baseDAO.queryBySql(entityClass, sql, paramMap);
	}
}