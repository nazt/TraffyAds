package com.Ads
import grails.converters.*
class UserController {
		def apiUrl = "http://twitter.com/statuses/user_timeline.json"
	    def oauthService
 		def allowedUser=["NAzT", 'traffy','wapst7'].collect { it.toLowerCase()  }
    def index = {  render 'index' }
	def login = {   }
	def permission = {  
	 	
        if (session.oauthToken == null) {
		 	println "error no session!"
            redirect(uri:"/")
			return ;
        }
	        def response = oauthService.accessResource(
	                apiUrl, 'twitter', [key:session.oauthToken.key, secret:session.oauthToken.secret])
	        def statuses = JSON.parse(response)
			
			def authenticatedUser=statuses.user.screen_name.get(0)
			
			if(  authenticatedUser.toLowerCase() in allowedUser)
			{
				session["user"]  = authenticatedUser;
/*				render 'Hello ' +  authenticatedUser
				render " Allowed."*/
			  redirect(controller:'data',action:'list')
			}
			else
			{
				render 'Hello ' +  authenticatedUser
				render " Don't Allowed."
			}
		 }
}
