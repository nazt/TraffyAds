 
class BootStrap {

     def init = { servletContext ->
		def info=new com.Ads.Type(name:"Information").save()
		def relax=new com.Ads.Type(name:"Relax").save()	
		def testInfo=new com.Ads.Data(message:"ทดสอบInfo",adsType:info).save()
		new com.Ads.Data(message:"ทดสอบRelax",adsType:relax,frequency:4).save()		
		
		new com.Ads.Stat(adsMessage:testInfo).save()
     }
     def destroy = {
	
     }
} 