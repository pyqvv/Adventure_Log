package ddwu.com.mobile.adventurelog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.adventurelog.data.Mission
import ddwu.com.mobile.adventurelog.databinding.PerformMissionBinding


class PerformMissionActivity : AppCompatActivity() {
    private val binding by lazy {
        PerformMissionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedAddress = intent.getStringExtra("selectedAddress")
        val selectedLocation = intent.getStringExtra("selectedLocation")
        val selectedTelNo = intent.getStringExtra("selectedTelNo")
        val selectedDesc = intent.getStringExtra("selectedDesc")
        val currentMission = intent.getSerializableExtra("currentMission") as? Mission

        if (currentMission != null) {
            binding.tvMissionTitle.text = "${currentMission.description}"
        }

        runOnUiThread {
            binding.tvLocationAddress.text = selectedAddress
            if (currentMission != null) {
                when (currentMission.category) {
                    "카페" -> binding.imageView.setImageResource(R.drawable.cafe_logo)
                    "식당" -> binding.imageView.setImageResource(R.drawable.dinner_logo)
                    "도서관" -> binding.imageView.setImageResource(R.drawable.library_logo)
                    "공원" -> binding.imageView.setImageResource(R.drawable.park_logo)
                    else -> binding.imageView.setImageResource(R.drawable.adventure_log_logo)
                }
            }
            binding.tvLocationName.text = selectedLocation
            if (selectedTelNo != null) {
                binding.tvLocationTel.text = selectedTelNo
            }
            if (selectedDesc != null) {
                binding.tvLocationDetail.text = selectedDesc
            }
        }

        // 미션 완료 버튼 클릭
        binding.btnCompleteMission.setOnClickListener {
            if (currentMission != null) {
                val intent = Intent(this, MissionRecordActivity::class.java).apply {
                    putExtra("locationName", selectedLocation)
                    putExtra("currentMission", currentMission)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "현재 미션이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 미션 포기 버튼 클릭
        binding.btnCancelMission.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}