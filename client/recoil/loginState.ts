import {atom} from 'recoil'

export const loginState = atom<{
  email: string
  exp: number
  id: number
  roles: string
  sub: string
  username: string
} | null>({
  key: 'loginState',
  default: null,
})
