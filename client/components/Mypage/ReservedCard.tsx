import {afterReservation} from '@/types/mypage'
import {toLocalScale} from '@/utils/util'
import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import ReservedTag from './ReservedTag'

interface Props {
  reservation: afterReservation
}

function ReservedCard({reservation}: Props) {
  return (
    <Contaienr>
      <CardImage mode="post" imgUrl={reservation.url}></CardImage>
      <Title>{reservation.title}</Title>
      <Content>
        <Price>{toLocalScale(reservation.roomPrice)}원 / 1박</Price>
        <ReservedTag title={'리뷰달기'} reservation={reservation} />
      </Content>
    </Contaienr>
  )
}

export default ReservedCard

const Contaienr = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: 250px;

  @media (max-width: 768px) {
    width: 200px;
  }

  @media (max-width: 390px) {
    width: 170px;
  }
`

const Title = styled.h1`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 15px;
  line-height: 18px;
  display: flex;

  @media (max-width: 768px) {
    font-size: 12px;
  }
`
const Content = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
`

const Price = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 15px;
  line-height: 18px;
`
