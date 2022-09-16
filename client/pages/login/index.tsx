import {useState} from 'react'
import styled from 'styled-components'
import TapMenu from '@/components/Login/TapMenu'
import LoginForm from '@/components/Login/LoginForm'
import SocialAuthButtons from '@/components/SocialAuthButtons/SocialAuthButtons'

const index = () => {
  const [error, setErorr] = useState(false)

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const formdata = new FormData(e.currentTarget)
    const email = String(formdata.get('email'))
    const password = String(formdata.get('password'))
    const form = {
      email,
      password,
    }
    console.log(form)
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
  align-items: center;
  margin: 0 auto;
  height: 100vh;
  width: 33.33%;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
`

const Wrapper = styled.div``

const Title = styled.h1`
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 34px;
  text-align: center;
`
