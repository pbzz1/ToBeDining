package com.edupack.edu.service;

import java.util.List;
import java.util.Map;

import com.edupack.edu.vo.NexaEduVO;

public interface NexaEduService {

	List<Map<String, Object>> getEmpList();

	List<Map<String, Object>> getEmpListParam1(String sDeptCd);

	List<Map<String, Object>> getEmpListParam2(NexaEduVO searchVO);

	List<Map<String, Object>> getEmpListParamDs(Map<String, String> searchMap);

	List<Map<String, Object>> getDeptList();

	List<Map<String, Object>> getPosList();

	int checkId(String empId);

	void saveEmp(List<Map<String, Object>> saveList);

	List<Map<String, Object>> getUser();

	void saveUser(List<Map<String, Object>> saveList);

	void saveMember(List<Map<String, Object>> saveList);

	List<Map<String, Object>> selectMember();

	void savingMember(List<Map<String, Object>> saveList);

	List<Map<String, Object>> ArkGetUserRole(String sUserRole);

	List<Map<String, Object>> arkLogin(NexaEduVO loginVO);

	void saveStore(List<Map<String, Object>> saveList, List<Map<String, Object>> saveMenu,List<Map<String, Object>> saveTag);

	List<Map<String, Object>> getStore();

	List<Map<String, Object>> getStoreMenu();

	List<Map<String, Object>> getStoreHome(String fvHomeAddr);

	List<Map<String, Object>> getStoreSearch(String pSearch);

	List<Map<String, Object>> arkFindID(NexaEduVO findIDVO);

	List<Map<String, Object>> arkFindPW(NexaEduVO findPWVO);

	List<Map<String, Object>> arkCheckStore(NexaEduVO findIDVO);

	List<Map<String, Object>> getProfile(String userId);

	void UpdateProfile(List<Map<String, Object>> updateUserList);

	List<Map<String, Object>> getStoreDetail(String sStoreId);

	List<Map<String, Object>> getStoreMenuDetail(String sStoreId);

	List<Map<String, Object>> arkGetProfileStoreID(String userId);

	List<Map<String, Object>> getStoreReview(String sStoreId);

	void saveReview(List<Map<String, Object>> dsReview);

	void saveReserve(List<Map<String, Object>> dsReserve);

	List<Map<String, Object>> arkGetReserve(String sStoreId);

	void saveComplReserve(List<Map<String, Object>> dsComplReserve);

	void updateReserve(List<Map<String, Object>> dsReserve);

	List<Map<String, Object>> getCustomerReserveList(String userId);

	List<Map<String, Object>> getCustomerComplReserveList(String userId);

	void saveAdminStore(List<Map<String, Object>> saveList);

	List<Map<String, Object>> getStoreAdmin(Map<String, String> saveList);

	List<Map<String, Object>> getPaging(Map<String, String> saveList);

	List<Map<String, Object>> highRatingStore();

	void saveNotice(Map<String, Object> dsNotice);

	List<Map<String, Object>> getNotice();
	

}