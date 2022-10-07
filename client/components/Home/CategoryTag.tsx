import {flexCenter} from '@/styles/css'
import {useRouter} from 'next/router'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title: string
}

function CategoryTag({title}: Props) {
  const router = useRouter()
  return (
    <Container
      onClick={() => {
        if (title === '전체') {
          router.push(`/`)
          return
        }
        router.push(`/search?tag=${title}`)
      }}
    >
      {title}
    </Container>
  )
}

export default CategoryTag

const Container = styled.div`
  ${flexCenter}
  width: 100px;
  height: 100px;
  border-radius: 50%;
  cursor: pointer;
  font-size: 20px;
  margin: 10px 0;

  @media (max-width: 768px) {
    width: 75px;
    height: 75px;
    font-size: 15px;
  }

  @media (max-width: 390px) {
    width: 60px;
    height: 60px;
    font-size: 10px;
  }
`
