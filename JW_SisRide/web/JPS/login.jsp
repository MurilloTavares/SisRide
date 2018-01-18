<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SisRide - Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="../CSS/login.css" rel="stylesheet">
        <script type="text/javascript" src="../JS/login.js"></script>
    </head>
    <body>
        <div class="login-page">
            <div class="logo-header">
                <img src="../IMG/sisRideBranco.png"/>
            </div>
            <div class="form">
                <form id="login-form">
                    <input type="text" placeholder="email"/>
                    <input type="password" placeholder="senha"/>
                    <button>login</button>
                    <p class="mensagem">Não possui cadastro? <a onclick="cadastroForm()">cadastrar</a></p>
                </form>
                <form id="cadastro-form">
                    <p class="obrigatorio">email:</p> 
                    <input type="text" name="email"/>
                    <p class="obrigatorio">senha:</p>
                    <input type="text" name="senha"/>
                    <p class="obrigatorio">confirmar senha:</p>
                    <input type="tex" name="confSenha"/>
                    <p class="obrigatorio">nome:</p>
                    <input type="text" name="nome"/>
                    <p class="obrigatorio">nascimento:</p>
                    <input type="date" name="nascimento"/>
                    <p class="obrigatorio">sexo:</p>
                    <input type="radio" name="sexo" value="masculino"><p class="radioP">masculino</p>
                    <input type="radio" name="sexo" value="feminino"><p class="radioP">feminino</p><br>
                    <p>cidade:</p>
                    <input type="text" name="cidade"/>
                    <p>profissao:</p>
                    <input type="text" name="profissao"/>
                    
                    <p id="mensagemCampo" class="obrigatorio">Campo obrigatório</p>
                    <button>Criar Conta</button>
                    <p class="mensagem">Já registrado? <a onclick="loginForm()">Login</a></p>
                </form>
            </div>
        </div>
    </body>
</html>
