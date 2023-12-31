<%-- 
    Document   : diretorR
    Created on : 23 de ago. de 2023, 13:55:31
    Author     : LEDS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="domain.Diretor" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora de Vídeo Passatempo - Listar Diretor</title>
        
        <link rel="icon" href="${pageContext.request.contextPath}/img/ReelRover.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/diretorR.css">
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
            <h2>Lista De Diretores</h2>
            <div id="spinner-container" class="d-flex justify-content-center">
                <div class="spinner-border" role="status">
                  <span class="visually-hidden">Loading...</span>
                </div>
              </div>

            <div id="listaDiretores" class="row row-cols-2 row-cols-lg-5 align-items-center">
            </div>
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
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/diretor-control/listar",
                    dataType: "json",
                    success: function (diretores) {
                        var listaDiretores = $("#listaDiretores");

                        listaDiretores.empty();

                        $.each(diretores, function (index, diretor) {
                            var modalId = 'exampleModal' + diretor.idDiretor;
                            var cardHtml = '<div class="card col mb-4 me-4 shadow bg-body-tertiary rounded" style="width: 18rem;cursor: pointer;">' +
                                '<div class="card-body">' +
                                '<h5 class="card-title">' + diretor.nome + '</h5>' +
                                '<a href="${pageContext.request.contextPath}/diretor/diretorU.jsp?id=' + diretor.idDiretor + '&nome=' + diretor.nome + '" class="btn btn-warning me-2">Editar</a>' +
                                '<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#' + modalId + '">Deletar</button>' +
                                '</div>' +
                                '</div>'+
                                
                                
                                '<div class="modal fade" id="' + modalId + '" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                                '<div class="modal-dialog modal-dialog-centered">' +
                                '<div class="modal-content">' +
                                '<div class="modal-header">' +
                                '<h1 class="modal-title fs-5" id="exampleModalLabel">Você está prestes a excluir este item.</h1>' +
                                '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>' +
                                '</div>' +
                                '<div class="modal-body">' +
                                '<h3 id="modalAlert">Esta ação é irreversível.</h3>' +
                                '<p>Você deseja continuar?</p>' +
                                '</div>' +
                                '<div class="modal-footer">' +
                                '<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>' +
                                '<button onclick="deletarDiretor(' + diretor.idDiretor + ')" id="btn-confirmar-deleta" type="button" class="btn btn-secondary">Confirmar</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>';

                            $("#listaDiretores").append(cardHtml);
                            $("#spinner-container").addClass("invisible");
                        });
                    },
                    error: function () {
                        alert("Erro ao buscar os diretores.");
                        $("#spinner-container").addClass("invisible");
                    }
                });
                $("#spinner-container").addClass("invisible");
            });
            
        </script>
        
        <script>
            function deletarDiretor(id) {
                if(id){
                    var formData = {
                        id: id
                    };
                    $.ajax({
                        type: 'DELETE',
                        url: '${pageContext.request.contextPath}/diretor-control/excluir',
                        data: JSON.stringify(formData),
                        success: function (response) {
                            alert('Diretor excluído com sucesso');
                            $('#exampleModal').modal('hide');
                            $("#spinner-container").addClass("invisible");
                        },
                        error: function () {
                            alert('Erro ao excluir o diretor');
                            $('#exampleModal').modal('hide');
                            $("#spinner-container").addClass("invisible");
                        }
                    });
                }
            }
        </script>
        
    </body>
</html>
