package com.yxlg.base.util;

import java.util.List;
import java.util.Map;

public class ComboTree {
	
	private String id;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Map<String, Object> attributes;// 其他参数
	private List<ComboTree> children;// 子节点
	private String state = "open";// 是否展开(open,closed)
	private String parentId; // 父id
	
	public String getParentId() {
		
		return parentId;
	}
	
	public void setParentId(String parentId) {
		
		this.parentId = parentId;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getText() {
		
		return text;
	}
	
	public void setText(String text) {
		
		this.text = text;
	}
	
	public String getIconCls() {
		
		return iconCls;
	}
	
	public void setIconCls(String iconCls) {
		
		this.iconCls = iconCls;
	}
	
	public Boolean getChecked() {
		
		return checked;
	}
	
	public void setChecked(Boolean checked) {
		
		this.checked = checked;
	}
	
	public Map<String, Object> getAttributes() {
		
		return attributes;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		
		this.attributes = attributes;
	}
	
	public List<ComboTree> getChildren() {
		
		return children;
	}
	
	public void setChildren(List<ComboTree> children) {
		
		this.children = children;
	}
	
	public String getState() {
		
		return state;
	}
	
	public void setState(String state) {
		
		this.state = state;
	}
}