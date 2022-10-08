/* eslint-disable react-hooks/rules-of-hooks */
import {useState} from 'react'
import styled from 'styled-components'
import TapMenu from '@/components/Login/TapMenu'
import LoginForm from '@/components/Login/LoginForm'
import {useRouter} from 'next/router'
import {toast} from 'react-toastify'
import {authService} from '@/apis/AuthAPI'
import {useRecoilState} from 'recoil'
import {loginState} from '@/recoil/loginState'
// import {useRecoilState, useRecoilValue, useSetRecoilState} from 'recoil'
// import {bookingWishlist} from '@/recoil/bookingWishlist'

const Login = () => {
  // const [bookingWish, setBookingWish] = useRecoilState(bookingWishlist)
  const [isLogin, setIsLogin] = useRecoilState(loginState)

  const [error, setError] = useState(false)
  const [saveId, setSaveId] = useState(false)

  const router = useRouter()
  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const formdata = new FormData(e.currentTarget)
    const email = String(formdata.get('email'))
    const password = String(formdata.get('password'))
    const form = {
      email,
      password,
    }

    // if (saveId) {
    //   localStorage.setItem('email', email)
    // } else {
    //   localStorage.removeItem('email')
    // }

    const [result, userInfo] = await authService.Login(form)
    console.log('Result', userInfo)
    setIsLogin(userInfo)
    if (result === 200) {
      router.push('/')
      toast.success('로그인 성공')
    } else {
      setError(true)
    }
  }

  return (
    <Container>
      <Wrapper>
        <Title>로그인</Title>
        <TapMenu />
        <LoginForm onSubmit={onSubmit} setSaveId={setSaveId} saveId={saveId} />
      </Wrapper>
    </Container>
  )
}

export default Login

const Container = styled.div`
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 33.33%;
  margin-top: 3em;
`

const Wrapper = styled.div``

const Title = styled.h1`
  font-size: 2.5rem;
  font-weight: bold;
  margin: 3rem 0 5rem 0;
  text-align: center;
`
