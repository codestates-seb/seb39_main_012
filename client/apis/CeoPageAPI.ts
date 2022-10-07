import {Ceo} from '@/types/ceopage'
import axios from 'axios'
import {SERVER_URL} from '.'
import LocalStorage from '@/utils/util/localStorage'

const authInstance = axios.create({
  baseURL: SERVER_URL,
  headers: {
    Authorization: `Bearer ${LocalStorage.getItem('accessToken')}`,
  },
})

const getCeoPage = async () => {
  try {
    const result = await authInstance.get<Ceo>(`/v1/company/profile?page=1&size=100`)
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '마이페이지 불러오기 실패')
    }
    throw new Error('마이페이지 불러오기 실패')
  }
}

const deleteMyCompany = async (postId: number) => {
  try {
    const result = await authInstance.delete(`/v1/company/posts/${postId}`)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '회사 삭제 실패')
    }
    throw new Error('회사 삭제 실패')
  }
}

const deleteUserReservation = async (reservationId: number) => {
  try {
    const result = await authInstance.delete(`/v1/company/reservation/cancel/${reservationId}`)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '예약 삭제 실패')
    }
    throw new Error('예약 삭제 실패')
  }
}

export const ceoService = {
  getCeoPage,
  deleteMyCompany,
  deleteUserReservation,
}
