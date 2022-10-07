import axios from 'axios'
import LocalStorage from '@/utils/util/localStorage'
import {SERVER_URL} from '.'
import {IPostWrite} from './type/types'
import {CEO} from '@/types/post'

const authInstance = axios.create({
  baseURL: SERVER_URL,
  timeout: 1000,
  headers: {
    Authorization: `Bearer ${LocalStorage.getItem('accessToken')}`,
  },
})

const authImageInstance = axios.create({
  baseURL: SERVER_URL,
  // timeout: 1000,
  headers: {
    Authorization: `Bearer ${LocalStorage.getItem('accessToken')}`,
    'Content-Type': 'multipart/form-data',
  },
})

const authPostLikesAPI = async (postId: number) => {
  try {
    const result = await authInstance.post(`/v1/likes/${postId}`)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '좋아요 추가 실패')
    }
    throw new Error('좋아요 추가 실패')
  }
}

const authPostWriteAPI = async (WriteForm: IPostWrite) => {
  try {
    const result = await authImageInstance.post(`/v1/company/posts/create`, WriteForm)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '새글 등록 실패')
    }
    throw new Error('새글 등록 실패')
  }
}

const authGetCeoPage = async () => {
  try {
    const result = await authInstance.get<CEO>(`/v1/company/profile`)
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '마이페이지 불러오기 실패')
    }
    throw new Error('마이페이지 불러오기 실패')
  }
}

const authEditPostAPI = async (postId: number, WriteForm: IPostWrite) => {
  try {
    const result = await authInstance.patch(`/v1/company/posts/${postId}`, WriteForm)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '글 수정 실패')
    }
    throw new Error('글 수정 실패')
  }
}

export const authPostService = {
  authPostLikesAPI,
  authPostWriteAPI,
  authEditPostAPI,
  authGetCeoPage,
}
