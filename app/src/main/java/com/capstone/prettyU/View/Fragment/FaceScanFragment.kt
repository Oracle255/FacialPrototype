package com.capstone.prettyU.View.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.BackEnd.Utilities.Utils
import com.capstone.prettyU.R
import com.capstone.prettyU.View.Activity.CameraActivity
import com.capstone.prettyU.View.Activity.CameraActivity.Companion.CAMERAX_RESULT
import com.capstone.prettyU.View.ViewModel.FaceScanViewModel
import com.capstone.prettyU.View.ViewModel.ViewModelFactory
import com.capstone.prettyU.databinding.FragmentFaceScanBinding


class FaceScanFragment : Fragment() {
    private val util = Utils()
    private var _binding: FragmentFaceScanBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private lateinit var localPref: LocalPreference
    private lateinit var viewModel: FaceScanViewModel

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "permission granted", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "permission denied", Toast.LENGTH_LONG)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localPref = LocalPreference(requireContext())
        viewModel =
            ViewModelProvider(this, ViewModelFactory(localPref))[FaceScanViewModel::class.java]
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaceScanBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.btnTakePicture.setOnClickListener {
            startCamera()
        }
        binding.btnScanImage.setOnClickListener {
            predict()
        }
        binding.btnCropImage.isEnabled = false

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FaceScanFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        //checkImage("$currentImageUri")
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivFacePreview.setImageURI(it)
        }
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun startCamera() {
        currentImageUri = util.getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun predict() {
        val imageFIle = currentImageUri
        viewModel.uploadImage(requireContext(), imageFIle)
        observeViewModel()
    }

    private fun observeViewModel() {
        loadState(true)
        val modelResultObserver = {result: String? ->
            if (result != null) {
                binding.tvAnalysisSkinType.text = result
                if (result == "normal") {
                    Glide.with(requireContext()).load(R.drawable.ic_skintype_normal).into(binding.ivSkinPreview)
                }
                else if (result == "oily") {
                    Glide.with(requireContext()).load(R.drawable.ic_skintype_oily).into(binding.ivSkinPreview)
                }
                else if (result == "dry") {
                    Glide.with(requireContext()).load(R.drawable.ic_skintype_dry).into(binding.ivSkinPreview)
                }

            }
        }

        val modelExplanationObserver = {message: String? ->
            if (message != null) {

            }
        }

        val modelSuggestionObserver = {message: String? ->
            if (message != null) {
                binding.tvAnalysisSuggestion.text = message
            }
        }

        val modelConfidenceScoreObserver = {value: Double? ->
            if (value != null) {
                binding.tvAnalysisScore.text = "${value.toInt()}%"
                loadState(false)
            }
        }

        val createdAtObserver = {message: String? ->
            if (message != null) {

            }
        }


        val errorMessageObserver = { message: String? ->
            if (message != null) {
                Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage() // bugfix pesan error tidak update
                viewModel.errorMessage.removeObservers(this)
                loadState(false)
            }
        }

        val resultObserver = {result: String ->
            Toast.makeText(requireActivity().applicationContext, result, Toast.LENGTH_SHORT).show()
        }

        viewModel.listResul.observe(viewLifecycleOwner) {result ->
            //binding.lbAnalysisResult.text = result.toString()
        }
        viewModel.predictResult.observe(viewLifecycleOwner, resultObserver)
        viewModel.errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.modelResult.observe(viewLifecycleOwner, modelResultObserver)
        viewModel.modelExplanation.observe(viewLifecycleOwner, modelExplanationObserver)
        viewModel.modelSuggestion.observe(viewLifecycleOwner, modelSuggestionObserver)
        viewModel.modelConfidenceScore.observe(viewLifecycleOwner, modelConfidenceScoreObserver)
        viewModel.createdAt.observe(viewLifecycleOwner, createdAtObserver) // STUB buat fungsi save history
    }

    private fun loadState(state: Boolean) {
        binding.progressBar.isVisible = state
    }

}