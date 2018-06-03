package com.braismoure.cantinamoseisley.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.braismoure.cantinamoseisley.R
import com.braismoure.cantinamoseisley.adapter.MenuListViewAdapter
import com.braismoure.cantinamoseisley.model.Restaurant
import com.braismoure.cantinamoseisley.model.Table
import com.braismoure.cantinamoseisley.model.Tables
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity() {

    private val REQUEST_MENU_DETAIL = 1
    private lateinit var table: Table

    companion object {

        private val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)
        table = Tables.getTable(tableIndex)

        title = String.format(getString(R.string.table_number), table.number)

        add_button.setOnClickListener(View.OnClickListener {

            val intent = MenusActivity.intent(this, tableIndex)
            startActivityForResult(intent, REQUEST_MENU_DETAIL)
        })

        loadTable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.table_menu, menu)
        return true
    }

    @SuppressLint("RestrictedApi")
    private fun loadTable() {

        val sessionMenus = Restaurant.getMenus(table)

        val adapter = MenuListViewAdapter(this, table.getMenus())
        table_menus_list.adapter = adapter
        table_menus_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    view,
                    getString(R.string.transition_to_detail)
            )

            val menu = table.getMenus()[position]
            startActivityForResult(MenuDetailActivity.intent(this, table.number - 1, menu.number - 1, true, sessionMenus[position].notes, position), REQUEST_MENU_DETAIL, animationOptions.toBundle())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
            R.id.menu_calculate -> {

                var total: Double = 0.0
                for(menu in table.getMenus()) {
                    total += menu.price
                }
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Total:")
                builder.setMessage(String.format(getString(R.string.menu_credits), total.toString()))
                builder.setPositiveButton("Ok"){dialog, which ->
                    // Do nothing
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.menu_delete -> {
                table.removeMenus()
                loadTable()
                val parentLayout = findViewById<View>(android.R.id.content)
                Snackbar.make(parentLayout, "Se han borrado todos los platos", Snackbar.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_MENU_DETAIL -> {
                if (resultCode == Activity.RESULT_OK) {
                    loadTable()
                }
            }
        }
    }

}
