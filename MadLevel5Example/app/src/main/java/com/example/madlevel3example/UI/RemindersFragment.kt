package com.example.madlevel3example.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3example.Model.Reminder
import com.example.madlevel3example.R
import com.example.madlevel3example.Repository.ReminderRepository
import com.example.madlevel3example.ViewModel.ReminderViewModel
import kotlinx.android.synthetic.main.fragment_reminders.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RemindersFragment : Fragment() {

//    private lateinit var reminderRepository: ReminderRepository
    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter =
        ReminderAdapter(reminders)

    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        reminderRepository =
//            ReminderRepository(
//                requireContext()
//            )
//        getRemindersFromDatabase()

        initView()
        observeAddReminderResult()
    }

    private fun initView() {

        remindersRecycleview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        remindersRecycleview.adapter = reminderAdapter
        remindersRecycleview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        onLeftSwipe().attachToRecyclerView(remindersRecycleview)
    }


//    private fun getRemindersFromDatabase() {
//        CoroutineScope(Dispatchers.Main).launch {
//            val reminders = withContext(Dispatchers.IO) {
//                reminderRepository.getAllReminders()
//            }
//            this@RemindersFragment.reminders.clear()
//            this@RemindersFragment.reminders.addAll(reminders)
//            reminderAdapter.notifyDataSetChanged()
//        }
//    }
//
    private fun observeAddReminderResult() {
        viewModel.reminders.observe(viewLifecycleOwner, Observer {
            reminders ->
            this@RemindersFragment.reminders.clear()
            this@RemindersFragment.reminders.addAll(reminders)
            reminderAdapter.notifyDataSetChanged()
        })
    }

//    private fun observeAddReminderResult() {
//        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
//            bundle.getString(BUNDLE_REMINDER_KEY)?.let {
//                val reminder = Reminder(it)
//
//                CoroutineScope(Dispatchers.Main).launch {
//                    withContext(Dispatchers.IO) {
//                        reminderRepository.insertReminder(reminder)
//                    }
//                }
//                getRemindersFromDatabase()
//
//            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")
//
//        }
//    }

    private fun onLeftSwipe() : ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                CoroutineScope(Dispatchers.Main).launch {
//                    withContext(Dispatchers.IO) {
//                        reminderRepository.deleteReminder(reminders[position])
//                    }
//                }
//
//                getRemindersFromDatabase()
                viewModel.deleteReminder(reminders[position])
            }
        }
        return ItemTouchHelper(callback)
    }
}