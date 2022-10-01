import {DogCard, Mypage} from '@/types/mypage'
import axios from 'axios'
import {SERVER_URL, TOKEN} from '.'

const authInstance = axios.create({
  baseURL: SERVER_URL,
  headers: {
    Authorization: `Bearer ${TOKEN}`,
  },
})

const getMyPage = async () => {
  try {
    const result = await authInstance.get<Mypage>(`/v1/customer/profile`)
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '마이페이지 불러오기 실패')
    }
    throw new Error('마이페이지 불러오기 실패')
  }
}

const createDogCard = async (form: any) => {
  try {
    const result = await authInstance.post(`/v1/customer/dogCard/create`, form)
    console.log(result)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '강아지 등록 실패')
    }
    throw new Error('강아지 등록 실패')
  }
}

const getMyDogById = async (dogId: number) => {
  try {
    const result = await authInstance.get<DogCard>(`v1/customer/dogCard/${dogId}`)
    return result.data.data
  } catch {
    throw new Error('강아지 정보 불러오기 실패')
  }
}

const editMyDogById = async (form: any, dogId: number) => {
  try {
    const result = await authInstance.patch(`/v1/customer/dogCard/${dogId}`, form)
    return result
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '강아지 등록 실패')
    }
    throw new Error('강아지 등록 실패')
  }
}

const deleteMyDogById = async (dogId: number) => {
  try {
    const result = await authInstance.delete(`/v1/customer/dogCard/${dogId}`)
    return result
  } catch {
    throw new Error('강아지 삭제 실패')
  }
}

export const userService = {
  getMyPage,
  createDogCard,
  getMyDogById,
  editMyDogById,
  deleteMyDogById,
}
