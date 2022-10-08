/* eslint-disable @typescript-eslint/no-explicit-any */
import LocalStorage from '@/utils/util/localStorage'
import axios from 'axios'
import {SERVER_URL} from '.'

const authInstance = axios.create({
  baseURL: SERVER_URL,
  headers: {
    'Content-Type': 'multipart/form-data',
    Authorization: `Bearer ${LocalStorage.getItem('accessToken')}`,
  },
})

const createReview = async (form: any) => {
  try {
    const result = await authInstance.post(`/v1/customer/review/write`, form)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '리뷰 등록 실패')
    }
    throw new Error('리뷰 등록 실패')
  }
}

const editReview = async (form: any, reviewId: number) => {
  try {
    const result = await authInstance.patch(`/v1/customer/review/update/${reviewId}`, form)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '리뷰 수정 실패')
    }
    throw new Error('리뷰 수정 실패')
  }
}

const deleteReview = async (reviewId: number) => {
  try {
    const result = await authInstance.delete(`/v1/customer/review/delete/${reviewId}`)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '리뷰 삭제 실패')
    }
    throw new Error('리뷰 삭제 실패')
  }
}

export const reviewService = {
  createReview,
  editReview,
  deleteReview,
}
