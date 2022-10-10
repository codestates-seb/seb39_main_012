/* eslint-disable react-hooks/rules-of-hooks */
import styled from 'styled-components'
import TapMenu from '@/components/Login/TapMenu'

const Login = () => {
  return (
    <Container>
      <Wrapper>
        <Title>로그인</Title>
        <TapMenu />
      </Wrapper>
    </Container>
  )
}

export default Login

const Container = styled.div`
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 33.33%;
  margin-top: 3em;
`

const Wrapper = styled.div``

const Title = styled.h1`
  font-size: 2.5rem;
  font-weight: bold;
  margin: 3rem 0 5rem 0;
  text-align: center;
`
