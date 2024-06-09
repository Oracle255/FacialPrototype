package com.capstone.prettyU.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.prettyU.BackEnd.Utilities.Constant.DevTestConfig
import com.capstone.prettyU.R
import com.capstone.prettyU.View.adapter.ItemData.TreatmentData
import com.capstone.prettyU.View.adapter.RvAdapterHorizontal
import com.capstone.prettyU.databinding.FragmentMainPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val rvItem = binding.rvItem

        rvItem.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // Set horizontal orientation

        // TODO: (SET ONCLICK LISTENER) & Mock data json api
        val dataList = listOf(
            TreatmentData(R.drawable.ic_placeholder, "wdqwdqwdqwdqwdqw", "${DevTestConfig.loremWithLength(12)}"),
            TreatmentData(R.drawable.ic_placeholder, "${DevTestConfig.loremWithLength(1)}", "${DevTestConfig.loremWithLength(12)}"),
            TreatmentData(R.drawable.ic_placeholder, "${DevTestConfig.loremWithLength(1)}", "${DevTestConfig.loremWithLength(12)}")
        )

        val adapter = RvAdapterHorizontal(dataList)
        rvItem.adapter = adapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    // TODO: set animasi
    private fun playAnimation() {

    }
}