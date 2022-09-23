import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title: string
}

function ReservedTag({title}: Props) {
  return (
    <Contaienr>
      <p>{title}</p>
    </Contaienr>
  )
}

export default ReservedTag

const Contaienr = styled.div`
  ${flexCenter}
  background: #6fa767;
  border-radius: 20px;
  width: 60px;
  height: 25px;

  p {
    font-size: 12px;
    color: white;
  }

  @media (max-width: 768px) {
    width: 50px;
    height: 20px;

    p {
      font-size: 8px;
    }
  }
`
