import SectionTitle from '@/components/StoreDetail/SectionTitle'
import React from 'react'
import styled from 'styled-components'
import {userData} from '@/utils/dummy/dummy'
import UserProfileCard from '@/components/Mypage/UserProfileCard'
import DogCardSwiper from '@/components/Mypage/DogCardSwiper'
import ReservationProcessing from '@/components/Mypage/ReservationProcessing'
import ReservationSwiper from '@/components/Mypage/ReservationSwiper'
import ReviewTable from '@/components/Mypage/ReviewTable'
import {ToastContainer} from 'react-toastify'

function MyPage() {
  return (
    <Container>
      <ToastContainer />
      <Title>마이페이지</Title>
      <SectionTitle title={'견주님 & 반려견 정보'} />
      <UserAndDogBox>
        <UserProfileCard user={userData} />
        <DogCardBoxs>
          <DogCardSwiper />
        </DogCardBoxs>
      </UserAndDogBox>
      <SectionTitle title={'예약처리 현황'} />
      <ReservationBox>
        <ReservationProcessing />
      </ReservationBox>
      <SectionTitle title={'예약된 호캉스 내역'} />
      <ReservatedBox>
        <ReservationSwiper />
      </ReservatedBox>
      <SectionTitle title={'다녀온 호캉스 내역'} />
      <WentToBox>
        <ReservationSwiper />
      </WentToBox>
      <SectionTitle title={'리뷰관리'} />
      <ReviewTable />
    </Container>
  )
}

export default MyPage

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`

const Title = styled.h1`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 25px;
  line-height: 36px;
`

const UserAndDogBox = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  gap: 15px;

  @media (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
  }
`
const DogCardBoxs = styled.div`
  display: flex;
  width: 85%;
  margin-left: 20px;

  .mySwiper {
    width: 100%;
    padding-top: 3px;
    padding-bottom: 3px;
  }

  @media (max-width: 768px) {
    flex-direction: column;
    align-items: center;
    margin-left: 0px;
    width: 100%;
  }
`
const ReservationBox = styled.div`
  margin-bottom: 30px;
`

const ReservatedBox = styled.div`
  width: 100%;
  margin-bottom: 30px;
  .mySwiper {
    padding-top: 3px;
    padding-bottom: 3px;

    @media (max-width: 768px) {
      margin-left: 10px;
    }

    @media (max-width: 390px) {
    }
  }
`

const WentToBox = styled(ReservatedBox)``
