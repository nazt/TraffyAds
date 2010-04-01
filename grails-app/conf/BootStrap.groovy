 
class BootStrap {

     def init = { servletContext ->
		new com.Ads.Type(name:"Information").save()
		new com.Ads.Type(name:"Relax").save()		
     }
     def destroy = {
	
     }
} 