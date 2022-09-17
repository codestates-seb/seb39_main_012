import {ToastContainer} from 'react-toastify'
import styled from 'styled-components'

function Home() {
  return (
    <Container>
      <h1>테스트</h1>
      <ToastContainer />
    </Container>
  )
}

export default Home

const Container = styled.div`
  background-color: red;
  width: 100%;
`
