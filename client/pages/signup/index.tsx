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
  const [form, setForm] = useState({
    email: '',
    password: '',
    passwordCheck: '',
    name: '',
    phone: '',
    companyName: '',
    address: '',
    detailAddress: '',
  })

  const [checks, setChecks] = useState({
    totalAgree: false,
    useAgree: false,
    privateAgree: false,
    snsAgree: false,
    ageAgree: false,
  })

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target
    setForm({
      ...form,
      [name]: value,
    })
  }

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const newForm: Partial<Form> = form

    if (mode === 'user') {
      delete newForm.companyName
      delete newForm.address
      delete newForm.detailAddress
    }

    console.log(newForm)
  }

  return (
    <Container>
      <Title>회원가입</Title>
      <SignUpTab mode={mode} setMode={setMode} />
      <UserForm form={form} onChange={onChange} setForm={setForm} mode={mode} onSubmit={onSubmit} />
    </Container>
  )
}

export default SignUp

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  width: 456px;
  height: 100vh;
`

const Title = styled.h1`
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 34px;
`
