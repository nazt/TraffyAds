package com.Ads

class Data {
	static belongsTo = [ adsType : Type ]  
	String message
	Integer frequency=0
	
    static constraints = {
		message(unique:true,nullable:false,blank:false)
		frequency()
    }
}
