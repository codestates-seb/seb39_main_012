import {bookingService} from '@/apis/bookingAPI'
import ConfirmCardSwiper from '@/components/BookConfirm/ConfirmCardSwiper'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import SectionTitle from '@/components/StoreDetail/SectionTitle'
import {ConfirmBook} from '@/types/book'
import {useRouter} from 'next/router'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'

function BookConfirm() {
  const router = useRouter()
  const id = Number(router.query.id)

  const [dogDatas, setDogDatas] = useState<ConfirmBook[]>()
  useEffect(() => {
    if (id) {
      bookingService.confirmBooking(id).then((res) => {
        setDogDatas(res)
      })
    }
  }, [id])

  if (!dogDatas) return <LoadingSpinner></LoadingSpinner>

  return (
    <Container>
      <Title>예약내역</Title>
      <SectionTitle title={'예약자정보'} />
      <DogCardBoxs>
        <ConfirmCardSwiper dogDatas={dogDatas} id={id} />
      </DogCardBoxs>
    </Container>
  )
}

export default BookConfirm

const Container = styled.div`
  width: 100%;
  height: 100%;
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
const DogCardBoxs = styled.div`
  display: flex;
  width: 100%;
  margin-bottom: 30px;

  .mySwiper {
    padding-left: 3px;
    padding-top: 3px;
    padding-bottom: 3px;

    @media (max-width: 768px) {
      margin-left: 10px;
    }

    @media (max-width: 390px) {
    }
  }
`
