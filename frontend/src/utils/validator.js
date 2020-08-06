const validator = {
  path: {
    source: [(v) => (Number.isInteger(v) && v > 0) || '역의 id는 양의 정수입니다.'],
    target: [(v) => (Number.isInteger(v) && v > 0) || '역의 id는 양의 정수입니다.']
  },
  departureTime: {
    dayTime: [(v) => (v === "am" || v === "pm") || '오전, 오후 외의 값이 들어올 수 없습니다.'],
    hour: [(v) => (Number.isInteger(v) && (v >=1 && v <= 12)) || '시각은 1이상 12이하의 정수여야 합니다.'],
    minute: [(v) => (Number.isInteger(v) && (v >=0 && v <= 60)) || '분은 0이상 60이하의 정수여야 합니다.']
  },
  stationName: [(v) => !!v || '이름 입력이 필요합니다.', (v) => v.length > 0 || '이름은 1글자 이상 입력해야 합니다.'],
  line: {
    name: [(v) => !!v || '이름 입력이 필요합니다.'],
    startTime: [(v) => !!v || '출발 시간 입력이 필요합니다.'],
    endTime: [(v) => !!v || '도착 시간 입력이 필요합니다.'],
    intervalTime: [(v) => !!v || '간격 입력이 필요합니다.'],
    color: [(v) => !!v || '색상 입력이 필요합니다.'],
    extraFare: [(v) => !!v || '추가 요금 입력이 필요합니다.', (v) => v >= 0 || '추가 요금은 0 이상의 양수이어야 합니다.']
  },
  edge: {
    preStationId: [(v) => !!v || '이전역 id가 필요합니다.'],
    stationId: [(v) => !!v || '대상역 id가 필요합니다.'],
    distance: [(v) => !!v || '거리 입력이 필요합니다.', (v) => v >= 0 || '거리는 0 이상의 양수이어야 합니다.'],
    duration: [(v) => !!v || '시간 입력이 필요합니다.', (v) => v >= 0 || '시간은 0 이상의 양수이어야 합니다.']
  },
  member: {
    email: [(v) => !!v || '이메일 입력이 필요합니다.', (v) => /.+@.+/.test(v) || '유효한 이메일을 입력해주세요'],
    age: [(v) => !!v || '나이 입력이 필요합니다.', (v) => v > 0 || '나이는 1살 이상 이어야 합니다.'],
    password: [(v) => !!v || '비밀번호 입력이 필요합니다.'],
    confirmPassword: [(v) => !!v || '비밀번호 확인이 필요합니다.', (v, c) => v === c || '비밀번호가 일치하지 않습니다.']
  }
}

export default validator
