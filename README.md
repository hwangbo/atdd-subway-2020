<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <img alt="license" src="https://img.shields.io/github/license/woowacourse/atdd-subway-2020">
</p>

<br>

# 레벨2 최종 미션 - 지하철 노선도 애플리케이션

## 🎯 요구사항

## 백엔드 요구사항 목록
### 기능 요구사항
- [ ] 1. 경로 조회 응답 결과에 요금 정보 추가(필수)
    - 현재 경로 조회 기능(PathAcceptanceTest)이 구현되어 있습니다.
    - 요금 계산 기준을 참고하여 기존 응답에 요금 정보도 함께 응답이 될 수 있도록 기능을 추가로 구현하세요.
    - 요금 정보가 포함되어야 합니다.
- [ ] 2. 가장 빠른 경로 도착 경로 타입 추가(선택)
    - 현재 경로 조회 기능은 최단 거리와 최소 시간 기준으로 조회가 가능합니다.
    - 경로 조회의 새로운 기준인 특정 시간 기준 가장 빠른 도착 조회 기능을 추가하세요.
    - 목적지에 도착하는 시간을 계산하기 위해서는 경로의 각 지하철역에 도착하는 시간을 고려해야 합니다.
    - 도착하는 시간 이후 가장 빠른 출발 시간을 조회하는 반복 로직이 포함되어야 합니다.
### 프로그래밍 요구사항
- [ ] 1. 인수 테스트를 작성하세요.
- [ ] 2. 경로 조회 기능의 문서화를 진행하세요.
- [ ] 3. 기능 구현 시 TDD를 활용하여 개발하세요. (커밋 단위를 TDD 사이클을 확인할 수 있으면 좋겠네요!)
---
## 프론트엔드 요구사항 목록
- [ ] 1. 백엔드 요금 조회 api를 프론트엔드에서 사용할 수 있게 연동
    - [ ] frontend/src/api/modules/path.js 에 백엔드에서 구현한 path 검색 api의 endpoint를 추가합니다.
    - [ ] 여러분이 api를 추가하고, 함께 업데이트 할 부분은 actions의 PathService.get() 부분의 파라미터입니다.
- [ ] 2. 템플릿 리터럴을 이용해 현재 시간을 사용자가 보기 편한 형식으로 문자열 렌더링
    - [ ] 현재시간을 아래 format처럼 보이도록, frontend/src/views/path/pathPage.vue 의 208line에 있는 getCurrentTime을 구현합니다.
- [ ] 3. validator를 구현해, form의 유효성을 검사
    - [ ] path와 departureTime form의 validation을 구현하기
        - 공통적으로는 빈 값이 없는지 확인하는 로직이 필요합니다.
        - path form에서 필요한 유효성 검사
            - [ ] source: 유효한 Id 값인지 검사 (예를 들면, 양의 정수, 자연수 등)
            - [ ] target: 유효한 Id 값인지 검사 (예를 들면, 양의 정수, 자연수 등)
        - departureTime form에서 필요한 검사
            - [ ] dayTime: '오전' or '오후'인지 검사
            - [ ] hour: 숫자 타입, 1~12 사이의 정수인지 검사
            - [ ] minute: 숫자 타입, 0~60 사이의 정수인지 검사
- [ ] 4. 길찾기를 위해 사용자가 입력한 값을 이용해 검색결과를 불러오는 핸들러를 구현
    - [ ] 요금조회를 위해 검색 버튼을 눌렀을 때 실행되는 이벤트 핸들러를 구현합니다.
        - frontend/src/views/path/PathPage.vue에서 onSearchResult()를 구현합니다.
        - onSearchResult() 는 사용자 입력값을 받은 값을 이용해 서버에 요청하고, response를 받아오는 메서드입니다.
        - try ~ catch 문에서 try 부분을 구현합니다.
        - api 요청하는 메서드는 this.searchPath() 입니다.
        - 사용자가 입력한 출발역, 도착역의 값은 자동으로 this.path 에 저장됩니다.
        - 사용자의 입력을 받은 시간에 대한 값은 this.departureTimeView 에 저장됩니다.
        - 빠른 도착시간을 구하는 경우 YYYYMMDDHHmm 형태로 time을 파라미터로 보내주세요.


## 🤔 미션 제출 방법
- 진행 방식은 오프라인 코딩 테스트와 동일하다.
- 저장소를 Fork하여 자신의 저장소에서 미션 구현을 완료하고, Pull Request를 통해 미션을 제출한다.
- Pull Request를 보낸 후 woowa_course@woowahan.com로 메일을 발송한다.

## 😌 레벨2 최종 미션을 임하는 자세
레벨2 과정을 스스로의 힘으로 구현했다는 것을 증명하는데 집중해라
- [ ] 기능 목록을 잘 작성한다.  
- [ ] 자신이 구현한 기능에 대해 인수 테스트를 작성한다.
- [ ] 자신이 구현한 코드에 대해 단위 테스트를 작성한다.
- [ ] TDD 사이클 이력을 볼 수 있도록 커밋 로그를 잘 작성한다.
- [ ] 사용자 친화적인 예외처리를 고민한다.
- [ ] 읽기 좋은 코드를 만든다.

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## 📝 License

This project is [MIT](https://github.com/woowacourse/atdd-subway-2020/blob/master/LICENSE.md) licensed.
