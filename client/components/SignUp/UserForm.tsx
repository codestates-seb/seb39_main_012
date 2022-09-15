import {Form} from '@/pages/signup'
import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'
import AuthButton from '../AuthButton/AuthButton'
import CheckBox from '../CheckBox/CheckBox'
import LabelInput from '../LabelInput/LabelInput'
import CheckBoxLabel from './CheckBoxLabel'
import Postcode from './Postcode'

interface Props {
  form: Form
  setForm: React.Dispatch<React.SetStateAction<Form>>
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
  onSubmit: (e: React.FormEvent<HTMLFormElement>) => void
  mode: 'user' | 'company'
}

function UserForm({form, onChange, mode, setForm, onSubmit}: Props) {
  const {email, password, passwordCheck, name, phone, companyName, detailAddress, address} = form

  return (
    <SignUpForm onSubmit={onSubmit}>
      <LabelInput
        type={'email'}
        name={'email'}
        value={email}
        onChange={onChange}
        label={'이메일'}
      />
      <LabelInput
        type={'password'}
        name={'password'}
        value={password}
        onChange={onChange}
        label={'비밀번호'}
      />
      <LabelInput
        type={'password'}
        name={'passwordCheck'}
        value={passwordCheck}
        onChange={onChange}
        label={'비밀번호 확인'}
      />
      <LabelInput type={'text'} name={'name'} value={name} onChange={onChange} label={'이름'} />
      <LabelInput
        type={'number'}
        name={'phone'}
        value={phone}
        onChange={onChange}
        label={'전화번호'}
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
