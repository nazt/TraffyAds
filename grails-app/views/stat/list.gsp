
<%@ page import="com.Ads.Stat" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stat.label', default: 'Stat')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'stat.id.label', default: 'Id')}" />
                        
                            <th><g:message code="stat.adsMessage.label" default="Ads Message" /></th>
                   	    
                            <g:sortableColumn property="dateCreated" title="${message(code: 'stat.dateCreated.label', default: 'Date Created')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${statInstanceList}" status="i" var="statInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${statInstance.id}">${fieldValue(bean: statInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: statInstance, field: "adsMessage")} (${statInstance.adsMessage.frequency})</td>
                        
                            <td><g:formatDate date="${statInstance.dateCreated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${statInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
