import React from 'react'
import Image from 'next/image'
import {useRouter} from 'next/router'
import styled from 'styled-components'

const Footer = () => {
  const router = useRouter()
  return (
    <Container>
      <Wrapper>
        <LogoNav>
          <LogoWrapper>
            <Image
              src="/svg/logos/MobileLogo.png"
              width={50}
              height={50}
              alt="logo"
              onClick={() => router.push('/')}
            />
          </LogoWrapper>
          <Nav>
            <Column>
              <ColumnTitle>MOONGTEL</ColumnTitle>
              <ColumnLists>
                <ColumnList>
                  <a
                    href="https://docs.google.com/presentation/d/1kDworaqfeTy1xYk_0UvHv_eAEzKO9PDQGQ9jYQXXZA8/edit#slide=id.g163e0cb4160_1_21"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    서비스 소개
                  </a>
                </ColumnList>
                <ColumnList>
                  <a
                    href="https://github.com/codestates-seb/seb39_main_012"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Github
                  </a>
                </ColumnList>
                <ColumnList>
                  <a
                    href="https://www.figma.com/file/IPeBo0ZKWKwJXa619E7rUY/Project-Moongtel"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Figma
                  </a>
                </ColumnList>
              </ColumnLists>
            </Column>
            <Column>
              <ColumnTitle>FRONTEND TEAM</ColumnTitle>
              <ColumnLists>
                <ColumnList>
                  <a
                    href="https://github.com/JEONGHWANMIN"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    정환민
                  </a>
                </ColumnList>
                <ColumnList>
                  <a href="https://github.com/bruadarach" target="_blank" rel="noopener noreferrer">
                    지수정
                  </a>
                </ColumnList>
              </ColumnLists>
            </Column>
            <Column>
              <ColumnTitle>BACKEND TEAM</ColumnTitle>
              <ColumnLists>
                <ColumnList>
                  <a href="https://github.com/DZOOOOO" target="_blank" rel="noopener noreferrer">
                    이동주
                  </a>
                </ColumnList>
                <ColumnList>
                  <a href="https://github.com/penrose15" target="_blank" rel="noopener noreferrer">
                    홍성주
                  </a>
                </ColumnList>
                <ColumnList>
                  <a href="https://github.com/moodeary" target="_blank" rel="noopener noreferrer">
                    황병수
                  </a>
                </ColumnList>
              </ColumnLists>
            </Column>
            <Column>
              <ColumnTitle></ColumnTitle>
              <ColumnLists>
                <ColumnList></ColumnList>
              </ColumnLists>
            </Column>
          </Nav>
        </LogoNav>
        <Info>
          <SocialAcc>
            <SocialAccNav>
              <InfoList>Blog</InfoList>
              <InfoList>Facebook</InfoList>
              <InfoList>Twitter</InfoList>
              <InfoList>Linkedin</InfoList>
              <InfoList>Instagram</InfoList>
            </SocialAccNav>
          </SocialAcc>
          <CopyRights>
            <CopyRightsList>
              Site Design / Logo © 2022 Sujeong Ji.
              <CopyRightsLink>All Rights Reserved.</CopyRightsLink>
              <CopyRightsList></CopyRightsList>
            </CopyRightsList>
          </CopyRights>
        </Info>
      </Wrapper>
    </Container>
  )
}

export default Footer

const Container = styled.div`
  width: 100%;
  margin-top: 15rem;
  border-top: 1px solid rgb(238, 237, 237);
`
const Wrapper = styled.div`
  max-width: 126rem;
  margin: 0 auto;
  padding: 1.5rem 3.5rem;
  display: flex;

  @media (max-width: 1133px) {
    flex-direction: column;
  }

  @media (max-width: 600px) {
    padding: 0rem 1rem;
  }
`
const LogoNav = styled.div`
  display: flex;
  flex: 4;

  @media (max-width: 680px) {
    flex-direction: column;
  }
`

const LogoWrapper = styled.div`
  flex: 0.2;
  text-align: right;

  @media (max-width: 680px) {
    display: flex;
    align-items: flex-start;
  }

  @media (max-width: 600px) {
    display: none;
  }
`

const Nav = styled.div`
  flex: 3.5;
  margin: 1rem 0;
  display: flex;
  justify-content: space-around;

  .newSection {
    margin-top: 1.5rem;
  }

  @media (max-width: 680px) {
    flex-direction: column;

    .newSection {
      margin: 0;
      margin-right: 1rem;
    }
  }
`

const Column = styled.div`
  padding: 0 1rem;
`
const ColumnTitle = styled.h3`
  color: rgb(85, 85, 85);
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
  padding: 1rem 0 2rem 0;
  font-size: 1.3rem;
  cursor: pointer;

  @media (max-width: 680px) {
    margin-bottom: -1rem;
  }

  @media (max-width: 600px) {
    font-size: 1.1rem;
    margin-top: 0.7rem;
  }
`

const ColumnLists = styled.ol`
  list-style: none;

  a:hover {
    color: rgb(190, 186, 186);
  }

  @media (max-width: 680px) {
    display: flex;
    flex-wrap: wrap;
  }
`

const ColumnList = styled.li`
  color: rgb(142, 151, 159);
  font-size: 1.3rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding-bottom: 1rem;
  list-style: none;
  cursor: pointer;

  a {
    text-decoration: none;
    background-color: transparent;
    color: rgb(142, 151, 159);
  }

  @media (max-width: 680px) {
    margin-right: 1rem;
    font-size: 1.1rem;
  }

  @media (max-width: 600px) {
    font-size: 1rem;
  }
`

const Info = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  margin: 2.2rem 0 0 2.5rem;

  a:hover {
    color: rgb(190, 186, 186);
  }

  @media (max-width: 680px) {
    margin: 3rem 0 0 0;
    padding: 0 1rem;
  }
`

const SocialAcc = styled.div`
  flex: 1.2;
`

const SocialAccNav = styled.ol`
  display: flex;
  justify-content: space-between;

  @media (max-width: 1133px) {
    justify-content: flex-start;
  }
`
const InfoList = styled(ColumnList)`
  font-size: 1.1rem;
  padding: 0.2rem 0;

  @media (max-width: 1133px) {
    margin-right: 1rem;
    padding: 0.5rem 0;
  }
`

const CopyRights = styled.div`
  flex: 9;
  display: flex;
  flex-direction: column;
  justify-content: end;
  margin-top: 15rem;
  color: rgb(142, 151, 159);
  font-size: 1.3rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

  @media (max-width: 1133px) {
    flex-direction: row;
    justify-content: flex-start;
    margin-top: 0.5rem;
  }
`
const CopyRightsList = styled.div`
  pointer-events: none;
  font-size: 1rem;
  line-height: 1.4rem;
  padding-bottom: 1rem;

  @media (max-width: 1133px) {
    display: flex;
    flex-wrap: wrap;
  }
`

const CopyRightsLink = styled.div`
  color: rgb(142, 151, 159);

  @media (max-width: 1133px) {
    display: flex;
    flex-wrap: wrap;
    margin: 0 0.5rem;
  }
`
