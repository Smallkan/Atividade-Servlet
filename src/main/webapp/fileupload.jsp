
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
    <form class="form-signin" id="fileUploadForm" method="post" action="fileUploadServlet" enctype="multipart/form-data">
  <div class="text-center mb-4">
    <img class="mb-4" src="resource/img/logo.jpg" alt="" width="170" height="auto">
    <h1 class="h3 mb-3 font-weight-normal">Desenvolvimento de Servlet</h1>
    <p>Carregando imagem e exibindo conteúdo, <a href="https://github.com/Smallkan/Atividade-Servlet" target="_blank">Github</a>.</p>
    <p>Pressione "CTRL" + Clique para selecionar múltiplas imagens ao abrir a caixa de seleção, tente fazer isso :)</p>
  </div>
	<br />
	<center><label for="fileUpload">Selecione uma ou mais imagens</label></center>
  <div class="form-label-group">
    <input type="file" id="inputEmail" class="form-control" type="file" name="fileUpload" multiple="multiple">
  </div>

  <button class="btn btn-lg btn-primary btn-block" type="submit">Fazer upload</button>
  <p class="mt-5 mb-3 text-muted text-center">Equipe LGBTQ+&trade;</p>
</form>
<script src="resource/js/bootstrap.min.js"></script>
</body>
</html>
