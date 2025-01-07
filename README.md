# Adventure_Log
## 1. 프로젝트 개요
### (1) 목적 및 용도
- 목적
  - 새로운 장소에 방문하고, 새로운 경험을 하여 일상에 새로움을 더하기 위함
  - 타겟층
    - 새로운 장소에 방문하는 것을 좋아하지만, 장소 결정에 어려움을 겪는 사람들
    - 같은 일상에 따분함을 느끼는 사람들
- 용도
  - 특정 장소에서 수행할 수 있는 미션을 랜덤으로 추천
  - 유저는 완료한 미션에 대해 간단한 메모와 사진을 남겨 하나의 기록으로 만들 수 있음
### (2) 개발 환경
- 기능 : Android Studio, [SK open API](https://openapi.sk.com/)
- 디자인 : Android Studio, [로고 만들기 AI GPT](https://chatgpt.com/g/g-Pi8jBuFwp-rogo-mandeulgi-ai-gpt)
## 2. 프로젝트 구조
```
Android
├── app
│   ├── manifests
│   │   └── AndroidManifest.xml
│   │
│   ├── kotlin + java
│   │   ├── ddwu.com.mobile.adventurelog (androidTest)
│   │   ├── ddwu.com.mobile.adventurelog (test)
│   │   └── ddwu.com.mobile.adventurelog
│   │       ├── data
│   │       │   ├── AdventureRecord
│   │       │   ├── AppDatabase
│   │       │   ├── Mission
│   │       │   ├── MissionRepository
│   │       │   └── RecordDao
│   │       │
│   │       ├── Converters
│   │       ├── DetailActivity
│   │       ├── MainActivity
│   │       ├── MissionMapActivity
│   │       ├── MissionRecordActivity
│   │       ├── PerformMissionActivity
│   │       ├── TimelineActivity
│   │       └── TimelineAdapter
│   │   
│   ├── res
│   │   ├── drawable
│   │   │   ├── adventure_log_logo.png
│   │   │   ├── cafe_logo.png
│   │   │   ├── dinner_logo.png
│   │   │   ├── ic_launcher_background.xml
│   │   │   ├── ic_launcher_foreground.xml
│   │   │   ├── library_logo.png
│   │   │   └── park_logo.png
│   │   │
│   │   ├── layout
│   │   │   ├── activity_detail.xml
│   │   │   ├── activity_main.xml
│   │   │   ├── activity_mission_map.xml
│   │   │   ├── activity_timeline.xml
│   │   │   ├── item_timeline.xml
│   │   │   ├── mission_record.xml
│   │   │   └── perform_mission.xml
│   │   │
│   │   ├── mipmap
│   │   ├── values
│   │   └── xml
│   │       ├── backup_rules.xml
│   │       ├── data_extraction_rules.xml
│   │       └── file_paths.xml
│   │
│   ├── java (generated)
│   └── res (generated)
│
└── Gradle Scripts
    ├── build.gradle.kts (Module: app)
```
## 3. 페이지별 기능
## 4. 개선 방향
- 기능
  - 랜덤 미션 장소(식당, 카페, 공원, 도서관)의 다양성 확장
    - 영화관, 편의점, 전시회 등
  - 공유 기능 개선
    - 미션 완료 메시지 공유뿐만 아니라 미션 자체를 공유하는 기능 추가
  - 길찾기 기능 추가
    - 현재 위치에서 미션 장소까지 안내하는 기능 추가
- 기술
## 5. 프로젝트 후기
