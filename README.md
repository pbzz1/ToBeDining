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

# 🧩 시스템 아키텍처

## 🔐 Role-Based Access Control (RBAC)

본 시스템은 다음 4단계 권한 체계를 적용합니다:

- **Admin**
- **Owner**
- **Customer**
- **Guest**

각 역할에 따라 화면 구성 및 기능 접근이 동적으로 제어됩니다.

<img width="1024" height="1536" src="https://github.com/user-attachments/assets/d2ea0afc-541c-46fc-aa45-dd07b216f28a" />

---

# 🗄 데이터베이스 설계 (ERD)

아래는 ToBeDining의 전체 데이터베이스 구조(Entity Relationship Diagram)입니다.

<img width="4000" height="4000" alt="ToBeDiningERD" src="https://github.com/user-attachments/assets/070694a8-1225-43ad-9cd0-b4737eb2f454" />


### 주요 테이블 구성

| 테이블 | 설명 |
|--------|------|
| D_MEMBER | 사용자 정보 |
| D_STORE | 매장 정보 |
| D_STORE_MENU_DETAIL | 매장 메뉴 |
| D_REVIEW | 리뷰 정보 |
| D_RESERVE | 예약 정보 |
| D_ANNOUNCEMENT | 공지사항 |
| D_FAVORITES | 즐겨찾기 |
| D_INQUIRY | 문의 게시판 |

### 핵심 관계 구조

- **회원(1) : 매장(N)**  
- **매장(1) : 리뷰(N)**  
- **매장(1) : 메뉴(N)**  
- **회원(1) : 예약(N)**  
- **회원(1) : 즐겨찾기(N)**  

---

# 🏠 메인 화면

<img width="2873" height="1580" src="https://github.com/user-attachments/assets/04bbf89a-824c-45b7-a944-016f45295fb5" />

- 사용자 위치 기반 맛집 로딩
- 태그 기반 카테고리 분류
- 공지사항 및 추천 맛집 노출

---

# 👤 Guest 기능

## 🔐 로그인 / 회원가입

<img width="1434" height="787" src="https://github.com/user-attachments/assets/b9d119f2-99f8-42a3-9b02-be6e43a3d65b" />

<img width="508" height="522" src="https://github.com/user-attachments/assets/b3c6b67d-10a6-48db-9101-330c55898ae7" />

---

## 🔍 가게 검색

- 사용자 위치 기반 지도 표시
- 가게명 검색 기능 제공

---

# 🏪 Owner 기능

- 매장 등록
- 메뉴 및 사진 등록
- 예약 승인 / 거절

---

# 👥 Customer 기능

- 매장 예약
- 리뷰 작성
- 예약 내역 조회

---

# 🛠 Admin 기능

- 회원 관리
- 매장 관리
- 공지사항 관리

---

# 🚀 기술 스택 (Tech Stack)

## 🖥 Frontend
- **Nexacro Platform**

## 🛠 Backend
- **전자정부프레임워크 (eGovFramework)**
- **Java**
- **Apache Tomcat Server**

## 🗺 API
- **Kakao Map API**

## 🗄 Database
- **MySQL**
- **HeidiSQL** (DB 관리 도구)

---

# 📌 프로젝트 특징

- 위치 기반 맛집 추천 시스템
- RBAC 기반 동적 권한 제어
- 예약 승인 / 거절 시스템
- 리뷰 및 평점 평균 자동 계산
- 관리자 통합 관리 기능

---

© 2025 ToBeDining Project
