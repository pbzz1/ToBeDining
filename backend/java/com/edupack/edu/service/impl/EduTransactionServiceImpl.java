package com.edupack.edu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nexacro.java.xapi.data.DataSet;	// N
import com.edupack.edu.service.EduTransactionService;
import com.edupack.edu.service.mapper.EduTransactionMapper;
import com.edupack.edu.vo.EduTransactionVO;
import com.edupack.edu.web.EduTransactionController;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 
 * <pre>
 * @title   Education ServiceImpl Class
 *  -       
 * @package com.edupack.edu.service.impl
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

@Service("eduTransactionService")
public class EduTransactionServiceImpl extends EgovAbstractServiceImpl implements EduTransactionService {

	private Logger log = LoggerFactory.getLogger(EduTransactionController.class);
	
	
	@Resource(name = "eduTransactionMapper")
	EduTransactionMapper eduTransactionMapper;
	
	@Override
	public List<Map<String,Object>> selectPagingSample(Map<String,String> searchMap) {
		return eduTransactionMapper.selectPagingSample(searchMap);
	}

	@Override
	public List<Map<String,Object>> selectPagingSampleTotalcnt(Map<String,String> searchMap) {
		return eduTransactionMapper.selectPagingSampleTotalcnt(searchMap);
	}

	@Override
	public List<Map<String,Object>> selectTransaction(Map<String,String> searchMap) {
		return eduTransactionMapper.selectTransaction(searchMap);
	}
	
	@Override
	public List<Map<String,Object>> selectDeptList() {
		return eduTransactionMapper.selectDeptList();
	}

	@Override
	public List<Map<String,Object>> updateData(List<Map<String,Object>> saveList) {
		int size = saveList.size();
		List<Map<String,Object>> result = null; 
		
		for (int i=0; i<size; i++) {
            Map<String,Object> sample = saveList.get(i);//DataSetRowType
            
            if((int)sample.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
            	eduTransactionMapper.insertSampleTransacation(sample);
            }
            else if((int)sample.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
            	eduTransactionMapper.updateSampleTransacation(sample);
            }
            else if((int)sample.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
            	//eduTransactionMapper.deleteSampleTransacation(sample);
            	
            	log.debug("aaaaaaaaaaaa  " + result);
            	
            }
        }
		return result;
	}

	@Override
	public List<EduTransactionVO> transactionSelectVoList(EduTransactionVO searchVo) {
		return eduTransactionMapper.transactionSelectVoList(searchVo);
	}
	
}
