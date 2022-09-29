import axios from 'axios'
import {SERVER_URL} from '.'
import {getUserInfo} from './getUserInfo'
import {ILogin, ICustomerSignUp, ICompanySingUp, UserSignUp} from './type/types'

const authAPI = axios.create({baseURL: SERVER_URL, timeout: 1000})

const authInstance = axios.create({
  baseURL: SERVER_URL,
})

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

export let getUser = null
export const jwt = ''
export const Login = async (LoginForm: ILogin) => {
  try {
    const result = await authAPI.post('/login', LoginForm)
    const jwt = result.headers.authorization.split(' ')[1]
    getUser = getUserInfo(jwt)
    console.log(getUser)
    return [result.status, jwt]
  } catch (error) {
    throw new Error('로그인 실패 👻')
  }
}

export const CustomerSignUp = async (SignUpForm: ICustomerSignUp) => {
  try {
    const result = await authAPI.post(`/v1/users/join/customer`, SignUpForm)
    return result
  } catch {
    throw new Error('회원가입 실패 👻')
  }
}

export const CompanySingUp = async (SignUpForm: ICompanySingUp) => {
  try {
    const result = await authAPI.post(`/v1/users/join/company`, SignUpForm)
    return result
  } catch {
    throw new Error('회원가입 실패 👻')
  }
}

export const authService = {
  Login,
  signUp,
  CustomerSignUp,
  CompanySingUp,
}
