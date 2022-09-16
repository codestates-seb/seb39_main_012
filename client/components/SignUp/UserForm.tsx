import {Form} from '@/pages/signup'
import {Validate} from '@/utils/validate'
import React, {useState} from 'react'
import styled from 'styled-components'
import AuthButton from '../AuthButton/AuthButton'
import LabelInput from '../LabelInput/LabelInput'
import AgreeBox from './AgreeBox'
import Postcode from './Postcode'

interface Props {
  mode: 'user' | 'company'
}

function UserForm({mode}: Props) {
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

  const [errors, setErrors] = useState({
    email: true,
    password: true,
    passwordCheck: true,
    phone: true,
  })

  const [checks, setChecks] = useState({
    totalAgree: false,
    useAgree: false,
    privateAgree: false,
    snsAgree: false,
    ageAgree: false,
  })

  const {email, password, passwordCheck, name, phone, companyName, detailAddress, address} = form

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    let error = false
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

    if (name === 'passwordCheck') {
      error = Validate.passwordConfirmValidate(password, value)
    }

    if (name === 'phone') {
      error = Validate.phoneValidate(value)
    }

    setErrors({
      ...errors,
      [name]: error,
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

    const {email, password, passwordCheck, name, phone} = newForm

    if (!email || !password || !passwordCheck || !name || !phone) {
      alert('모든 항목을 입력해주세요')
      return
    }

    if (
      errors.email === true ||
      errors.password === true ||
      errors.passwordCheck === true ||
      errors.phone === true
    ) {
      alert('입력값을 확인해주세요')
      return
    }

    if (!checks.useAgree || !checks.privateAgree || !checks.ageAgree) {
      alert('필수 항목에 동의해주세요')
      return
    }

    console.log(newForm)
  }

  return (
    <SignUpForm onSubmit={onSubmit}>
      <LabelInput
        type={'email'}
        name={'email'}
        value={email}
        onChange={onChange}
        label={'이메일'}
        Errors={errors.email}
        ErrorMessage={'이메일 형식이 올바르지 않습니다.'}
      />
      <LabelInput
        type={'password'}
        name={'password'}
        value={password}
        onChange={onChange}
        label={'비밀번호'}
        Errors={errors.password}
        ErrorMessage={'비밀번호는 문자 숫자 특수문자 조합 8자 이상으로 조합해주세요.'}
      />
      <LabelInput
        type={'password'}
        name={'passwordCheck'}
        value={passwordCheck}
        onChange={onChange}
        label={'비밀번호 확인'}
        Errors={errors.passwordCheck}
        ErrorMessage={'비밀번호가 일치하지 않습니다. 다시 확인해 주세요.'}
      />
      <LabelInput type={'text'} name={'name'} value={name} onChange={onChange} label={'이름'} />
      <LabelInput
        type={'text'}
        name={'phone'}
        value={phone}
        onChange={onChange}
        label={'전화번호'}
        Errors={errors.phone}
        ErrorMessage={'핸드폰 번호 형식이 올바르지 않습니다.'}
      />
      {mode === 'company' && (
        <>
          <LabelInput
            type={'text'}
            name={'companyName'}
            value={companyName as string}
            onChange={onChange}
            label={'회사명'}
          />
          <AddressBox>
            <LabelInput
              type={'text'}
              name={'detailAddress'}
              value={detailAddress as string}
              onChange={onChange}
              label={'회사/점포주소'}
              width={'22rem'}
            />
            <Postcode setForm={setForm} />
          </AddressBox>
          <LabelInput
            type={'text'}
            name={'address'}
            value={address as string}
            onChange={onChange}
            label={'상세주소'}
          />
        </>
      )}
      <Border />
      <AgreeBox checks={checks} setChecks={setChecks} />
      <AuthButton title="회원가입" />
    </SignUpForm>
  )
}

export default UserForm

const SignUpForm = styled.form`
  display: flex;
  flex-direction: column;
  margin-top: 36px;
  gap: 16px;
`
const AddressBox = styled.div`
  display: flex;
  gap: 7px;
`
const Border = styled.div`
  margin: 40px 0;
  width: 100%;
  height: 0.1px;
  border: 0.5px solid #e5e5e5;
  background-color: #e5e5e5;
`
