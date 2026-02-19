package com.edupack.edu.service.mapper;

import java.util.List;
import java.util.Map;

import com.edupack.edu.vo.NexaEduVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface NexaEduMapper {

	List<Map<String, Object>> getEmpList();

	List<Map<String, Object>> getEmpListParam1(String sDeptCd);

	List<Map<String, Object>> getEmpListParam2(NexaEduVO searchVO);

	List<Map<String, Object>> getEmpListParamDs(Map<String, String> searchMap);

	List<Map<String, Object>> getDeptList();

	List<Map<String, Object>> getPosList();

	int checkId(String empId);

	void insertEmp(Map<String, Object> rowData);

	void updateEmp(Map<String, Object> rowData);

	void deleteEmp(Map<String, Object> rowData);

	List<Map<String, Object>> getUser();

	void insertUser(Map<String, Object> rowData);

	void updateUser(Map<String, Object> rowData);

	void deleteUser(Map<String, Object> rowData);

	void insertMember(Map<String, Object> rowData);

	List<Map<String, Object>> selectMember();

	void insertMem(Map<String, Object> rowData);

	void updateMem(Map<String, Object> rowData);

	void deleteMem(Map<String, Object> rowData);

	List<Map<String, Object>> ArkGetUserRole(String sUserRole);

	Map<String, Object> getUserByCredentials(String userId, String userPw);

	List<Map<String, Object>> arkLogin(NexaEduVO loginVO);

	void insertStore(Map<String, Object> rowData);

	void insertMenu(Map<String, Object> rowData2);
	
	void insertTag(Map<String, Object> rowData3);

	Object selectLastInsertId();

	List<Map<String, Object>> getStoreList();

	List<Map<String, Object>> getStoreMenuList();

	List<Map<String, Object>> getStoreHome(String fvHomeAddr);

	List<Map<String, Object>> getStoreSearch(String pSearch);

	List<Map<String, Object>> arkFindID(NexaEduVO findIDVO);

	List<Map<String, Object>> arkFindPW(NexaEduVO findPWVO);

	List<Map<String, Object>> arkCheckStore(NexaEduVO findIDVO);

	List<Map<String, Object>> getProfile(String userId);

	void updateUserProfile(Map<String, Object> rowData);

	List<Map<String, Object>> getStoreDetail(String sStoreId);

	List<Map<String, Object>> getStoreMenuDetail(String sStoreId);

	List<Map<String, Object>> arkGetProfileStoreID(String userId);

	List<Map<String, Object>> getStoreReview(String sStoreId);

	void saveReview(Map<String, Object> rowData);

	List<Map<String, Object>> arkGetReserve(String sStoreId);

	void saveReserve(Map<String, Object> rowData);

	void saveComplReserve(Map<String, Object> rowData);

	void insertReservation(Map<String, Object> rowData);

	void updateReservation(Map<String, Object> rowData);

	void deleteReservation(Map<String, Object> rowData);

	List<Map<String, Object>> getCustomerReserveList(String userId);

	List<Map<String, Object>> getCustomerComplReserveList(String userId);

	List<Map<String, Object>> getStoreAdmin();

	void insertAdminStore(Map<String, Object> rowData);

	void updateAdminStore(Map<String, Object> rowData);

	void deletetAdminStore(Map<String, Object> rowData);

	List<Map<String, Object>> getStoreAdmin(Map<String, String> saveList);

	List<Map<String, Object>> getPaging(Map<String, String> saveList);

	List<Map<String, Object>> highRatingStore();

	void saveNotice(Map<String, Object> dsNotice);

	List<Map<String, Object>> getNotice();

}