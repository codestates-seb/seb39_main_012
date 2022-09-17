import {useState} from 'react'
import styled from 'styled-components'
import TapMenu from '@/components/Login/TapMenu'
import LoginForm from '@/components/Login/LoginForm'
import SocialAuthButtons from '@/components/SocialAuthButtons/SocialAuthButtons'
import axios from 'axios'
import {useRouter} from 'next/router'
import {toast} from 'react-toastify'

const index = () => {
  const [error, setErorr] = useState(false)
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
    const result = await axios.post('/api/auth/login', form)
    if (result.status === 200) {
      router.push('/')
      toast.success('로그인 성공')
    } else {
      setErorr(true)
    }
  }

  return (
    <Container>
      <Wrapper>
        <Title>로그인</Title>
        <TapMenu />
        <LoginForm onSubmit={onSubmit} />
        <SocialAuthButtons />
      </Wrapper>
    </Container>
  )
}

export default index

const Container = styled.div`
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 33.33%;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  margin-top: 5rem;
`

const Wrapper = styled.div``

const Title = styled.h1`
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 34px;
  text-align: center;
`
