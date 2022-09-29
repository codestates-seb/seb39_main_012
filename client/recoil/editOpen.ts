import {atom} from 'recoil'

export const editOpenState = atom({
  key: 'editOpenState',
  default: false,
})

export const addOpenState = atom({
  key: 'addOpenState',
  default: false,
})
