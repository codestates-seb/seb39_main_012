import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'
import {FiDelete} from 'react-icons/fi'

interface Props {
  setAddOpen: React.Dispatch<React.SetStateAction<boolean>>
}

function Adversting({setAddOpen}: Props) {
  return (
    <Container>
      <p>
        Moongtel 연수익의 <span>7%를 동물 보호 활동</span>에 사용합니다.
      </p>
      <IconBox onClick={() => setAddOpen(false)}>
        <FiDelete />
      </IconBox>
    </Container>
  )
}

export default Adversting

const Container = styled.div`
  ${flexCenter}
  position: relative;
  width: 100%;
  padding: 10px 0;
  background-color: ${colors.mainColor};
  color: white;
  font-size: 16px;

  span {
    font-weight: bold;
  }
`

const IconBox = styled.div`
  cursor: pointer;
  font-size: 20px;
  position: absolute;
  right: 0;
  margin-right: 30px;
  margin-top: 4px;
`
