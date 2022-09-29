import axios from 'axios'
import {MdEmail} from 'react-icons/md'
import {SERVER_URL} from '.'
import {getUserInfo} from './getUserInfo'
import {ILogin, ICustomerSignUp, ICompanySingUp} from './type/types'

const authAPI = axios.create({baseURL: SERVER_URL, timeout: 1000})

// export const Login = async(username:string, password:string) => {
export let getUser = null
export let jwt = ''
export const Login = async (LoginForm: ILogin) => {
  try {
    // const result = await authAPI.post('/login', {username, password})
    const result = await authAPI.post('/login', LoginForm)
    const jwt = result.headers.authorization.split(' ')[1]
    getUser = getUserInfo(jwt) // { "email": "test@test.com", "password": "asdf1234!@#$"}
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
  CustomerSignUp,
  CompanySingUp,
}
