import React from 'react'
import styled from 'styled-components'
import StoreDetail from '@/components/StoreDetail/StoreDetail'
import {useRouter} from 'next/router'

const Detail = () => {
  const router = useRouter()
  const postId = router.query.postId

  return (
    <Container>
      <StoreDetail postId={Number(postId)} />
    </Container>
  )
}

export default Detail

const Container = styled.div`
  width: 100%;
`
