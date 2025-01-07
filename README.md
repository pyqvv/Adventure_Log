# Adventure_Log
![001](https://github.com/user-attachments/assets/29f46206-b9c1-4ebd-b839-7c5bf4cf06c0)
## 1. 프로젝트 개요
### 목적
- 새로운 장소를 방문하고, 새로운 경험을 통해 일상에 새로움을 더하기 위함.
### 타겟층
1. 새로운 장소를 방문하고 싶지만, 장소 선택에 어려움을 겪는 사람들.
2. 반복적인 일상에 지루함을 느끼고 변화를 원하는 사람들.
### 용도 및 주요 기능
1. 랜덤 미션 추천
    - 특정 장소(예: 카페, 공원, 영화관 등)에서 수행할 수 있는 미션을 랜덤으로 추천.
2. 기록 기능
    - 사용자는 완료한 미션에 대해 간단한 메모 작성과 사진 첨부가 가능하며, 이를 하나의 기록으로 저장할 수 있음.
    - 기록된 미션은 타임라인 형태로 정리되어 사용자 경험을 추억으로 남길 수 있음.
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
![002](https://github.com/user-attachments/assets/a66296b9-9037-4e00-b75f-fc089ee35e5d)
![003](https://github.com/user-attachments/assets/e6c87386-bb1b-45be-ac4a-feb936763cc2)
![004](https://github.com/user-attachments/assets/7e3686ed-a7a9-46ab-8e5c-c9c9d883dcef)
![005](https://github.com/user-attachments/assets/9600fde3-7572-4090-9138-28e9833850de)
![006](https://github.com/user-attachments/assets/dc15c5d0-4207-46c0-8bdc-0dcf27d1447f)
![007](https://github.com/user-attachments/assets/c39f2078-1e7f-4f82-a7f7-68827a40db4d)
![008](https://github.com/user-attachments/assets/e5518089-84a0-4801-9187-e14fb73aa2dc)

## 4. 개선 방향
- 랜덤 미션 장소 다양성 확장
  - 기존 : 식당, 카페, 공원, 도서관 
  - 추가 : 영화관, 편의점, 전시회 등을 포함하여 사용자 경험을 풍부하게 개선.
- 위치 기반 미션 생성 기능
  - 현재 위치 기반
  - 선택한 위치 기반
  - 다양한 사용자 목적을 충족시켜 사용자층 확대 가능.
- 길찾기 기능 추가
  - 현재 위치에서 미션 장소까지 경로를 안내하는 기능.
- 타임라인 수정 기능 추가
## 5. 프로젝트 후기
프로젝트는 과제 제출을 목적으로 시작했지만, 밤을 새워 작업할 만큼 재미있었다. 프로젝트를 마친 후에는 다른 안드로이드 프로젝트에도 참여하고 싶다는 생각이 들었고, 이번 프로젝트에서 부족했던 점을 보완하기 위해 교수님께 피드백을 요청드렸다.
교수님께서 제안하신 추가 학습 및 개선 사항은 다음과 같다:
1. 기본적인 DB 연산 중 update 부분의 구현이 없었음 -> 기본적인 CRUD 연산의 구현.
2. Tmap 대신 GoogleMap 활용.
3. POI 정보를 가져오는 부분을 Activity 가 아닌 별도의 클래스로 구현.
4. 대체로 Activity 에 대부분의 기능을 구현하였는데 기능 별로 별도의 모듈로 구현.
5. 다양한 UI를 적용해보는 연습.
