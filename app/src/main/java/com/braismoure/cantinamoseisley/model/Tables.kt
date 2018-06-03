package com.braismoure.cantinamoseisley.model

/**
 * Created by MoureDev by Brais Moure on 2/6/18.
 * www.mouredev.com
 */

object Tables {

    private val tables: List<Table> = listOf(
            Table(1, "Mesa de Luke y Ben"),
            Table(2, "Mesa de Han y Chewbacca"),
            Table(3, "Mesa de Greedo"),
            Table(4, "Mesa del Dr. Evazan y Ponda Baba"),
            Table(5, "Mesa de Figrin D'an y los Modal Nodes"),
            Table(6, "Mesa de Jabba El Hutt"),
            Table(7, "Mesa de Wuher"),
            Table(8, "Mesa de Chalmun"),
            Table(9, "Mesa de ¿Trancos?... Creo que no era esta cantina"),
            Table(10, "Mesa de R2-D2 y C-3PO ¿Cómo han entrado?")
    )

    val count get() = tables.size

    fun getTable(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    //operator fun get(index: Int) = tables[index]

    //fun toArray() = tables.toTypedArray()

    fun list() = tables

}