import axios from 'axios'
import {SERVER_URL} from '.'
import {IPostWrite} from './type/types'

const AuthPostAPI = axios.create({
  baseURL: SERVER_URL,
  timeout: 1000,
  headers: {
    // Authorization: `Bearer ${accessToken}`,
  },
})

export const writeCompanyPost = async (WriteForm: IPostWrite) => {
  try {
    const result = await AuthPostAPI.post(`/v1/company/posts/create`, WriteForm)
    return result
  } catch {
    throw new Error('새글 등록 실패 👻')
  }
}

export const deleteCompanyPost = async (id: number) => {
  try {
    const result = await AuthPostAPI.delete(`/v1/company/posts/${id}`)
    return result
  } catch {
    throw new Error('글 삭제 실패 👻')
  }
}

export const editCompanyPost = async (id: number, EditForm: IPostWrite) => {
  try {
    const result = await AuthPostAPI.patch(`v1/company/posts/${id}`, EditForm)
    return result
  } catch {
    throw new Error('글 수정 실패 👻')
  }
}

export const authPostService = {
  writeCompanyPost,
  deleteCompanyPost,
  editCompanyPost,
}
