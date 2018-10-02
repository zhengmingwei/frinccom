package com.cfcp.incc.entity;

import java.util.*;

public class Dictionary {
	private Integer id;
	private String type;
	private String value;
	private Integer weight;
	private Integer recommend;
	private Integer status;
	private Date createTime;
	private Integer parentId;
	private Dictionary parent;
	private List<Dictionary> children;


	//状态：1=正常；2=删除
	public static final Integer STATUS_NORMAL = 1;
	public static final Integer STATUS_DELETE = 2;
	public static final Map<Integer, String> STATUS_MAP = new LinkedHashMap<Integer, String>();
	static {
		STATUS_MAP.put(STATUS_NORMAL, "正常");
		STATUS_MAP.put(STATUS_DELETE, "删除");
	}

	public static final String TYPE_TAG = "tag";
	public static final String TYPE_TRAVELS = "travels";
	public static final String TYPE_COST_INCLUDES = "costIncludes";


	public Dictionary() {
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Dictionary> getChildren() {
		return children;
	}

	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}

	public Dictionary getParent() {
		return parent;
	}

	public void setParent(Dictionary parent) {
		this.parent = parent;
	}

	public void addChild(Dictionary dictionary){
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
//		dictionary.setParent(this);
		dictionary.setParentId(this.getId());
		this.children.add(dictionary);
	}
}