package com.lgbtq.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Download File From The Server", urlPatterns = { "/downloadServlet" })
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static int BUFFER_SIZE = 1024 * 100;
	public static final String UPLOAD_DIR = "uploadedFiles";

	//Recebendo requisição apenas por GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Obter o path da aplicação
		String fileName = request.getParameter("fileName"),
				applicationPath = getServletContext().getRealPath(""),
				downloadPath = applicationPath + File.separator + UPLOAD_DIR,
				filePath = downloadPath + File.separator + fileName;

		File file = new File(filePath);
		OutputStream outStream = null;
		FileInputStream inputStream = null;

		if (file.exists()) {

			//Configura os atributos do conteúdo para o objeto do request
			String mimeType = "application/octet-stream";
			response.setContentType(mimeType);

			//Configura os headers para o objeto do request
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
			response.setHeader(headerKey, headerValue);

			try {

				//Isso é para obter o fluxo de saída do request
				outStream = response.getOutputStream();
				inputStream = new FileInputStream(file);
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;

				//A ideia desse é escrever cada byte de dados lidos a partir do fluxo de entrada e depois gravá-los para lançar no fluxo de saída, eu acho.
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}				
			} catch(IOException ioExObj) {
				System.out.println("Exceção para executar a operação de I/O = " + ioExObj.getMessage());
			} finally {				
				if (inputStream != null) {
					inputStream.close();
				}

				outStream.flush();
				if (outStream != null) {
					outStream.close();
				}
			}
		} else {

			//Tipo do charset do request
			response.setContentType("text/html");

			//Printar o response e tals
			response.getWriter().println("<h3>O arquivo "+ fileName +" Não está aqui, rs...!</h3>");
		}
	}
}