package com.yxlg.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;

import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * 基于Hibernate的通用Service
 * @author Marvin
 *
 */
public interface IBaseService {

	/**
	 * 保存实体
	 * @param entity 
	 * 			待保存的实体
	 * @return 
	 * 			序列化id
	 */
	public <T> Serializable save(T entity);
	
	/**
	 * 批量保存数据,每20条数据提交一次
	 * 
	 * @param entityList
	 *            要持久化的临时实体对象集合
	 */
	public <T> void saveBatch(List<T> entityList);
	
	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param entity
	 *  		待保存或更新的对象
	 */
	public <T> void saveOrUpdate(T entity);
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	 * 			待更新的实体
	 */
	public <T> void update(T entity);
	
	/**
     * 根据属性值批量更新实体
     * update 表名 set propertyName1=propertyValue1,propertyName2=propertyValue2... where propertyName=?
     * @param entityClass
     * 			待更新的实体
     * @param updateMap
     * 			待更新属性值集合
     * @param propertyName
     * 			更新条件属性
     * @param propertyValue
     * 			更新条件属性值
     * @return 影响的行数
     */
	public <T> int updateBatchByPropertyName(Class<T> entityClass,Map<String, Object> updateMap,String propertyName,Serializable propertyValue);
	
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
     * @return 影响的行数
     */
    public <T> int updateBatchByPropertyName(Class<T> entityClass,Map<String, Object> updateMap,String propertyName,List<Serializable> propertyValueList);
	
	/**
	 * 删除实体
	 * 
	 * @param entity
	 * 			待删除的实体
	 */
	public <T> void delete(T entity);
	
	/**
	 * 删除指定的实体集合
	 * 
	 * @param entityList
	 * 			待删除的实体集合
	 */
	public <T> void deleteAllEntity(Collection<T> entityList);
	
	/**
	 * 根据主键删除实体
	 * @param entityClass 待删除的实体Class
	 * @param id 待删除的实体主键
	 */
	public <T> void deleteById(Class<T> entityClass,Serializable id);
	
    /**
     * 根据属性集合删除数据
     * delete from table where propertyName in ()
     * @param entityClass
     * 			待删除的实体类
     * @param propertyName
     * 			待删除属性名称
     * @param valueList
     * 			待删除属性集合
     */
    public <T> int deleteByValueList(Class<T> entityClass,String propertyName,List<Serializable> valueList);
    
	/**
	 * 根据主键查询实体
	 * @param entityClass
	 * 			待查询实体的Class
	 * @param id
	 * 			待查询实体的主键
	 * @return T
	 * 			查询到的实体
	 */
	public <T> T findById(Class<T> entityClass, Serializable id);
	/**
	 * 根据主键查询实体
	 * @param entityClass
	 * 			待查询实体的Class
	 * @param id
	 * 			待查询实体的主键
	 * @return T
	 * 			查询到的实体
	 */
	public <T> T findById(Class<T> entityClass, Serializable id,LockOptions lockOptions);
		
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
	public <T> T findUniqueByProperty(Class<T> entityClass,String propertyName, Object value);
	
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
	public <T> List<T> findListByProperty(Class<T> entityClass,String propertyName, Object value);
	
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
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,String propertyName, Object value, boolean isAsc);
	
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
	public <T> List<T> findListByPropertyLike(Class<T> entityClass,String propertyName,Object value,MatchMode matchMode);
	
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
	public <T> List<T> findListByPropertyLikeIsOrder(Class<T> entityClass,String propertyName,Object value,boolean isAsc,MatchMode matchMode);
	
	/**
	 * 查询全部实体
	 * @param entityClass
	 * 			待查询的实体Class
	 * @return	List<T>
	 * 			查询到的实体集合
	 */
	public <T> List<T> findAll(final Class<T> entityClass);
	
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
	public <T> List<T> findAllIsOrder(final Class<T> entityClass,String orderbyProperty,boolean isAsc);
	
	/**
     * 根据条件查询裕
     * @param detachedCriteria 查询条件
     * @return 查询到的实体集合
     */
	public <T> List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	/**
	 * 分页查询实体
	 * @param page
	 * 			分页条件
	 * @param entityClass
	 * 			待查询的实体
	 * @return	查询到的实体集合
	 */
	public <T> Pagination findAllWithPage(PageBean pageBean, final Class<T> entityClass);
	
	/**
	 * 查询结果条数
	 * @param entityClass
	 * 			待查询的实体Class
	 * @return 查询到的记录条数
	 */
	public <T> Long findAllCount(final Class<T> entityClass);
	
	/**
	 * 根据条件分页查询实体
	 * @param pageBean
	 * 			分页条件
	 * @param detachedCriteria
	 * 			查询条件
	 * @return 查询到的实体集合
	 */
	public <T> Pagination findAllWithPageCriteria(PageBean pageBean, DetachedCriteria detachedCriteria);
	
	/**
	 * 根据条件查询结果总数
	 * @param detachedCriteria
	 * 			查询条件
	 * @return 查询到的记录条数
	 */
	public <T> Long findCountCriteria(DetachedCriteria detachedCriteria);
	
	/**
     * 根据条件查询数据
     * @param queryName 待查询的hql或sql语句名称
     * @param paramMap 查询条件
     * @return 符合条件的数据集合
     */
	public <T> List<T> findListByNamedQuery(String queryName, Map<String, Object> paramMap);
	
	 /**
     * 更新数据库,加锁选项
     * @param hql 更新hql语句
     * @return 影响的行数
     */
    public int updateByHql(String hql,List<Object> valueList,LockOptions lockOptions);
    /**
     * 更新数据库
     * @param hql 更新hql语句
     * @return 影响的行数
     */
    public int updateByHql(String hql,List<Object> valueList);

    public <T> List<T> createSQLQuery(Class<T> entityClass, String sql);
    
    List<Object[]> queryBySQL(String sql);
    
    List<Object[]> queryBySql(String sql, Map<String, Object> paramMap);
    <T> List<T> queryBySql(Class<T> entityClass, String sql, Map<String, Object> paramMap);
    
}
