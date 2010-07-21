package com.Ads

class SecurityFilters {
	def allowController=["oauth","user"]
    def filters = {
       loginCheck(controller:'*', action:'*') {
           before = {
   			  if (params.controller == null) 
				{ 
					redirect(controller:'user',action:'login')
	                 /* When using Tomcat and the controller is null you must 
	                    return true. Otherwise an exception is thrown. */
					return true
			    }
				else if (  actionName.equals('random')  || (controllerName in allowController) )
				{
					return true
				}
 			
	          else if(!session.user && !actionName.equals('login')) {
                  redirect(controller:'user',action:'login')
                  return false
               }

			
	 	
          }

       }       
    }
    
}
