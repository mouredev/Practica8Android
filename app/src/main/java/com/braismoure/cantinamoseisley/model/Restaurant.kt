package com.braismoure.cantinamoseisley.model

/**
 * Created by MoureDev by Brais Moure on 3/6/18.
 * www.mouredev.com
 */

object Restaurant {

    // Sesión del restaurante. Mapa con índice de mesa y listado de comida
    private var session: MutableMap<Int, MutableList<SessionMenu>> = hashMapOf()

    fun addMenu(table: Table, menu: Menu, notes: String) {
        table.addMenu(menu)
        if(!session.containsKey(table.number)) {
            session[table.number] = arrayListOf()
        }
        session[table.number]?.add(SessionMenu(menu, notes))
    }

    fun removeMenu(table: Table, menuIndex: Int) {
        table.getMenus().removeAt(menuIndex)
        session[table.number]!!.removeAt(menuIndex)
    }

    fun removeMenus(table: Table) {
        table.removeMenus()
        session.remove(table.number)
    }

    fun getMenus(table: Table): List<SessionMenu> {
        if(session.containsKey(table.number) && !session[table.number]!!.isEmpty()) {
            return session[table.number]!!
        }
        return arrayListOf()
    }

}

// Wrapper de meús para fijar notas del mismo
data class SessionMenu(private val menu: Menu, var notes: String)