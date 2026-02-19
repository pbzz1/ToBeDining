package com.edupack.edu.service;

import java.util.List;
import java.util.Map;

import com.edupack.edu.vo.EduTransactionVO;


/**
 *
 * @file name   EduTransactionService.java
 * @description 트랜잭션 통신 인터페이스
 * @package     com.edupack.edu.service
 * @project     edupack_egov
 * 
 * @author      TOBESOFT Education
 * @date        2023.01.06
 * @version     1.0
 * @history
 * <pre>
 * ========================================================
 *    @ate          author         		memo
 * ---------------------------------------------------------
 * 2023.01.06	TOBESOFT Education	최초작성
 *
 * </pre>
 */
public interface EduTransactionService {

	List<Map<String,Object>> selectTransaction(Map<String,String> searchMap);

	List<Map<String,Object>> selectDeptList();
	
	
	List<Map<String,Object>> updateData(List<Map<String,Object>> saveList);

	List<Map<String,Object>> selectPagingSample(Map<String,String> searchMap);
	List<Map<String,Object>> selectPagingSampleTotalcnt(Map<String,String> searchMap);

	// VO를 이용한 데이터 조회
	List<EduTransactionVO> transactionSelectVoList(EduTransactionVO searchVo);
	
}
