# Adventure_Log
![001](https://github.com/user-attachments/assets/29f46206-b9c1-4ebd-b839-7c5bf4cf06c0)
## 1. 프로젝트 개요
### 목적
- 사용자에게 새로운 장소에서 수행할 수 있는 랜덤 미션을 추천하여 일상에 활력을 제공.
### 핵심 기능
- 랜덤 미션 추천 (카페, 공원, 영화관 등)
- 완료한 미션 기록 및 사진 첨부
- 타임라인 형태 기록
## 2. 개발 환경
- Android Studio, [SK open API](https://openapi.sk.com/)
- Android Studio, [로고 만들기 AI GPT](https://chatgpt.com/g/g-Pi8jBuFwp-rogo-mandeulgi-ai-gpt)
## 3. 프로젝트 구조
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
## 4. 페이지별 기능
![002](https://github.com/user-attachments/assets/fbc0ca6d-97da-4aa5-905e-f30bfd863cba)
![003](https://github.com/user-attachments/assets/4c8e5ec0-eba0-4ecf-8eef-6e4d43a7368e)
![004](https://github.com/user-attachments/assets/c0162486-9986-4e18-bedf-42dcb8e5d525)
![005](https://github.com/user-attachments/assets/aef1d4c5-12da-43a9-88e5-c772a31235d3)
![006](https://github.com/user-attachments/assets/674177f7-e1cc-44bd-861c-187bcadd6c3b)
![007](https://github.com/user-attachments/assets/b68c3331-a0ab-41e4-aaee-b179db438e86)
![008](https://github.com/user-attachments/assets/88635a7a-3cf5-4e39-8713-6fce37031c1d)


## 5. 개선 방향
- 랜덤 미션 장소 다양화
- 위치 기반 미션 생성 기능 강화
- 길찾기 기능 추가
- 타임라인 수정 기능 추가
