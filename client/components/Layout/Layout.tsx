import React from 'react'
import styled from 'styled-components'
import Header from './Header/Header'

interface Props {
  children: React.ReactNode
}

function Layout({children}: Props) {
  return (
    <LayOutContainer>
      <Box>
        <Header />
        {children}
      </Box>
    </LayOutContainer>
  )
}

export default Layout

const LayOutContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: auto;
  max-width: 1280px;
`
const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`
