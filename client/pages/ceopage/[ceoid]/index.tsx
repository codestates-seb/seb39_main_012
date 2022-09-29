import SectionTitle from '@/components/StoreDetail/SectionTitle'
import React from 'react'
import styled from 'styled-components'
import {userData} from '@/utils/dummy/dummy'
import CeoProfileCard from '@/components/CeoPage/CeoProfileCard'
import CompanyInfoCard from '@/components/CeoPage/CompanyInfoCard'

function CeoMyPage() {
  return (
    <Container>
      <Title>마이페이지</Title>
      <SectionTitle title={'사장님 정보'} />
      <ProfileBox>
        <CeoProfileCard user={userData} />
      </ProfileBox>
      <SectionTitle title={'점포 정보'} />
      <CompanyInfoCard />
    </Container>
  )
}

export default CeoMyPage

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
const ProfileBox = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 20px;
`
