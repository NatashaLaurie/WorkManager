package com.example.workmanager.ui.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workmanager.databinding.OnboardingSlideItemBinding

class OnBoardingSliderAdapter(private val onBoardingSlide: List<OnBoardingSlide>) :
    RecyclerView.Adapter<OnBoardingSliderAdapter.OnBoardingSliderViewHolder>() {


    inner class OnBoardingSliderViewHolder(private val binding: OnboardingSlideItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoardingSlide: OnBoardingSlide) {
            binding.apply {
                tvSlideTitle.setText(onBoardingSlide.titleId)
                tvSlideDescription.setText(onBoardingSlide.descriptionId)
                ivSlideIcon.setImageResource(onBoardingSlide.iconId)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingSliderViewHolder {
        return OnBoardingSliderViewHolder(
            OnboardingSlideItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingSliderViewHolder, position: Int) {
        holder.bind(onBoardingSlide[position])
    }

    override fun getItemCount(): Int {
        return onBoardingSlide.size
    }
}