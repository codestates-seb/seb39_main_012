/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @next/next/no-img-element */
import {userService} from '@/apis/MyPageAPI'
import LabelInput from '@/components/LabelInput/LabelInput'
import {userInfoState} from '@/recoil/mypage'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {useRouter} from 'next/router'
import React, {useEffect, useState} from 'react'
import {BsPlusCircleFill} from 'react-icons/bs'
import {toast} from 'react-toastify'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'
import LocalStorage from '@/utils/util/localStorage'

function ProfileEdit() {
  const router = useRouter()
  const [userInfo] = useRecoilState(userInfoState)

  const [form, setForm] = useState({
    name: userInfo.name,
    phone: userInfo.phone,
    email: userInfo.email,
  })

  const [selectedFile, setSelectedFile] = useState<any>()
  const [fileDataURL, setFileDataURL] = useState<any>(null)

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target
    setForm({
      ...form,
      [name]: value,
    })
  }

  useEffect(() => {
    let fileReader: FileReader,
      isCancel = false
    if (selectedFile) {
      fileReader = new FileReader()
      fileReader.onload = (e) => {
        const {result} = e.target as FileReader
        if (result && !isCancel) {
          setFileDataURL(result)
        }
      }
      fileReader.readAsDataURL(selectedFile)
    }
    return () => {
      isCancel = true
      if (fileReader && fileReader.readyState === 1) {
        fileReader.abort()
      }
    }
  }, [selectedFile])

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.currentTarget.files?.[0]
    if (!file) return alert('파일이 없습니다.')
    setSelectedFile(file)
  }

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const formData = new FormData()
    formData.append('file', selectedFile)
    formData.append('dto', new Blob([JSON.stringify(form)], {type: 'application/json'}))

    const result = await userService.userProfileEdit(formData)
    const jwt = result.data.etc
    LocalStorage.setItem('accessToken', jwt)

    if (result.status === 200) {
      toast.success('프로필 수정이 완료되었습니다.')
      router.push('/mypage')
    }
  }

  return (
    <Container>
      <Title>프로필 수정</Title>
      <Form onSubmit={onSubmit}>
        <UserImgBox imgUrl={fileDataURL}>
          <label htmlFor="dogImg">
            <BsPlusCircleFill />
            {selectedFile && <img src={fileDataURL} alt="preview" />}
          </label>
          <input
            type="file"
            id="dogImg"
            onChange={handleFileChange}
            accept=".png, .jpg, .jpeg"
            multiple
          />
        </UserImgBox>
        <LabelInput
          type={'text'}
          label={'닉네임'}
          name={'name'}
          marginRight={'0px'}
          value={form.name}
          onChange={onChange}
        ></LabelInput>
        <LabelInput
          type={'text'}
          label={'전화번호'}
          name={'phone'}
          marginRight={'0px'}
          value={form.phone}
          onChange={onChange}
        ></LabelInput>
        <Button type="submit">완료</Button>
      </Form>
      <EditPassword onClick={() => router.push('/edit_password')}>비밀번호 수정하기</EditPassword>
    </Container>
  )
}

export default ProfileEdit

const Container = styled.div`
  margin-top: 30px;
  /* background-color: skyblue; */
  height: 500px;
`

const Title = styled.h1`
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 1rem;
`

const UserImgBox = styled.div<{imgUrl: string}>`
  width: 25%;

  label {
    cursor: pointer;
    ${flexCenter}
    width: 100px;
    height: 100px;
    background-color: ${colors.grey1};
    border-radius: 30px;
    font-size: 30px;
    color: lightgray;
  }

  img {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 50%;
  }

  & input[type='file'] {
    /* 파일 필드 숨기기 */
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
  }
`

const Form = styled.form`
  border: solid 1px ${colors.grey1};
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  gap: 20px;
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
const EditPassword = styled.span`
  margin-top: 30px;
  color: ${colors.grey3};
  font-size: 15px;
  display: flex;
  cursor: pointer;
`
