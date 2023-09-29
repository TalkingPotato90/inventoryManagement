package com.snf.domain;

public class CodeInfo {
	private int num;
	private int id;
	private String codeGroup;
    private String codeGroupName;
    private String createdDate;
    private String code;
    private String codeName;
    private String parentGroup;
    private String parentCode;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeGroup() {
		return codeGroup;
	}
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}
	public String getCodeGroupName() {
		return codeGroupName;
	}
	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	@Override
	public String toString() {
		return "CodeInfo [num=" + num + ", id=" + id + ", codeGroup=" + codeGroup + ", codeGroupName=" + codeGroupName
				+ ", createdDate=" + createdDate + ", code=" + code + ", codeName=" + codeName + ", parentGroup="
				+ parentGroup + ", parentCode=" + parentCode + "]";
	}
    
    
}
