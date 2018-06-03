package com.braismoure.cantinamoseisley.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.model.Menu

/**
 * Created by MoureDev by Brais Moure on 3/6/18.
 * www.mouredev.com
 */

class MenuListViewAdapter(private val context: Context, private val menus: List<Menu>): BaseAdapter() {

    override fun getCount(): Int {
        return menus.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return menus[position]
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        var viewHolder: MenuViewHolder?
        var view: View?

        if (convertView == null) {
            view = View.inflate(context, R.layout.content_menu, null)
            viewHolder = MenuViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as MenuViewHolder
            view = convertView
        }

        val menu = getItem(position) as Menu

        viewHolder?.nameTextView?.text = menu.title
        viewHolder?.imageView?.setImageResource(menu.photoSrc)
        viewHolder?.creditsTextView?.text = String.format(context.getString(R.string.menu_credits), menu.price.toString())

        // Soporte para 2 alérgenos. Por supuesto, podría mejorarse esta implementación
        viewHolder?.allergen1ImageView?.visibility = View.INVISIBLE
        viewHolder?.allergen2ImageView?.visibility = View.INVISIBLE
        menu.allergens?.let { allergens ->
            for ((index, allergen) in allergens.withIndex()) {
                if(index == 0) {
                    viewHolder?.allergen1ImageView?.setImageResource(allergen.icoSrc)
                    viewHolder?.allergen1ImageView?.visibility = View.VISIBLE
                } else if(index == 1) {
                    viewHolder?.allergen2ImageView?.setImageResource(allergen.icoSrc)
                    viewHolder?.allergen2ImageView?.visibility = View.VISIBLE
                }
            }
        }

        return view!!
    }

    inner class MenuViewHolder(itemView: View) {

        val nameTextView = itemView.findViewById<TextView>(R.id.menuNameTextView)
        val imageView = itemView.findViewById<ImageView>(R.id.menuImageView)
        val creditsTextView = itemView.findViewById<TextView>(R.id.menuCreditsTextView)
        val allergen1ImageView = itemView.findViewById<ImageView>(R.id.allergen1ImageView)
        val allergen2ImageView = itemView.findViewById<ImageView>(R.id.allergen2ImageView)

        //private val context = itemView.context

    }

}