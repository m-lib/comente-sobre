<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            @import url("<core:url value="/resources/css/comente-sobre.css"/>");
        </style>
        <title>${topico.titulo}</title>
    </head>
    <body>
        <h1><a HREF=".">Comente Sobre:</a> ${topico.titulo}</h1>

        <core:if test="${!empty errors}">
            <h3 id="mensagem" class="alinhamento">
                Para Comentrar:
                <ul>
                <core:forEach var="error" items="${errors}">
                    <li>${error.message}</li>
                </core:forEach>
                </ul>
            </h3>
        </core:if>

        <table width="100%">
            <core:forEach items="${topico.comentarios}" var="comentario">
                <tr>
                    <td>
                    <blockquote class="alinhamento">
                        <h3>${comentario.autor.email} disse:</h3>
                        ${comentario.mensagem}
                    </blockquote>
                    </td>
                </tr>
            </core:forEach>
        </table>

        <form id="formComentario" class="alinhamento" action="${topico.uri}/comentar" method="post">
            <h3>E-Mail:</h3>
            <input id="email" class="campo" type="text" name="comentario.autor.email" size="80" value="${comentario.autor.email}"/>
            <br/>
            <h3>Comentário:</h3>
            <textarea id="comentario" class="campo" name="comentario.mensagem" rows="5" cols="80">${comentario.mensagem}</textarea>
            <input id="enviar" class="botao" type="submit" value="Enviar"/>
        </form>
    </body>
</html>