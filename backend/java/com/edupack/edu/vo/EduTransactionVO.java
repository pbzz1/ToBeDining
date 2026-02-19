package com.edupack.edu.vo;

/**
 *
 * @File Name   EduTransactionVO.java
 * @Description VO를 이용한 트랜잭선 통신 샘플 VO
 * @Package     com.edupack.edu.vo
 * @Project     edupack_egov
 * 
 * @Author      TOBESOFT Education
 * @Date        2023.01.06
 * @Version     1.0
 * @History
 * <pre>
 * ========================================================
 *    DATE          AUTHOR         		MENO
 * ---------------------------------------------------------
 * 2023.01.06	TOBESOFT Education	최초작성
 *
 * </pre>
 */

public class EduTransactionVO extends EduDefaultVO {

	/** 아이디 */
	private String id;

	/** 이름 */
	private String name;

	/** 내용 */
	private String description;

	/** 사용여부 */
	private String useYn;

	/** 등록자 */
	private String regUser;

	public EduTransactionVO() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

}
