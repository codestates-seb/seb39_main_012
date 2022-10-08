import LabelInput from '@/components/LabelInput/LabelInput'
import {passWordCheckState} from '@/recoil/passwordCheck'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import Router, {useRouter} from 'next/router'
import React, {useState} from 'react'
import {toast} from 'react-toastify'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'

function EditPassword() {
  const router = useRouter()
  const [password, setPassword] = useState('')
  const [, setIsCheck] = useRecoilState(passWordCheckState)
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    console.log('submit')

    if (!password) {
      return toast.error('비밀번호를 입력해주세요')
    }

    const request = {
      password,
    }

    // const result =

    // if (result.status === 200) {
    //   router.push('/edit_password')
    //   setIsCheck(true)
    // }
  }
  return (
    <Container>
      <Title>비밀번호 확인</Title>
      <Form onSubmit={handleSubmit}>
        <LabelInput
          type={'password'}
          label={'패스워드'}
          name={'password'}
          marginRight={'0px'}
          value={password}
          onChange={(e) => {
            setPassword(e.target.value)
          }}
        ></LabelInput>
        <Button type="submit">완료</Button>
      </Form>
    </Container>
  )
}

export default EditPassword

const Container = styled.div`
  margin-top: 100px;
`

const Title = styled.h1`
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 1rem;
`
const Form = styled.form`
  border: solid 1px ${colors.grey1};
  padding: 50px;
`

const Button = styled.button`
  margin-top: 20px;
  ${flexCenter};
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: none;
  font-size: 15px;
  color: white;
  background-color: ${colors.mainColor};
  cursor: pointer;
`
