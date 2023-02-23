package com.akrio.pagertest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.akrio.pagertest.FirstFragment.Companion.NOTIFICATION_CHANNEL_ID
import com.akrio.pagertest.FirstFragment.Companion.NOTIFICATION_ID
import com.akrio.pagertest.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var manager: NotificationManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        val notification =
            NotificationCompat.Builder(requireContext(), NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.baseline_car_repair_24)
                .setContentTitle("Test notification")
                .setContentText("You are notified")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

        manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.btnFirst.setOnClickListener {
            createNotificationChannel()
            manager.notify(NOTIFICATION_ID, notification)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = NOTIFICATION
            manager.createNotificationChannel(channel)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "101"
        private const val NOTIFICATION_ID = 100
        private const val NOTIFICATION_CHANNEL_NAME = "PagerTest notification"
        private const val NOTIFICATION = "Notify the user when button is pressed"
        fun newInstance() = FirstFragment()

    }

    //change for commit

}