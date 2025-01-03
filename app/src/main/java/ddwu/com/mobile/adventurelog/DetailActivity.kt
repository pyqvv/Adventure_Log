package ddwu.com.mobile.adventurelog

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import ddwu.com.mobile.adventurelog.data.AdventureRecord
import ddwu.com.mobile.adventurelog.data.AppDatabase
import ddwu.com.mobile.adventurelog.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record = intent.getSerializableExtra("record") as? AdventureRecord
        record?.let {
            binding.tvDate.text = it.date
            binding.tvLocation.text = it.location
            binding.tvMission.text = it.mission
            binding.tvMessage.text = it.message
            Glide.with(this).load(it.imageUri).into(binding.ivDetailImage)
        }

        binding.btnDeleteRecord.setOnClickListener {
            record?.let { recordToDelete ->
                AlertDialog.Builder(this)
                    .setTitle("삭제 확인")
                    .setMessage("정말 삭제하시겠습니까?")
                    .setPositiveButton("삭제") { _, _ ->
                        lifecycleScope.launch {
                            AppDatabase.getDatabase(this@DetailActivity).recordDao().delete(recordToDelete)
                            Toast.makeText(this@DetailActivity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()

                            // 결과 전달
                            setResult(RESULT_OK)
                            finish() // 이전 화면으로 돌아가기
                        }
                    }
                    .setNegativeButton("취소", null)
                    .show()
            }
        }

        binding.btnShare.setOnClickListener {
            val locationText = binding.tvLocation.text.toString()
            val missionText = binding.tvMission.text.toString()
            val shareMessage = "\'$locationText'에서 \'$missionText\' 미션 성공!"

            val drawable = binding.ivDetailImage.drawable
            if (drawable != null) {
                val bitmap = (drawable as BitmapDrawable).bitmap
                val imageFile = saveImageToCache(bitmap) // 캐시에 이미지 저장
                if (imageFile != null) {
                    val uri = FileProvider.getUriForFile(
                        this,
                        "$packageName.fileprovider",
                        imageFile
                    )

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "image/*"
                        putExtra(Intent.EXTRA_STREAM, uri) // 이미지 URI 추가
                        putExtra(Intent.EXTRA_TEXT, shareMessage) // 텍스트 추가
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // URI 읽기 권한 추가
                    }
                    startActivity(Intent.createChooser(shareIntent, "공유하기"))
                } else {
                    Toast.makeText(this, "이미지를 공유할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "이미지가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImageToCache(bitmap: Bitmap): File? {
        return try {
            val cachePath = File(cacheDir, "images")
            cachePath.mkdirs() // 디렉토리 생성
            val file = File(cachePath, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}