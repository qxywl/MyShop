package com.zhaidou.product.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * 商品中心临时实体条件
 * 
 * @author caizhan
 * 
 */
public class ProductObjBufCondiction extends BaseModelCondiction {

	private static final long serialVersionUID = -4512718218470199175L;

	private List<Long> ids;
	private Long id;
	private Long objId;
	private Integer objType;
	private List<Integer> objTypes;
	private Integer state;
	private Timestamp resolveTime;
	private Integer resolveState;
	private String resolveMsg;

	// 0 BufId取模。1 ObjId取模。默认0
	private Integer modBy = 0;

	public List<Long> getIds() {
		return this.ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObjId() {
		return this.objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public Integer getObjType() {
		return this.objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public List<Integer> getObjTypes() {
		return objTypes;
	}

	public void setObjTypes(List<Integer> objTypes) {
		this.objTypes = objTypes;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getResolveTime() {
		return this.resolveTime;
	}

	public void setResolveTime(Timestamp resolveTime) {
		this.resolveTime = resolveTime;
	}

	public Integer getResolveState() {
		return this.resolveState;
	}

	public void setResolveState(Integer resolveState) {
		this.resolveState = resolveState;
	}

	public String getResolveMsg() {
		return this.resolveMsg;
	}

	public void setResolveMsg(String resolveMsg) {
		this.resolveMsg = resolveMsg;
	}

	public Integer getModBy() {
		return modBy;
	}

	public void setModBy(Integer modBy) {
		this.modBy = modBy;
	}

}
