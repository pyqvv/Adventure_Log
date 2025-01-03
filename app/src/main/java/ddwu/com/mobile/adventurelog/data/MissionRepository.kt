package ddwu.com.mobile.adventurelog.data

object MissionRepository {
    // 미션 목록 정의 (카테고리별로 그룹화 가능)
    private val missions = listOf(
        Mission("식당", "방문한 적 없는 식당 가보기"),
        Mission("식당", "근처 식당에서 대표 메뉴 주문하기"),
        Mission("식당", "주변 식당에서 인기 메뉴 주문하기"),
        Mission("식당", "사장님 추천 메뉴 먹어보기"),
        Mission("도서관", "평소 읽지 않던 장르의 책 읽어 보기"),
        Mission("도서관", "이름이 같은 작가의 책 읽어 보기"),
        Mission("도서관", "신간도서 읽어보기"),
        Mission("도서관", "시집 읽고 시 한 편 필사해 보기"),
        Mission("카페", "디저트 먹기"),
        Mission("카페", "카페 시그니처 메뉴 먹어보기"),
        Mission("카페", "카페 신메뉴 먹어보기"),
        Mission("카페", "카페 계절메뉴 먹어보기"),
        Mission("공원", "공원 벤치에서 좋아하는 노래 듣기"),
        Mission("공원", "인근 공원에서 미니 피크닉 즐기기")
    )

    // 미션 리스트 반환
    fun getAllMissions(): List<Mission> = missions

    // 랜덤 미션 반환
    fun getRandomMission(): Mission = missions.random()
}