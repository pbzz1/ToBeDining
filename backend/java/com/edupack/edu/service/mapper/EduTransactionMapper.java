package com.edupack.edu.service.mapper;

import java.util.List;
import java.util.Map;

import com.edupack.edu.vo.EduTransactionVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * 
 * <pre>
 * @title   Education Mapper Interface
 *  -       
 * @package com.edupack.edu.service.impl.mybatis
 * <pre>
 * <pre>
 * @author  Education
 * @since   2022. 04. 20.
 * @version 1.0
 * @see
 *
 * =================== 변경 내역 ==================
 * 날짜			변경자		내용
 * ------------------------------------------------
 * 2022. 04. 20.		Education	최초작성
 */

@Mapper("eduTransactionMapper")
public interface EduTransactionMapper {
	
	public List<Map<String,Object>> selectPagingSample(Map<String,String> searchMap);
	public List<Map<String,Object>> selectPagingSampleTotalcnt(Map<String,String> searchMap);
	
	public List<Map<String,Object>> selectTransaction(Map<String,String> searchMap);

	public List<Map<String,Object>> selectDeptList();
	
	public int updateSampleTransacation(Map<String,Object> saveList);;
	public int insertSampleTransacation(Map<String,Object> saveList);
	public int deleteSampleTransacation(Map<String,Object> saveList);
	
	// VO를 이용한 통신
	public List<EduTransactionVO> transactionSelectVoList(EduTransactionVO searchVo);		
	
}
