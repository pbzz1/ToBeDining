package com.edupack.edu.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.nexacro.uiadapter.spring.core.NexacroException;
import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.datatype.PlatformDataType;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Random;
import com.nexacro.uiadapter.spring.core.data.NexacroFileResult;
import com.nexacro.uiadapter.spring.core.util.CharsetUtil;
import com.nexacro.java.xapi.tx.PlatformType;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/**
 * 
 * <pre>
 * @title   Education Controller
 *  -       
 * @package com.edupack.edu.web
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

@Controller
public class EduFileController {

	private Logger log = LoggerFactory.getLogger(EduFileController.class);
	
    private static final String SP = File.separator;	//같은 클래스 내부 전체 필드, 메서드에서 공유
//    private static final String PATH = "WEB-INF"+SP+"upload";  //서버 첨부파일 경로
//    private static final String PATH = "upload"+SP+"nexacro";  //서버 첨부파일 경로
    private static final String PATH = "upload";  //서버 첨부파일 경로
    private static String sUserPath = "";   //사용자 폴더경로

    
    @Autowired
    private WebApplicationContext appContext; 
	  
    /*
      	파일 저장 후 저장파일 정보 반환 (화면에서 호출)
     */     
	@RequestMapping(value = "/uploadFile.do" )
	public NexacroResult uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        
		//MultipartHttpServletRequest 체크
		if(!(request instanceof MultipartHttpServletRequest)) {
			if(log.isDebugEnabled()) {
				log.debug("Request is not a MultipartHttpServletRequest");
		    }
			return new NexacroResult();
		}
	
		log.debug("-------------------- nexacro platform uploadFiles ---------------------------");
		
		//반환될 파일저장 정보 Dataset 생성 
		DataSet resultDs = createDataSet4UploadResult();
	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	
		
		//파라미터 처리
		uploadParameters(multipartRequest);
		
		//파일저장 및 파일정보 반환 Dagaset 셋팅처리
		uploadMultipartFiles(multipartRequest, resultDs);
		
		NexacroResult nexacroResult = new NexacroResult();
		nexacroResult.addDataSet(resultDs);
		nexacroResult.setErrorCode(0);
		nexacroResult.setErrorMsg("File Save Success!");
		return nexacroResult;
	}

	
	
	/*
	파라미터 셋팅
	*/
	private void uploadParameters(MultipartHttpServletRequest multipartRequest) throws NexacroException {
		
		// parameter and multipart parameter
		Enumeration<String> parameterNames = multipartRequest.getParameterNames();

		
		while(parameterNames.hasMoreElements()) {

			String parameterName = parameterNames.nextElement();
			if(parameterName == null || parameterName.length() == 0) {
				continue;
			}
			
			String value = multipartRequest.getParameter(parameterName);
			//화면 FileUpTransfer 의 setPostData 로 셋팅한 저장될 파일경로 String을 셋팅한다. ("file")
			if("filepath".equals(parameterName)) {
				if(value != null && !value.equals("")) {
					// "WEB-INF/attachFile/" + "sample"
					sUserPath = SP + value;
				}
				continue;                
			}
		}
	}

	/*
	실제파일 저장 및 저장파일정보 셋팅
	*/
	private void uploadMultipartFiles(MultipartHttpServletRequest multipartRequest, DataSet resultDs) throws IOException {

		String removeFileName = multipartRequest.getParameter("removeFile");
		log.debug("============== removeFileName: " + removeFileName);

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String filePath = getFilePath();
		
		if(removeFileName != null) {
			File removeFile = new File(filePath+SP+removeFileName);
			int row = resultDs.newRow();
			resultDs.set(row, "fileid", removeFileName);
			resultDs.set(row, "filename", removeFileName);						
			resultDs.set(row, "filesize", removeFile.length());			
			removeFile.delete();
		}
		else {
			
			Set<String> keySet = fileMap.keySet();		
			for(String name: keySet) {
	
				MultipartFile multipartFile = fileMap.get(name);
								
				String originalFilename = multipartFile.getOriginalFilename();
				
				// IE에서 파일업로드 시 DataSet 파라매터의 Content-Type이 설정되지 않아 여기로 옴. 무시.
				if(originalFilename == null || originalFilename.length() == 0) {
					continue;
				}
	
				File destination = new File(filePath);
	
				if( destination.exists() == false) {
					boolean mkdirs = destination.mkdirs();
					destination.setWritable(true);
	
					log.debug("-------------- create directory ----------------------" + mkdirs);
				}
	
				
				String newFileName = originalFilename;
				File targetFile = new File(filePath+SP+originalFilename);				
				if(targetFile.exists())
				{					
					Date nowDate = new Date();
					SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					newFileName = simpleDate.format(nowDate) + "_" + originalFilename;
							
					log.debug("있다" + originalFilename + " : " + newFileName);
					targetFile = new File(filePath+SP+newFileName);				
				}
				else {
					log.debug("없다" + originalFilename);
				}
				
				InputStream inputStream = multipartFile.getInputStream();				
				FileCopyUtils.copy(inputStream, new FileOutputStream(targetFile));
	
				int row = resultDs.newRow();
				resultDs.set(row, "fileid", newFileName);
				resultDs.set(row, "filename", originalFilename);
				resultDs.set(row, "filesize", targetFile.length());

				log.debug("uploaded file write success. file="+originalFilename);
			}
		}
	}

	@RequestMapping(value = "/deleteFile.do" )
	public NexacroResult deleteFile(@ParamVariable(name="uploadpath") String uploadpath,
			                        @ParamVariable(name="fileid") String fileid) {
		
		sUserPath = SP+uploadpath;
		String filePath = getFilePath();
				
		log.debug(" file delete path ===========================================");
		log.debug(filePath);
		log.debug("=============================================================");		
		
		DataSet resultDs = createDataSet4UploadResult();
		
		File removeFile = new File(filePath+SP+fileid);
		int row = resultDs.newRow();
		resultDs.set(row, "fileid", fileid);
		resultDs.set(row, "filename", fileid);						
		resultDs.set(row, "filesize", removeFile.length());			
		removeFile.delete();
		
		NexacroResult nexacroResult = new NexacroResult();
		nexacroResult.addDataSet(resultDs);
		nexacroResult.addVariable("removeFileId", fileid);
		nexacroResult.setErrorCode(0);
		nexacroResult.setErrorMsg("File Delete Success!");
	
		return nexacroResult;
		
	}
	
	
	/*
	파일을 저장할 절대경로 반환    
	*/
	private String getFilePath() {
		ServletContext sc = appContext.getServletContext();		
		String realPath = sc.getRealPath("/");
		String uploadPath = realPath + PATH + sUserPath;
		
		log.debug("############################# realPath: " + realPath);
		log.debug("############################# PATH: " + PATH);
		log.debug("############################# sUserPath: " + sUserPath);
		log.debug("############################# uploadPath: " + uploadPath);
		return uploadPath;
	}

	/*
	반환용 파일정보 데이터셋 생성
	*/
	private DataSet createDataSet4UploadResult() {
		DataSet ds = new DataSet("ds_output");
		ds.addColumn("fileid", PlatformDataType.STRING);
		ds.addColumn("filename", PlatformDataType.STRING);
		ds.addColumn("filesize", PlatformDataType.INT);
		return ds;
	}

	@RequestMapping(value = "/selectFiles.do" )
	public NexacroResult selectFiles(@ParamVariable(name="uploadpath") String uploadpath) {
		
		sUserPath = SP+uploadpath;
		String filePath = getFilePath();
				
		log.debug(" file select path ===========================================");
		log.debug(filePath);
		log.debug("=============================================================");		
		
		DataSet resultDs = createDataSet4UploadResult();
		
		int row;
		File rf = new File(filePath); 
		File[] rfiles = rf.listFiles();
		
		if(rfiles != null)
		{
			for(int i=0; i<rfiles.length; i++)
			{
				if(rfiles[i].isFile())
				{
					row = resultDs.newRow();
					resultDs.set(row, "fileid", rfiles[i].getName());
					resultDs.set(row, "filename", rfiles[i].getName());
					resultDs.set(row, "filesize", rfiles[i].length());									
				}
			}
		}
		
		NexacroResult nexacroResult = new NexacroResult();
		nexacroResult.addDataSet(resultDs);
		nexacroResult.setErrorCode(0);
		nexacroResult.setErrorMsg("File Select Success!");
	
		return nexacroResult;
		
	}
	
	/*
	파일 다운로드 - 해당경로의 파일을 NexacroFileResult 에 담아 반환 (화면에서 호출)
	*/   
	@RequestMapping(value = "/downloadFile.do")
	public NexacroFileResult downloadFile(HttpServletRequest request) throws Exception {
	
		log.debug("-------------------- nexacro platform downloadFile ---------------------------");
		
		String characterEncoding = request.getCharacterEncoding();
		if(characterEncoding == null) {
			characterEncoding = PlatformType.DEFAULT_CHAR_SET;
		}
	
		//문자셋 타입
		String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);

		//파일정보 문자열 전송시 "," 구분
		String arrFileName[] = request.getParameter("fileInfo").split(",");

		// 파일정보 데이터셋 전송시
		String fileInfoXml = request.getParameter("fileInfoDs");
		DataSet dsFileInfo = null;		
		if(fileInfoXml != null) {
			dsFileInfo = new DataSet("fileInfo");
			fileInfoXml = fileInfoXml.replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&gt;", ">").replaceAll("&#32;", " ");    
			log.debug(fileInfoXml);		
			dsFileInfo.loadXml(fileInfoXml);
		}

		//다운로드 Client 파일명
		String displayFileName = "";
		
		//filepath 파라미터 (폴더명)
		String fileDir = request.getParameter("filepath");		
		
		//사용자지정폴더가 넘어왔을시
		if(fileDir != null) {
			fileDir = fileDir + SP;
		}
		else {
			fileDir = "";          
		}

		sUserPath = SP+fileDir;
		String filePath = getFilePath();
		
		String fileName = "";
		
		File file = null;

		/*
		//문자열 전송시
		if(arrFileName.length > 1) {
			//압축 다운로드 구현
		}
		else {
			
			fileName = arrFileName[0];			
			displayFileName = request.getParameter("downfilename"); 

			fileName = URLDecoder.decode(fileName, charsetOfRequest);
			// 파일명에 불필요 문자 제거
			fileName = removedPathTraversal(fileName);
		
			String realFileName = filePath +fileName;

			log.debug("     charsetOfRequest :{}",charsetOfRequest);
			log.debug("     FILE PATH :{}" , filePath);
			log.debug("     FILE NAME :{}" , fileName);
			log.debug("     realFileName :{}" , realFileName);
			
			file = new File(realFileName);					
		}
		*/
		
		//데이터셋 전송시
		if(dsFileInfo.getRowCount() > 1) {
			displayFileName = "첨부파일.zip";
	
			Random rnd = new Random();
	
			String randomStr = String.valueOf(rnd.nextInt(999999999))+String.valueOf(rnd.nextInt(999999999));
	
			file = getCompressZipFile(dsFileInfo, filePath, "compressZip-"+randomStr, charsetOfRequest);
		}
		else {
			displayFileName = request.getParameter("downfilename"); 
			fileName = dsFileInfo.getString(0, "filename");
			
			fileName = URLDecoder.decode(fileName, charsetOfRequest);
			// 파일명에 불필요 문자 제거
			fileName = removedPathTraversal(fileName);
		
			String realFileName = filePath +fileName;

			log.debug("     charsetOfRequest :{}",charsetOfRequest);
			log.debug("     FILE PATH :{}" , filePath);
			log.debug("     FILE NAME :{}" , fileName);
			log.debug("     realFileName :{}" , realFileName);
			
			file = new File(realFileName);		
		}
		
        
 		
	
		NexacroFileResult result = new NexacroFileResult(file);
		result.setOriginalName(displayFileName);
		
		return result;
	}    
	
	/*
	*   파일명에 불필요한 문자제거
	* */
	private String removedPathTraversal(String fileName) {
		if(fileName == null) {
			return null;
		}
	
		fileName = fileName.replace("/", "");
		fileName = fileName.replace("\\", "");
		fileName = fileName.replace("&", "");
	
		return fileName;
	}
	
	/*
	*   파일 압축 (압축파일도 서버에 저장을 하기 때문에 Job Scheduler 등으로 이후 삭제하여야 합니다)
	* */
	private File getCompressZipFile(DataSet fileInfo, String realPath, String compressName, String charsetOfRequest) throws IOException {
	
		String dumpDir = "dummy"+ SP;
		String path = realPath;
		String files[] = new String[fileInfo.getRowCount()];
	
		for(int i=0;i<files.length;i++) {
			files[i] = URLDecoder.decode(fileInfo.getString(i, "realFileName"), charsetOfRequest);
			files[i] = removedPathTraversal(files[i]);
		}
	
		File destination = new File(path + dumpDir);
	
		if( destination.exists() == false) {
			boolean mkdirs = destination.mkdirs();
			destination.setWritable(true);
	
			log.debug("-------------- create directory ----------------------{}" , mkdirs);
		}
	
		//buffer size
		int size = 1024;
		byte[] buf = new byte[size];
		String outZipNm = path + dumpDir + compressName +".zip";
	
		File file = new File(outZipNm);
	
		FileInputStream fis = null;
		ZipArchiveOutputStream zos = null;
		BufferedInputStream bis = null;
	
		try {
			// Zip 파일생성
			zos = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(outZipNm))); 
			for( int i=0; i < files.length; i++ ){
				//encoding 설정
				zos.setEncoding("UTF-8");
			
				//buffer에 해당파일의 stream을 입력한다.
				fis = new FileInputStream(path + files[i]);
				bis = new BufferedInputStream(fis,size);
			
				//zip에 넣을 다음 entry 를 가져온다.
				zos.putArchiveEntry(new ZipArchiveEntry(files[i]));                 
			
				//준비된 버퍼에서 집출력스트림으로 write 한다.
				int len;
				while((len = bis.read(buf,0,size)) != -1){
					zos.write(buf,0,len);
				}
			
				bis.close();
				fis.close();
				zos.closeArchiveEntry();
			}
			zos.close();
	
		log.debug("     charsetOfRequest :{}",charsetOfRequest);
		log.debug("     FILE PATH :{}" , (path+ dumpDir));
		log.debug("     FILE NAME :{}.zip", compressName);
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if( zos != null ){  zos.close();  }
			if( fis != null ){  fis.close();  }
			if( bis != null ){  bis.close();  }
		}
		
		return file;
	}	
}	



    

