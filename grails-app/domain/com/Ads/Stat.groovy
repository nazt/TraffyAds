package com.Ads

class Stat {
	static belongsTo = [adsMessage:Data]	
/*	static hasMany = [adsMessage:Data]	*/
	Date dateCreated
	static mapping = {
      table 'twitter_ads_stat'     
    }	
    static constraints = {
		adsMessage()
		dateCreated(nullable:true)
    }
	String toString()
	{
		"${dateCreated}"
	}
}
