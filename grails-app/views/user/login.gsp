<html>
    <head>
        <title>Welcome to Grails OAuth</title>
		<meta name="layout" content="main" />
    </head>
    <body>
 
        <!-- <h1 style="margin-left:20px;">Welcome to Grails OAuth</h1> -->
        <%--<p style="margin-left:20px;width:80%">Congratulations, you have successfully started your first Grails application! At the moment
        this is the default page, feel free to modify it to either redirect to a controller or display whatever
        content you may choose. Below is a list of controllers that are currently deployed in this application,
        click on each to execute its default action:</p>
        <div class="dialog" style="margin-left:20px;width:60%;">
            <ul>
              <g:each var="c" in="${grailsApplication.controllerClasses}">
                    <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
              </g:each>
            </ul>
        </div>--%>
        
 
        
        <!-- <div style="margin-left: 20px; width: 220px; margin-top: 30px; border-top: 1px dotted silver; padding-top: 10px">
            <g:oauthLink consumer='linkedin' returnTo="[controller:'data']">
                <h3>Login with
                <img src="http://static.raibledesigns.com/repository/images/linkedin-logo.gif" width="129" height="36"
                    style="border: 0; float: right"/></h3>
            </g:oauthLink>
        </div>
         -->
        <div style="margin-left: 20px; margin-top: 20px; width: 240px; padding-top: 10px; margin-top: 30px">
            <g:oauthLink consumer="twitter" returnTo="[controller:'user',action:'permission']" error="[controller:'errorController',action:'errorAction']">
 
                <h3>Login with 
                <img src="http://static.raibledesigns.com/repository/images/twitter-logo.png" width="155" height="36"
                    style="border: 0; float: right"></h3>
            </g:oauthLink>
        </div>
    </body>
</html>