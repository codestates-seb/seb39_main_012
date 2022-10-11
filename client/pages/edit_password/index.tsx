import {userService} from '@/apis/MyPageAPI'
import LabelInput from '@/components/LabelInput/LabelInput'
import {} from '@/recoil/passwordCheck'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {useRouter} from 'next/router'
import React, {useState} from 'react'
import {toast} from 'react-toastify'
import {} from 'recoil'
import styled from 'styled-components'

function EditPassword() {
  const router = useRouter()

  const [form, setForm] = useState({
    password: '',
    passwordCheck: '',
  })

  const [error] = useState(false)

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target

    setForm({
      ...form,
      [name]: value,
    })
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    window.alert('준비중인 기능입니다.')
    return

    if (error) {
      return toast.error('비밀번호가 일치하지 않습니다.')
    }

    if (!form.password || !form.passwordCheck) {
      return toast.error('비밀번호를 입력해주세요')
    }

    const request = {
      password: form.password,
      newPassword: form.passwordCheck,
    }

    const result = await userService.userUpdatemPassword(request)

    if (result.status === 200) {
      toast.success('비밀번호가 변경되었습니다.')
      router.push('/mypage')
      return
    }

    toast.error('비밀번호 변경에 실패했습니다.')
  }
  return (
    <Container>
      <Title>비밀번호 수정</Title>
      <Form onSubmit={handleSubmit}>
        <LabelInput
          type={'password'}
          label={'패스워드'}
          name={'password'}
          marginRight={'0px'}
          value={form.password}
          onChange={onChange}
        ></LabelInput>
        <LabelInput
          type={'password'}
          label={'뉴패스워드'}
          name={'passwordCheck'}
          marginRight={'0px'}
          value={form.passwordCheck}
          Errors={error}
          ErrorMessage={'패스워드가 일치하지 않습니다.'}
          onChange={onChange}
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
  display: flex;
  flex-direction: column;
  gap: 20px;
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
