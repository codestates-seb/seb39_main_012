/* eslint-disable react-hooks/exhaustive-deps */
import SectionTitle from '@/components/StoreDetail/SectionTitle'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import UserProfileCard from '@/components/Mypage/UserProfileCard'
import DogCardSwiper from '@/components/Mypage/DogCardSwiper'
import ReservationProcessing from '@/components/Mypage/ReservationProcessing'
import ReservationSwiper from '@/components/Mypage/ReservationSwiper'
import ReviewTable from '@/components/Mypage/ReviewTable'
import {useRecoilState} from 'recoil'
import {addOpenState, addReviewState, editOpenState} from '@/recoil/editOpen'
import AddDogModal from '@/components/Mypage/AddDogModal'
import {userService} from '@/apis/MyPageAPI'
import {afterReservation, BeforeReservation, DogCard, Review, Users} from '@/types/mypage'
import EditDogModal from '@/components/Mypage/EditDogModal'
import {flexCenter} from '@/styles/css'
import {dataState, userInfoState} from '@/recoil/mypage'
import ReservedSwiper from '@/components/Mypage/ReservedSwiper'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import AddReviewModal from '@/components/Mypage/AddReviewModal'

function MyPage() {
  const [isChange] = useRecoilState(dataState)
  const [editOpen, setEditOpen] = useRecoilState(editOpenState)
  const [addOpen, setAddOpen] = useRecoilState(addOpenState)
  const [addReview, setAddReview] = useRecoilState(addReviewState)
  const [userInfo, setUserInfo] = useState<Users>()
  const [, setUserInfo2] = useRecoilState(userInfoState)

  const [dogs, setDogs] = useState<DogCard[]>()
  const [reviews, setReviews] = useState<Review[]>()
  const [beforeReservations, setBeforeReservations] = useState<BeforeReservation[]>()
  const [afterReservations, setAfterReservations] = useState<afterReservation[]>()

  useEffect(() => {
    ;(async () => {
      const userInfoPromise = userService.getMyPage()
      const beforeReviewPromise = userService.beforeReservations()
      const afterReviewPromise = userService.afterReservations()

      const [userInfo, beforeReview, afterReview] = await Promise.all([
        userInfoPromise,
        beforeReviewPromise,
        afterReviewPromise,
      ])

      setUserInfo(userInfo.data.users)
      setUserInfo2({
        name: userInfo.data.users.username,
        email: userInfo.data.users.email,
        phone: userInfo.data.users.phone,
      })
      setDogs(userInfo.data.users.dogCardList)
      setReviews(userInfo.data.reviewList)
      setBeforeReservations(beforeReview.data.data)
      setAfterReservations(afterReview.data.data)
      console.info(beforeReview.data.data)
      console.info(afterReview.data.data)
    })()
  }, [isChange])

  if (!userInfo || !dogs || !reviews || !beforeReservations || !afterReservations)
    return <LoadingSpinner />

  return (
    <Container>
      {editOpen && <EditDogModal setIsOpen={setEditOpen} />}
      {addOpen && <AddDogModal setIsOpen={setAddOpen} />}
      {addReview && <AddReviewModal setIsOpen={setAddReview} />}
      <Title>마이페이지</Title>
      <SectionTitle title={'견주님 & 반려견 정보'} />
      <UserAndDogBox>
        <UserProfileCard user={userInfo} />
        <DogCardBoxs>
          {dogs.length === 0 ? (
            <NoContent>강아지 카드를 등록해주세요.</NoContent>
          ) : (
            <DogCardSwiper dogs={dogs} />
          )}
        </DogCardBoxs>
      </UserAndDogBox>
      <SectionTitle title={'예약처리 현황'} />
      <ReservationBox>
        <ReservationProcessing count={beforeReservations.length} />
      </ReservationBox>
      <SectionTitle title={'예약된 호캉스 내역'} />
      <ReservatedBox>
        {beforeReservations.length === 0 ? (
          <NoContent>예약된 호캉스가 없습니다.</NoContent>
        ) : (
          <ReservationSwiper beforeReservations={beforeReservations} />
        )}
        {/* <ReservationSwiper beforeReservations={beforeReservations} /> */}
      </ReservatedBox>
      <SectionTitle title={'다녀온 호캉스 내역'} />
      <WentToBox>
        {afterReservations.length === 0 ? (
          <NoContent>다녀온 호캉스가 없습니다.</NoContent>
        ) : (
          <ReservedSwiper afterReservations={afterReservations} />
        )}
      </WentToBox>
      <SectionTitle title={'리뷰관리'} />
      <ReviewTable reviews={reviews} username={userInfo.username} />
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
  margin-bottom: 30px;

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
    padding-left: 3px;
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

const NoContent = styled.div`
  width: 100%;
  ${flexCenter};
  font-size: 20px;
  color: gray;
`
