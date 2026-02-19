package com.edupack.edu.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.uiadapter.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;
import com.edupack.edu.service.EduTransactionService;
import com.edupack.edu.vo.EduTransactionVO;

/**
 *
 * @File Name   EduTransactionController.java
 * @Description 트랜잭션 샘플 컨트롤러 Map, VO
 * @Package     com.edupack.edu.web
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
@Controller
public class EduTransactionController {
	
	private Logger log = LoggerFactory.getLogger(EduTransactionController.class);
	
    @Resource(name = "eduTransactionService")
    
    private EduTransactionService eduTransactionService;
    
    /**
     * 
     * <pre>
     * @desc 데이터 조회
     * 
     * @param  
     * @return NexacroResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "transactionSelect.do")
    public NexacroResult transactionSelect(@ParamDataSet(name="dsSearch", required=false) Map<String,String> searchMap, @ParamVariable(name="NAME") String NAME) {
    	log.debug(" >>> client param searchMap : " + searchMap);
    	log.debug(" >>> client param name : " + NAME);
    	
    	searchMap.put("NAME", NAME);
    	
    	List<Map<String,Object>> resultList = eduTransactionService.selectTransaction(searchMap) ;
    	
		NexacroResult result = new NexacroResult();
		result.addDataSet("dsList",resultList);
		return result;
	}

    @RequestMapping(value = "selectDeptList.do")
    public NexacroResult selectDeptList() {
    	log.debug(" >>> selectDeptList ");
    	    	
    	List<Map<String,Object>> resultList = eduTransactionService.selectDeptList() ;
    	
		NexacroResult result = new NexacroResult();
		result.addDataSet("dsList",resultList);
		return result;
	}
    
    
    /**
     * 
     * <pre>
     * @desc 데이터 입력/수정/삭제
     * 
     * @param  
     * @return NexacroResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "transactionSave.do")
    public NexacroResult transactionSave(@ParamDataSet(name="dsList", required=false) List<Map<String,Object>> saveList) {

    	NexacroResult result = new NexacroResult();
    	eduTransactionService.updateData(saveList);	
		return result;
	}
    
    /**
     * 
     * <pre>
     * @desc 데이터 조회
     * 
     * @param  
     * @return NexacroResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "selectPagingSample.do")
    public NexacroResult selectPagingSample(@ParamDataSet(name="dsSearch", required=false) Map<String,String> searchMap) {
    	List<Map<String,Object>> resultList  = eduTransactionService.selectPagingSample(searchMap) ;
    	List<Map<String,Object>> resultList2 = eduTransactionService.selectPagingSampleTotalcnt(searchMap) ;
    	
		NexacroResult result = new NexacroResult();
		
		result.addDataSet("dsList"      ,resultList);
		result.addDataSet("dsPagingInfo",resultList2);
		
	
		return result;
	}    
 
    
    /**
     * <pre>
     * @desc VO를 이용한 트랜잭션 샘플
     * </pre>
     *
     * @param searchVo
     * @return
     */
//    public NexacroResult selectVo(@ParamDataSet(name = "ds_search", required = false) SampleVO searchVo) {
    
    @RequestMapping(value = "/transactionSelectVoList.do")
    public NexacroResult transactionSelectVoList(@ParamDataSet(name = "ds_search", required = false) EduTransactionVO searchVo) {
    	
    	List<EduTransactionVO> sampleList = eduTransactionService.transactionSelectVoList(searchVo);

		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);

		return result;
	}
    
    
    
}
