/*
 * ResourceService.java
 * 
 * Created Date: 2015年5月6日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.impl.BaseDAOImpl;
import com.yxlg.base.sys.entity.RoleResource;
import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.util.TreeGrid;
import com.yxlg.manage.sys.service.IManageSysResourceService;

/**
 * @author Cary.yue
 * @version <br>
 *          <p>
 *          权限资源ServiceImpl
 *          </p>
 */
@Service
public class ManageSysResourceServiceImpl implements IManageSysResourceService {
    
    /**
     * 
     * @param treeGrid
     */
    public List<TreeGrid> findRes(TreeGrid treeGrid) {
    
        List<TreeGrid> treeList = new ArrayList<TreeGrid>();
        List<SysResource> resList = new ArrayList<SysResource>();
        
        // 如果没有ID,查询一级目录
        if (StringUtils.isEmpty(treeGrid.getId())) {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            // 父菜单为空
            det.add(Restrictions.or((Restrictions.eq("presource.id", "")), Restrictions.isNull("presource.id")));
            det.addOrder(Order.asc("showOrder"));
            resList = baseDao.findByDetachedCriteria(det);
            // 查找二级目录
        } else {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            det.add(Restrictions.eq("presource.id", treeGrid.getId()));
            det.addOrder(Order.asc("showOrder"));
            resList = baseDao.findByDetachedCriteria(det);
        }
        for (SysResource confSysMenu : resList) {
            TreeGrid listTreeGrid = new TreeGrid();
            // 设置主键
            listTreeGrid.setId(confSysMenu.getSysResourceId());
            // 设置名称
            listTreeGrid.setText(confSysMenu.getResName());
            // 设置父节点ID
            if (confSysMenu.getPresource() == null || StringUtils.isEmpty(confSysMenu.getPresource().getSysResourceId())) {
                listTreeGrid.setParentId(StringUtils.EMPTY);
            } else {
                listTreeGrid.setParentId(confSysMenu.getPresource().getSysResourceId());
            }
            // 设置类型
            listTreeGrid.setType(confSysMenu.getResType());
            // 设置访问路径
            if (StringUtils.isEmpty(confSysMenu.getResUrl())) {
                listTreeGrid.setSrc(StringUtils.EMPTY);
            } else {
                listTreeGrid.setSrc(confSysMenu.getResUrl());
            }
            // 设置顺序
            listTreeGrid.setOrder(confSysMenu.getShowOrder().toString());
            // 设置展开状态
            // 查询是否有子菜单
            List<SysResource> childrenList = baseDao.findListByProperty(SysResource.class, "presource.id", confSysMenu.getSysResourceId());
            // 有子点
            if (childrenList != null && !childrenList.isEmpty()) {
                listTreeGrid.setState("closed");
                // 无子节点
            } else {
                listTreeGrid.setState("open");
            }
            treeList.add(listTreeGrid);
        }
        return treeList;
        
    }
    
    /**
     * 保存
     */
    
    public String saveRes(SysResource res) {
    
        if (!StringUtils.isEmpty(res.getPresource().getSysResourceId())) {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            SysResource r = baseDao.findById(SysResource.class, res.getPresource().getSysResourceId());
            det.add(Restrictions.eq("presource.sysResourceId", res.getPresource().getSysResourceId()));
            det.add(Restrictions.eq("resName", res.getResName()));
            List<SysResource> res_list = baseDao.findByDetachedCriteria(det);
            if (res_list == null || res_list.size() == 0){
                res.setPresource(r);
            }else{
                return "名称已存在";
            }
        } else {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            det.add(Restrictions.isNull("presource.sysResourceId"));
            det.add(Restrictions.eq("resType", res.getResType()));
            det.add(Restrictions.eq("resName", res.getResName()));
            List<SysResource> res_list = baseDao.findByDetachedCriteria(det);
            if (res_list != null && !res_list.isEmpty())
                return "名称已存在";
            res.setPresource(null);
        }
        baseDao.save(res);
        return "操作成功";
    }
    
    /**
     * 修改
     */
    public String update(SysResource res) {
    
        SysResource dbRes = baseDao.findById(SysResource.class, res.getSysResourceId());
        if (!StringUtils.isEmpty(res.getPresource().getSysResourceId())) {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            //获取父节点
            SysResource r = baseDao.findById(SysResource.class, res.getPresource().getSysResourceId());
            det.add(Restrictions.eq("presource.sysResourceId", res.getPresource().getSysResourceId()));
            det.add(Restrictions.eq("resName", res.getResName()));
            
            List<SysResource> res_list = baseDao.findByDetachedCriteria(det);
            //在更新编辑器中，不修改名字，此时查出来肯定有重名，必须判断重名的不是自己才不允许保存
            if (res_list != null && res_list.size() != 0 && !res_list.get(0).getSysResourceId().equals(res.getSysResourceId()))
                return "名称已存在";
            //将父节点添加给自己
            res.setPresource(r);
            
        //传递过来的对象没有id
        } else {
            DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
            det.add(Restrictions.isNull("presource.sysResourceId"));
            det.add(Restrictions.eq("resType", res.getResType()));
            det.add(Restrictions.eq("resName", res.getResName()));
            List<SysResource> res_list = baseDao.findByDetachedCriteria(det);
            //在更新编辑器中，不修改名字，此时查出来肯定有重名，必须判断重名的不是自己才不允许保存
            if (res_list != null && res_list.size() != 0 && !res_list.get(0).getSysResourceId().equals(res.getSysResourceId()))
                return "名称已存在";
            res.setPresource(null);
        }
        dbRes.setLeve(res.getLeve());
        //如果是一级菜单，那么就直接这个菜单的父类设置为空 表示为1级目录
        if(res.getLeve().equals("1")){
        	dbRes.setPresource(null);
        }else if(res.getLeve().equals("2")){
        	dbRes.setPresource(res.getPresource());
        }else{
        	//表示不是一级也不是二级的菜单
		}
        dbRes.setResName(res.getResName());
        dbRes.setResType(res.getResType());
        dbRes.setResUrl(res.getResUrl());
        dbRes.setShowOrder(res.getShowOrder());
        baseDao.saveOrUpdate(dbRes);
        return "操作成功";
    }
    
    public String delete(String resId) {
    
        List<SysResource> list = baseDao.findListByProperty(SysResource.class, "presource.sysResourceId", resId);
        List<RoleResource> rr_list = baseDao.findListByProperty(RoleResource.class, "res.sysResourceId", resId);
        if (rr_list == null || rr_list.size() == 0) {
            if (list == null || list.size() == 0) {
                baseDao.delete(baseDao.findById(SysResource.class, resId));
                return "删除成功";
            } else {
                return "含有子菜单禁止删除";
            }
        } else {
            return "菜单已分配禁止删除";
        }
        
    }
    
    public SysResource findById(String id) {
    
        return baseDao.findById(SysResource.class, id);
    }
    
    public List<SysResource> findByPid(String pid) {
    
        DetachedCriteria det = DetachedCriteria.forClass(SysResource.class);
        det.add(Restrictions.eq("presource.id", pid));
        det.addOrder(Order.asc("showOrder"));
        return baseDao.findByDetachedCriteria(det);
    }
    
    public List<SysResource> getFirst() {
    
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysResource.class);
        detachedCriteria.add(Restrictions.eq("leve", "1"));
        detachedCriteria.addOrder(Order.asc("showOrder"));
        return baseDao.findByDetachedCriteria(detachedCriteria);
    }
    
    /**
     * 注入总 BASEDAO
     */
    @Resource
    private BaseDAOImpl baseDao;
    
}
