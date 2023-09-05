<%-- 
    Document   : atorU
    Created on : 23 de ago. de 2023, 14:03:56
    Author     : LEDS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora de Vídeo Passatempo - ID Ator</title>
        
        <link rel="icon" href="../img/ReelRover.png">
        <link rel="stylesheet" href="../css/atorU.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.0/css/all.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    
    </head>
    <body>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
              <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                Reel Rover
              </a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ator/atorR.jsp">Ator</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/classe/classeR.jsp">Classe</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/diretor/diretorR.jsp">Diretor</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Controle de Acervo
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ator/atorC.jsp">Cadastrar Ator</a></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/classe/classeC.jsp">Cadastrar Classe</a></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/diretor/diretorC.jsp">Cadastrar Diretor</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
        
        <main class="mx-auto mt-5">
            <h2>Editar Ator por ID: ...</h2>
            <form id="meuFormulario" class="row g-3 needs-validation mx-auto mt-4" method="PUT" action="${pageContext.request.contextPath}/ator-control/editar" novalidate>
                <div class="col-md-8">
                  <label for="inputID" class="form-label">ID do Ator</label>
                  <input type="text" class="form-control" name="inputID" id="inputID" readonly>
                </div>
                <div class="col-md-8">
                  <label for="inputNome" class="form-label">Nome do Ator</label>
                  <input type="text" class="form-control" name="inputNome" id="inputNome" required>
                  <div class="invalid-feedback">
                    Informação inválida. Preencha o campo!
                  </div>
                </div>

                <div class="col-12">
                  <button type="submit" class="btn btn-success">Salvar</button>
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
    
        <script>
            var urlParams = new URLSearchParams(window.location.search);
            var id = urlParams.get('id');
            var nomeAtor = urlParams.get('nome');

            document.getElementById("inputID").value = id;
            document.getElementById("inputNome").value = nomeAtor;

        </script>
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $("form").on("submit", function (event) {
                    event.preventDefault();
                    var nome = $("#inputNome").val();
                    var id = $("#inputID").val();

                    if (nome && id) {
                        var formData = {
                            id: id,
                            nome: nome
                        };

                        $.ajax({
                            type: "PUT",
                            url: "${pageContext.request.contextPath}/ator-control/editar",
                            data: JSON.stringify(formData),
                            success: function (response) {
                                alert("Ator editado com sucesso!");
                                document.getElementById("meuFormulario").reset();
                            },
                            error: function () {
                                alert("Erro! Alteração do ator não foi salva.");
                            }
                        });
                    }
                });
            });
        </script>
        
        <script>
            (() => {
                'use strict';

                const forms = document.querySelectorAll('.needs-validation');

                Array.from(forms).forEach(form => {
                    form.addEventListener('submit', event => {
                        if (!form.checkValidity()) {
                          event.preventDefault();
                          event.stopPropagation();
                        }

                        form.classList.add('was-validated');
                    }, false);

                    form.addEventListener('reset', () => {
                        form.classList.remove('was-validated');
                    }, false);
                });
            })();
        </script>
    </body>
</html>
