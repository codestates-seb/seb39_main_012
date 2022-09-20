import {atom} from 'recoil'

export const searchValueState = atom({
  key: 'SearchValueState',
  default: {
    local: '',
    checkin: '체크인',
    checkout: '체크아웃',
    dogSize: '',
  },
})
