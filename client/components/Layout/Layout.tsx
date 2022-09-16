import React from 'react'
import styled from 'styled-components'

interface Props {
  children: React.ReactNode
}

function Layout({children}: Props) {
  return (
    <LayOutContainer>
      <Box>{children}</Box>
    </LayOutContainer>
  )
}

export default Layout

const LayOutContainer = styled.div`
  margin: auto;
  width: 76.7%;
`
const Box = styled.div`
  display: flex;
  justify-content: center;
`
