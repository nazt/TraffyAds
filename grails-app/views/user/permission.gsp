<%@ page import="com.Ads.Data" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'data.label', default: 'Data')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
 	 
   
        <div class="body">
		${flash.message}
		</div>
             
 
    </body>
</html>
