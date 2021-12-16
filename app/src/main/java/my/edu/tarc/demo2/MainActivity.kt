package my.edu.tarc.demo2

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import my.edu.tarc.demo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    protected lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_add_inventory, R.id.nav_slideshow, R.id.nav_searchActivity, R.id.nav_searchFragDetails, R.id.nav_InBound
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Toggle Bar Animation
        val toggle = ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.toolbar,
        R.string.navigation_drawer_open, R.string.nav_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }


    @Override
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

//    override fun onNavigationItemSelected

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> {
//                val navController = findNavController(R.id.nav_host_fragment_content_main)
//                navController.navigate(R.id.action_nav_home_to_searchActivity) //navigate from home fragment to setting fragment
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        const val CHOICE = "my.edu.tarc.demo2.ui.search.choice"
        const val ITEM_NAME = "my.edu.tarc.demo2.ui.search.item_name"
    }


}