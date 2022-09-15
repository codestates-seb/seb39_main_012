import React from 'react'
import styled from 'styled-components'

interface Props {
  children: React.ReactNode
}

function Layout({children}: Props) {
  return <LayOutContainer>{children}</LayOutContainer>
}

export default Layout

const LayOutContainer = styled.div`
  margin: auto;
  width: 1280px;
`
