package ddwu.com.mobile.adventurelog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.adventurelog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Start button click listener
        binding.btnGenerateMission.setOnClickListener {
            val intent = Intent(this, MissionMapActivity::class.java)
            startActivity(intent)
        }

        // Timeline button click listener
        binding.btnTimeline.setOnClickListener {
            val intent = Intent(this, TimelineActivity::class.java)
            startActivity(intent)
        }
    }
}