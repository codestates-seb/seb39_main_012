import SignUpTab from '@/components/SignUp/SignUpTab'
import UserForm from '@/components/SignUp/UserForm'
import React, {useState} from 'react'
import styled from 'styled-components'

export interface Form {
  email: string
  password: string
  passwordCheck: string
  name: string
  phone: string
  companyName: string
  address: string
  detailAddress: string
}

function SignUp() {
  const [mode, setMode] = useState<'user' | 'company'>('user')

  return (
    <Container>
      <Title>회원가입</Title>
      <SignUpTab mode={mode} setMode={setMode} />
      <UserForm mode={mode} />
    </Container>
  )
}

export default SignUp

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
`

const Title = styled.h1`
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 34px;
`
