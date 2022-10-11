import {atom} from 'recoil'

export const saveIdState = atom({
  key: 'saveIdState',
  default: false,
})

export const currentTabState = atom({
  key: 'currentTabState',
  default: 0,
})
