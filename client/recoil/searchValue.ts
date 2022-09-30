import {atom} from 'recoil'

export const titleSearchState = atom({
  key: 'titleSearchState',
  default: '',
})

export const searchValueState = atom({
  key: 'searchValueState',
  default: {
    local: '',
    checkin: '체크인',
    checkout: '체크아웃',
    dogSize: '',
  },
})
