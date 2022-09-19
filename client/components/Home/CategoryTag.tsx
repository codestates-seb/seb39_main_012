import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title: string
}

function CategoryTag({title}: Props) {
  return <Container onClick={() => console.log(title)}>{title}</Container>
}

export default CategoryTag

const Container = styled.div`
  ${flexCenter}
  width: 107px;
  height: 107px;
  border-radius: 50%;
  cursor: pointer;
  font-size: 20px;

  @media (max-width: 768px) {
    width: 75px;
    height: 75px;
    font-size: 15px;
  }

  @media (max-width: 390px) {
    width: 55px;
    height: 55px;
    font-size: 10px;
  }
`
