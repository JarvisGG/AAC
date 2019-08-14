package jarvis.com.aac.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.*
import androidx.navigation.ui.NavigationUI
import jarvis.com.aac.R
import jarvis.com.aac.model.FeedTab
import jarvis.com.aac.utils.Resource
import jarvis.com.aac.vm.HostViewModel
import kotlinx.android.synthetic.main.bottom_nav_layout.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_host.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * @author yyf
 * @since 08-13-2019
 */
class HostFragment : NavHostFragment() {

    private lateinit var vm: HostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationWidget()
        setupVM()
        setupView()
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return FragmentNavigator(requireContext(), childFragmentManager, R.id.navHost)
    }

    /**
     * 初始化 Widget（Navigation 进行绑定）
     */
    private fun setupNavigationWidget() {
        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout)

//        NavigationUI.setupWithNavController(navView, navController)

        NavigationUI.setupWithNavController(bottomNav, navController)
    }


    private fun setupVM() {
        vm = ViewModelProviders.of(requireActivity()).get(HostViewModel::class.java)

        vm.feedTabsLiveData.observe(this, Observer {
            when(it) {
                is Resource.Success -> {
                    it.data.forEach { tab ->
                        navView.menu.add(R.id.menu_tabs_group, tab.id, tab.id, tab.title)
                    }
                    vm.drawerSelectedTab.postValue(FeedTab(it.data[0].id, it.data[0].title))
                }
                is Resource.Error -> {

                }
            }
        })
        vm.fetchFeedTabs()
    }

    private fun setupView() {
        navView.setNavigationItemSelectedListener {
            vm.drawerSelectedTab.postValue(FeedTab(it.itemId, it.title.toString()))
            drawerLayout.closeDrawer(navWrapper)
            true
        }
    }



}