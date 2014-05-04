<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<html>
    <head>
        <style type="text/css"> @import url("./resources/css/comente-sobre.css"); </style>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Comente Sobre</title>
    </head>
    <body>

    <center>
        <h1>Comente Sobre:</h1>
        <form id="formInicio" action="topico/novoTopico" accept-charset="UTF-8" method="post">
            <input id="titulo" class="campo" type="text" size="30" name="topico.titulo">
            <input id="ok" class="botao" type="submit" value="Ok"/>
        </form>
        <core:if test="${!empty errors}">
            <h3 id="mensagem">
            <core:forEach var="error" items="${errors}">
                ${error.message}
            </core:forEach>
            </h3>
        </core:if>
    </center>
    
    <core:if test="${!empty topicos}">
    <div style="position: absolute; bottom: 10px; left: 10px; right: 10px">
        <center>
        <h3>
            ÚLTIMOS TÓPICOS:
            <core:forEach items="${topicos}" var="topico">
                (<a HREF="${topico.uri}">${topico.titulo}</a>)
            </core:forEach>
        </h3>
        </center>
    </div>
    </core:if>
    
    <div class="rodape">
        powered by <a HREF="http://vraptor.caelum.com.br/">vraptor</a>
    </div>
</body>
</html>