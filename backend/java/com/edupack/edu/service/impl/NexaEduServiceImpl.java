package com.edupack.edu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edupack.edu.service.NexaEduService;
import com.edupack.edu.service.mapper.NexaEduMapper;
import com.edupack.edu.vo.NexaEduVO;
import com.nexacro.java.xapi.data.DataSet;

@Service
public class NexaEduServiceImpl implements NexaEduService {
	
	@Resource
	NexaEduMapper nexaEduMapper;
	
	@Override
	public List<Map<String, Object>> getEmpList() {
		return nexaEduMapper.getEmpList();
	}

	@Override
	public List<Map<String, Object>> getEmpListParam1(String sDeptCd) {
		// TODO Auto-generated method stub
		return nexaEduMapper.getEmpListParam1(sDeptCd);
	}

	@Override
	public List<Map<String, Object>> getEmpListParam2(NexaEduVO searchVO) {
		// TODO Auto-generated method stub
		return nexaEduMapper.getEmpListParam2(searchVO);
	}

	@Override
	public List<Map<String, Object>> getEmpListParamDs(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return nexaEduMapper.getEmpListParamDs(searchMap);
	}

	@Override
	public List<Map<String, Object>> getDeptList() {
		// TODO Auto-generated method stub
		return nexaEduMapper.getDeptList();
	}

	@Override
	public List<Map<String, Object>> getPosList() {
		// TODO Auto-generated method stub
		return nexaEduMapper.getPosList();
	}

	@Override
	public int checkId(String empId) {
		// TODO Auto-generated method stub
		return nexaEduMapper.checkId(empId);
	}

	@Override
	public void saveEmp(List<Map<String, Object>> saveList) {
		int nSize = saveList.size();
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData = saveList.get(i);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
				// insert sql
				nexaEduMapper.insertEmp(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				Map orgData = (Map) rowData.get("DataSetSavedData");
				String orgEmpId = (String) orgData.get("EMPL_ID");
				rowData.put("ORG_EMPL_ID", orgEmpId);
				// update sql
				nexaEduMapper.updateEmp(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
				// delete sql
				nexaEduMapper.deleteEmp(rowData);
			}	
		}		
	}
	
	@Override
	public List<Map<String, Object>> getUser() {
		return nexaEduMapper.getUser();
	}

	@Override
	public void saveUser(List<Map<String, Object>> saveList) {
		int nSize = saveList.size();
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData = saveList.get(i);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
				// insert sql
				nexaEduMapper.insertUser(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				Map orgData = (Map) rowData.get("DataSetSavedData");
				String orgUserId = (String) orgData.get("USER_ID");
				rowData.put("ORG_USER_ID", orgUserId);
				// update sql
				nexaEduMapper.updateUser(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
				// delete sql
				nexaEduMapper.deleteUser(rowData);
			}
		}

	}	
	
	@Override
	public void saveMember(List<Map<String, Object>> saveList) {
		
		Map<String, Object> rowData = saveList.get(0);			
		
		// insert sql
		nexaEduMapper.insertMember(rowData);
	}

	@Override
	public List<Map<String, Object>> selectMember() {
		return nexaEduMapper.selectMember();
	}

	@Override
	public void savingMember(List<Map<String, Object>> saveList) {
		int nSize = saveList.size();
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData = saveList.get(i);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
				// insert sql
				nexaEduMapper.insertMem(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				Map orgData = (Map) rowData.get("DataSetSavedData");
				String orgUserId = (String) orgData.get("USER_ID");
				rowData.put("ORG_USER_ID", orgUserId);
				// update sql
				nexaEduMapper.updateMem(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
				// delete sql
				nexaEduMapper.deleteMem(rowData);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> ArkGetUserRole(String sUserRole) {
		return nexaEduMapper.ArkGetUserRole(sUserRole);
	}

	@Override
	public List<Map<String, Object>> arkLogin(NexaEduVO loginVO) {
		// TODO Auto-generated method stub
		return nexaEduMapper.arkLogin(loginVO);
	}

	@Override
	public void saveStore(List<Map<String, Object>> saveList, List<Map<String, Object>> saveMenu,List<Map<String, Object>> saveTag) {
		int nSize =saveMenu.size();
		int nSize2 = saveTag.size();
		Map<String, Object> rowData = saveList.get(0);	
		
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData2 = saveMenu.get(i);
			nexaEduMapper.insertMenu(rowData2);
		}
		for(int i=0; i<nSize2; i++) {
			Map<String, Object> rowData3 = saveTag.get(i);
			nexaEduMapper.insertTag(rowData3);
		}
		nexaEduMapper.insertStore(rowData);
		return;
	}

	@Override
	public List<Map<String, Object>> getStore() {
		return nexaEduMapper.getStoreList();
	}

	@Override
	public List<Map<String, Object>> getStoreMenu() {
		return nexaEduMapper.getStoreMenuList();
	}

	@Override
	public List<Map<String, Object>> getStoreHome(String fvHomeAddr) {
		return nexaEduMapper.getStoreHome(fvHomeAddr);
	}

	@Override
	public List<Map<String, Object>> getStoreSearch(String pSearch) {
		return nexaEduMapper.getStoreSearch(pSearch);
	}

	@Override
	public List<Map<String, Object>> arkFindID(NexaEduVO findIDVO) {
		// TODO Auto-generated method stub
		return nexaEduMapper.arkFindID(findIDVO);
	}

	@Override
	public List<Map<String, Object>> arkFindPW(NexaEduVO findPWVO) {
		return nexaEduMapper.arkFindPW(findPWVO);
	}

	@Override
	public List<Map<String, Object>> arkCheckStore(NexaEduVO findIDVO) {
		return nexaEduMapper.arkCheckStore(findIDVO);
	}

	@Override
	public List<Map<String, Object>> getProfile(String userId) {
		return nexaEduMapper.getProfile(userId);
	}

	@Override
	public void UpdateProfile(List<Map<String, Object>> updateUserList) {
			Map<String, Object> rowData = updateUserList.get(0);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				Map orgData = (Map) rowData.get("DataSetSavedData");
				String orgUserId = (String) orgData.get("USER_ID");
				rowData.put("ORG_USER_ID", orgUserId);
				nexaEduMapper.updateUserProfile(rowData);
			}
	}

	@Override
	public List<Map<String, Object>> getStoreDetail(String sStoreId) {
		return nexaEduMapper.getStoreDetail(sStoreId);
	}

	@Override
	public List<Map<String, Object>> getStoreMenuDetail(String sStoreId) {
		return nexaEduMapper.getStoreMenuDetail(sStoreId);
	}

	@Override
	public List<Map<String, Object>> arkGetProfileStoreID(String userId) {
		return nexaEduMapper.arkGetProfileStoreID(userId);
	}

	@Override
	public List<Map<String, Object>> getStoreReview(String sStoreId) {
		return nexaEduMapper.getStoreReview(sStoreId);
	}

	@Override
	public void saveReview(List<Map<String, Object>> dsReview) {
		Map<String, Object> rowData = dsReview.get(0);			
		
		nexaEduMapper.saveReview(rowData);
	}

	@Override
	public void saveReserve(List<Map<String, Object>> dsReserve) {
		Map<String, Object> rowData = dsReserve.get(0);			
		// insert sql
		nexaEduMapper.saveReserve(rowData);
	}

	@Override
	public List<Map<String, Object>> arkGetReserve(String sStoreId) {
		return nexaEduMapper.arkGetReserve(sStoreId);
	}

	@Override
	public void saveComplReserve(List<Map<String, Object>> dsComplReserve) {
		Map<String, Object> rowData = dsComplReserve.get(0);			
		nexaEduMapper.saveComplReserve(rowData);
	}

	@Override
	public void updateReserve(List<Map<String, Object>> dsReserve) {
		int nSize = dsReserve.size();
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData = dsReserve.get(i);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
				nexaEduMapper.insertReservation(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				nexaEduMapper.updateReservation(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
				nexaEduMapper.deleteReservation(rowData);
			}	
		}
	}

	@Override
	public List<Map<String, Object>> getCustomerReserveList(String userId) {
		return nexaEduMapper.getCustomerReserveList(userId);
	}

	@Override
	public List<Map<String, Object>> getCustomerComplReserveList(String userId) {
		return nexaEduMapper.getCustomerComplReserveList(userId);
	}


	@Override
	public void saveAdminStore(List<Map<String, Object>> saveList) {
		int nSize = saveList.size();
		for(int i=0; i<nSize; i++) {
			Map<String, Object> rowData = saveList.get(i);
			
			if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_INSERTED){
				nexaEduMapper.insertAdminStore(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_UPDATED){
				nexaEduMapper.updateAdminStore(rowData);
			}
			else if((int) rowData.get("DataSetRowType") == DataSet.ROW_TYPE_DELETED){
				nexaEduMapper.deletetAdminStore(rowData);
			}	
		}
		
	}


	@Override
	public List<Map<String, Object>> getStoreAdmin(Map<String, String> saveList) {
		return nexaEduMapper.getStoreAdmin(saveList);
	}

	@Override
	public List<Map<String, Object>> getPaging(Map<String, String> saveList) {
		return nexaEduMapper.getPaging(saveList);
	}

	@Override
	public List<Map<String, Object>> highRatingStore() {
		return nexaEduMapper.highRatingStore();
	}

	@Override
	public void saveNotice(Map<String, Object> dsNotice) {
		nexaEduMapper.saveNotice(dsNotice);
	}

	@Override
	public List<Map<String, Object>> getNotice() {
		return nexaEduMapper.getNotice();
	}

}