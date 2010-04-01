package com.Ads

class TypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [typeInstanceList: Type.list(params), typeInstanceTotal: Type.count()]
    }

    def create = {
        def typeInstance = new Type()
        typeInstance.properties = params
        return [typeInstance: typeInstance]
    }

    def save = {
        def typeInstance = new Type(params)
        if (typeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'type.label', default: 'Type'), typeInstance.id])}"
            redirect(action: "show", id: typeInstance.id)
        }
        else {
            render(view: "create", model: [typeInstance: typeInstance])
        }
    }

    def show = {
        def typeInstance = Type.get(params.id)
        if (!typeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
            redirect(action: "list")
        }
        else {
            [typeInstance: typeInstance]
        }
    }

    def edit = {
        def typeInstance = Type.get(params.id)
        if (!typeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [typeInstance: typeInstance]
        }
    }

    def update = {
        def typeInstance = Type.get(params.id)
        if (typeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (typeInstance.version > version) {
                    
                    typeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'type.label', default: 'Type')] as Object[], "Another user has updated this Type while you were editing")
                    render(view: "edit", model: [typeInstance: typeInstance])
                    return
                }
            }
            typeInstance.properties = params
            if (!typeInstance.hasErrors() && typeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'type.label', default: 'Type'), typeInstance.id])}"
                redirect(action: "show", id: typeInstance.id)
            }
            else {
                render(view: "edit", model: [typeInstance: typeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def typeInstance = Type.get(params.id)
        if (typeInstance) {
            try {
                typeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'type.label', default: 'Type'), params.id])}"
            redirect(action: "list")
        }
    }
}
