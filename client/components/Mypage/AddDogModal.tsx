/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @next/next/no-img-element */
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import {BsPlusCircleFill} from 'react-icons/bs'
import LabelInput from '../LabelInput/LabelInput'
import LabelRadioButton from './LabelRadioButton'
import {toast} from 'react-toastify'
import {userService} from '@/apis/MyPageAPI'
import {useRecoilState} from 'recoil'
import {dataState} from '@/recoil/mypage'

interface Props {
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>
}

function AddDogModal({setIsOpen}: Props) {
  const [isChange, setIsChange] = useRecoilState(dataState)
  const [selectedFile, setSelectedFile] = useState<any>()
  const [fileDataURL, setFileDataURL] = useState<any>(null)

  const [dogInfo, setDogInfo] = useState({
    name: '',
    type: '',
    age: '',
    gender: false,
    weight: '',
    surgery: false,
    bowel_trained: false,
    snack_method: false,
    etc: false,
    bark: false,
  })

  const [extraInfo, setExtraInfo] = useState({
    _etc: '',
    _bark: '',
  })

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

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target
    setDogInfo({
      ...dogInfo,
      [name]: value,
    })
  }

  const onClick = (name: string, value: boolean) => {
    setDogInfo({
      ...dogInfo,
      [name]: value,
    })

    if (name === 'etc' && value === false) {
      setExtraInfo({
        ...extraInfo,
        _etc: '',
      })
    }

    if (name === 'bark' && value === false) {
      setExtraInfo({
        ...extraInfo,
        _bark: '',
      })
    }
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    if (!dogInfo.name || !dogInfo.age || !dogInfo.weight || !dogInfo.type) {
      return toast.error('모든 항목을 입력해주세요.')
    }

    if (
      dogInfo.name.trim().length === 0 ||
      dogInfo.age.trim().length === 0 ||
      dogInfo.weight.trim().length === 0 ||
      dogInfo.type.trim().length === 0
    ) {
      return toast.error('모든 항목을 입력해주세요.')
    }

    const requestForm = {
      dogName: dogInfo.name,
      type: dogInfo.type,
      age: dogInfo.age,
      weight: dogInfo.weight,
      gender: dogInfo.gender ? '수컷' : '암컷',
      surgery: dogInfo.surgery ? '완료' : '미완료',
      bowelTrained: dogInfo.bowel_trained ? '실내' : '실외',
      snackMethod: dogInfo.snack_method ? '급여' : '미급여',
      etc: dogInfo.etc ? extraInfo._etc : '없음',
      bark: dogInfo.bark ? extraInfo._bark : '없음',
    }

    const formData = new FormData()

    // https://shrewd.tistory.com/43
    formData.append('file', selectedFile)
    formData.append(
      'dogCardDto',
      new Blob([JSON.stringify(requestForm)], {type: 'application/json'})
    )

    const result = await userService.createDogCard(formData)

    if (result?.status === 201) {
      toast.success('등록되었습니다.')
      setIsOpen(false)
      setIsChange(!isChange)
    }
  }

  return (
    <AddDogModalContainer onClick={() => setIsOpen(false)}>
      <Modal onClick={(e) => e.stopPropagation()} onSubmit={handleSubmit}>
        <Title>반려견 정보</Title>
        <DogInfoBox>
          <DogImgBox imgUrl={fileDataURL}>
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
          </DogImgBox>
          <DogInfo>
            <LabelInput
              type={'text'}
              label={'이름'}
              name={'name'}
              marginRight={'0px'}
              value={dogInfo.name}
              onChange={onChange}
            ></LabelInput>
            <LabelInput
              type={'text'}
              label={'견종'}
              name={'type'}
              marginRight={'0px'}
              value={dogInfo.type}
              onChange={onChange}
            ></LabelInput>
            <LabelInput
              type={'number'}
              label={'나이'}
              name={'age'}
              marginRight={'0px'}
              value={dogInfo.age}
              onChange={onChange}
            ></LabelInput>
            <LabelInput
              type={'number'}
              label={'몸무게'}
              name={'weight'}
              marginRight={'0px'}
              value={dogInfo.weight}
              onChange={onChange}
            ></LabelInput>
          </DogInfo>
        </DogInfoBox>
        <Border />
        <DogInfoBoxBody>
          {/* 레프트 벨류 */}
          <DogInfoBodyLeft>
            <LabelRadioButton
              label={'중성화'}
              name={'surgery'}
              value1={'완료'}
              value2={'미완료'}
              onClick={onClick}
              isChecked={dogInfo.surgery}
            />
            <LabelRadioButton
              label={'간식급여'}
              name={'snack_method'}
              value1={'급여'}
              value2={'미급여'}
              onClick={onClick}
              isChecked={dogInfo.snack_method}
            />
            <LabelRadioButton
              label={'특이사항'}
              name={'etc'}
              value1={'있음'}
              value2={'없음'}
              onClick={onClick}
              isChecked={dogInfo.etc}
            />
            <LabelRadioButton
              label={'입질&짖음'}
              name={'bark'}
              value1={'있음'}
              value2={'없음'}
              onClick={onClick}
              isChecked={dogInfo.bark}
            />
          </DogInfoBodyLeft>
          {/* 라이트 벨류 */}
          <DogInfoBodyRight>
            <LabelRadioButton
              label={'배변방식'}
              name={'bowel_trained'}
              value1={'실내'}
              value2={'실외'}
              onClick={onClick}
              isChecked={dogInfo.bowel_trained}
            />
            <LabelRadioButton
              label={'성별'}
              name={'gender'}
              value1={'수컷'}
              value2={'암컷'}
              onClick={onClick}
              isChecked={dogInfo.gender}
            />
            <Input
              placeholder="(특이사항 입력)"
              disabled={!dogInfo.etc}
              value={extraInfo._etc}
              onChange={(e) =>
                setExtraInfo({
                  ...extraInfo,
                  _etc: e.target.value,
                })
              }
            />
            <Input
              placeholder="(입질&짖음 정도 입력)"
              disabled={!dogInfo.bark}
              value={extraInfo._bark}
              onChange={(e) =>
                setExtraInfo({
                  ...extraInfo,
                  _bark: e.target.value,
                })
              }
            />
          </DogInfoBodyRight>
        </DogInfoBoxBody>
        <Button type="submit">완료</Button>
      </Modal>
    </AddDogModalContainer>
  )
}

export default AddDogModal

const AddDogModalContainer = styled.div`
  position: fixed;
  ${flexCenter}
  z-index: 2;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.3);
  width: 100vw;
  height: 100vh;
`

const Modal = styled.form`
  display: flex;
  flex-direction: column;
  width: 450px;
  background-color: white;
  border-radius: 10px;
  padding: 20px;
`

const Title = styled.div`
  font-size: 20px;
  font-weight: bold;
`
const DogInfoBox = styled.div`
  display: flex;
  margin-top: 20px;
`

const DogImgBox = styled.div<{imgUrl: string}>`
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
const DogInfo = styled.div`
  width: 75%;
  display: flex;
  flex-direction: column;
`
const Border = styled.div`
  margin-top: 20px;
  width: 100%;
  height: 1px;
  background-color: lightgray;
`

const DogInfoBoxBody = styled.div`
  display: flex;
  margin-top: 20px;
`

const DogInfoBodyLeft = styled.div`
  width: 50%;
  display: flex;
  flex-direction: column;
  gap: 20px;
`

const DogInfoBodyRight = styled(DogInfoBodyLeft)`
  width: 50%;
`

const Input = styled.input`
  width: 190px;
  height: 30px;
  padding: 0 10px;
  border: 1px solid lightgray;
  border-radius: 5px;
  ::placeholder {
    text-align: center;
    color: lightgray;
  }

  :focus {
    outline: none;
  }
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
