<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora de Vídeo Passatempo - Cadastrar Classe</title>
        
        <link rel="icon" href="../img/ReelRover.png">
        <link rel="stylesheet" href="../css/cadastrarClasse.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.0/css/all.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
              <a class="navbar-brand" href="../index.jsp">
                Reel Rover
              </a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="../index.jsp">Home</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="../ator/atorR.jsp">Ator</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="classeR.jsp">Classe</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Controle de Acervo
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="../ator/atorC.jsp">Cadastrar Ator</a></li>
                      <li><a class="dropdown-item" href="classeC.jsp">Cadastrar Classe</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
        <main class="mx-auto mt-5">
            <h2>Cadastrar Classe</h2>
            <form class="row g-3 mx-auto mt-4 needs-validation" method="post" action="" novalidate="">
                <div class="col-md-6">
                  <label for="inputNome" class="form-label">Nome</label>
                  <input type="text" class="form-control" name="nome" id="inputNome" required="">
                </div>
                <div class="col-md-6">
                  <label for="inputValor" class="form-label">Valor</label>
                  <input type="number" class="form-control" name="valor" id="inputValor" required="">
                </div>
                <div class="col-md-12">
                  <label for="inputDt" class="form-label">Data de Devolução</label>
                  <input type="date" class="form-control" name="dtDevolucao" id="inputDt" required="">
                </div>

                <div class="col-2">
                  <button type="submit" class="btn btn-primary">Enviar</button>
                </div>
                <div class="col-2">
                  <button type="reset" class="btn btn-secondary">Resetar</button>
                </div>
            </form>
        </main>
        
        <footer class="footer mt-lg-5">
            <div class="container pt-3 text-center">
              <div class="footer-content row">
                <div class="footer-left col">
                  <h4>Explore o nosso universo</h4>
                  <p>Acompanhe-nos nas redes sociais para ficar por dentro das novidades!</p>
                  <div class="social-icons">
                    <a href="#"><i class="fab fa-facebook"></i></a>
                    <a class="mx-4" href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                    <a class="mx-4" href="#"><i class="fab fa-pinterest"></i></a>
                  </div>
                </div>
                <div class="footer-right col">
                  <h4>Contato</h4>
                  <ul class="list-group list-group-horizontal">
                    <li class="list-group-item bg-transparent"><i class="fas fa-map-marker-alt"></i> 123 Rua Principal, Cidade, Estado</li>
                    <li class="list-group-item bg-transparent"><i class="fas fa-envelope"></i> exemplo@email.com</li>
                    <li class="list-group-item bg-transparent"><i class="fas fa-phone"></i> +55 123 456 789</li>
                  </ul>
                </div>
              </div>
              <div class="footer-bottom row py-2">
                <small>Todos os direitos reservados &copy; 2023 | <a href="#">Política de Privacidade</a></small>
              </div>
            </div>
        </footer>
        
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    </body>
</html>
