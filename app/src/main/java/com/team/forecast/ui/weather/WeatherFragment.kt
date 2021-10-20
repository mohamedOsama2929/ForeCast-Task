package com.team.forecast.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.team.core.utils.NetworkState
import com.team.core.utils.network.NetworkUtils
import com.team.entities.weather.remote.response.ListItem
import com.team.forecast.R
import com.team.forecast.databinding.FragmentWeatherBinding
import com.team.forecast.ui.weather.adapters.WeatherRecycleViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.app.Activity
import android.view.inputmethod.InputMethodManager


@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var weatherRecycleViewAdapter: WeatherRecycleViewAdapter

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
        showEnterDataLayout()
        detectNetworkState()
        prepareView()
        observeData()


    }

    private fun prepareView() {
        binding.btnSearch.setOnClickListener {
            hideKeyboard(requireActivity())
            callWeatherApi(binding.editTextCityName.text?.trim().toString())
            binding.editTextCityName.setText("")

        }
        binding.btnRetry.setOnClickListener {
            callWeatherApi(binding.editTextCityName.text?.trim().toString())
        }
    }

    private fun callWeatherApi(cityName: String) {
        if (cityName.isNotEmpty()) {
            if (NetworkUtils.getNetworkUtils().isConnected()) {
                weatherViewModel.getWeather(cityName)
            } else {
                weatherViewModel.getCachedWeather(cityName)
            }
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.enter_city_name_toast),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun observeData() {
        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, { weather ->
            // TODO: 8/28/2021 send data to rv
            showWeathersLayout()
            binding.shimmerFrameLayout.stopShimmer()
            binding.shimmerFrameLayout.visibility=View.GONE
            binding.rvWeathers.visibility=View.VISIBLE
            weatherRecycleViewAdapter.setData(weather.list as MutableList<ListItem>)

        })
    }

    private fun detectNetworkState() {
        weatherViewModel.getNetworkState().observe(viewLifecycleOwner, Observer {
            if (it == NetworkState.LOADING) {
                //binding.pbWeather.visibility = View.VISIBLE

                binding.shimmerFrameLayout.visibility=View.VISIBLE


                if (it == NetworkState.ERROR) {
                   // binding.pbWeather.visibility = View.GONE
                    binding.shimmerFrameLayout.visibility=View.GONE

                    showErrorLayout()

                }
            } else {
               // binding.pbWeather.visibility = View.GONE
                binding.shimmerFrameLayout.visibility=View.GONE


            }
        })

        weatherViewModel.getError().observe(viewLifecycleOwner, Observer {
           // binding.pbWeather.visibility = View.GONE
            binding.shimmerFrameLayout.visibility=View.GONE

            showErrorLayout()
        })
    }

    private fun showErrorLayout() {
        binding.layoutError.visibility = View.VISIBLE
        binding.layoutEnterData.visibility = View.GONE
        binding.layoutShowData.visibility = View.GONE
    }

    private fun showWeathersLayout() {
        binding.layoutError.visibility = View.GONE
        binding.layoutEnterData.visibility = View.GONE
        binding.layoutShowData.visibility = View.VISIBLE
        binding.rvWeathers.adapter=weatherRecycleViewAdapter
    }

    private fun showEnterDataLayout() {
        binding.layoutError.visibility = View.GONE
        binding.layoutEnterData.visibility = View.VISIBLE
        binding.layoutShowData.visibility = View.VISIBLE
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}