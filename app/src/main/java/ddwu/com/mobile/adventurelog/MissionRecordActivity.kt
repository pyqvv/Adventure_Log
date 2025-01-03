package ddwu.com.mobile.adventurelog

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ddwu.com.mobile.adventurelog.data.AdventureRecord
import ddwu.com.mobile.adventurelog.data.AppDatabase
import ddwu.com.mobile.adventurelog.data.Mission
import ddwu.com.mobile.adventurelog.databinding.MissionRecordBinding
import kotlinx.coroutines.launch
import java.time.LocalDate

class MissionRecordActivity : AppCompatActivity() {
    private val binding by lazy {
        MissionRecordBinding.inflate(layoutInflater)
    }

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var selectedImageUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentDate = LocalDate.now().toString()

        val locationName = intent.getStringExtra("locationName")
        val currentMission = intent.getSerializableExtra("currentMission") as? Mission

        binding.tvDate.text = "$currentDate"
        binding.tvMissionLocation.text = locationName
        if (currentMission != null) {
            binding.tvMissionName.text = currentMission.description
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                if (selectedImageUri != null) {
                    binding.ivAttachImage.setImageURI(selectedImageUri)
                } else {
                    Toast.makeText(this, "이미지를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.ivAttachImage.setOnClickListener {
            openDownloadFolder()
        }

        binding.btnSaveRecord.setOnClickListener {
            val message = binding.etMessage.text.toString()
            val date = LocalDate.now().toString()
            val imageUri = selectedImageUri.toString()
            val location = binding.tvMissionLocation.text.toString()
            val mission = binding.tvMissionName.text.toString()

            if (message.isBlank() || imageUri == null || location.isBlank() || mission.isBlank()) {
                Toast.makeText(this, "모든 항목을 입력하세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val record = AdventureRecord(date = date, imageUri = imageUri, location = location, mission = mission, message = message)

            lifecycleScope.launch {
                AppDatabase.getDatabase(this@MissionRecordActivity).recordDao().insertRecord(record)
            }
            val intent = Intent(this, TimelineActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openDownloadFolder() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        imagePickerLauncher.launch(intent)
    }

}
