package ddwu.com.mobile.adventurelog

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skt.tmap.TMapData
import com.skt.tmap.TMapPoint
import com.skt.tmap.TMapView
import com.skt.tmap.overlay.TMapMarkerItem
import com.skt.tmap.poi.TMapPOIItem
import ddwu.com.mobile.adventurelog.data.Mission
import ddwu.com.mobile.adventurelog.data.MissionRepository
import ddwu.com.mobile.adventurelog.databinding.ActivityMissionMapBinding
import kotlin.random.Random

class MissionMapActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMissionMapBinding.inflate(layoutInflater)
    }
    private var randomLatitude: Double = 0.0
    private var randomLongitude: Double = 0.0
    private var todayAddress = ""

    private var madeMission = false
    private var madeLocation = false
    private var currentMission: Mission? = null  // 현재 미션 저장

    private var selectedName = ""
    private var selectedTel = ""
    private var selectedDesc = ""

    private val tMapView by lazy {
        TMapView(this)
    } // TMapView 초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // TMapView 설정
        binding.mapView.addView(tMapView)
        tMapView.setSKTMapApiKey("Q1f6uP8Jw640HpbirKSYS2aGed7qMura938zyQ6Q")

        // 지도 준비 완료 리스너
        tMapView.setOnMapReadyListener {
            // 미션 생성 버튼 클릭
            binding.btnCreateMission.setOnClickListener {
                generateRandomMission()
            }

            // 미션 위치 생성 버튼 클릭
            binding.btnMissionLocation.setOnClickListener {
                generateRandomLocation()
            }
        }
    }

    // 랜덤 미션 생성
    private fun generateRandomMission() {
        // MissionRepository에서 랜덤으로 미션 가져오기
        currentMission = MissionRepository.getRandomMission()

        // 선택된 미션을 보여줌
        showMission(currentMission!!)

        // 미션 생성 완료 후 버튼 상태 변경
        madeMission = true
        updateButtonVisibility()
    }

    private fun selectLocation () {

    }

    // 랜덤 위치 생성
    private fun generateRandomLocation() {
        // 서울의 위도 범위
        val minLatitude = 37.4133
        val maxLatitude = 37.7151

        // 서울의 경도 범위
        val minLongitude = 126.8016
        val maxLongitude = 127.1839

        // 랜덤하게 위도와 경도를 생성
        randomLatitude = Random.nextDouble(minLatitude, maxLatitude)
        randomLongitude = Random.nextDouble(minLongitude, maxLongitude)

        val randomData = TMapData()
        tMapView.setCenterPoint(randomLatitude, randomLongitude);

        // 주소 변환 비동기 처리
        randomData.convertGpsToAddress(randomLatitude, randomLongitude) { address ->
            if (address != null) {
                todayAddress = address
                binding.tvMissionTitle1.text = simpleAddress(todayAddress)
            } else {
                // 주소 변환 실패 시 처리
                Log.e("MissionMapActivity", "주소 변환 실패")
                Toast.makeText(this, "주소 변환에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 위치 생성 완료 후 버튼 상태 변경
        madeLocation = true
        updateButtonVisibility()
    }


    // 버튼 상태 변경 함수
    private fun updateButtonVisibility() {
        if (madeMission && madeLocation) {
            // 버튼 상태 변경
            binding.btnMissionLocation.visibility = View.GONE
            binding.btnCreateMission.visibility = View.GONE

            binding.spinner.visibility = View.VISIBLE
            binding.btnMissionStart.visibility = View.VISIBLE
        }
    }

    // 미션 생성 이후
    private fun showMission(mission: Mission) {
        binding.tvMissionTitle2.text = "${mission.description}" // 상단에 미션명 표시

        handleMissionType(mission)

        binding.btnMissionStart.setOnClickListener {
            if (currentMission != null) {
                val intent = Intent(this, PerformMissionActivity::class.java).apply {
                    putExtra("currentMission", currentMission)
                    putExtra("selectedLocation", selectedName)
                    putExtra("selectedAddress", todayAddress)
                    putExtra("selectedTelNo", selectedTel)
                    putExtra("selectedDesc", selectedDesc)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "현재 미션이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 미션 종류에 따른 동작
    private fun handleMissionType(mission: Mission) {
        val tMapData = TMapData()
        val currentLocation = TMapPoint(randomLatitude, randomLongitude)

        // 카테고리에 따라 동작 수행
        tMapData.findAroundNamePOI(currentLocation, mission.category) { poiItemList ->
            if (poiItemList.isNullOrEmpty()) {
                Log.e("handleMissionType", "검색된 POI가 없습니다.")
                runOnUiThread {
                    Toast.makeText(this, "검색된 장소가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                // "주차장"을 포함하지 않는 POI 필터링
                val filteredPOIs = poiItemList.filter {
                    !it.poiName.contains("주차장")
                }

                if (filteredPOIs.isEmpty()) {
                    Log.e("handleMissionType", "주차장을 제외한 검색된 POI가 없습니다.")
                    runOnUiThread {
                        Toast.makeText(this, "검색된 장소가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                    return@findAroundNamePOI
                }

                // 거리순으로 POI 정렬 후 상위 5개 선택
                val sortedPOIs = filteredPOIs.sortedBy {
                    it.distanceTo(currentLocation)
                }.take(5).toCollection(ArrayList())

                // 지도에 POI 추가
                runOnUiThread {
                    // 마커 리스트 선언
                    val markerList = ArrayList<TMapMarkerItem>()

                    val poiNames = sortedPOIs.map { it.poiName }
                    sortedPOIs.forEachIndexed { index, item ->
                        val tItem = TMapMarkerItem()
                        // 고유한 ID 설정
                        tItem.id = "marker_${index}"

                        //tItem.setTMapPoint(item.poiPoint.latitude, item.poiPoint.longitude)
                        tItem.tMapPoint = TMapPoint(item.poiPoint.latitude, item.poiPoint.longitude)
                        tItem.calloutTitle = "${item.poiName}"
                        tItem.calloutSubTitle = "${item.poiAddress}"
                        tItem.canShowCallout = true
                        tItem.visible = true

                        markerList.add(tItem)
                        tMapView.addTMapMarkerItem(tItem)
                    }
                    // Spinner에 데이터 연결
                    val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, poiNames)
                    binding.spinner.adapter = adapter

                    // Spinner 선택 이벤트 처리
                    binding.spinner.setOnItemSelectedListener { parent, position ->
                        val selectedItem = parent.getItemAtPosition(position).toString()
                        Toast.makeText(this, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()

                        sortedPOIs.forEachIndexed { index, item ->
                            if (item.poiName == selectedItem) {
                                selectedName = item.name?.takeIf { it.isNotBlank() } ?: "이름 정보 없음"
                                selectedTel = item.telNo?.takeIf { it.isNotBlank() } ?: "전화번호 정보 없음"
                                selectedDesc = item.desc?.takeIf { it.isNotBlank() } ?: "소개 정보 없음"
                            }
                        }
                    }
                }
            }
        }
    }

    // POI 간 거리 계산
    private fun TMapPOIItem.distanceTo(point: TMapPoint): Double {
        val dx = this.poiPoint.latitude - point.latitude
        val dy = this.poiPoint.longitude - point.longitude
        return Math.sqrt(dx * dx + dy * dy)
    }

    // 주소에서 시 구 동 추출
    private fun simpleAddress(input: String): String {
        val words = input.split(" ")
        return words.take(3).joinToString(" ")
    }

    private fun Spinner.setOnItemSelectedListener(listener: (parent: AdapterView<*>, position: Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                listener(parent, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}
