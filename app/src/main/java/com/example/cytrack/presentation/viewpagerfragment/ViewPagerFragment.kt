package com.example.cytrack.presentation.viewpagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cytrack.R
import com.example.cytrack.presentation.schedulefragment.ScheduleFragment
import com.example.cytrack.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    private lateinit var binding: FragmentViewPagerBinding
    private val gameList = listOf("csgo", "dota2", "valorant")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = gameList.size

            override fun createFragment(position: Int) =
                ScheduleFragment.newInstance(gameList[position])
        }

        binding.viewPager.adapter = pagerAdapter
    }

    companion object {
        fun newInstance() = ViewPagerFragment()
    }
}
