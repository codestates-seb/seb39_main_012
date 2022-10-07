/* eslint-disable @next/next/no-img-element */
import React from 'react'
import styled from 'styled-components'
import {RiEditBoxLine} from 'react-icons/ri'
import {flexCenter} from '@/styles/css'
import {useRecoilState} from 'recoil'
import {addOpenState} from '@/recoil/editOpen'
import {Users} from '@/types/mypage'
import {useRouter} from 'next/router'

interface Props {
  user: Users
}

function UserProfileCard({user}: Props) {
  const router = useRouter()
  const [, setAddOpen] = useRecoilState(addOpenState)
  return (
    <Container>
      <UserInfoBoxs onClick={() => router.push('profile_edit')}>
        <UserImgBox>
          <img
            src={'https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png'}
            alt="UserprofillImg"
          />
        </UserImgBox>
      </UserInfoBoxs>
      <UserInfoBox>
        <UserName>
          <span>견주</span>
          <p>{user.username}</p>
        </UserName>
        <UserTell>{user.phone}</UserTell>
        <UserEmail>{user.email}</UserEmail>
        <AddDogButton onClick={() => setAddOpen(true)}>
          <p>반려견 추가하기</p>
          <RiEditBoxLine />
        </AddDogButton>
      </UserInfoBox>
    </Container>
  )
}

export default UserProfileCard

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
  &:hover::after {
    content: '프로필 수정';
    position: absolute;
    ${flexCenter}
    top: 0;
    left: 0;
    font-size: 20px;
    font-weight: 600;
    color: #fff;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 50%;
    cursor: pointer;
  }
`

const UserInfoBoxs = styled.div`
  position: relative;
`

const UserInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 15px;
  margin-top: 10px;
  gap: 8px;
`

const UserName = styled.div`
  display: flex;
  align-items: center;
  gap: 3px;

  span {
    white-space: nowrap;
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
