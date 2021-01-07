package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { onPortalClick(it) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeAddReminderResult()
    }

    private fun initView() {

        portalRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        portalRecyclerView.adapter = portalAdapter

    }

    private fun onPortalClick(portal: Portal){

        val builder = CustomTabsIntent.Builder()
        builder.addDefaultShareMenuItem()
        builder.setShowTitle(true)
        val customTabsIntent = builder.build();

        // Launch url if it's a valid url.
        if (URLUtil.isValidUrl(portal.portalUrl)) {

            customTabsIntent.launchUrl(
                requireContext(),
                Uri.parse(portal.portalUrl)
            )
        }
        else Toast.makeText(context, "Invalid Url: ${portal.portalUrl}", Toast.LENGTH_SHORT).show()
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key , bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.portalName, it.portalUrl)
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal text!")

        }
    }
}