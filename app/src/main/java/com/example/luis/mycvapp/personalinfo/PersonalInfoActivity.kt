package com.example.luis.mycvapp.personalinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.common.ViewModelFactory
import com.example.luis.mycvapp.dagger.Injectable
import com.example.luis.mycvapp.databinding.ActivityPersonalInfoBinding
import com.example.luis.mycvapp.personalinfo.viewmodel.PersonalInfoViewModel
import kotlinx.android.synthetic.main.activity_personal_info.*
import javax.inject.Inject
import android.content.Intent
import android.provider.MediaStore
import android.app.Activity
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.DatePickerDialog
import com.karumi.dexter.Dexter
import android.net.Uri
import android.os.Environment
import android.view.MenuItem
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.luis.mycvapp.BuildConfig
import com.example.luis.mycvapp.common.Resource
import com.karumi.dexter.listener.PermissionRequest
import org.joda.time.LocalDate
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class PersonalInfoActivity : AppCompatActivity(),Injectable, DatePickerDialog.OnDateSetListener{
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var personalInfoViewModel: PersonalInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPersonalInfoBinding>(this,R.layout.activity_personal_info)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        personalInfoViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PersonalInfoViewModel::class.java)

        binding.activity = this
        binding.vm = personalInfoViewModel
        binding.lifecycleOwner = this

        personalInfoViewModel.getResponse().observe(this, Observer {
            when (it!!.status) {
                Resource.ResourceState.ERROR -> {

                }
                Resource.ResourceState.COMPLETED -> {
                    Toast.makeText(this,"Saved!",Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        })

        personalInfoViewModel.pictureImageUri.observe(this, Observer {
            Glide.with(this).load(Uri.parse(it)).
                apply( RequestOptions().centerCrop().circleCrop()
                    .placeholder(R.drawable.ic_perfil_sinfoto))
                .into(profileImage)
        })

        profileImage.setOnClickListener {
            dialogCameraGallery()
        }
    }

    private fun dialogCameraGallery() {
        val items = arrayOf("Camera","Gallery")

        val alertdialog = android.app.AlertDialog.Builder(this)
        alertdialog.setTitle("Add Photo")
        alertdialog.setCancelable(false)
        alertdialog.setItems(items) { dialog, item ->
            if(item == 0){
                requestStoragePermission(true)
            }else{
                requestStoragePermission(false)

            }
        }
        alertdialog.setNegativeButton(
            getString(R.string.dialog_txt_cancel)
        ) { _, _ -> }
        alertdialog.show()

    }

    private fun invokeGallery() {

        val pickPhoto = Intent(Intent.ACTION_PICK,
               MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
       pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
       startActivityForResult(pickPhoto, 1)
    }

    var photoFile: File? = null
    var uriPath:String = ""

     private fun invokeCamera(){
         val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            try {
                photoFile = createImageFile()
            } catch ( ex:IOException) {
                ex.printStackTrace()
            }
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                    photoFile!!
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, 2)

            }
        }
     }

    private fun createImageFile():File {

         val  timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format( Date())
         val mFileName = "JPEG_" + timeStamp + "_"
         val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(mFileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                1 -> {
                    val selectedImage = data!!.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                    cursor!!.moveToFirst()

                    cursor.close()
                    uriPath = selectedImage.toString()
                    Glide.with(this).load(selectedImage).apply( RequestOptions().centerCrop().circleCrop().placeholder(R.drawable.ic_perfil_sinfoto)).into(profileImage)
                }else ->{
                    uriPath = "file://"+photoFile?.absolutePath.orEmpty()
                    Glide.with(this).load(Uri.parse(uriPath))
                            .apply( RequestOptions().centerCrop().circleCrop()
                                .placeholder(R.drawable.ic_perfil_sinfoto))
                            .into(profileImage)
                }
            }
    }

    private fun requestStoragePermission(isCamera: Boolean) {

        if(isCamera) {
            Dexter.withActivity(this).withPermissions(
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE,
                permission.CAMERA
            )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            invokeCamera()
                        }
                        if (report.isAnyPermissionPermanentlyDenied) {
                            showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest>,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).withErrorListener { error ->
                    Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show()
                }
                .onSameThread()
                .check()
        }else{
            Dexter.withActivity(this).withPermissions(
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE
            )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                                invokeGallery()
                        }
                        if (report.isAnyPermissionPermanentlyDenied) {
                            showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest>,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).withErrorListener {
                    Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show()
                }
                .onSameThread()
                .check()
        }
    }


    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("SETTINGS") { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()

    }

    private fun openSettings() {
        val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    fun saveAction(){

        personalInfoViewModel.savePersonalInfo(uriPath)
    }

    fun showDatePickerDialog() {
        val today = LocalDate.now()
        val datePickerDialog = DatePickerDialog(this, this, today.year - 18, today.monthOfYear, today.dayOfMonth)
        datePickerDialog.datePicker.maxDate = today.minusYears(18).toDateTimeAtCurrentTime().millis
        datePickerDialog.show()
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, day: Int) {
        birthday.setText(LocalDate(year, month + 1, day).toString("dd-MM-yyyy"))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
