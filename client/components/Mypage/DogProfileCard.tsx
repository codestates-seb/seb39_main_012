import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import {BsGenderMale, BsGenderFemale} from 'react-icons/bs'
import {RiEditBoxLine} from 'react-icons/ri'
import {IoMdRemoveCircle} from 'react-icons/io'
import {useRecoilState} from 'recoil'
import {editOpenState} from '@/recoil/editOpen'
import {DogCard} from '@/types/mypage'
import {dataState, dogIdState} from '@/recoil/mypage'
import {userService} from '@/apis/MyPageAPI'
import {toast} from 'react-toastify'

interface Props {
  dog: DogCard
}

function DogProfileCard({dog}: Props) {
  const [isChange, setIsChange] = useRecoilState(dataState)
  const [, setEditOpen] = useRecoilState(editOpenState)
  const [, setDogId] = useRecoilState(dogIdState)
  return (
    <Container>
      <Buttons>
        <DeleteButton
          onClick={async () => {
            if (window.confirm('정말 삭제하시겠습니까?')) {
              const result = await userService.deleteMyDogById(dog.id)
              console.log(result)
              if (result.status === 200) {
                setIsChange(!isChange)
                toast.success('삭제되었습니다.')
                return
              }
              toast.error('삭제에 실패했습니다.')
            }
          }}
        >
          <IoMdRemoveCircle />
        </DeleteButton>
        <EditButton
          onClick={() => {
            setEditOpen(true)
            setDogId(dog.id)
          }}
        >
          <RiEditBoxLine />
        </EditButton>
      </Buttons>
      <ImgBox>
        <CardImage imgUrl={dog.photoImgUrl} mode="my"></CardImage>
      </ImgBox>
      <ImageTextBox>
        <DogInfoBox>
          <div>
            <DogName>{dog.dogName}</DogName>
            <DogGender>{dog.gender === '수컷' ? <BsGenderMale /> : <BsGenderFemale />}</DogGender>
          </div>
          <div>
            <DogAge>{dog.age}살</DogAge>
            <DogWeight>{dog.weight}kg</DogWeight>
          </div>
        </DogInfoBox>
        <DogBreed>{dog.type}</DogBreed>
      </ImageTextBox>
      <DogBottomInfoBox>
        <InfoBox>
          <div>
            <InfoTitle>중성화</InfoTitle>
            <InfoContent>{dog.surgery}</InfoContent>
          </div>
          <div>
            <InfoTitle>입질&짖음</InfoTitle>
            <InfoContent>{dog.bark}</InfoContent>
          </div>
          <div>
            <InfoTitle>특이사항</InfoTitle>
            <InfoContent>{dog.etc}</InfoContent>
          </div>
        </InfoBox>
        <InfoBox>
          <div>
            <InfoTitle>배변방식</InfoTitle>
            <InfoContent>{dog.bowelTrained}</InfoContent>
          </div>
          <div>
            <InfoTitle>간식급여</InfoTitle>
            <InfoContent>{dog.snackMethod}</InfoContent>
          </div>
        </InfoBox>
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

const Buttons = styled.div`
  display: flex;
  position: absolute;
  bottom: 0;
  right: 20px;
`

const EditButton = styled.div`
  z-index: 20;
  font-size: 20px;
  color: gray;
  cursor: pointer;
`

const DeleteButton = styled.div`
  z-index: 20;
  right: 40px;
  font-size: 20px;
  color: #f25757;
  cursor: pointer;
`
