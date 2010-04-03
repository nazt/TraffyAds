
<%@ page import="com.Ads.Data" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'data.label', default: 'Data')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
		 
	 
        <div class="message">logged in with ${session.user}</div>
        ${flash.message}
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'data.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="message" title="${message(code: 'data.message.label', default: 'Message')}" />
                        
                            <g:sortableColumn property="frequency" title="${message(code: 'data.frequency.label', default: 'Frequency')}" />
                        
                            <th><g:message code="data.adsType.label" default="Ads Type" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dataInstanceList}" status="i" var="dataInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${dataInstance.id}">${fieldValue(bean: dataInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: dataInstance, field: "message")}</td>
                        
                            <td>${fieldValue(bean: dataInstance, field: "frequency")}</td>
                        
                            <td>${fieldValue(bean: dataInstance, field: "adsType")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${dataInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
