import React from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import {useRouter} from 'next/router'
import Input from '../Input/Input'
import AuthButton from '../AuthButton/AuthButton'

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
          name={'id'}
          width={'56rem'}
          height={'5rem'}
          marginBottom={'2rem'}
        />
        <Input
          type={'password'}
          placeholder={'비밀번호'}
          name={'password'}
          width={'56rem'}
          height={'5rem'}
          marginBottom={'2rem'}
        />
      </InputWrapper>
      <KeepEmail>
        <input type="checkbox" name="" id="" />
        <span>이메일 저장</span>
      </KeepEmail>
      <AuthButton title={'로그인'} width={'100%'} />
      <AuthButton
        title={'게스트로 로그인하기'}
        width={'100%'}
        backgroundColor={'rgb(11, 80, 2)'}
        marginTop={'1rem'}
      />

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
  font-size: 1.4rem;
  margin: 3rem 0;

  span {
    margin-right: 2rem;
    font-weight: 500;
    color: ${colors.grey2};
    cursor: pointer;
  }
`
