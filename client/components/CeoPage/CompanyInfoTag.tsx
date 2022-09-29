import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title: string
  onClick: () => void
}

function CompanyInfoTag({title, onClick}: Props) {
  return (
    <Contaienr onClick={onClick} title={title}>
      <p>{title}</p>
    </Contaienr>
  )
}

export default CompanyInfoTag

const Contaienr = styled.div<{title: string}>`
  ${flexCenter}
  background: ${({title}) => (title === '수정' ? '#6fa767' : 'gray')};
  border-radius: 20px;
  width: 45px;
  height: 25px;
  cursor: pointer;

  p {
    font-size: 12px;
    color: white;
  }

  @media (max-width: 768px) {
    width: 40px;
    height: 20px;

    p {
      font-size: 8px;
    }
  }
`
