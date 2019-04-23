package com.lgbtq.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
@WebServlet(description = "Listar os arquivos já enviados", urlPatterns = { "/uploadedFilesServlet" })
public class UploadedFilesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";

	//Método para receber a requisição por GET apenas
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Obter o endereço completo do path
		String applicationPath = getServletContext().getRealPath(""),
				uploadPath = applicationPath + File.separator + UPLOAD_DIR;

		File fileUploadDirectory = new File(uploadPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}

		UploadDetail details = null;
		File[] allFiles = fileUploadDirectory.listFiles();
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();		

		for (File file : allFiles) {
			details = new UploadDetail();
			details.setFileName(file.getName());
			details.setFileSize(file.length() / 1024);
			fileList.add(details);
		}

		request.setAttribute("uploadedFiles", fileList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/allfiles.jsp");
		dispatcher.forward(request, response);
	}
}