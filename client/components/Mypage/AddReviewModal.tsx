import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {toLocalScale} from '@/utils/util'
import React, {useEffect, useState} from 'react'
import {Rating} from 'react-simple-star-rating'
import styled from 'styled-components'
import {AiOutlineCamera} from 'react-icons/ai'
import {toast} from 'react-toastify'
import {format} from 'node:path/win32'

interface Props {
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>
}

const imgUrl = 'https://www.qplace.kr/content/images/2021/09/9-14.jpg'

function AddReviewModal({setIsOpen}: Props) {
  const [ratingValue, setRatingValue] = useState(2.5)
  const [selectedFile, setSelectedFile] = useState<any>([])
  const [fileDataURL, setFileDataURL] = useState<any>([])
  const [form, setForm] = useState({
    title: '',
    content: '',
  })

  useEffect(() => {
    console.log(selectedFile)
    let fileReader: FileReader,
      isCancel = false
    if (selectedFile.length > 0) {
      fileReader = new FileReader()
      fileReader.onload = (e) => {
        const {result} = e.target as FileReader
        if (result && !isCancel) {
          setFileDataURL([...fileDataURL, result])
        }
      }
      // selectedFile.forEach((file: any) => {
      //   fileReader.readAsDataURL(file)
      // })
      fileReader.readAsDataURL(selectedFile[selectedFile.length - 1])
    }
    return () => {
      isCancel = true
      if (fileReader && fileReader.readyState === 1) {
        fileReader.abort()
      }
    }
  }, [selectedFile])

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (selectedFile.length >= 3) {
      return toast.error('사진은 최대 3장까지 등록 가능합니다.')
    }
    const file = e.currentTarget.files?.[0]
    if (!file) return alert('파일이 없습니다.')
    setSelectedFile([...selectedFile, file])
  }

  const handleRemoveImg = (index: number) => {
    const newFileDataURL = fileDataURL.filter((_: any, i: number) => i !== index)
    const newSelectedFile = selectedFile.filter((_: any, i: number) => i !== index)
    setFileDataURL(newFileDataURL)
    setSelectedFile(newSelectedFile)
  }

  const handleRatingClick = (rate: number) => {
    setRatingValue(rate)
  }

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const formData = new FormData()

    if (selectedFile) {
      selectedFile.forEach((file: any) => {
        formData.append('files', file)
      })
    }

    const request = {
      rating: ratingValue,
      ...form,
    }

    console.log(request)
  }

  return (
    <ModalContainer onClick={() => setIsOpen(false)}>
      <Modal onClick={(e) => e.stopPropagation()} onSubmit={handleSubmit}>
        <Title>상품 후기</Title>
        <ProductInfo>
          <ImgBox>
            <img src={imgUrl} alt="" />
          </ImgBox>
          <ContentBox>
            <Rating
              onClick={handleRatingClick}
              ratingValue={ratingValue}
              initialValue={50}
              allowHalfIcon
              size={40}
              fillColor={colors.mainColor}
            />
            <ProductName>JW메리어트 멍멍스퀘어, 서울</ProductName>
            <ProductPrice>{toLocalScale(30000)}원 / 2박</ProductPrice>
          </ContentBox>
        </ProductInfo>
        <TextAreaBox>
          <Input
            type={'text'}
            value={form.title}
            name={'title'}
            onChange={(e) => setForm({...form, [e.target.name]: e.target.value})}
          ></Input>
          <TextArea
            value={form.content}
            name={'content'}
            onChange={(e) => setForm({...form, [e.target.name]: e.target.value})}
          />
        </TextAreaBox>
        <ReviewImgBox>
          <label htmlFor="dogImg">
            <AiOutlineCamera size={30} />
            사진{selectedFile.length}/3
          </label>
          <input
            type="file"
            id="dogImg"
            onChange={handleFileChange}
            accept=".png, .jpg, .jpeg"
            multiple
          />
          <ReviewImgs>
            {fileDataURL.map((url: any, idx: number) => (
              <ReviewImg key={idx} src={url} alt="ReviewImg" onClick={() => handleRemoveImg(idx)} />
            ))}
          </ReviewImgs>
        </ReviewImgBox>
        <Button>완료</Button>
      </Modal>
    </ModalContainer>
  )
}

export default AddReviewModal

const ModalContainer = styled.div`
  ${flexCenter}
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 2;
`

const Modal = styled.form`
  width: 450px;
  background-color: white;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  padding: 20px;
`

const Title = styled.div`
  font-size: 20px;
  font-weight: bold;
  margin-top: 20px;
  margin-left: 10px;
`

const ProductInfo = styled.div`
  display: flex;
  margin-top: 20px;
`

const ImgBox = styled.div`
  img {
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 35px;
  }
`
const ContentBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  justify-content: center;
  gap: 15px;
`
const ProductName = styled.h1`
  font-size: 15px;
  font-weight: bold;
`

const ProductPrice = styled.p`
  font-size: 20px;
  font-weight: bold;
`

const TextAreaBox = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
`

const Input = styled.input`
  margin-top: 20px;
  width: 90%;
  height: 15px;
  border-radius: 10px;
  padding: 20px;
  border: none;
  background-color: ${colors.grey1};
  &:focus {
    outline: none;
  }
`

const TextArea = styled.textarea`
  margin-top: 25px;
  width: 90%;
  height: 180px;
  border: none;
  border-radius: 10px;
  padding: 20px;
  background-color: ${colors.grey1};
  resize: none;
  font-size: 15px;
  &:focus {
    outline: none;
  }
`

const ReviewImgBox = styled.div`
  display: flex;
  margin-top: 20px;

  label {
    ${flexCenter}
    flex-direction: column;
    cursor: pointer;
    border-radius: 5px;
    width: 60px;
    height: 60px;
    border: solid 1px lightgray;
    font-size: 12px;
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

const ReviewImgs = styled.div`
  display: flex;
  gap: 10px;
  margin-left: 5px;
`

const ReviewImg = styled.img`
  border-radius: 5px;
  width: 60px;
  height: 60px;
  object-fit: cover;
  cursor: pointer;
  border: solid 1px lightgray;
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
