import SectionTitle from '@/components/StoreDetail/SectionTitle'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import UserProfileCard from '@/components/Mypage/UserProfileCard'
import DogCardSwiper from '@/components/Mypage/DogCardSwiper'
import ReservationProcessing from '@/components/Mypage/ReservationProcessing'
import ReservationSwiper from '@/components/Mypage/ReservationSwiper'
import ReviewTable from '@/components/Mypage/ReviewTable'
import {ToastContainer} from 'react-toastify'
import {useRecoilState} from 'recoil'
import {addOpenState, editOpenState} from '@/recoil/editOpen'
import AddDogModal from '@/components/Mypage/AddDogModal'
import {userService} from '@/apis/MyPageAPI'
import {DogCard, Users} from '@/types/mypage'
import EditDogModal from '@/components/Mypage/EditDogModal'
import {flexCenter} from '@/styles/css'
import Dialog from '@/components/Dialog/Dialog'

function MyPage() {
  const [editOpen, setEditOpen] = useRecoilState(editOpenState)
  const [addOpen, setAddOpen] = useRecoilState(addOpenState)
  const [userInfo, setUserInfo] = useState<Users>()
  const [dogs, setDogs] = useState<DogCard[]>()
  // const [dogs, setDogs] = useState<DogCard[]>()

  useEffect(() => {
    userService.getMyPage().then((res) => {
      setUserInfo(res.users)
      setDogs(res.dogCardList)
      console.log(res.dogCardList)
    })
  }, [editOpen, addOpen])

  if (!userInfo || !dogs) {
    return <div>로딩중</div>
  }

  return (
    <Container>
      <ToastContainer />
      {editOpen && <EditDogModal setIsOpen={setEditOpen} />}
      {addOpen && <AddDogModal setIsOpen={setAddOpen} />}
      {/* <Dialog
        title="정말 삭제하시겠습니까?"
        confirm={() => console.log('삭제')}
        cancel={() => console.log('취소')}
      /> */}
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
