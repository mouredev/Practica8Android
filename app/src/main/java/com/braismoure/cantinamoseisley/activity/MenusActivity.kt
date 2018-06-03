package com.braismoure.cantinamoseisley.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.MenuItem
import android.widget.AdapterView
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.adapter.MenuListViewAdapter
import com.braismoure.cantinamoseisley.model.Menus
import kotlinx.android.synthetic.main.activity_menus.*

class MenusActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, MenusActivity::class.java)
            intent.putExtra(MenuDetailActivity.EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)

        title = getString(R.string.add_menu)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val tableIndex = intent.getIntExtra(MenuDetailActivity.EXTRA_TABLE_INDEX, 0)

        val adapter = MenuListViewAdapter(this, Menus.list())
        menus_list.adapter = adapter
        menus_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    view,
                    getString(R.string.transition_to_detail)
            )

            startActivity(MenuDetailActivity.intent(this, tableIndex, position, false, null, position), animationOptions.toBundle())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
