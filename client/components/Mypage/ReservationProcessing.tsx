import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'
import {MdKeyboardArrowRight} from 'react-icons/md'

interface Props {
  count: number
}

function ReservationProcessing({count}: Props) {
  return (
    <Container>
      <Content>
        <Count>0</Count>
        <Title>장바구니</Title>
      </Content>
      <IconBox>
        <MdKeyboardArrowRight />
      </IconBox>
      <Content>
        <Count>0</Count>
        <Title>예약진행중</Title>
      </Content>
      <IconBox>
        <MdKeyboardArrowRight />
      </IconBox>
      <Content>
        <Count>0</Count>
        <Title>결제완료</Title>
      </Content>
      <IconBox>
        <MdKeyboardArrowRight />
      </IconBox>
      <Content>
        <Count>{count}</Count>
        <Title>예약완료</Title>
      </Content>
    </Container>
  )
}

export default ReservationProcessing

const Container = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: ${colors.grey1};
  padding: 10px 0;
`

const Content = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`
const Count = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 30px;
  line-height: 36px;

  @media (max-width: 768px) {
    font-size: 25px;
  }
`
const Title = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 15px;
  line-height: 18px;

  @media (max-width: 768px) {
    font-size: 13px;
  }
`
const IconBox = styled.div`
  font-size: 30px;
  color: ${colors.grey2};

  @media (max-width: 768px) {
    font-size: 20px;
  }
`
