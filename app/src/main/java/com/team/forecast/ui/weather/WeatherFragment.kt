package com.team.forecast.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.team.core.utils.NetworkState
import com.team.core.utils.network.NetworkUtils
import com.team.forecast.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detectNetworkState()
        callWeatherApi()
        observeData()
    }

    private fun callWeatherApi() {
        if (NetworkUtils.getNetworkUtils().isConnected()) {
            weatherViewModel.getWeather("cairo")
        } else {
            weatherViewModel.getCachedWeather("cairo")
        }
    }

    private fun observeData() {
        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, { weather ->
            Log.e("TAG", "observeData: ${weather.city}")
        })
    }

    private fun detectNetworkState() {
        weatherViewModel.getNetworkState().observe(viewLifecycleOwner, Observer {
            if (it == NetworkState.LOADING) {
                binding.pbWeather.visibility = View.VISIBLE

                if (it == NetworkState.ERROR) {
                    binding.pbWeather.visibility = View.GONE
                }
            } else {
                binding.pbWeather.visibility = View.GONE
            }
        })

        weatherViewModel.getError().observe(viewLifecycleOwner, Observer {
            binding.pbWeather.visibility = View.GONE
        })
    }
}