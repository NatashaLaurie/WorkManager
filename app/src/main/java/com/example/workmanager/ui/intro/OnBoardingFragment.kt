package com.example.workmanager.ui.intro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.workmanager.R
import com.example.workmanager.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    private val onBoardingSliderAdapter = OnBoardingSliderAdapter(
        listOf(
            OnBoardingSlide(
                R.string.slider1_title,
                R.string.slider1_description,
                R.drawable.ic_slider1,
            ),
            OnBoardingSlide(
                R.string.slider2_title,
                R.string.slider2_description,
                R.drawable.ic_slider2,
            ),
            OnBoardingSlide(
                R.string.slider3_title,
                R.string.slider3_description,
                R.drawable.ic_slider3,
            )

        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = onBoardingSliderAdapter
        binding.indicator.setViewPager(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    when (position) {
                        0 -> binding.ivIntroBack.setImageResource(R.drawable.ic_slider1_background)
                        1 -> binding.ivIntroBack.setImageResource(R.drawable.ic_slider2_background)
                        2 -> binding.ivIntroBack.setImageResource(R.drawable.ic_slider3_background)
                    }
                }
            }
        )
        val animation = AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.app_name_animation
        )
        binding.btnGetStarted.animation = animation
        binding.btnGetStarted.setOnClickListener {
            onBoardingFinished()
            findNavController().navigate(R.id.action_onBoardingFragment_to_welcomeFragment)
        }
    }

    private fun onBoardingFinished() {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}