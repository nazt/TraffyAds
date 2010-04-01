package com.Ads
import grails.converters.*
class UserController {
		def apiUrl = "http://twitter.com/statuses/user_timeline.json"
	    def oauthService
 
    def index = {  render 'index' }
	def login = {   }
	def permission = {  
	 	println 'session'+ session
        if (session.oauthToken == null) {
		 	println "error no session!"
            redirect(uri:"/")
			return ;
        }
		
	        def response = oauthService.accessResource(
	                apiUrl, 'twitter', [key:session.oauthToken.key, secret:session.oauthToken.secret])
	        def statuses = JSON.parse(response)
			render 'Hello ' +  statuses.user.screen_name.get(0)
		 }
}
