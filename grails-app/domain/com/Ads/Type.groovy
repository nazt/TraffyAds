package com.Ads

class Type {
	static hasMany = [adsMessage:Data]	
	String name
	
    static constraints = {
		name(unique:true,nullable:false,blank:false)
    }
	String toString()
	{
		name
	}
}
