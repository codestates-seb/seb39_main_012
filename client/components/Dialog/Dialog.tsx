import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  confirm: () => void
  cancel: () => void
  title: string
}

function Dialog({title, confirm, cancel}: Props) {
  return (
    <DialogContainer>
      <DialogBox>
        <Title>{title}</Title>
        <ButtonBox>
          <ConfirmButton onClick={() => confirm()}>확인</ConfirmButton>
          <CancelButton onClick={() => cancel()}>취소</CancelButton>
        </ButtonBox>
      </DialogBox>
    </DialogContainer>
  )
}

export default Dialog

const DialogContainer = styled.div`
  position: fixed;
  ${flexCenter}
  z-index: 2;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
`

const DialogBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 350px;

  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  background-color: white;
  border-radius: 10px;
`

const Title = styled.h1`
  margin-top: 25%;
  font-size: 25px;
`

const ButtonBox = styled.div`
  margin-top: 30px;
  display: flex;
  gap: 10px;
  margin-bottom: 50px;
`

const ConfirmButton = styled.button`
  width: 100px;
  height: 40px;
  background-color: ${colors.mainColor};
  color: white;
  border: none;
  cursor: pointer;
`
const CancelButton = styled.button`
  width: 100px;
  height: 40px;
  background-color: white;
  color: ${colors.mainColor};
  border: solid 1px ${colors.mainColor};
  cursor: pointer;
`
