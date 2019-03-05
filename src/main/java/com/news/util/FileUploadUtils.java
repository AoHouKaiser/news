package com.news.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileUploadUtils {
	// ����ļ��ϴ�����Ҫ�����SpringMVC��ܵ�

	// ������Ҫ�ǰ��ļ��ϴ�������Tomcat��������webapp���� ��������web���ļ����ʵ�ַ

	/**
	 * �ļ��ϴ�
	 * 
	 * @param request
	 *            HTTP����
	 * @param files
	 *            �ϴ����ļ�
	 * @param folder
	 *            Ҫ�������ļ��е�����
	 * @return
	 */
	public static Map<String, UploadFile> upLoadFile(
            HttpServletRequest request, List<MultipartFile> files, String folder) {
		Map<String, UploadFile> map = new HashMap<String, UploadFile>();
		if (files != null && files.size() > 0) {
			// 1.��ȡ����·��
			// request.getSession().getServletContext().getRealPath("/")==>
			// E:\apache-tomcate-7.0\webapps\��Ŀ��
			// getParentFile() ==>E:\apache-tomcate-7.0\webapps
			File root = new File(request.getSession().getServletContext()
					.getRealPath("/")).getParentFile();
			// 2.����ͼƬ�����ڷ��������Ǹ�λ��
			// ==>E:\apache-tomcate-7.0\webapps\floder(��ָ�����ļ�������)
			File loaclFilePath = new File(root, folder);
			// 3.ͨ��httpЭ����ʵ�·�� http://localhost:8080/floder(��ָ�����ļ�������)
			String webFileUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ "/" + folder;
			try {
				for (MultipartFile file : files) {
					// �ļ���ԭʼ�ļ���
					String originalFileName = file.getOriginalFilename();
					if (file.isEmpty()) {
						continue;
					}
					// �������һ���µ��ļ����ָ��ϴ����ļ�
					// 29e6c3676c23466c8735bcc4d157ee08.xxx
					String newFileName = randomFileName()
							+ originalFileName.substring(originalFileName
									.lastIndexOf("."));
					// E:\apache-tomcate-7.0\webapps\floder(��ָ�����ļ�������)\2017\02\25\
					String dir = loaclFilePath.getAbsolutePath()
							+ dateFileDirectory();
					// ���´����µı����ļ���·��
					// E:\apache-tomcate-7.0\webapps\floder(��ָ�����ļ�������)\2017\02\25\29e6c3676c23466c8735bcc4d157ee08.xxx
					String newFilePath = dir + newFileName;
					// ���´����µ�httpЭ������ļ���·��
					// http://localhost:8080/floder(��ָ�����ļ�������)\2017\02\25\29e6c3676c23466c8735bcc4d157ee08.xxx
					String newFileUrl = webFileUrl + dateFileDirectory()
							+ newFileName;
					// �����ļ�·��
					File saveFileDirectory = new File(dir);
					if (!saveFileDirectory.exists()) {
						saveFileDirectory.mkdirs();
					}
					// �½��ļ�
					File createFiles = new File(newFilePath);
					long fileSize = file.getSize();
					String fileType = file.getContentType();
					
					//ͨ������Ĵ���ʵ�����ļ����Ե�ƴ��,���ɲ���,�������UploadFile,ʵ����һ���ļ�����
					UploadFile uploadFile = new UploadFile(originalFileName,
							newFileName, newFilePath, newFileUrl, fileType,
							fileSize);
					
					//���ļ��������map���� 
					map.put(originalFileName, uploadFile);
					
					//�ϴ��ļ�spring����ṩ�Ĺ���
					file.transferTo(createFiles);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * ��������ļ���
	 * 
	 * @return 29e6c3676c23466c8735bcc4d157ee08
	 */
	private static String randomFileName() {
		String name = UUID.randomUUID().toString();
		name = name.replaceAll("-", "");
		return name;
	}

	/**
	 * �������ڵ��ļ���
	 * 
	 * @return \2017\02\25\
	 */
	private static String dateFileDirectory() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		return File.separator + year + File.separator + (month+1) + File.separator
				+ day + File.separator;
	}
	
	public static void main(String[] args) {
		System.out.println(FileUploadUtils.randomFileName());
	}

}
