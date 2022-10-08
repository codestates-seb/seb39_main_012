import axios from 'axios'
import {SERVER_URL} from '.'
import {ILogin, UserSignUp} from './type/types'
import LocalStorage from '../utils/util/localStorage'

const authAPI = axios.create({baseURL: SERVER_URL, timeout: 1000})

const authInstance = axios.create({
  baseURL: SERVER_URL,
})

const parseUserInfo = (token: string) => {
  if (!token) {
    return
  }
  const base64Url = token.split('.')[1]
  const base64 = base64Url.replace('-', '+').replace('_', '/')
  return JSON.parse(window.atob(base64))
}

const signUp = async (form: UserSignUp, mode: 'user' | 'company') => {
  try {
    if (mode === 'user') {
      return await authInstance.post('/v1/users/join/customer', form)
    } else if (mode === 'company') {
      return await authInstance.post('/v1/users/join/company', form)
    }
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message)
    }
    throw new Error('회원가입 실패')
  }
}

export const duplicateCheck = async (email: string) => {
  try {
    return await authInstance.get(`/v1/users/check?email=${email}`)
  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message)
    }
    throw new Error('중복확인 실패')
  }
}

const Login = async (LoginForm: ILogin) => {
  try {
    const result = await authAPI.post('/login', LoginForm)
    const accessToken = result.headers.authorization.split(' ')[1]
    LocalStorage.setItem('accessToken', accessToken)
    LocalStorage.setItem('userInfo', JSON.stringify(parseUserInfo(accessToken)))
    
    const userInfo = parseUserInfo(accessToken)
    console.log(userInfo)
    return [result.status, userInfo]

  } catch (e) {
    if (e instanceof Error) {
      throw new Error(e.message)
    }
    throw new Error('로그인 실패')
  }
}

export const authService = {
  signUp,
  Login,
}
