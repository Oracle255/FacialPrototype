package com.capstone.prettyU.View.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.BackEnd.Utilities.Utils
import com.capstone.prettyU.R
import com.capstone.prettyU.View.Activity.CameraActivity
import com.capstone.prettyU.View.Activity.CameraActivity.Companion.CAMERAX_RESULT
import com.capstone.prettyU.View.ViewModel.FaceScanViewModel
import com.capstone.prettyU.View.ViewModel.RegisterViewModel
import com.capstone.prettyU.View.ViewModel.ViewModelFactory
import com.capstone.prettyU.databinding.FragmentFaceScanBinding
import com.capstone.prettyU.databinding.FragmentMainPageBinding
import com.yalantis.ucrop.UCrop
import java.io.File
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FaceScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FaceScanFragment : Fragment() {
    private val util = Utils()
    private var _binding: FragmentFaceScanBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private lateinit var localPref: LocalPreference
    private lateinit var viewModel: FaceScanViewModel


    private var param1: String? = null
    private var param2: String? = null

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

//        viewModel =
//            ViewModelProvider(this)[FaceScanViewModel::class.java]
        viewModel =
            ViewModelProvider(this, ViewModelFactory(localPref))[FaceScanViewModel::class.java]
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaceScanBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.btnTakePicture.setOnClickListener {
            startCamera()
            //startCameraX()
        }
        binding.btnScanImage.setOnClickListener {
            predict()
        }

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
        //binding.tvAnalysisSkinType.text = viewModel.predictResult.toString()
        //viewModel.predictResult.observe(requireContext())
    }

    private fun observeViewModel() {
        val errorMessageObserver = { message: String? ->
            if (message != null) {
//                simpleDialogBuilder(this@LoginActivity, "Error", message)
//                loadBar(false)
                viewModel.clearErrorMessage() // bugfix pesan error tidak update
                viewModel.errorMessage.removeObservers(this)
            }
        }

        val resultObserver = {result: String ->
            binding.tvAnalysisSkinType.text = result
            Toast.makeText(requireActivity().applicationContext, result, Toast.LENGTH_SHORT).show()

            //viewModel.registerResult.removeObserver(this)
        }
        viewModel.predictResult.observe(viewLifecycleOwner, resultObserver)
        //Toast.makeText()
    }

    // TODO(konversi kode original activity ke fragment)
//    private fun cropImage(aspectRatioX: Float, aspectRatioY: Float) {
//        currentImageUri?.let { inputUri ->
//            val cropName = Random.nextLong(0, 1000)
//            val outputFileName = "IMG${cropName}.jpg"
//            val outputUri = Uri.fromFile(File(cacheDir, outputFileName))
//            UCrop.of(inputUri, outputUri)
//                .withAspectRatio(aspectRatioX, aspectRatioY)
//                .withOptions(UCrop.Options())
//                .withMaxResultSize(1000, 1000)
//                .start(this@MainActivity)
//        }
//    }


}