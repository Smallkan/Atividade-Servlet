package com.lgbtq.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//Define um endereço para 
@WebServlet(description = "Upload para o servidor", urlPatterns = { "/fileUploadServlet" })

//Define um limite de tamanho para o arquivo do upload
//Limite de Tamanho de arquivo = 10MB, Tamanho máximo do arquivo = 30MB, Tamanho máximo da solicitação = 50MB
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";

	//Método para processar uma solicitação 'POST'
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	//Método para receber os requests
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Obter o path da aplicação
		String applicationPath = getServletContext().getRealPath(""),
				uploadPath = applicationPath + File.separator + UPLOAD_DIR;

		File fileUploadDirectory = new File(uploadPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}

		String fileName = "";
		UploadDetail details = null;
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();
		
		//Loop recebendo as partes por requisição(?)
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			details = new UploadDetail();
			details.setFileName(fileName);
			details.setFileSize(part.getSize() / 1024);
			
			//Verificando se o arquivo foi enviado com sucesso.
			try {
				part.write(uploadPath + File.separator + fileName);
				details.setUploadStatus("Sucesso");
			} 
			//Caso dê falha, apresenta a mensagem:
			catch (IOException ioObj) {
				details.setUploadStatus("Falha :( : "+ ioObj.getMessage());
			}
			fileList.add(details);
		}

		request.setAttribute("uploadedFiles", fileList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fileuploadResponse.jsp");
		dispatcher.forward(request, response);
	}

	//Método para leitura do nome do arquivo (FIX 3)
	private String extractFileName(Part part) {
		String fileName = "", 
				contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}
}
