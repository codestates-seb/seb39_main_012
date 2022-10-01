import {DogCard} from '@/types/mypage'
import {atom} from 'recoil'

export const dogListState = atom<DogCard[]>({
  key: 'dogListState',
  default: [],
})

export const dogIdState = atom<number>({
  key: 'dogIdState',
  default: 0,
})
