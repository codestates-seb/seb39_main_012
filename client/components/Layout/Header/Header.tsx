import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import Search from './Search'
import Link from 'next/link'
import {colors} from '@/styles/colors'
import {useRouter} from 'next/router'
import Image from 'next/image'
import MoblieLogo from '@/public/svg/MoblieLogo.png'
import Logo from '@/public/svg/Logoimg.png'
import {useScreenX} from '@/hooks/useScreenX'
import {GiHamburgerMenu} from 'react-icons/gi'

function Header() {
  const router = useRouter()
  const {screenX} = useScreenX()
  const [isMenuOpen, setIsMenuOpen] = useState(false)

  return (
    <Container>
      <LogoBox onClick={() => router.push('/')}>
        <Image src={screenX <= 890 ? MoblieLogo : Logo} objectFit="cover" />
      </LogoBox>
      <Search />
      <TextBox>
        <Link href="/login" passHref>
          <StyledLink>로그인</StyledLink>
        </Link>
        <Link href="/signup" passHref>
          <StyledLink>회원가입</StyledLink>
        </Link>
        <Link href="/login" passHref>
          <StyledLink>예약내역</StyledLink>
        </Link>
      </TextBox>
      <HamburgerBox onClick={() => setIsMenuOpen((pre) => !pre)}>
        <GiHamburgerMenu />
      </HamburgerBox>
      {isMenuOpen && (
        <MobileMenu>
          <Link href="/login" passHref>
            <MobileLink>로그인</MobileLink>
          </Link>
          <Link href="/signup" passHref>
            <MobileLink>회원가입</MobileLink>
          </Link>
          <Link href="/login" passHref>
            <MobileLink>예약내역</MobileLink>
          </Link>
        </MobileMenu>
      )}
    </Container>
  )
}

export default Header

const Container = styled.div`
  position: relative;
  width: 100%;
  height: 150px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  @media (max-width: 768px) {
    height: 100px;
  }
  @media (max-width: 390px) {
    height: 75px;
  }
`
const LogoBox = styled.div`
  height: 43px;
  cursor: pointer;
`

const TextBox = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  @media (max-width: 890px) {
    display: none;
  }
`
const StyledLink = styled.a`
  font-size: 25px;
  font-weight: 600;
  font-family: 'Inter';
  font-style: normal;
  line-height: 30px;
  color: #000000;
  text-decoration: none;
  white-space: nowrap;
  &:hover {
    color: ${colors.mainColor};
  }
  @media (max-width: 1100px) {
    font-size: 17px;
  }
  @media (max-width: 890px) {
    font-size: 15px;
  }
`
const HamburgerBox = styled.div`
  margin-top: 1.5rem;
  font-size: 3rem;
  cursor: pointer;
  @media (min-width: 768px) {
    display: none;
  }
`

const MobileMenu = styled.div`
  position: absolute;
  bottom: -80px;
  width: 100%;
  background-color: #fff;

  animation: growDown 250ms ease-in;
  transform-origin: top center;

  display: flex;
  flex-direction: column;

  text-align: center;

  @keyframes growDown {
    0% {
      transform: scaleY(0);
    }
    100% {
      transform: scaleY(1);
    }
  }
  @media (min-width: 768px) {
    display: none;
  }
`
const MobileLink = styled(StyledLink)``
