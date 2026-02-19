# 🍽️ ToBeDining

위치 기반 맛집 탐색 및 예약 플랫폼  
Nexacro + 전자정부프레임워크 기반 웹 서비스

---

## 📌 프로젝트 개요

ToBeDining은 사용자의 위치를 기반으로 주변 맛집 정보를 제공하고,  
예약 · 리뷰 · 매장 관리 기능을 제공하는 통합 맛집 플랫폼입니다.

본 시스템은 **Role-Based Access Control (RBAC)** 구조를 적용하여  
사용자 권한에 따라 UI 및 기능 접근을 제어합니다.

---

## 🧩 시스템 아키텍처

### 🔐 Role-Based Access Control (RBAC)

본 시스템은 다음 4단계 권한 체계를 적용합니다:

- **Admin**
- **Owner**
- **Customer**
- **Guest**

각 역할에 따라 화면 구성 및 기능 접근이 동적으로 제어됩니다.

<img width="670" height="1172" alt="image" src="https://github.com/user-attachments/assets/d4a447b0-a8e0-44a0-b22c-02f34f7f5a38" />


---

# 🏠 메인 화면

<img width="2873" height="1580" alt="메인화면" src="https://github.com/user-attachments/assets/04bbf89a-824c-45b7-a944-016f45295fb5" />

- 사용자 위치 기반 맛집 로딩
- 태그 기반 카테고리 분류
- 공지사항 및 추천 맛집 노출

---

# 👤 Guest 기능

## 🔐 로그인 / 회원가입

<img width="1434" height="787" src="https://github.com/user-attachments/assets/b9d119f2-99f8-42a3-9b02-be6e43a3d65b" />

<img width="508" height="522" src="https://github.com/user-attachments/assets/b3c6b67d-10a6-48db-9101-330c55898ae7" />

### 회원가입

<img width="1431" height="784" src="https://github.com/user-attachments/assets/e92ac3ad-e704-4399-b584-cc5e051684b3" />

### 아이디 / 비밀번호 찾기

<img width="600" height="620" src="https://github.com/user-attachments/assets/fcd274d8-99db-462f-b35c-d002c8380199" />

---

## 👤 마이프로필

<img width="2544" height="1255" src="https://github.com/user-attachments/assets/39e01405-f516-4478-abcb-8f0f3cbdcc41" />

---

## 🔍 가게 검색

<img width="1419" height="781" src="https://github.com/user-attachments/assets/e991263c-d179-4490-97cd-0c609cfb7581" />

- 사용자 위치 기반 지도 표시
- 가게명 검색 기능 제공

<img width="1431" height="785" src="https://github.com/user-attachments/assets/da24fa11-a401-4eca-a864-5976491e736a" />

---

## 🏬 가게 상세 정보

<img width="2848" height="1558" src="https://github.com/user-attachments/assets/8f8e40a8-904b-4ffc-86a7-ebe1b7dc48ba" />

<img width="2864" height="1588" src="https://github.com/user-attachments/assets/0fe43805-ebb3-456f-8d13-f5ecb41472df" />

- 가게 정보
- 메뉴 정보
- 리뷰 목록
- 평점 평균 계산

---

## 📢 공지사항 및 Best 맛집

<img width="2361" height="1102" src="https://github.com/user-attachments/assets/b89de37c-c6c9-4e06-b25b-9982b10dd2c2" />

<img width="2495" height="1360" src="https://github.com/user-attachments/assets/c08d59b5-01cf-4fae-a96d-03cf73f3f7ea" />

---

# 🏪 Owner 기능

## 📝 가게 등록

<img width="1386" height="827" src="https://github.com/user-attachments/assets/d42eda28-a312-41c9-a8a2-f5c6823522f5" />

<img width="1296" height="1267" src="https://github.com/user-attachments/assets/f28d63cb-bce8-46f5-a0c7-dce6f58f9551" />

- 매장 정보 등록
- 메뉴 및 사진 등록

---

## 📅 예약 관리

<img width="2569" height="1259" src="https://github.com/user-attachments/assets/5e8f9007-4199-4e72-871f-a13ea3c8d543" />

- Customer 예약 확인
- 예약 승인 / 거절 처리

---

# 👥 Customer 기능

## 📅 가게 예약

<img width="1417" height="1326" src="https://github.com/user-attachments/assets/59677b81-6a42-45ce-a17b-b74f8f822d47" />

<img width="578" height="940" src="https://github.com/user-attachments/assets/1b22fbc9-43dd-4fe0-9720-19cf24872a08" />

---

## 📋 예약 정보 조회

<img width="2517" height="1240" src="https://github.com/user-attachments/assets/5544e8a2-a542-4a99-a0f4-6b09b2fd367b" />

- 나의 예약 내역 조회
- 예약 상태 확인

---

# 🛠 Admin 기능

## 📢 공지사항 관리

<img width="2336" height="1076" src="https://github.com/user-attachments/assets/3844b4ad-59d2-416a-b5fd-8501696753d7" />

<img width="1196" height="942" src="https://github.com/user-attachments/assets/9677c530-035b-424e-b622-4d76d17d70f3" />

- 공지사항 등록 / 수정 / 삭제

---

## 👥 회원 정보 관리

<img width="2576" height="1043" src="https://github.com/user-attachments/assets/ebb1cbb9-cd26-4113-81c6-851a14069787" />

- 사용자 정보 조회
- 권한 관리

---

## 🏬 가게 관리

<img width="2830" height="1132" src="https://github.com/user-attachments/assets/377f9dc3-8a3b-4816-927c-473517b3e59a" />

- 매장 정보 수정 / 삭제
- 전체 매장 관리

---

# 🔐 권한 구조 요약

| Role      | 주요 기능 |
|-----------|------------|
| Guest     | 검색, 회원가입 |
| Customer  | 예약, 리뷰 작성 |
| Owner     | 매장 등록, 예약 관리 |
| Admin     | 전체 사용자/매장/공지 관리 |

---

# 🚀 기술 스택 (Tech Stack)

## 🖥 Frontend
- **Nexacro Platform**

## 🛠 Backend
- **전자정부프레임워크 (eGovFramework)**
- **Java**
- **Apache Tomcat Server**

## 🗺 API
- **Kakao Map API** (위치 기반 지도 및 주소 검색)

## 🗄 Database
- **MySQL**
- **HeidiSQL** (DB 관리 및 쿼리 작업 툴)

---

# 📌 프로젝트 특징

- 위치 기반 맛집 추천 시스템
- RBAC 기반 동적 권한 제어
- 예약 승인 / 거절 시스템
- 리뷰 및 평점 평균 자동 계산
- 관리자 통합 관리 기능

---

© 2025 ToBeDining Project
