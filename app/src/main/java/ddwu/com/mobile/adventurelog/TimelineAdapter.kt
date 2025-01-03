package ddwu.com.mobile.adventurelog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ddwu.com.mobile.adventurelog.data.AdventureRecord
import ddwu.com.mobile.adventurelog.databinding.ItemTimelineBinding

class TimelineAdapter(
    private val onClick: (AdventureRecord) -> Unit,
) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    private var records: List<AdventureRecord> = listOf()

    inner class TimelineViewHolder(private val binding: ItemTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: AdventureRecord) {
            // 이미지 로드
            Glide.with(binding.root.context).load(record.imageUri).into(binding.ivThumbnail)

            // 아이템 클릭 리스너
            binding.root.setOnClickListener {
                onClick(record)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int = records.size

    // 리스트 갱신
    fun submitList(newRecords: List<AdventureRecord>) {
        records = newRecords
        notifyDataSetChanged()
    }
}
