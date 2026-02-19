package com.edupack.edu.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edupack.edu.service.NexaEduService;
import com.edupack.edu.vo.NexaEduVO;
import com.nexacro.uiadapter.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;

@Controller
public class NexaEduController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private NexaEduService nexaEduService; 
	
	@RequestMapping(value = "edu/getEmpList.do")
	public NexacroResult getEmpList() {
		
		log.debug("#########################################");
		log.debug("여기까지 도착?????");
		log.debug("#########################################");
		
		List<Map<String, Object>> resultData = nexaEduService.getEmpList();
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("out_emp", resultData);
		return result;
	}

	@RequestMapping(value = "edu/getEmpListParam1.do")
	public NexacroResult getEmpListParam1(@ParamVariable(name="pDept") String sDeptCd) {
		
		List<Map<String, Object>> resulData = nexaEduService.getEmpListParam1(sDeptCd);
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("out_emp", resulData);
		return result;
	}
	
	@RequestMapping(value = "edu/getEmpListParam2.do")
	public NexacroResult getEmpListParam2(@ParamVariable(name="pDept") String sDeptCd,
			                              @ParamVariable(name="pName") String sName) {
		
		NexaEduVO searchVO = new NexaEduVO();
		searchVO.setDeptCd(sDeptCd);
		searchVO.setFullName(sName);
		
//		Map<String, String> searchMap = new HashedMap();
//		searchMap.put("dpet_cd", sDeptCd)
//		searchMap.put("full_name", sName)
		
		List<Map<String, Object>> resulData = nexaEduService.getEmpListParam2(searchVO);
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("out_emp", resulData);
		return result;
	}
	
	@RequestMapping(value = "edu/getEmpListParamDs.do")
	public NexacroResult getEmpListParamDs(@ParamDataSet(name="paramDs") Map<String,String> searchMap) {
		
		List<Map<String, Object>> resulData = nexaEduService.getEmpListParamDs(searchMap);
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("out_emp", resulData);
		return result;
	}

	
	@RequestMapping(value = "edu/getCodeList.do")
	public NexacroResult getCodeList() {
		
		List<Map<String, Object>> resulDept = nexaEduService.getDeptList();
		List<Map<String, Object>> resulPos  = nexaEduService.getPosList();

		NexacroResult result = new NexacroResult();
		result.addDataSet("out_dept", resulDept);
		result.addDataSet("out_pos", resulPos);
		return result;
	}
	
	
	@RequestMapping(value = "edu/checkId.do")
	public NexacroResult checkId(@ParamVariable(name="paramId") String empId) {
		
		int nCnt = nexaEduService.checkId(empId);

		NexacroResult result = new NexacroResult();
		result.addVariable("id_cnt", nCnt);
		return result;
	}
	
	@RequestMapping(value = "edu/saveEmp.do")
	public NexacroResult saveEmp(@ParamDataSet(name="in_emp") List<Map<String,Object>> saveList) {
		
		nexaEduService.saveEmp(saveList);

		NexacroResult result = new NexacroResult();
		return result;
	}
	
	
	@RequestMapping(value = "edu/getUser.do")
	public NexacroResult getUser() {
		
		List<Map<String, Object>> resultData = nexaEduService.getUser();
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("ds00", resultData);
		return result;
	}
	
	@RequestMapping(value = "edu/saveUser.do")
	public NexacroResult saveUser(@ParamDataSet(name="ds00") List<Map<String,Object>> saveList) {
		
		nexaEduService.saveUser(saveList);

		NexacroResult result = new NexacroResult();
		return result;
	}
	
	
	@RequestMapping(value = "edu/saveMember.do")
	public NexacroResult saveMember(@ParamDataSet(name="dsMember") List<Map<String,Object>> saveList) {
		
		nexaEduService.saveMember(saveList);

		NexacroResult result = new NexacroResult();
		return result;
	}
	
	@RequestMapping(value = "edu/selectMember.do")
	public NexacroResult selectMember() {
		log.debug("여기까지 도착?????");
		
		List<Map<String, Object>> resultData = nexaEduService.selectMember();
		
		NexacroResult result = new NexacroResult();
		result.addDataSet("dsMember", resultData);
		return result;
	}
	
	@RequestMapping(value = "edu/savingMember.do")
	public NexacroResult savingMember(@ParamDataSet(name="dsMember") List<Map<String,Object>> saveList) {		
		nexaEduService.savingMember(saveList);

		NexacroResult result = new NexacroResult();
		return result;
	}
	
	  @RequestMapping(value = "ark/Login.do")
	    public NexacroResult arkLogin(@ParamVariable(name = "USER_ID") String userId,
	                                  @ParamVariable(name = "USER_PW") String userPw) {
		  	
		  	NexaEduVO loginVO = new NexaEduVO();
		  	loginVO.setUserId(userId);
		    loginVO.setUserPw(userPw);
		  	
			List<Map<String, Object>> resulData = nexaEduService.arkLogin(loginVO);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_login", resulData);
	        return result;
	    }
	  
		@RequestMapping(value = "ark/saveStore.do")
		public NexacroResult saveStore(@ParamDataSet(name="dsDStore") List<Map<String,Object>> saveList,
								@ParamDataSet(name="dsDMenu") List<Map<String,Object>> saveMenu,
								@ParamDataSet(name="dsTag") List<Map<String,Object>> saveTag) {
			nexaEduService.saveStore(saveList, saveMenu,saveTag);
		   
			List<Map<String, Object>> resultStore = nexaEduService.getStore();
			List<Map<String, Object>> resultStoreMenu = nexaEduService.getStoreMenu();
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsDStore", resultStore);
			result.addDataSet("dsDMenu", resultStoreMenu);
			return result;
		}
		
		@RequestMapping(value = "ark/getStore2.do")
		public NexacroResult getStoreList() {
			List<Map<String, Object>> resultData = nexaEduService.getStore();
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_store", resultData);
			return result;
		}
		@RequestMapping(value = "ark/GetStore.do")
		public NexacroResult getStoreHome(@ParamVariable(name="STORE_ADDR_MAIN") String fvHomeAddr) {
			log.debug("????????????????????????????????????????????????");
			List<Map<String, Object>> resulData = nexaEduService.getStoreHome(fvHomeAddr);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_filteredAddr", resulData);
			return result;
		}
		@RequestMapping(value = "ark/getStoreSearch.do")
		public NexacroResult getStoreSearch(@ParamVariable(name="pSearchKey") String pSearch) {
			
			List<Map<String, Object>> resulData = nexaEduService.getStoreSearch(pSearch);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsStore", resulData);
			return result;
		}
		
		@RequestMapping(value = "ark/findID.do")
		   public NexacroResult arkFindID(@ParamVariable(name = "USER_NAME") String sName,
	                                  @ParamVariable(name = "USER_PHONE") String sPhone) {
		  	
		  	NexaEduVO findIDVO = new NexaEduVO();
		  	findIDVO.setsName(sName);
		  	findIDVO.setsPhone(sPhone);
		  	
			List<Map<String, Object>> resulData = nexaEduService.arkFindID(findIDVO);
				
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_result", resulData);
	        return result;
	    }
		
		@RequestMapping(value = "ark/findPW.do")
		   public NexacroResult arkFindPW(@ParamVariable(name = "USER_NAME") String sName,
	                                  	@ParamVariable(name = "USER_PHONE") String sPhone,
	                                  @ParamVariable(name = "USER_ID") String sId) {
		  	
		  	NexaEduVO findPWVO = new NexaEduVO();
		  	findPWVO.setsName(sName);
		  	findPWVO.setsPhone(sPhone);
		  	findPWVO.setsId(sId);
		  	
			List<Map<String, Object>> resulData = nexaEduService.arkFindPW(findPWVO);
				
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_result", resulData);
	        return result;
	    }
		
		@RequestMapping(value = "ark/checkStore.do")
		   public NexacroResult checkStore(@ParamVariable(name = "STORE_NAME") String sName,
	                                  @ParamVariable(name = "STORE_PHONE") String sPhone) {
		  	
		  	NexaEduVO findIDVO = new NexaEduVO();
		  	findIDVO.setsName(sName);
		  	findIDVO.setsPhone(sPhone);
		  	
			List<Map<String, Object>> resulData = nexaEduService.arkCheckStore(findIDVO);
				
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_result", resulData);
	        return result;
	    }
		
		@RequestMapping(value = "ark/GetProfile.do")
		public NexacroResult arkGetProfile(@ParamVariable(name="USER_ID") String userId) {
			
			List<Map<String, Object>> resultData = nexaEduService.getProfile(userId);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_login", resultData);
			return result;
		}
		
		@RequestMapping(value = "ark/UpdateProfile.do")
		public NexacroResult UpdateProfile(@ParamDataSet(name="dsDMember") List<Map<String,Object>> updateUserList) {
			
			nexaEduService.UpdateProfile(updateUserList);

			NexacroResult result = new NexacroResult();
			return result;
		}
		
		@RequestMapping(value = "ark/GetStoreDetail.do")
		public NexacroResult GetStoreDetail(@ParamVariable(name="STORE_ID") String sStoreId) {
			
			List<Map<String, Object>> resulStore = nexaEduService.getStoreDetail(sStoreId);
			List<Map<String, Object>> resulMenu  = nexaEduService.getStoreMenuDetail(sStoreId);
			List<Map<String, Object>> resulReview  = nexaEduService.getStoreReview(sStoreId);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_store", resulStore);
			result.addDataSet("out_store_menu", resulMenu);
			result.addDataSet("dsReview", resulReview);
			return result;
		}
		@RequestMapping(value = "ark/GetProfileStoreID.do")
		public NexacroResult arkGetProfileStoreID(@ParamVariable(name="USER_ID") String userId) {
			
			List<Map<String, Object>> resultData = nexaEduService.arkGetProfileStoreID(userId);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("out_data", resultData);
			return result;
		}
		@RequestMapping(value = "ark/saveReview.do")
		public NexacroResult saveReview(@ParamDataSet(name="dsReview") List<Map<String,Object>> dsReview) {
			
			nexaEduService.saveReview(dsReview);

			NexacroResult result = new NexacroResult();
			return result;
		}
		@RequestMapping(value = "ark/SaveReservation.do")
		public NexacroResult saveReserve(@ParamDataSet(name="dsReserve") List<Map<String,Object>> dsReserve) {
			
			nexaEduService.saveReserve(dsReserve);

			NexacroResult result = new NexacroResult();
			return result;
		}
		@RequestMapping(value = "ark/GetReserve.do")
		public NexacroResult arkGetReserve(@ParamVariable(name="STORE_ID") String sStoreId) {
			
			List<Map<String, Object>> resultData = nexaEduService.arkGetReserve(sStoreId);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsReserve", resultData);
			return result;
		}
		@RequestMapping(value = "ark/saveComplReserve.do")
		public NexacroResult saveComplReserve(@ParamDataSet(name="dsComplReserve") List<Map<String,Object>> dsComplReserve,
											@ParamDataSet(name="dsReserve") List<Map<String,Object>> dsReserve) {
			
			nexaEduService.saveComplReserve(dsComplReserve);
			nexaEduService.updateReserve(dsReserve);

			NexacroResult result = new NexacroResult();
			return result;
		}
		@RequestMapping(value = "ark/GetCustomerReserve.do")
		public NexacroResult GetCustomerReserve(@ParamVariable(name="USER_ID") String userId) {
			
			List<Map<String, Object>> resulReserve = nexaEduService.getCustomerReserveList(userId);
			List<Map<String, Object>> resulComplReserve  = nexaEduService.getCustomerComplReserveList(userId);

			NexacroResult result = new NexacroResult();
			result.addDataSet("dsReserve", resulReserve);
			result.addDataSet("dsComplReserve", resulComplReserve);
			return result;
		}
		
		@RequestMapping(value = "ark/getStoreAdmin.do")
		public NexacroResult getStoreAdmin(@ParamDataSet(name="dsSearch00") Map<String,String> saveList) throws IOException{
			log.debug("adf"+saveList.toString());
			
			List<Map<String, Object>> resultData = nexaEduService.getStoreAdmin(saveList);
			List<Map<String, Object>> resulReserve = nexaEduService.getPaging(saveList);
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsStore", resultData);
			result.addDataSet("dsPagingInfo", resulReserve);
			return result;
		}
		
		@RequestMapping(value = "ark/saveAdminStore.do")
		public NexacroResult saveAdminStore(@ParamDataSet(name="dsStore") List<Map<String,Object>> saveList) {
			
			nexaEduService.saveAdminStore(saveList);

			NexacroResult result = new NexacroResult();
			return result;
		}
		
		@RequestMapping(value = "ark/highRatingStore.do")
		public NexacroResult highRatingStore() {
			
			List<Map<String, Object>> resultData = nexaEduService.highRatingStore();
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsStore", resultData);
			return result;
		}
		
		@RequestMapping(value = "ark/saveNotice.do")
		public NexacroResult saveNotice(@ParamDataSet(name="dsNotice") Map<String,Object> dsNotice) {
			
			nexaEduService.saveNotice(dsNotice);

			NexacroResult result = new NexacroResult();
			return result;
		}
		
		@RequestMapping(value = "ark/getNotice.do")
		public NexacroResult getNotice() {
			List<Map<String, Object>> resultData = nexaEduService.getNotice();
			
			NexacroResult result = new NexacroResult();
			result.addDataSet("dsNotice", resultData);
			return result;
		}
}






