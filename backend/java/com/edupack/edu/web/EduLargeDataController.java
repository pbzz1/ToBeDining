package com.edupack.edu.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroFirstRowHandler;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;
import com.edupack.edu.service.EduLargeDataService;
import com.nexacro.java.xapi.tx.PlatformType;

/**
 * <pre>
 * @title   Controller Sample Class
 * @desc    제공 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        대용량 데이터인 경우 firstRow 방식 데이터 조회
 * @package com.nexacro.sample.web
 * <pre>
 * @author  TOBESOFT
 * @since   2017. 11. 8.
 * @version 1.0
 * @see
 * =================== 변경 내역 ==================
 * 날짜			변경자		내용
 * ------------------------------------------------
 * 2017. 11. 8.		TOBESOFT	최초작성
 */
@Controller
public class EduLargeDataController {
	private static final Logger log = LoggerFactory.getLogger(EduLargeDataController.class);
	
    @Resource(name = "eduLargeDataService")
    private EduLargeDataService eduLargeDataService;
    
    private static int DATA_CNT = 1000;
    
    
    @RequestMapping(value = "/eduLargeData.do")
    public NexacroResult eduLargeData(NexacroFirstRowHandler firstRowHandler, @ParamVariable(name="firstRowCount", required=false) int firstRowCount){
        
        firstRowHandler.setContentType(PlatformType.CONTENT_TYPE_SSV);
        String sendDataSetName = "ds_firstRowData";
        int initDataCount      = DATA_CNT; // this is dummy data!!
        
        eduLargeDataService.selectLargeData(firstRowHandler, sendDataSetName, firstRowCount, initDataCount);
        
        NexacroResult result = new NexacroResult();
        return result;
    }
}
