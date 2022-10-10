import React, {useEffect} from 'react'
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

interface Prop {
  // saveId: boolean
  // setSaveId: React.Dispatch<React.SetStateAction<boolean>>
  onSubmit: (e: React.FormEvent<HTMLFormElement>) => void
}

// const LoginForm = ({saveId, setSaveId, onSubmit}: Prop) => {
const LoginForm = ({onSubmit}: Prop) => {
  const router = useRouter()
  const [email, setEmail] = React.useState('')
  const [saveId, setSaveId] = useRecoilState(saveIdState)
  const [currentTab, setCurrentTab] = useRecoilState(currentTabState)
  const [isLogin, setIsLogin] = useRecoilState(loginState)

  useEffect(() => {
    const email = LocalStorage.getItem('email')
    if (email) {
      setEmail(email)
      setSaveId(true)
    }
  }, [])

  useEffect(() => {
    if (saveId) {
      LocalStorage.setItem('email', email)
    } else {
      LocalStorage.removeItem('email')
    }
  }, [saveId])

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value)
  }

  const checkboxHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSaveId(!saveId)
  }

  const guestLoginHandler = async (
    e: React.MouseEvent<HTMLButtonElement>,
    mode: string | undefined
  ) => {
    e.preventDefault()

    if (mode === '견주님') {
      const [result, userInfo] = await authService.Login({
        email: 'guest@moongtel.com',
        password: 'asdf1234!@#$',
      })
      setIsLogin(userInfo)
      if (result === 200) {
        router.push('/')
        toast.success('로그인 성공')
      }
    } else if (mode === '사장님') {
      const [result, userInfo] = await authService.Login({
        email: 'ceo@ceo.com',
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
          width={'42rem'}
          height={'4.2rem'}
          marginBottom={'2rem'}
          value={email}
          onChange={onChange}
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
