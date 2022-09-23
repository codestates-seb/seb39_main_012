import {useState} from 'react'
import styled from 'styled-components'
import TapMenu from '@/components/Login/TapMenu'
import LoginForm from '@/components/Login/LoginForm'
import axios from 'axios'
import {useRouter} from 'next/router'
import {toast} from 'react-toastify'

const index = () => {
  const [error, setError] = useState(false)
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
      setError(true)
    }
  }

  return (
    <Container>
      <Wrapper>
        <Title>로그인</Title>
        <TapMenu />
        <LoginForm onSubmit={onSubmit} />
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
  margin-top: 3em;
`

const Wrapper = styled.div``

const Title = styled.h1`
  font-size: 2.5rem;
  font-weight: bold;
  margin: 3rem 0 5rem 0;
  text-align: center;
`
