<%@page import="java.util.List"%>
<%@page import="com.lgbtq.servlet.UploadDetail"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Uma descrição legal para um projeto legal">
    <meta name="author" content="Equipe LGBTQ+">
    <title>Atividade Servlet - Carregar Imagem</title>

    <!-- Bootstrap core CSS e jQuery -->
	<link href="resource/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="resource/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resource/js/fileupload.js"></script>
	<link rel="stylesheet" href="resource/venobox/venobox.css" />
	<script src="resource/venobox/venobox.min.js"></script>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="resource/css/floating-labels.css" rel="stylesheet">
  </head>
  <body>
    <div class="boxe" id="fileUploadForm">
  <div class="text-center mb-8">
    <a href="/AtividadeServlet"><img class="mb-4" src="resource/img/logo.jpg" alt="" width="170" height="auto" border="0"></a>
  </div>
	<br />
	<center><label for="fileUpload">Selecione uma ou mais imagens</label></center>
	<div class="container">
  <div class="row">
  <table class="table">
		    	<thead>
		    		<tr align="center">
		    			<th>Nome da Imagem</th>
		    			<th>Tamanho</th>
		    			<th>Status do Upload</th>
		    			<th>Ações</th></tr>
		    	</thead>
		    	<tbody>
		    		<% List<UploadDetail> uploadDetails = (List<UploadDetail>)request.getAttribute("uploadedFiles");
                     if(uploadDetails != null && uploadDetails.size() > 0) {
                     for(int i=0; i<uploadDetails.size(); i++) {
                  %>
		    	    <tr>
		    	     	<td align="center"><span id="fileName"><%=uploadDetails.get(i).getFileName() %></span></td>
                     	<td align="center"><span id="fileSize"><%=uploadDetails.get(i).getFileSize() %> KB</span></td>
                  	 	<td align="center"><span id="fileuploadStatus"><%=uploadDetails.get(i).getUploadStatus() %></span></td>
                  	 	<td align="center">
                  	 		<span id="fileDownload">
                  	 			<a id="downloadLink" class="venobox" href="<%=request.getContextPath()%>/downloadServlet?fileName=<%=uploadDetails.get(i).getFileName() %>">Visualizar</a> / 
                  	 			<a id="downloadLink" class="hyperLink" href="<%=request.getContextPath()%>/downloadServlet?fileName=<%=uploadDetails.get(i).getFileName() %>">Download</a>
                  	 		</span>
                  	 	</td>                		    	    	
		    	    </tr>
		    	    <% }
                   } else { %>
                  <tr>
                     <td colspan="3" align="center"><span id="noFiles">Nenhum arquivo foi upado...!</span></td>
                  </tr>
                  <% } %>
		    	</tbody>
		    </table>
		    </div>
		    </div>
  <p class="mt-5 mb-3 text-muted text-center">Equipe LGBTQ+&trade;</p>
</div>
<script type="text/javascript">
$(document).ready(function(){
	  $('.venobox').venobox(); 
	});
</script>
<script src="resource/js/bootstrap.min.js"></script>
</body>
</html>
