package ddwu.com.mobile.adventurelog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.com.mobile.adventurelog.data.AppDatabase
import ddwu.com.mobile.adventurelog.databinding.ActivityTimelineBinding
import kotlinx.coroutines.launch

class TimelineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimelineBinding
    private lateinit var adapter: TimelineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimelineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TimelineAdapter { record ->
            // content:// URI를 그대로 사용하여 Intent 전달
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("record", record) // record 객체를 그대로 전달
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // URI 권한 부여
            }
            startActivity(intent)
        }

        binding.rvTimeline.layoutManager = GridLayoutManager(this, 2) // 열 개수를 2로 설정
        binding.rvTimeline.adapter = adapter

        observeRecords()
    }

    private fun observeRecords() {
        val recordDao = AppDatabase.getDatabase(this).recordDao()

        // Flow 관찰
        lifecycleScope.launch {
            recordDao.getAllRecords().collect { records ->
                adapter.submitList(records)
            }
        }
    }

    // 뒤로 가기 버튼 눌렀을 때 MainActivity로 이동
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}
