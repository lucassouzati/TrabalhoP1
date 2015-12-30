<html>

    <%
        String status = request.getParameter("status");
        if (status == null) {
            status = "";
        }
        
    %>
    <head>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>Gerenciamento de Livros</title>
    </head>
    <body>

        <!-- Barra de Login -->

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Gerenciamento de Livros</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" method="POST" action="srvLogin?cmd=autentica">
                        <label><font color ="ffffff"><%=status%></font></label>
                        <div class="form-group">
                            <input type="text" placeholder="Login" class="form-control" name="txtlogin">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Senha" class="form-control" name="txtsenha">
                        </div>
                        
                        
                        
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a href="cadastro.jsp" class="btn btn-success">Cadastrar-se</a>
                    </form>
                </div>
            </div>
        </nav>




        <!-- Carousel da page -->   

        <div id="myCarousel" class="carousel slide" data-ride="carousel">


            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <!-- Slide das imagens -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <label class="carousel-caption text-capitalize"><font size="7" color="FFD700">Gerencie seu status de Leitura</font></label>
                    <img src="ler.jpg" alt="Chania">
                </div>

                <div class="item">
                    <label class="carousel-caption text-capitalize"><font size="7" color="FFD700">Organize suas cole��es</font></label>
                    <img src="colec.jpg" alt="Chania">
                </div>

                <div class="item">
                    <label class="carousel-caption text-capitalize"><font size="7" color="FFD700">Re�na seus livros preferidos</font></label>
                    <img src="mel.jpg" alt="Flower">
                </div>

                <div class="item">
                    <label class="carousel-caption text-capitalize"><font size="7" color="FFD700">Serve at� para quem n�o sabe ler</font></label>
                    <img src="zoe.jpg" alt="Flower">
                </div>
            </div>

            <!-- Controle de passar as imagens -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <script src="bootstrap/js/jquery-1.11.3.js"></script>    
        <script src="bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
