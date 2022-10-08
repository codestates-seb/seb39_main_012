import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import {BsGenderMale} from 'react-icons/bs'
import {toLocalScale} from '@/utils/util'
import {ConfirmBook} from '@/types/book'
import {toast} from 'react-toastify'
import {useRouter} from 'next/router'
import {bookingService} from '@/apis/bookingAPI'

interface Props {
  dogInfo: ConfirmBook
  id: number
}

function ConfirmCard({dogInfo, id}: Props) {
  const router = useRouter()
  const cancelBooking = (id: number) => {
    bookingService.deleteBooking(id).then((res) => {
      if (res.status === 204) {
        toast.success('예약이 취소되었습니다.')
        router.push('/')
      }
    })
  }
  return (
    <Container>
      <ImgBox>
        <CardImage imgUrl={dogInfo.photoImgUrl} mode="my"></CardImage>
      </ImgBox>
      <ImageTextBox>
        <DogInfoBox>
          <div>
            <DogName>{dogInfo.dogName}</DogName>
            <DogGender>
              <BsGenderMale />
            </DogGender>
          </div>
          <div>
            <DogAge>10살</DogAge>
            <DogWeight>{dogInfo.weight}kg</DogWeight>
          </div>
        </DogInfoBox>
        <DogBreed>불독</DogBreed>
      </ImageTextBox>
      <DogBottomInfoBox>
        <InfoBox>
          <div>
            <InfoTitle>중성화</InfoTitle>
            <InfoContent>{dogInfo.surgery}</InfoContent>
          </div>
          <div>
            <InfoTitle>입질&짖음</InfoTitle>
            <InfoContent>{dogInfo.bark}</InfoContent>
          </div>
          <div>
            <InfoTitle>특이사항</InfoTitle>
            <InfoContent>{dogInfo.etc}</InfoContent>
          </div>
        </InfoBox>
        <InfoBox>
          <div>
            <InfoTitle>배변방식</InfoTitle>
            <InfoContent>{dogInfo.bowelTrained}</InfoContent>
          </div>
          <div>
            <InfoTitle>간식급여</InfoTitle>
            <InfoContent>{dogInfo.snackMethod}</InfoContent>
          </div>
        </InfoBox>
      </DogBottomInfoBox>
      <UserInfoBox>
        <DesignTitle className="DesignTitle">우리 {dogInfo.dogName} 호캉스 가는날!</DesignTitle>
        <UserNameAndTell>
          <Name>
            <p className="title">예약자</p>
            <p className="content">{dogInfo.name}</p>
          </Name>
          <Tell>
            <p className="title">연락처</p>
            <p>{dogInfo.phone}</p>
          </Tell>
        </UserNameAndTell>
        <UserAddress>
          <p className="title">숙소</p>
          <p>{dogInfo.address}</p>
        </UserAddress>
        <Check>
          <p className="title">체크인</p>
          <p>{dogInfo.checkInDate + '' + dogInfo.checkInTime}</p>
        </Check>
        <Check>
          <p className="title">체크아웃</p>
          <p>{dogInfo.checkOutDate + '' + dogInfo.checkOutTime}</p>
        </Check>
        <TotalPrice>
          <p className="title">총 결제 금액(VAT포함)</p>
          <p>{toLocalScale(dogInfo.totalPrice)}원</p>
        </TotalPrice>
        <RejectButton onClick={() => cancelBooking(id)}>예약 취소</RejectButton>
      </UserInfoBox>
    </Container>
  )
}

export default ConfirmCard

const Container = styled.div`
  width: 300px;
  position: relative;
  border-radius: 10px;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  white-space: nowrap;

  @media (max-width: 768px) {
    width: 220px;
  }

  @media (max-width: 390px) {
    width: 170px;
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
    font-size: 13px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`

const UserInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  font-size: 13px;
  gap: 20px;

  .title {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 500;
    line-height: 16px;
    color: #444;
  }

  .content {
    font-family: 'Inter';
    line-height: 16px;
    color: #555;
  }
`
const DesignTitle = styled.div`
  font-family: 'Nanum Pen Script', cursive;
  font-size: 30px;
`

const UserNameAndTell = styled.div`
  display: flex;
  justify-content: space-between;
`

const Name = styled.div``
const Tell = styled.div`
  padding-right: 30px;
`

const UserAddress = styled.div``
const Check = styled.div``

const TotalPrice = styled.div``
const RejectButton = styled.button`
  padding: 10px;
  border-radius: 15px;
  border: none;
  color: white;
  cursor: pointer;
`
