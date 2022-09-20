import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import Link from 'next/link'
import {useRouter} from 'next/router'
import {colors} from '@/styles/colors'
import Image from 'next/image'
import SearchBar from './SearchBar'
import {GiHamburgerMenu} from 'react-icons/gi'
import {BsSearch} from 'react-icons/bs'

const Header = () => {
  const router = useRouter()
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const [isLogin, setIsLogin] = useState(false)

  return (
    <Container>
      <Wrapper>
        <HeaderLogo>
          <MobileHeaderWrapper>
            <BsSearch className="MobileSearchIcon" onClick={() => router.push('/')} />
          </MobileHeaderWrapper>
          <DesktopHeaderWrapper className="DesktopLogo">
            <Image
              src="/svg/DesktopLogo.png"
              width={190}
              height={40}
              onClick={() => router.push('/')}
            />
          </DesktopHeaderWrapper>
        </HeaderLogo>
        <HeaderSearch>
          <MobileHeaderWrapper className="MobileLogo">
            <Image
              src="/svg/DesktopLogo.png"
              width={170}
              height={35}
              onClick={() => router.push('/')}
            />
          </MobileHeaderWrapper>
          <DesktopHeaderWrapper className="DesktopSearchBar">
            <SearchBar />
          </DesktopHeaderWrapper>
        </HeaderSearch>
        <HeaderMenus>
          <Link href="/login">
            <HeaderMenu>로그인</HeaderMenu>
          </Link>
          <Link href="/signup">
            <HeaderMenu>회원가입</HeaderMenu>
          </Link>
          <Link href="/login">
            <HeaderMenu>예약내역</HeaderMenu>
          </Link>
        </HeaderMenus>
        <HamburgerBox onClick={() => setIsMenuOpen(!isMenuOpen)}>
          <GiHamburgerMenu className="HamburgerMenuIcon" />
        </HamburgerBox>
        {isMenuOpen && !isLogin && (
          <MobileMenus>
            <Link href="/login">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>로그인</MobileMenu>
            </Link>
            <Link href="/signup">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>회원가입</MobileMenu>
            </Link>
            <Link href="/login">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>예약내역</MobileMenu>
            </Link>
          </MobileMenus>
        )}
        {isMenuOpen && isLogin && (
          <MobileMenus>
            <Link href="/">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>마이페이지</MobileMenu>
            </Link>
            <Link href="/">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>예약내역</MobileMenu>
            </Link>
            <Link href="/">
              <MobileMenu onClick={() => setIsMenuOpen(!isMenuOpen)}>로그아웃</MobileMenu>
            </Link>
          </MobileMenus>
        )}
      </Wrapper>
    </Container>
  )
}

export default Header

const Container = styled.div`
  height: 100px;
  width: 100%;
  display: flex;

  @media (max-width: 768px) {
    height: 80px;
  }
`

const Wrapper = styled.div`
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
`

const HeaderLogo = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  cursor: pointer;

  @media (max-width: 768px) {
    display: inline-flex;
    flex: 0;
  }
`
const DesktopHeaderWrapper = styled.div`
  cursor: pointer;

  .DesktopLogo {
  }

  .DesktopSearchBar {
  }

  display: block;

  @media (max-width: 768px) {
    display: none;
  }
`

const MobileHeaderWrapper = styled.div`
  cursor: pointer;

  .MobileLogo {
  }

  .MobileSearchIcon {
    color: rgb(51, 51, 51);
    font-size: 2.7rem;
  }

  @media (min-width: 768px) {
    display: none;
  }
`

const HeaderSearch = styled.div`
  flex: 1;
  align-items: center;

  @media (max-width: 768px) {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`

const HeaderMenus = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  font-weight: bold;
  cursor: pointer;

  @media (max-width: 768px) {
    display: none;
  }
`

const HeaderMenu = styled.div`
  font-size: 1.6rem;
  margin-left: 2.5rem;
  white-space: nowrap;

  &:hover {
    color: ${colors.mainColor};
  }
`

const HamburgerBox = styled.div`
  cursor: pointer;

  .HamburgerMenuIcon {
    font-size: 3.5rem;
    position: relative;
  }

  @media (min-width: 768px) {
    display: none;
  }
`

const MobileMenus = styled.div`
  border: 1px solid ${colors.grey1};
  background-color: rgb(255, 255, 255);
  position: absolute;
  top: 70px;
  right: -6.2rem;
  width: 20rem;
  transform: translateX(-45%);
  border-radius: 0.7rem;
  z-index: 100;
  animation: slideIn 0.3s ease-in-out;
  cursor: pointer;

  @keyframes slideIn {
    0% {
      transform: translateX(-45%) translateY(-10%);
    }
    100% {
      transform: translateX(-45%) translateY(0);
    }
  }

  @media (min-width: 768px) {
    display: none;
    flex: 0;
  }
`

const MobileMenu = styled.div`
  height: 10%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1.5rem;
  font-size: 1.3rem;
  font-weight: 700;
  border-bottom: 1px solid ${colors.grey1};

  &:hover {
    background-color: ${colors.mainColor};
    color: rgb(255, 255, 255);
    border-radius: 0.7rem;
  }

  :last-child {
    border-bottom: none;
  }
`
