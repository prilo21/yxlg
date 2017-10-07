package com.yxlg.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.util.BusinessException;
import com.yxlg.base.util.PageBean;

/**
 * 基于Hibernate的DAO通用实现类
 * 
 * @author dirk
 *
 */
@Repository
public class BaseDAOImpl implements IBaseDAO {
	
	/**
	 * 初始化Log4j实例
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(BaseDAOImpl.class);
	
	/**
	 * 注入SessionFactory属性
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 获取Session
	 * 
	 * @return
	 */
	public Session getSession() {
	
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			logger.info("打开session");
			session = sessionFactory.openSession();
		}
	//	session.setFlushMode(FlushMode.AUTO);
		return session;
	}
	
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            待保存的实体
	 * @return
	 *         序列化id
	 */
	@Override
	public <T> Serializable save(T entity) {
			
		try {
			Serializable id = getSession().save(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			return id;
		} catch (Exception e) {
			logger.error("保存实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 批量保存数据,每20条数据提交一次
	 *
	 * @param entityList
	 *            要持久化的临时实体对象集合
	 */
	@Override
	public <T> void saveBatch(List<T> entityList) {
	
		for (int i = 0; i < entityList.size(); i++) {
			getSession().save(entityList.get(i));
			/*if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}*/
		}
		// 最后清理一下----防止大于20小于40的不保存
		/*getSession().flush();
		getSession().clear();*/
	}
	
	/**
	 * 根据传入的实体添加或更新对象
	 *
	 * @param entity
	 *            待保存或更新的对象
	 */
	@Override
	public <T> void saveOrUpdate(T entity) {
	
		try {
			getSession().saveOrUpdate(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (Exception e) {
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	
	/**
	 * 更新实体
	 *
	 * @param entity
	 *            待更新的实体
	 */
	@Override
	public <T> void update(T entity) {
	
		try {
			getSession().update(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("更新实体成功," + entity.getClass().getName());
			}
		} catch (Exception e) {
			logger.error("更新实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 根据属性值批量更新实体
	 * update 表名 set
	 * propertyName1=propertyValue1,propertyName2=propertyValue2... where
	 * propertyName=?
	 * 
	 * @param entityClass
	 *            待更新的实体
	 * @param updateMap
	 *            待更新属性值集合
	 * @param propertyName
	 *            更新条件属性
	 * @param propertyValue
	 *            更新条件属性值
	 * @return 影响的行数
	 */
	@Override
	public <T> int updateBatchByPropertyName(Class<T> entityClass,
			Map<String, Object> updateMap, String propertyName,
			Serializable propertyValue) {
	
		if (updateMap == null) {
			return 0;
		}
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("update ").append(entityClass.getName())
				.append(" set ");
		// 组装更新条件
		List<Object> listValue = new ArrayList<Object>();
		for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
			hqlBuffer.append(entry.getKey()).append("=?,");
			listValue.add(entry.getValue());
		}
		// 删除最后一个逗号
		hqlBuffer.deleteCharAt(hqlBuffer.length() - 1);
		hqlBuffer.append(" where ").append(propertyName).append("=?");
		try {
			Query query = getSession().createQuery(hqlBuffer.toString());
			// set 值
			for (int j = 0; j < updateMap.size(); j++) {
				Class<?> cl = listValue.get(j).getClass();
				if (cl.equals(String.class)) {
					query.setString(j, listValue.get(j).toString());
				} else if (cl.equals(Date.class)) {
					query.setTimestamp(j,(Date)listValue.get(j));
				} else if (cl.equals(Integer.class) || cl.equals(int.class)) {
					query.setInteger(j, (int) listValue.get(j));
				} else if (cl.equals(Double.class)) {
					query.setDouble(j, (double) listValue.get(j));
				} else if (cl.equals(Byte.class)) {
					query.setByte(j, (Byte) listValue.get(j));
				}
			}
			// where 条件
			query.setString(updateMap.size(), propertyValue.toString());
			int rows = query.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("批量更新实体成功," + entityClass.getName());
			}
			return rows;
		} catch (Exception e) {
			logger.error("批量更新实体失败", e);
			throw e;
		}
	}
	
	/**
	 * 根据属性值批量更新实体
	 * update 表名 set
	 * propertyName1=propertyValue1,propertyName2=propertyValue2... where
	 * propertyName in ()
	 * 
	 * @param entityClass
	 *            待更新的实体
	 * @param updateMap
	 *            待更新属性值集合
	 * @param propertyName
	 *            更新条件属性
	 * @param propertyValueList
	 *            更新条件属性值集合
	 * @return 影响的行数
	 */
	@Override
	public <T> int updateBatchByPropertyName(Class<T> entityClass,
			Map<String, Object> updateMap, String propertyName,
			List<Serializable> propertyValueList) {
	
		if (updateMap == null) {
			return 0;
		}
		if (propertyValueList == null || propertyValueList.size() == 0) {
			return 0;
		}
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("update ").append(entityClass.getName())
				.append(" set ");
		// 组装更新条件
		List<Object> listValue = new ArrayList<Object>();
		for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
			hqlBuffer.append(entry.getKey()).append("=?,");
			listValue.add(entry.getValue());
		}
		// 删除最后一个逗号
		hqlBuffer.deleteCharAt(hqlBuffer.length() - 1);
		hqlBuffer.append(" where ").append(propertyName)
				.append(" in (:valueList)");
		try {
			
			Query query = getSession().createQuery(hqlBuffer.toString());
			// set 值
			for (int j = 0; j < updateMap.size(); j++) {
				Class<?> cl = listValue.get(j).getClass();
				if (cl.equals(String.class)) {
					query.setString(j, listValue.get(j).toString());
				} else if (cl.equals(Date.class)) {
					query.setTimestamp(j,(Date)listValue.get(j));
				} else if (cl.equals(Integer.class) || cl.equals(int.class)) {
					query.setInteger(j, (int) listValue.get(j));
				} else if (cl.equals(Double.class)) {
					query.setDouble(j, (double) listValue.get(j));
				} else if (cl.equals(Byte.class)) {
					query.setByte(j, (Byte) listValue.get(j));
				}
			}
			query.setParameterList("valueList", propertyValueList);
			int rows = query.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("批量更新实体成功," + entityClass.getName());
			}
			return rows;
		} catch (Exception e) {
			logger.error("批量更新实体失败", e);
			throw e;
		}
	}
	
	/**
	 * 刷新Session缓存，用于批量更新、批量删除后
	 * @param entieyList
	 */
	@Override
	public <T> void refresh(List<T> entityList) {
		entityList.forEach(e -> getSession().refresh(e));
	}
	
	@Override
	public <T> void refresh(String propertyName,List<T> entityList) {
		entityList.forEach(e -> getSession().refresh(propertyName,e));
	}
	
	/**
	 * 清除session中指定的实体
	 * 
	 * @param entity
	 *            待清除的实体
	 */
	@Override
	public <T> void evict(T entity) {
	
		try {
			getSession().evict(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("从session中清除实体成功," + entity.getClass().getName());
			}
		} catch (Exception e) {
			logger.error("从session中清除实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 删除实体
	 *
	 * @param entity
	 *            待删除的实体
	 */
	@Override
	public <T> void delete(T entity) {
	
		try {
			getSession().delete(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("删除实体成功," + entity.getClass().getName());
			}
		} catch (Exception e) {
			logger.error("删除实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 删除指定的实体集合
	 *
	 * @param entityList
	 *            待删除的实体集合
	 */
	@Override
	public <T> void deleteAllEntity(Collection<T> entityList) {
	
		try {
			//int i=0;
			for (T entity : entityList) {
				getSession().delete(entity);
				if (logger.isDebugEnabled()) {
					logger.debug("批量删除实体成功," + entity.getClass().getName());
				}
				//i++;
				/*if (i%20==0) {
					getSession().flush();
				}*/
			}
		} catch (Exception e) {
			logger.error("批量删除实体失败", e);
			throw e;
		}
	}
	
	/**
	 * 根据属性集合删除数据
	 * delete from table where propertyName in ()
	 * 
	 * @param entityClass
	 *            待删除的实体类
	 * @param propertyName
	 *            待删除属性名称
	 * @param valueList
	 *            待删除属性集合
	 */
	@Override
	public <T> int deleteByValueList(Class<T> entityClass, String propertyName,
			List<Serializable> valueList) {
	
		if (valueList == null) {
			return 0;
		}
		if (valueList.size() == 0) {
			return 0;
		}
		String hql = "delete from " + entityClass.getName() + " where "
				+ propertyName + " in (:valueList)";
		try {
			int rows = getSession().createQuery(hql)
					.setParameterList("valueList", valueList).executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除实体成功," + entityClass.getName());
			}
			return rows;
		} catch (Exception e) {
			logger.error("批量删除实体失败", e);
			throw e;
		}
	}
	
	/**
	 * 根据主键查询实体
	 * 
	 * @param entityClass
	 *            待查询实体的Class
	 * @param id
	 *            待查询实体的主键
	 * @return T
	 *         查询到的实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(Class<T> entityClass, Serializable id) {
	
		return (T) getSession().get(entityClass, id);
	}
	/**
	 * 根据主键查询实体
	 * 
	 * @param entityClass
	 *            待查询实体的Class
	 * @param id
	 *            待查询实体的主键
	 * @return T
	 *         查询到的实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(Class<T> entityClass, Serializable id,LockOptions lockOptions) {
	
		return (T) getSession().get(entityClass, id,lockOptions);
	}
	/**
	 * 根据实体属性查询唯一记录
	 * 
	 * @param entityClass
	 *            待查询实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值
	 * @return T
	 *         查询到的实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
	
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}
	/**
	 * 根据实体属性查询唯一记录
	 * 
	 * @param entityClass
	 *            待查询实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值
	 * @return T
	 *         查询到的实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value,LockMode lockMode) {
	
		return (T) createCriteria(entityClass,lockMode,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}
	/**
	 * 根据实体属性查询所有记录
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass,
			String propertyName, Object value) {
	
		return (List<T>) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).list();
	}
	/**
	 * 根据实体属性查询所有记录
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByProperty(Class<T> entityClass,
			String propertyName, Object value,LockMode lockMode) {
	
		return (List<T>) createCriteria(entityClass,lockMode,
				Restrictions.eq(propertyName, value)).list();
	}
	/**
	 * 根据实体属性查询所有记录带排序
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值
	 * @param isAsc
	 *            是否升序 true：升序 false：降序
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
	
		return createCriteria(
				createCriteria(entityClass,
						Restrictions.eq(propertyName, value)), propertyName,
				isAsc).list();
	}
	
	/**
	 * 根据实体属性模糊查询所有记录
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值（部分）
	 * @param matchMode
	 *            匹配模式
	 *            MatchMode。EXACT 完全匹配（=）
	 *            MatchMode.START 前端匹配
	 *            MatchMode.END 后端匹配
	 *            MatchMode.ANYWHERE 两端匹配
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByPropertyLike(Class<T> entityClass,
			String propertyName, Object value, MatchMode matchMode) {
	
		return (List<T>) createCriteria(entityClass,
				Restrictions.like(propertyName, (String) value, matchMode))
				.list();
	}
	
	/**
	 * 根据实体属性模糊查询所有记录带排序
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param propertyName
	 *            待查询实体属性
	 * @param value
	 *            待查询实体属性值（部分）
	 * @param isAsc
	 *            是否升序 true：升序 false：降序
	 * @param matchMode
	 *            匹配模式
	 *            MatchMode。EXACT 完全匹配（=）
	 *            MatchMode.START 前端匹配
	 *            MatchMode.END 后端匹配
	 *            MatchMode.ANYWHERE 两端匹配
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByPropertyLikeIsOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc,
			MatchMode matchMode) {
	
		return (List<T>) createCriteria(
				createCriteria(entityClass, Restrictions.like(propertyName,
						(String) value, matchMode)), propertyName, isAsc)
				.list();
	}
	
	/**
	 * 查询全部实体
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
	
		return createCriteria(entityClass).list();
	}
	
	/**
	 * 查询全部实体，支持按属性排序
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @param orderbyProperty
	 *            排序属性
	 * @param isAsc
	 *            是否升序 true：升序 false：降序
	 * @return List<T>
	 *         查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAllIsOrder(final Class<T> entityClass,
			String orderbyProperty, boolean isAsc) {
	
		return (List<T>) createCriteria(createCriteria(entityClass),
				orderbyProperty, isAsc).list();
	}
	
	/**
	 * 创建单一Criteria对象
	 *
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	@Override
	public <T> Criteria createCriteria(Class<T> entityClass) {
	
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}
	/**
	 * 创建单一Criteria对象
	 *
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	@Override
	public <T> Criteria createCriteria(Class<T> entityClass,LockMode LockMode) {
	
		Criteria criteria = getSession().createCriteria(entityClass).setLockMode(LockMode);
		return criteria;
	}
	/**
	 * 创建Criteria对象带属性比较
	 *
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
	
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	/**
	 * 创建Criteria对象带属性比较
	 *
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass,LockMode lockMode,
			Criterion... criterions) {
	
		Criteria criteria = getSession().createCriteria(entityClass).setLockMode(lockMode);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	/**
	 * 创建Criteria对象，有排序功能。
	 *
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Criteria criteria,
			String orderbyProperty, boolean isAsc) {
	
		if (isAsc) {
			criteria.addOrder(Order.asc(orderbyProperty));
		} else {
			criteria.addOrder(Order.desc(orderbyProperty));
		}
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
	
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}
	/**
	 * 分页查询实体
	 * 
	 * @param page
	 *            分页条件
	 * @param entityClass
	 *            待查询的实体
	 * @return 查询到的实体集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllWithPage(PageBean pageBean, Class<T> entityClass) {
	
		Criteria criteria = createCriteria(entityClass);
		criteria.setFirstResult((pageBean.getPage() - 1) * pageBean.getRows())
				.setMaxResults(pageBean.getRows());
		
		return criteria.list();
	}
	
	/**
	 * 查询结果条数
	 * 
	 * @param entityClass
	 *            待查询的实体Class
	 * @return 查询到的记录条数
	 */
	@Override
	public <T> Long findAllCount(Class<T> entityClass) {
	
		Criteria criteria = createCriteria(entityClass);
		return findCountResult(criteria);
	}
	
	private Long findCountResult(Criteria criteria) {
	
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		if (totalCount == null || totalCount < 1) {
			return 0L;
		}
		
		return totalCount;
	}
	
	/**
	 * 根据条件分页查询实体
	 * 
	 * @param pageBean
	 *            分页条件
	 * @param detachedCriteria
	 *            查询条件
	 * @return 查询到的实体集合
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	@Override
	public <T> List<T> findAllWithPageCriteria(PageBean pageBean,
			DetachedCriteria detachedCriteria) {
		
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		List list = criteria.setFirstResult((pageBean.getPage() - 1) * pageBean.getRows())
				.setMaxResults(pageBean.getRows()).list();
		criteria.setFirstResult(0).setMaxResults(0);
		return list;
	}
	
	/**
	 * 根据条件查询结果条数
	 * 
	 * @param detachedCriteria
	 *            查询条件
	 * @return 查询到的记录总数
	 */
	@Override
	public Long findAllCountCriteria(DetachedCriteria detachedCriteria) {
	
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		if (totalCount == null || totalCount < 1) {
			return 0L;
		}
		return totalCount;
	}
	public Long findAllCountCriteria(DetachedCriteria detachedCriteria, ProjectionList projections) {
		
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		Long totalCount = (Long) criteria.setProjection(projections.add(Projections.rowCount()))
				.uniqueResult();
		criteria.setProjection(null);
		if (totalCount == null || totalCount < 1) {
			return 0L;
		}
		return totalCount;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxlg.base.dao.IBaseDAO#deleteByValueList(java.lang.Class,
	 * java.lang.String, java.lang.String[])
	 */
	@Override
	public <T> int deleteByValueList(Class<T> entityClass,
			Map<String, Object> values) {
	
		if (values == null) {
			return 0;
		}
		StringBuffer hql = new StringBuffer("delete from ").append(
				entityClass.getName()).append(" as tab  where ");
		
		Set<String> valuesList = values.keySet();
		
		for (String string : valuesList) {
			hql.append("tab.").append(string).append(" = ? and ");
		}
		hql.append(" 1=1 ");
		try {
			int rows = 0;
			Query query = getSession().createQuery(hql.toString());
			int i = 0;
			for (String string : valuesList) {
				query.setParameter(i++, values.get(string));
			}
			rows = query.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除实体成功," + entityClass.getName());
			}
			return rows;
		} catch (Exception e) {
			logger.error("批量删除实体失败", e);
			throw e;
		}
	}

    /* (non-Javadoc)
     * @see com.yxlg.base.dao.IBaseDAO#updateBatch(java.util.List)
     */
    @Override
    public <T> void updateBatch(List<T> entityList) {
        
        for (int i = 0; i < entityList.size(); i++) {
            getSession().update(entityList.get(i));
            /*if (i % 20 == 0) {
                // 20个对象后才清理缓存，写入数据库
                getSession().flush();
                getSession().clear();
            }*/
        }
        // 最后清理一下----防止大于20小于40的不保存
       /* getSession().flush();
        getSession().clear();*/
    
    }
    /**
     * 执行sql语句
     */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> createSQLQuery(Class<T> entityClass, String sql) {
		return getSession().createSQLQuery(sql).addEntity(entityClass).list();  
		
	}
	 /**
     * 执行sql语句
     */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> createSQLQuery(Class<T> entityClass, String sql,LockOptions lockOptions) {
		return ((SQLQuery) getSession().createSQLQuery(sql).setLockOptions(lockOptions)).addEntity(entityClass).list();  
		
	}
    /**
     * 执行sql获得结果集
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryBySQL(String sql) {
		return getSession().createSQLQuery(sql).list();
	}
	/**
	 * 执行sql语句获得总数 a6c48f2
	 */
	@Override
	public <T> Object queryCountBySQL(String sql) {
		SQLQuery sq = getSession().createSQLQuery(sql);
	    return sq.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#updateByHql(java.lang.String)
	 */
	@Override
	public int updateByHql(String hql,List<Object> valueList,LockOptions options) {
		if (valueList==null || valueList.isEmpty()) {
			return 0;
		}
		Query query = getSession().createQuery(hql);
		query.setLockOptions(options);
		for (int i = 0; i < valueList.size(); i++) {
			query.setParameter(i, valueList.get(i));
		}
		return query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#updateByHql(java.lang.String)
	 */
	@Override
	public int updateByHql(String hql,List<Object> valueList) {
		if (valueList==null || valueList.isEmpty()) {
			return 0;
		}
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < valueList.size(); i++) {
			query.setParameter(i, valueList.get(i));
		}
		return query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#findByHql(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByNamedQuery(String queryName, Map<String, Object> paramMap) {
		Query query = getSession().getNamedQuery(queryName);
		if (paramMap!=null && !paramMap.isEmpty()) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				query.setParameter(key, paramMap.get(key));
			}
		}
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#queryBySql(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryBySql(String sql, Map<String, Object> paramMap) {
		Query query = getSession().createSQLQuery(sql);
		if (paramMap!=null && !paramMap.isEmpty()) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				query.setParameter(key, paramMap.get(key));
			}
		}
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#queryBySql(java.lang.Class, java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryBySql(Class<T> entityClass, String sql, Map<String, Object> paramMap) {
		Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
		if (paramMap!=null && !paramMap.isEmpty()) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				query.setParameter(key, paramMap.get(key));
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryByView(String sql,Map<String, Object> paramMap) {
		Query query = getSession().createSQLQuery(sql);
		if (paramMap!=null && !paramMap.isEmpty()) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				query.setParameter(key, paramMap.get(key));
			}
		}
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list ();
	}
	@Override
	public void  transactionRollback(){
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	/* (non-Javadoc)
	 * @see com.yxlg.base.dao.IBaseDAO#queryBySQLT(java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> List<T> queryBySQL(String sql, Class<T> t) {
	
		List<T> list = new ArrayList<T>();
		List<Object> objList = getSession().createSQLQuery(sql).list();
		for(int i = 0; i<objList.size(); i++){
			Object[] objects = (Object[]) objList.get(i);
			try {
				list.add(setReflectValue(t.newInstance(), objects));
			} catch (InstantiationException | IllegalAccessException e) {
				throw new BusinessException("反射异常", e);
			}
		}
		return list;
	}

	private <T> T setReflectValue(T t, Object[] objects){
		Field[] fields = t.getClass().getDeclaredFields();
		for(int i = 0 ; i < objects.length; i++){
			String type = fields[i+1].getGenericType().toString();
			String name = "set" + fields[i+1].getName().substring(0, 1).toUpperCase() + fields[i+1].getName().substring(1);
			try {
				switch(type){
					case "class java.lang.String":
						invokeMethod(t, name, String.class, objects[i]);
						break;
					case "class java.lang.Integer":
						invokeMethod(t, name, Integer.class, objects[i]);
						break;
					case "class java.lang.boolean":
						invokeMethod(t, name, Boolean.class, objects[i]);
						break;
					case "class java.util.Date":
						invokeMethod(t, name, Date.class, objects[i]);
						break;
				}
			} catch (SecurityException | IllegalArgumentException e) {
			}
		}
		return t;
	}
	private <T, J, K> void invokeMethod(T t, String setName, Class<J> j, K k){
		try {
			Method method = t.getClass().getDeclaredMethod(setName, j);
			method.invoke(t, k);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BusinessException("sql反射异常", e);
		}
	}
}
