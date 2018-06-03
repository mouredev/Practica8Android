package com.braismoure.cantinamoseisley.model

import com.braismoure.cantinamoseisley.R

/**
 * Created by MoureDev by Brais Moure on 3/6/18.
 * www.mouredev.com
 */


object Menus {

    private val menus: List<Menu> = listOf(
            Menu(1, "Pastel de Sarlacc", "Tanspórtate al gran pozo de Carkoon y venga al pobre Boba Fett. ¡Ahora eres tú el que come!\n\nIngredientes: Chocolate, Fresas, Harina de trigo, Huevo.",
                    R.drawable.menu_1, listOf(Menu.Allergen("Harina de trigo", R.drawable.alergeno_1)), 200.0),
            Menu(2, "Sandwich \"Ham\" (Jamón) Solo", "¡La verdadera comida de los contrabandistas!\n\nIngredientes: Pan, Jamón, Queso, Zanahorias.", R.drawable.menu_2, null, 120.0),
            Menu(3, "Pastel cremoso R2-A6", "Pastel cremoso con bacon y ajo. No apto para droides.\n\nIngredientes: Queso cremoso, Bacon, Ajo, Patatas.", R.drawable.menu_3, null, 150.0),
            Menu(4, "Bocaditos de Jabba el Hutt", "Este lord del crimen ahora es blandito y sabroso.\n\nIngredientes: Gelatina, Azúcar, Vainilla.", R.drawable.menu_4, null, 50.0),
            Menu(5, "Leche azul", "¡La mítica leche azul!\n\nIngredientes: Leche, ¿¿¿???.", R.drawable.menu_5, listOf(Menu.Allergen("Leche", R.drawable.alergeno_2)), 100.0),
            Menu(6, "Bolita de arroz Stormtrooper", "Estas sí son las bolas de arroz que estabas buscando, aunque desconocemos si los propios Stormtrooper las comen.\n\nIngredientes: Arroz, Salmón, Alga.", R.drawable.menu_6,null, 180.0),
            Menu(7, "Potaje de Yoda", "Ya no te tienes que estrellar en Dagobah con tu X-Wing para probar esta delicia.\n\nIngredientes: Hojas, Ajo, Patata, ¿Fuerza?.", R.drawable.menu_7, null, 10000.0),
            Menu(8, "Galletas Rebeldes Leia", "Para combatir al imperio lo primero es desayunar.\n\nIngredientes: Azúcar, Huevo, Leche, Harina de trigo.", R.drawable.menu_8,
                    listOf(Menu.Allergen("Harina de trigo", R.drawable.alergeno_1), Menu.Allergen("Leche", R.drawable.alergeno_2)), 80.0),
            Menu(9, "Alitas de Porg", "Porgs recién cazados (esta vez sí) por Chewbacca.\n\nIngredientes: Porg.", R.drawable.menu_9, null, 10.0),
            Menu(10, "Hamburguesas de Bantha", "Bantha recién hecho al estilo espada Láser.\n\nIngredientes: Bantha, Pan, Lechuga, Tomate, Cebolla.", R.drawable.menu_10, null, 300.0),
            Menu(11, "Darth Vader Dark Cookies", "Las galletas con el reverso más tenebrono y el chocolate más oscuro.\n\nIngredientes: Chocolate, Lado oscuro.", R.drawable.menu_11, null, 66.66),
            Menu(12, "Tacos Grand Moff", "Porque dirigir un destructor Imperial da hambre...\n\nIngredientes: Pan, Lechuga, Queso, Mano firme.", R.drawable.menu_12, null, 250.0)
    )

    val count get() = menus.size

    fun getMenu(index: Int) = menus[index]

    fun getIndex(menu: Menu) = menus.indexOf(menu)

    fun list() = menus

}