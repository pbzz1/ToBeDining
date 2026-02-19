package com.edupack.edu.vo;


/**
 * 
 * @author Education
 * @since 20220501
 * @version 1.0
 * @see
 */
public class EduSearchVO extends EduDefaultVO {

	private String searchFullName;
	private String searchDeptCd;
	private String searchValue;
	
	public String getSearchFullName() {
		return searchFullName;
	}
	public void setSearchFullName(String searchFullName) {
		this.searchFullName = searchFullName;
	}
	public String getSearchDeptCd() {
		return searchDeptCd;
	}
	public void setSearchDeptCd(String searchDeptCd) {
		this.searchDeptCd = searchDeptCd;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}