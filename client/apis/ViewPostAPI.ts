import axios from 'axios'
import {SERVER_URL} from '.'

const AuthGetAPI = axios.create({
  baseURL: SERVER_URL,
  timeout: 5000,
  headers: {
    // Authorization: `Bearer ${accessToken}`,
  },
})

export const getPosts = async (pageNum: number) => {
  try {
    const result = await AuthGetAPI.get(`/v1/posts?page=${pageNum}&size=5`)
    return result
  } catch {
    throw new Error('전체 질문 조회 실패 👻')
  }
}

export const getPostById = async (id: number) => {
  try {
    const result = await AuthGetAPI.get(`/v1/posts/${id}?page=1&size=5`)
    return result
  } catch {
    throw new Error('글 상세 조회 실패 👻')
  }
}

export const viewPostService = {
  getPosts,
  getPostById,
}
