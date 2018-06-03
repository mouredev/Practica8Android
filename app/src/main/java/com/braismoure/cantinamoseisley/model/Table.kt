package com.braismoure.cantinamoseisley.model

/**
 * Created by MoureDev by Brais Moure on 2/6/18.
 * www.mouredev.com
 */

/**
 * Modelo para mesas del restaurante.
 */
data class Table(val number: Int, val name: String) {

    private var menus: MutableList<Menu> = mutableListOf()

    fun addMenu(menu: Menu) {
        menus.add(menu)
    }

    fun removeMenus() {
        menus = mutableListOf()
    }

    fun getMenus() = menus

}