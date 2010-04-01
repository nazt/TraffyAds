package com.Ads

class SecurityFilters {

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
				else if (controllerName.equals('oauth') || controllerName.equals('user') )
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
