package com.Ads

class Data {
	static belongsTo = [ adsType : Type ]  
	String message
	Integer frequency=1
	static mapping = {
      table 'twitter_ads_data'     
    }	
    static constraints = {
		message(unique:true,nullable:false,blank:false)
		frequency()
    }
	String toString()
	{
		/*"${id} - ${message} (${frequency})"*/
		"${message}"
	}
}
