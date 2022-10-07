import {atom} from 'recoil'

export const passWordCheckState = atom<boolean>({
  key: 'passWordCheckState',
  default: false,
})
