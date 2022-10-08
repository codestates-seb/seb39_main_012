import {PageInfo, Post, PostById} from '@/types/post'
import axios from 'axios'
import {SERVER_URL} from '.'

interface PostsRespose {
  data: Post[]
  pageInfo: PageInfo
}

const postInstance = axios.create({
  baseURL: SERVER_URL,
  // timeout: 5000,
})

const getPosts = async (page: number) => {
  try {
    const result = await postInstance.get<PostsRespose>(`/v1/posts?page=${page}&size=8`)
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '게시글 불러오기 실패')
    }
    throw new Error('게시글 불러오기 실패')
  }
}

const getPostsTitle = async (page: number, title: string) => {
  try {
    const result = await postInstance.get<PostsRespose>(
      `/v1/posts/search-title?page=${page}&size=12&title=${encodeURI(title)}`
    )
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '게시글 타이틀 조회 실패')
    }
    throw new Error('게시글 타이틀 조회 실패')
  }
}

const getPostsTag = async (page: number, tag: string) => {
  try {
    const result = await postInstance.get<PostsRespose>(
      `/v1/posts/search-tag?page=${page}&size=12&tag=${encodeURI(tag)}`
    )
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '게시글 불러오기 실패')
    }
    throw new Error('게시글 불러오기 실패')
  }
}

const getPostsAddress = async (page: number, address: string) => {
  try {
    const result = await postInstance.get<PostsRespose>(
      `/v1/posts/search-address?page=${page}&size=12&address=${address}`
    )
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '게시글 불러오기 실패')
    }
    throw new Error('게시글 불러오기 실패')
  }
}

const getPostById = async (id: number) => {
  try {
    const result = await postInstance.get<PostById>(`/v1/posts/${id}?page=1&size=10`)
    return result.data
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message + '_' + '상세 포스트 조회 실패')
    }
    throw new Error('상세 포스트 조회 실패')
  }
}

export const postService = {
  getPosts,
  getPostsTitle,
  getPostsTag,
  getPostsAddress,
  getPostById,
}
