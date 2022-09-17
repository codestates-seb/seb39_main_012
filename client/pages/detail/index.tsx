import React from 'react'
import styled from 'styled-components'
import StoreDetail from '@/components/StoreDetail/StoreDetail'

const index = () => {
  return (
    <Container>
      <StoreDetail />
    </Container>
  )
}

export default index

const Container = styled.div`
  width: 100%;
`
