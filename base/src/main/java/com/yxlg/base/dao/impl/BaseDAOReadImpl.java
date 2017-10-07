package com.yxlg.base.dao.impl;

import java.io.Serializable;
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

import com.yxlg.base.dao.IBaseDAORead;
import com.yxlg.base.util.PageBean;

/**
 * 基于Hibernate的DAO通用实现类，用于从库，只读
 * 
 * @author dirk
 *
 */
@Repository
public class BaseDAOReadImpl implements IBaseDAORead {
	
	/**
	 * 初始化Log4j实例
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(BaseDAOReadImpl.class);
	
	/**
	 * 注入SessionFactory属性
	 */
	@Autowired
	@Qualifier("sessionFactoryRead")
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

}
