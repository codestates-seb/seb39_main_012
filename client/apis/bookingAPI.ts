import axios from 'axios'
import {SERVER_URL} from '.'
import {IPostBookingWish, IPostBooking} from './type/types'
import LocalStorage from '@/utils/util/localStorage'
import {ConfirmBooking} from '@/types/book'

const bookingAPI = axios.create({
  baseURL: SERVER_URL,
  timeout: 5000,
  headers: {
    Authorization: `Bearer ${LocalStorage.getItem('accessToken')}`,
  },
})

const postBookingWish = async (postId: number, WriteForm: IPostBookingWish) => {
  try {
    const result = await bookingAPI.post(`/v1/customer/reservation/${postId}`, WriteForm)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '예약 진행 실패')
    }
    throw new Error('예약 진행 실패')
  }
}

const postBooking = async (postId: number, WriteForm: IPostBooking) => {
  try {
    const result = await bookingAPI.post(`/v1/customer/reservation/${postId}/confirm`, WriteForm)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '예약 진행 실패')
    }
    throw new Error('예약 진행 실패')
  }
}

const confirmBooking = async (reservationId: number) => {
  try {
    const result = await bookingAPI.get<ConfirmBooking>(
      `v1/customer/reservation/${reservationId}/final`
    )
    return result.data.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '예약 진행 실패')
    }
    throw new Error('예약 진행 실패')
  }
}

const deleteBooking = async (reservationId: number) => {
  try {
    const result = await bookingAPI.delete(`/v1/customer/reservation/${reservationId}`)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '예약 취소 실패')
    }
    throw new Error('예약 취소 실패')
  }
}

export const bookingService = {postBookingWish, postBooking, confirmBooking, deleteBooking}
