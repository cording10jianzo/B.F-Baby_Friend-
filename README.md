![homeimage](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/07ae2514-5217-4117-bf41-2058603648db)

# :hatched_chick: Baby Friends :baby_chick:
### 자신의 아이를 소개하는 SNS인 Baby Friends
## :satisfied: Team Introduce :bowtie:
- #### [Team Notion] <https://www.notion.so/c8fc990df909438aa5bd723cae562fdc>
- #### [Team GitHub] <https://github.com/cording10jianzo/B.F-Baby_Friend->
<br>

## :information_desk_person: Member
| 역할 | 이름   | 개인 Github 주소                 |
| --- | ------ |----------------------------------|
| 팀장 | 권경운 |<https://github.com/kwonkyungun>|
| 팀원 | 임주리 |<https://github.com/Lim-Juri/Kotlin_Prac>|
| 팀원 | 김현정 |<https://github.com/khjgggg>    |
| 팀원 | 박용석 |<https://github.com/yspark2>     |
<details>
  <summary>역할 분담</summary>
  
![role](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/e3836821-51ab-438e-b656-224cafa1632a)
</details>
<br>

## :dragon: SDK version
- #### SDK version: Android 13.0 (Tiramisu)
- #### compileSDK: 33
- #### minSdk: 31
- #### targetSDK: 33

<br>


 ## :baby: Project Introduce
<details>
<summary>와이어 프레임</summary>
  

![wireframe](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/248bf9cb-eca6-4d54-a505-0414a241e304)

- 와이어 프레임은 회의를 통하여 구체적인 설계에 들어가기 전에 `대략적인 틀`을 구성했습니다.
</details>
<details>
 <summary>스플래쉬 페이지</summary>

![splash1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/5e612302-0ff8-4c96-84b1-3af4d98d1615)
 
 - 스플래시 페이지는 `lottie animation`이 들어간 페이지로 바로 메인 페이지로 이동합니다. 
  
</details>
<details>
<summary>메인 페이지</summary>
  
![main1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/7298eae8-fbb7-4428-be56-f177f338c928)
![main2](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/81b9d957-1bf1-4fa6-9e47-bc49cee7b8c3)

- 메인 페이지는 다른 페이지들과 상호작용하는 페이지이며 `view type 변환`, `my page 이동`, `fab을 활용한 다른 프레그먼트에 접근`하는 등 다양한 기능이 가능합니다.
- 리사이클러뷰 사용하여서 연락처 리스트 보여주기
  - RecyclerView, Adapter, ViewHolder로 사진, 이름, 연락처, 좋아요 정보를 리스트 형태로 표시되도록 하였고 MainItems에 더미데이터를 추가하고 단말에서 사진을 가져오기 위해 getUri형태로 데이터를 변경하였으며 drawable을 URI로 바꾸는 코드를 사용하였습니다. 

- `ItemViewType 변경` 적용
  - MainFragmentRecyclerView Adapter의 getItemViewType 및 onCreateViewHolder에 이름의 자음 정보를 가진 category와 사용자 정보를 가진 item 뷰타입으로 분기하여 오버라이드하였습니다. 

- `spinner` 상단 우측 버튼 클릭시 viewtype전환 (`리스트/그리드`)
  - MainGridFragment, MainGridListAdapter, FragmentRecyclerView, 그리드 타입레이아웃,  MainGridListItemHelper를 생성하여 MainActivity에서 우측 상단 Sppiner를 생성하였을 때, 그리드뷰 타입으로 전환할 수 있도록 하였고, 클릭시 상세페이지로 넘어가기, 롱클릭시 삭제 기능, Swipe call 기능을 리스트타입과 마찬가지로 적용하였습니다. 

- `Swipe-to-Action`
  - 연락처 아이템을 스와이프할 때, 스와이프의 방향에 따라 특정 액션(메세지 또는 통화)을 실행
MainListItemHelper class파일을 만들어 callback class를 상속받는 ItemTouchHelper class를 사용하여 오른쪽으로 스와이프 시 전화기 이미지가 보여지면서 call로 넘어가는 기능을 구현하였습니다.
그리드뷰 타입에서도 기능구현이 되도록 MainGridListItemHelper class를 생성하였습니다.
manifests CALL_PHONE permission과 메인프래그먼트 페이지requestPermissions(실시간 권한요청)하였습니다.  

- 즐겨찾기 기능구현
  - DetailFragment에서 Checkbox를 이용하여 즐겨찾기 아이콘을 클릭하였을 때 "즐겨찾기 목록에 추가되었다" actionbar를 띄우고 정보를 인텐트로 호출하여 MainFragment에 반영하도록 하였습니다.  

- `리스트 정렬과 초성추출` & `StickyHeader` 기능 구현
  - List sortBy함수로 연락처 목록을 이름순으로 정렬하고 Category로 쓰일 초성을 함수를 활용하여 추출하였습니다.
 RecyclerView의 ItemDecoration상속받는 StickyHeaderItemDecoration Class를 만들어 StickyHeaderInterface로 Category를 Header로 사용하는 StickyHeader를 구현하였습니다.  

- `롱클릭삭제` 
  - 아이템 롱클릭시 "연락처 삭제" 다이얼로그를 띄우고 확인을 누르면 삭제할 수 있도록 하였습니다.
  
</details>
<details>
<summary>마이 페이지</summary>
  
![my1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/d9bc1a9a-623b-4aa5-9a6c-d96f7a9a6f68)
![my2](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/a7ca2cd5-3afc-4b6e-97ed-7ae006feca15)

- 마이 페이지는 `수정하기`를 통해서 개인의 정보수정이 가능합니다.
</details>
<details>
<summary>상세 페이지</summary>
  
![detail1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/8800406a-f9c4-44c3-bf50-9aaab5072c78)
![detail2](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/ae0ae2a9-f855-4f7a-960a-7274f4147596)

- 상세 페이지는 클릭한 아이템의 `자세한 정보`확인이 가능합니다.
</details>
<details>
<summary>친구찾기 페이지</summary>
  
![search1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/338e478a-ec33-4c80-8252-38d8f8d04e44)

- 친구찾기 페이지에서는 존재하는 친구를 빠르게 찾아서 `상세 페이지`로 이동이 가능합니다.
</details>
<details>
<summary>연락처 추가 페이지</summary>
  
![addcontact1](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/3d359b6d-e242-4156-8c30-037444b1f072)
![addcontact2](https://github.com/cording10jianzo/B.F-Baby_Friend-/assets/88123219/02b4e77d-4523-4ae7-aadc-206b5d9b6dbb)

- 연락처 추가 페이지는 다른 사람의 연락처를 `유효성 검사`를 하여 `추가 후 저장`이 가능합니다.
</details>

<br>

## 📚  Platforms & Languages 

<img src="https://img.shields.io/badge/android-3DDC84?style=flat-square&logo=android&logoColor=white"/>  <img src="https://img.shields.io/badge/kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white"/>
## :gem: Tools 
  
<img src="https://img.shields.io/badge/figma-F24E1E?style=flat-square&logo=figma&logoColor=white"/>  <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white"/>  <img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white"/>  <img src="https://img.shields.io/badge/notion-000000?style=flat-square&logo=notion&logoColor=white"/>
