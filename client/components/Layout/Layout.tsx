import React, {useState} from 'react'
import {ToastContainer} from 'react-toastify'
import styled from 'styled-components'
import Adversting from '../\bAdversting/Adversting'
import Header from './Header/Header'

interface Props {
  children: React.ReactNode
}

function Layout({children}: Props) {
  const [AddOpen, setAddOpen] = useState(true)
  return (
    <>
      {AddOpen && <Adversting setAddOpen={setAddOpen} />}
      <LayOutContainer>
        <Box>
          <Header />
          {children}
        </Box>
      </LayOutContainer>
      <ToastContainer />
    </>
  )
}

export default Layout

const LayOutContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: auto;
  max-width: 1280px;

  @media (max-width: 1350px) {
    margin: 0 15px;
  }

  @media (max-width: 390px) {
    margin: 0 7.5px;
  }
`
const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`
