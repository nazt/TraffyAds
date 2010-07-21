package com.Ads
import grails.converters.*
class UserController {
		def apiUrl = "http://twitter.com/statuses/user_timeline.json"
	    def oauthService
 		def allowedUser=["NAzT", 'traffy','wapst7','wasawaz'].collect { it.toLowerCase()  }
    def index = { flash.message ="already logged in"; redirect(controller:'data',action:'list') }
	def login = { if(session.user) redirect(controller:'data',action:'list') }
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
			  	redirect(controller:'data',action:'list')
			}
			else
			{
				flash.message=" account ${authenticatedUser} :  permission denied."
				redirect(controller:'user',action:'login')
/*				render "(${authenticatedUser}) permission denied."*/
			}
		 }
}
