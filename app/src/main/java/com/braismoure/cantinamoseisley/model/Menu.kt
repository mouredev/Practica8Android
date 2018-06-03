package com.braismoure.cantinamoseisley.model

/**
 * Created by MoureDev by Brais Moure on 2/6/18.
 * www.mouredev.com
 */

/**
 * Modelo para platos de la carta del restaurante.
 */
data class Menu(val number: Int, val title: String, val description: String, val photoSrc: Int, var allergens: List<Allergen>?, val price: Double) {


    /**
     * Modelo para alérgenos que puede contener cada plato de la carta del restaurante.
     */
    data class Allergen(val name: String, val icoSrc: Int)

}
