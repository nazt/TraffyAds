package com.Ads
import grails.converters.*

class DataController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def apiUrl = "http://twitter.com/statuses/user_timeline.json"
    def oauthService

    def index = {			
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [dataInstanceList: Data.list(params), dataInstanceTotal: Data.count()]
    }
	def random = { 
		def tmp
		def messageList=[]
		Data.list().each{  tmp=[] ; tmp << it;  messageList <<  tmp * it.frequency  }
			//println " ${it.message}  ${it.frequency}"
			messageList=messageList.flatten()
			Collections.shuffle(messageList)   
			def randomedData=messageList.get(0)
			def randomed2render=[message:randomedData.message,frequency:randomedData.frequency,type:randomedData.adsType.name]
			new Stat( adsMessage:randomedData).save()
			switch(params.id?.toLowerCase()) {
				case "json":
					  render randomed2render  as JSON 
					break
				case "xml":
					render(text:randomed2render as XML, contentType: 'text/xml', encoding: 'UTF-8')					
				break
				case "text":
					render randomedData
					break
				default:
            		redirect(action: "random", id: "text")
					break
			}
		}
    def create = {
        def dataInstance = new Data()
        dataInstance.properties = params
        return [dataInstance: dataInstance]
    }

    def save = {
        def dataInstance = new Data(params)
        if (dataInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'data.label', default: 'Data'), dataInstance.id])}"
            redirect(action: "show", id: dataInstance.id)
        }
        else {
            render(view: "create", model: [dataInstance: dataInstance])
        }
    }

    def show = {
        def dataInstance = Data.get(params.id)
        if (!dataInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
            redirect(action: "list")
        }
        else {
            [dataInstance: dataInstance]
        }
    }

    def edit = {
        def dataInstance = Data.get(params.id)
        if (!dataInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [dataInstance: dataInstance]
        }
    }

    def update = {
        def dataInstance = Data.get(params.id)
        if (dataInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dataInstance.version > version) {
                    
                    dataInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'data.label', default: 'Data')] as Object[], "Another user has updated this Data while you were editing")
                    render(view: "edit", model: [dataInstance: dataInstance])
                    return
                }
            }
            dataInstance.properties = params
            if (!dataInstance.hasErrors() && dataInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'data.label', default: 'Data'), dataInstance.id])}"
                redirect(action: "show", id: dataInstance.id)
            }
            else {
                render(view: "edit", model: [dataInstance: dataInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def dataInstance = Data.get(params.id)
        if (dataInstance) {
            try {
                dataInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'data.label', default: 'Data'), params.id])}"
            redirect(action: "list")
        }
    }
}
