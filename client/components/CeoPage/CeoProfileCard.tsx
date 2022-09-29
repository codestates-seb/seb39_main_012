/* eslint-disable @next/next/no-img-element */
import React from 'react'
import styled from 'styled-components'
import {RiEditBoxLine} from 'react-icons/ri'

interface User {
  profileImg: string
  username: string
  phone: string
  email: string
}

interface Props {
  user: User
}

function CeoProfileCard({user}: Props) {
  const {profileImg, username, phone, email} = user

  return (
    <Container>
      <UserImgBox>
        <img src={profileImg} alt="UserprofillImg" />
      </UserImgBox>
      <UserInfoBox>
        <UserName>
          <span>견주</span>
          <p>{username}</p>
        </UserName>
        <UserTell>{phone}</UserTell>
        <UserEmail>{email}</UserEmail>
        <AddDogButton>
          <p>업체 등록하기</p>
          <RiEditBoxLine />
        </AddDogButton>
      </UserInfoBox>
    </Container>
  )
}

export default CeoProfileCard

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;

  @media (max-width: 768px) {
    flex-direction: row;
    align-items: center;
    margin-left: 20px;
  }
`

const UserImgBox = styled.div`
  img {
    width: 180px;
    height: 180px;
    border-radius: 50%;
    object-fit: cover;

    @media (max-width: 768px) {
      width: 160px;
      height: 160px;
    }

    @media (max-width: 390px) {
      width: 130px;
      height: 130px;
    }
  }
`

const UserInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 15px;
  margin-top: 10px;
  gap: 8px;
`

const UserName = styled.div`
  display: flex;
  align-items: center;
  gap: 3px;

  span {
    font-weight: lighter;
    font-size: 15px;
  }
  p {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 800;
    font-size: 20px;
    line-height: 24px;
  }
`
const UserTell = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;

  line-height: 18px;
`
const UserEmail = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 15px;
  line-height: 18px;
`

const AddDogButton = styled.button`
  display: flex;
  align-items: center;
  color: gray;
  background-color: transparent;
  border: none;
  text-decoration: underline;
  cursor: pointer;
`
