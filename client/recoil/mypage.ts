import {DogCard, Users} from '@/types/mypage'
import {atom} from 'recoil'

export const dogListState = atom<DogCard[]>({
  key: 'dogListState',
  default: [],
})

export const dogIdState = atom<number>({
  key: 'dogIdState',
  default: 0,
})

export const postIdState = atom({
  key: 'postIdState',
  default: {
    postsId: 0,
    roomPrice: 0,
    title: '',
    url: '',
  },
})

export const dataState = atom({
  key: 'dataState',
  default: false,
})

export const userInfoState = atom({
  key: 'userState',
  default: {
    name: '',
    email: '',
    phone: '',
  },
})
