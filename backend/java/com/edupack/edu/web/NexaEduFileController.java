package com.edupack.edu.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.datatype.PlatformDataType;
import com.nexacro.java.xapi.tx.PlatformType;
import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroFileResult;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;

@Controller
public class NexaEduFileController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String SP = File.separator;
	private static final String sFilePath = "upload_file";
	private static String sUserPath = "";
	
	@RequestMapping(value="edu/uploadFile.do")
	public NexacroResult uploadFile(MultipartHttpServletRequest request) throws IOException {
		String sUserDir = request.getParameter("userPath");
		if(sUserDir !=null && !sUserDir.equals("")) {
			sUserPath = sUserDir;
		}
		
		DataSet ds_file = createDataSet();
		
		uploadFile(request, ds_file);
		
		NexacroResult result = new NexacroResult();
		result.addDataSet(ds_file);
		result.setErrorCode(0);
		result.setErrorMsg("File Upload Success");
		
		return result;
	}

	private void uploadFile(MultipartHttpServletRequest request, DataSet ds_file) throws IOException {
		//파일 업로드 경로 구하기
		String uploadPath = getUploadPath();
		
		//업로드 경로의 파일 객체를 생성
		File fileDir = new File(uploadPath);
		//파일 객체 경로에 디렉토리 존재 여부 체크. 없으면 디렉토리 생성
		if(!fileDir.exists()) {
			fileDir.mkdirs();
			fileDir.setWritable(true);
		}
		
		//request 객체에서 업로드 파일의 이름 목록을 가져옴
		Iterator<String> fileName = request.getFileNames();
		while(fileName.hasNext()) {
			MultipartFile mFile = request.getFile(fileName.next());
			String sFileName = mFile.getOriginalFilename();
			String sNewFileName = sFileName;
			
			
			//파일명 중복
			File upFile = new File(uploadPath + SP + sFileName);
			if(upFile.exists()) {
				Date currDate = new Date();
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				sNewFileName = simpleDate.format(currDate) + "_" + sFileName;
				upFile = new File(uploadPath + SP + sNewFileName);
			}
			mFile.transferTo(upFile);
			
			int nRow = ds_file.newRow();
			ds_file.set(nRow, "file_id" , sFileName);
			ds_file.set(nRow, "file_name" , sFileName);
			ds_file.set(nRow, "file_size" , upFile.length());
		}
	}

	
	//실제 웹 어플리케이션이 실행되는 경로를 받아옴
	@Autowired
	private WebApplicationContext appContext;
	private String getUploadPath() {
		String sRealPath = appContext.getServletContext().getRealPath("/");
		String sFullPath = sRealPath + sFilePath + SP + sUserPath;
		
		log.debug("#######################################");
		log.debug(sFullPath);
		log.debug("#######################################");
		
		return sFullPath;
	}

	private DataSet createDataSet() {
		DataSet ds = new DataSet("ds_file");
		ds.addColumn("file_id",PlatformDataType.STRING);
		ds.addColumn("file_name",PlatformDataType.STRING);
		ds.addColumn("file_size",PlatformDataType.INT);
		return ds;
	}
	
	@RequestMapping(value = "edu/getFileList.do")
	public NexacroResult getFileList(@ParamVariable(name="userPath")String sPath) {
			sUserPath = sPath;
			String sFileDir = getUploadPath();
			DataSet ds_file = createDataSet();
			
			File file = new File(sFileDir);
			File[] arrFile = file.listFiles();
			for(int i=0; i<arrFile.length; i++) {
				int nRow = ds_file.newRow();
				ds_file.set(nRow, "file_id", arrFile[i].getName());
				ds_file.set(nRow, "file_name", arrFile[i].getName());
				ds_file.set(nRow, "file_size", arrFile[i].length());
				}
			
			NexacroResult result = new NexacroResult();
			result.addDataSet(ds_file);
			result.setErrorCode(0);
			result.setErrorMsg("File List");
			return result;
	}
	
	@RequestMapping(value = "edu/deleteFile.do")
	public NexacroResult getFileList(@ParamVariable(name="userPath")String sPath, @ParamVariable(name="fileId") String sFileId) {
			sUserPath = sPath;
			String sFileDir = getUploadPath();
			
			File rmFile = new File(sFileDir + SP + sFileId);
			rmFile.delete();
			
			DataSet ds_file = createDataSet();
			
			NexacroResult result = new NexacroResult();
			result.addDataSet(ds_file);
			result.setErrorCode(0);
			result.setErrorMsg("File Del");
			return result;
	}
	
	@RequestMapping(value = "edu/downloadFile.do")
	public NexacroFileResult downloadFile(HttpServletRequest request) throws IOException{
			String sFileDir = getUploadPath();
			String sFileId = request.getParameter("downFileId");
			String sFileDs = request.getParameter("downFileDs");
			
			File f = null;
			
			if(sFileId !=null && !sFileId.equals("")) {
				String arrFile[] = sFileId.split("\\|\\|");
				if(arrFile.length>1) {
					//압축 다운로드
					
				}else {
					f = new File(sFileDir + SP + sFileId);
					}
			}
			else if(sFileDs !=null && !sFileDs.equals("")) {
				sFileDs = URLDecoder.decode(sFileDs, PlatformType.DEFAULT_CHAR_SET);
				log.debug(sFileDs);
				DataSet objDs = new DataSet("ds_down");
				objDs.loadXml(sFileDs);
				if(objDs.getRowCount()>1) {
					//압축 다운로드
					Random rnd = new Random();
					String sRnd = String.valueOf(rnd.nextInt(99999999) + rnd.nextInt(999999999));
					f = getDownZipFile(objDs, sFileDir, "CompressZip"+sRnd);
				}
				else {
					//단건 다운로드
				}
			}
			NexacroFileResult result = new NexacroFileResult(f);
			return result;
	}
	private File getDownZipFile(DataSet objDs, String filePath, String compressName) throws IOException {
		String dumDir = "dummy"+SP;
		
		String files[] = new String[objDs.getRowCount()];
		
		for(int i=0; i<files.length; i++) {
			files[i] = URLDecoder.decode(objDs.getString(i, "file_id"), PlatformType.DEFAULT_CHAR_SET);
		}
		
		File tempDir = new File(filePath + dumDir);
		if(tempDir.exists() == false) {
			boolean mkdirs = tempDir.mkdirs();
			tempDir.setWritable(true);
		}
		
		int size = 1024;	
		byte[] buf = new byte[size];
		String outZipNm = filePath + dumDir + compressName + ".zip";
		
		File file = new File(outZipNm);
		FileInputStream fis = null;
		ZipArchiveOutputStream zos = null;
		BufferedInputStream bis = null;
		
		try {
			zos = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(outZipNm)));
			for( int i=0; i<files.length; i++) {
				zos.setEncoding("UTF-8");
				fis = new FileInputStream(filePath + SP + files[i]);
				bis = new BufferedInputStream(fis, size);
				
				zos.putArchiveEntry(new ZipArchiveEntry(files[i]));
				
				int len;
				while((len = bis.read(buf, 0, size)) != -1){
					zos.write(buf, 0, len);
				}
				bis.close();
				fis.close();
				zos.closeArchiveEntry();
			}
			zos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( zos != null ) { zos.close(); }
			if( fis != null ) { fis.close(); }
			if( bis != null ) { bis.close(); }
		}
		
		return file;
	}


}
