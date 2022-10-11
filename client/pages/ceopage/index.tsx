import SectionTitle from '@/components/StoreDetail/SectionTitle'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import CeoProfileCard from '@/components/CeoPage/CeoProfileCard'
import CompanyInfoCard from '@/components/CeoPage/CompanyInfoCard'
import {ceoService} from '@/apis/CeoPageAPI'
import {CeoInfo, PostRecoil, Reservation} from '@/types/ceopage'
import UserReservationTable from '@/components/CeoPage/UserReservationTable'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import {useRecoilState} from 'recoil'
import {dataState} from '@/recoil/mypage'
import {flexCenter} from '@/styles/css'

function CeoMyPage() {
  const [ceoInfo, setCeoInfo] = useState<CeoInfo>()
  const [post, setPost] = useState<PostRecoil>()
  const [reservations, setReservations] = useState<Reservation[]>()
  const [isChange] = useRecoilState(dataState)
  const [, setTotalLen] = useState(1)

  useEffect(() => {
    ceoService.getCeoPage().then((res) => {
      setCeoInfo({
        ceoName: res.username,
        ceoEmail: res.email,
        ceoPhone: res.phone,
      })
      if (res.postsInfo) {
        setPost({
          id: res.postsInfo.id,
          title: res.postsInfo.title,
          content: res.postsInfo.content,
          thumbnail: res.postsInfo.postsImgList[0].imgUrl,
        })
      } else {
        setPost({
          id: 0,
          title: '',
          content: '',
          thumbnail: '',
        })
      }

      setReservations(res.reservationListPage)
      setTotalLen(res.reservationListPage.length)
    })
  }, [isChange])

  if (!ceoInfo || !post || !reservations) return <LoadingSpinner />

  return (
    <Container>
      <Title>마이페이지</Title>
      <SectionTitle title={'사장님 정보'} />
      <ProfileBox>
        <CeoProfileCard ceo={ceoInfo} post={post} />
      </ProfileBox>
      <SectionTitle title={'점포 정보'} />
      <CompanyInfoBox>
        {post.id === 0 ? (
          <NoContent>등록된 점포가 없습니다.</NoContent>
        ) : (
          <CompanyInfoCard post={post} />
        )}
      </CompanyInfoBox>
      <SectionTitle title={'예약 현황 내역'} />
      <UserReservationTable title={post.title} reservations={reservations} />
    </Container>
  )
}

export default CeoMyPage

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 15px;
`

const Title = styled.h1`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 25px;
  line-height: 36px;
`
const ProfileBox = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 20px;
`
const CompanyInfoBox = styled.div`
  width: 100%;
  margin-bottom: 30px;
`

const NoContent = styled.div`
  width: 100%;
  ${flexCenter};
  font-size: 20px;
  color: gray;
`
