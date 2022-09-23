import React from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import {useRouter} from 'next/router'
import Input from '../Input/Input'
import AuthButton, {Button} from '../AuthButton/AuthButton'
import CheckBox from '../CheckBox/CheckBox'

interface Prop {
  onSubmit: (e: React.FormEvent<HTMLFormElement>) => void
}

const LoginForm = ({onSubmit}: Prop) => {
  const router = useRouter()
  return (
    <Container onSubmit={onSubmit}>
      <InputWrapper>
        <Input
          type={'string'}
          placeholder={'이메일'}
          name={'email'}
          width={'42rem'}
          height={'4.2rem'}
          marginBottom={'2rem'}
        />
        <Input
          type={'password'}
          placeholder={'비밀번호'}
          name={'password'}
          width={'42rem'}
          height={'4.2rem'}
          marginBottom={'2rem'}
        />
      </InputWrapper>
      <KeepEmail>
        <input type="checkbox" />
        <span>이메일 저장</span>
      </KeepEmail>
      <AuthButton title={'로그인'} width={'100%'} height={'4.5rem'} hoverFontColor={'#e5f9ef'} />
      <GuestButton>Guest</GuestButton>
      <HelpLogin>
        <span>아아디 찾기</span>
        <span>비밀번호 찾기</span>
        <span
          onClick={() => {
            router.push('/signup')
          }}
        >
          회원가입
        </span>
      </HelpLogin>
    </Container>
  )
}

export default LoginForm

const Container = styled.form`
  text-align: center;
`

const InputWrapper = styled.div`
  margin: 3rem 0 1rem 0;
`

const KeepEmail = styled.div`
  display: flex;
  align-items: center;
  margin: 0 0 4rem 0;
  text-align: left;
  font-size: 1.3rem;
  color: #666;
`

const HelpLogin = styled.div`
  font-size: 1.35rem;
  margin: 3rem 0;

  span {
    margin-right: 2rem;
    font-weight: 500;
    color: ${colors.grey2};
    cursor: pointer;
  }
`
const GuestButton = styled(Button)`
  background-color: rgb(253, 208, 124);
  width: 100%;
  height: 4.5rem;
  margin-top: 1rem;
  color: rgb(255, 255, 255);

  :hover {
    background-color: rgba(253, 208, 124, 0.9);
    color: rgb(205, 176, 123);
    opacity: 0.8;
  }
`
