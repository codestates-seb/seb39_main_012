import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import {BsGenderMale, BsGenderFemale} from 'react-icons/bs'
import {RiEditBoxLine} from 'react-icons/ri'
import {IoMdRemoveCircle} from 'react-icons/io'
import {useRecoilState} from 'recoil'
import {editOpenState} from '@/recoil/editOpen'
const imgUrl = 'https://dimg.donga.com/wps/NEWS/IMAGE/2022/01/28/111500268.2.jpg'

function DogProfileCard() {
  const [, setEditOpen] = useRecoilState(editOpenState)
  return (
    <Container>
      <ImgBox>
        <CardImage imgUrl={imgUrl} mode="my"></CardImage>
      </ImgBox>
      <ImageTextBox>
        <DogInfoBox>
          <div>
            <DogName>뽀삐</DogName>
            <DogGender>
              <BsGenderMale />
            </DogGender>
          </div>
          <div>
            <DogAge>10살</DogAge>
            <DogWeight>7.5kg</DogWeight>
          </div>
        </DogInfoBox>
        <DogBreed>불독</DogBreed>
      </ImageTextBox>
      <DogBottomInfoBox>
        <InfoBox>
          <div>
            <InfoTitle>중성화</InfoTitle>
            <InfoContent>완료</InfoContent>
          </div>
          <div>
            <InfoTitle>입질&짖음</InfoTitle>
            <InfoContent>약간 짖음</InfoContent>
          </div>
          <div>
            <InfoTitle>특이사항</InfoTitle>
            <InfoContent>없음</InfoContent>
          </div>
        </InfoBox>
        <InfoBox>
          <div>
            <InfoTitle>배변방식</InfoTitle>
            <InfoContent>실내</InfoContent>
          </div>
          <div>
            <InfoTitle>간식급여</InfoTitle>
            <InfoContent>미급여</InfoContent>
          </div>
        </InfoBox>
        <DeleteButton onClick={() => console.log('삭제')}>
          <IoMdRemoveCircle />
        </DeleteButton>
        <EditButton onClick={() => setEditOpen(true)}>
          <RiEditBoxLine />
        </EditButton>
      </DogBottomInfoBox>
    </Container>
  )
}

export default DogProfileCard

const Container = styled.div`
  width: 300px;
  height: 350px;
  position: relative;
  border-radius: 10px;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  white-space: nowrap;

  @media (max-width: 768px) {
    width: 220px;
    height: 300px;
  }

  @media (max-width: 390px) {
    width: 170px;
    height: 250px;
  }
`
const ImgBox = styled.div`
  height: 65%;
`

const ImageTextBox = styled.div`
  position: absolute;
  width: 100%;
  top: 0;
  font-size: 15px;

  @media (max-width: 768px) {
    font-size: 13px;
  }

  @media (max-width: 390px) {
    font-size: 12px;
  }
`

const DogInfoBox = styled.div`
  display: flex;
  justify-content: space-between;

  & > div {
    display: flex;
    margin: 10px 20px 0 20px;
    align-items: center;
  }
`

const DogName = styled.h2`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 25px;
  line-height: 25px;

  @media (max-width: 768px) {
    font-size: 20px;
  }

  @media (max-width: 390px) {
    font-size: 15px;
  }
`
const DogGender = styled.div`
  font-size: 15px;
  padding-left: 5px;

  @media (max-width: 768px) {
    font-size: 10px;
  }
`
const DogAge = styled.p``
const DogWeight = styled.p`
  margin-left: 5px;
`
const DogBreed = styled.p`
  margin-left: 20px;
`

const DogBottomInfoBox = styled.div`
  display: flex;
  position: absolute;
`
const InfoBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  font-size: 15px;
  margin-top: 10px;

  & > div {
    white-space: nowrap;
    margin: 10px 20px;
    display: flex;
    align-items: center;
    @media (max-width: 768px) {
      margin: 5px 15px;
    }

    @media (max-width: 390px) {
      margin: 3px 10px;
    }
  }
`

const InfoTitle = styled.p`
  font-style: normal;
  font-weight: 700;
  font-size: 13px;
  line-height: 16px;
  color: #444;

  @media (max-width: 768px) {
    font-size: 12px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`
const InfoContent = styled.p`
  margin-left: 10px;
  font-size: 13px;
  line-height: 16px;
  color: #555;

  @media (max-width: 768px) {
    font-size: 13.5px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`

const EditButton = styled.span`
  font-size: 20px;
  position: absolute;
  bottom: 0;
  right: 0;
  color: gray;
  cursor: pointer;
`

const DeleteButton = styled.span`
  font-size: 20px;
  position: absolute;
  bottom: 0;
  right: 25px;
  color: #f25757;
  cursor: pointer;
`
