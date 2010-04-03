package com.Ads

class StatController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        [statInstanceList: Stat.list(params), statInstanceTotal: Stat.count()]
    }

    def create = {
        def statInstance = new Stat()
        statInstance.properties = params

        return [statInstance: statInstance]
    }

    def save = {
		println params	
        def statInstance = new Stat(params)
        if (statInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'stat.label', default: 'Stat'), statInstance.id])}"
            redirect(action: "show", id: statInstance.id)
        }
        else {
            render(view: "create", model: [statInstance: statInstance])
        }
    }

    def show = {
        def statInstance = Stat.get(params.id)
        if (!statInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
            redirect(action: "list")
        }
        else {
            [statInstance: statInstance]
        }
    }

    def edit = {
        def statInstance = Stat.get(params.id)
        if (!statInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [statInstance: statInstance]
        }
    }

    def update = {
        def statInstance = Stat.get(params.id)
        if (statInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (statInstance.version > version) {
                    
                    statInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'stat.label', default: 'Stat')] as Object[], "Another user has updated this Stat while you were editing")
                    render(view: "edit", model: [statInstance: statInstance])
                    return
                }
            }
            statInstance.properties = params
            if (!statInstance.hasErrors() && statInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'stat.label', default: 'Stat'), statInstance.id])}"
                redirect(action: "show", id: statInstance.id)
            }
            else {
                render(view: "edit", model: [statInstance: statInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def statInstance = Stat.get(params.id)
        if (statInstance) {
            try {
                statInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stat.label', default: 'Stat'), params.id])}"
            redirect(action: "list")
        }
    }
}
