/* eslint-disable react-hooks/exhaustive-deps */
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import {useRouter} from 'next/router'
import Input from '../Input/Input'
import AuthButton, {Button} from '../AuthButton/AuthButton'
import LocalStorage from '../../utils/util/localStorage'
import {useRecoilState} from 'recoil'
import {currentTabState, saveIdState} from '@/recoil/login'
import {authService} from '@/apis/AuthAPI'
import {toast} from 'react-toastify'
import {loginState} from '@/recoil/loginState'
import {Validate} from '@/utils/validate'

const LoginForm = () => {
  const router = useRouter()
  const [saveId, setSaveId] = useRecoilState(saveIdState)
  const [currentTab] = useRecoilState(currentTabState)
  const [, setIsLogin] = useRecoilState(loginState)

  const [form, setForm] = useState({
    email: '',
    password: '',
  })

  const [errors, setErrors] = useState({
    email: false,
    password: false,
  })

  const {email, password} = form

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    let error = true

    const {name, value} = e.target

    setForm({
      ...form,
      [name]: value,
    })

    if (name === 'email') {
      error = Validate.emailValidate(value)
    }

    if (name === 'password') {
      error = Validate.passwordValidate(value)
    }

    setErrors({
      ...errors,
      [name]: error,
    })
  }

  const checkboxHandler = () => {
    setSaveId(!saveId)
  }

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const formdata = new FormData(e.currentTarget)
    const email = String(formdata.get('email'))
    const password = String(formdata.get('password'))

    if (!email || !password) {
      return toast.error('모든 항목을 입력해주세요')
    }

    if (errors.email === true || errors.password === true) {
      return toast.error('항목을 다시 확인해주세요')
    }

    const requestForm = {
      email,
      password,
    }

    const [result, userInfo] = await authService.Login(requestForm)
    setIsLogin(userInfo)
    if (result === 200) {
      router.push('/')
      toast.success('로그인 성공')
    } else {
      toast.error('유효한 이메일과 비밀번호를 입력해주세요')
    }
  }

  useEffect(() => {
    const email = LocalStorage.getItem('email')
    if (email) {
      setForm({
        ...form,
        email: email,
      })
      setSaveId(true)
    }
  }, [])

  useEffect(() => {
    if (saveId) {
      LocalStorage.setItem('email', form.email)
    } else {
      LocalStorage.removeItem('email')
    }
  }, [saveId])

  const guestLoginHandler = async (
    e: React.MouseEvent<HTMLButtonElement>,
    mode: string | undefined
  ) => {
    e.preventDefault()

    if (mode === '견주님') {
      const [result, userInfo] = await authService.Login({
        email: 'test12@gmail.com',
        password: 'asdf1234!@#$',
      })
      setIsLogin(userInfo)
      if (result === 200) {
        router.push('/')
        toast.success('로그인 성공')
      }
    } else if (mode === '사장님') {
      const [result, userInfo] = await authService.Login({
        email: 'ceo01@moongtel.com',
        password: 'asdf1234!@#$',
      })
      setIsLogin(userInfo)
      if (result === 200) {
        router.push('/')
        toast.success('로그인 성공')
      }
    }
  }

  return (
    <Container onSubmit={onSubmit}>
      <InputWrapper>
        <Input
          type={'string'}
          placeholder={'이메일'}
          name={'email'}
          value={email}
          width={'42rem'}
          height={'4rem'}
          onChange={onChange}
        />
        <ErrorMessageBox>{errors.email && <p>이메일 형식이 올바르지 않습니다.</p>}</ErrorMessageBox>
        <Input
          type={'password'}
          placeholder={'비밀번호'}
          name={'password'}
          value={password}
          width={'42rem'}
          height={'4rem'}
          onChange={onChange}
        />
      </InputWrapper>

      <ErrorMessageBox>
        {errors.password && <p>비밀번호는 문자 숫자 특수문자 조합 8자 이상으로 조합해주세요.</p>}
      </ErrorMessageBox>

      <KeepEmail>
        <input
          type="checkbox"
          onClick={() => {
            setSaveId(!saveId)
          }}
          checked={saveId}
          onChange={checkboxHandler}
        />
        <span>이메일 저장</span>
      </KeepEmail>
      <AuthButton
        title={'로그인'}
        width={'100%'}
        height={'4.5rem'}
        hoverFontColor={'rgb(229, 249, 239)'}
      />

      {currentTab === 0 ? (
        <GuestButton onClick={(e) => guestLoginHandler(e, '견주님')}>Guest 견주님</GuestButton>
      ) : (
        <GuestButton onClick={(e) => guestLoginHandler(e, '사장님')}>Guest 사장님</GuestButton>
      )}

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

const InputWrapper = styled.div``

const ErrorMessageBox = styled.div`
  display: flex;
  color: rgb(255, 0, 0);
  font-size: 1rem;
  margin-top: 1rem;
  min-height: 1.7rem;
`

const KeepEmail = styled.div`
  display: flex;
  align-items: center;
  margin: 0 0 4rem 0;
  text-align: left;
  font-size: 1.3rem;
  color: rgb(102, 102, 102);
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
