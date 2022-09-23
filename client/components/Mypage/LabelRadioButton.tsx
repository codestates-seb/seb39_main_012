import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  label: string
  value1: string
  value2: string
  name: string
  isChecked: boolean
  onClick: (label: string, value: boolean) => void
}

function LabelRadioButton({label, value1, value2, name, isChecked, onClick}: Props) {
  return (
    <LabelBoxs>
      <Label>{label}</Label>
      <ValueBox>
        <RadioButton isChecked={isChecked} onClick={() => onClick(name, true)}>
          {value1}
        </RadioButton>
        <RadioButton isChecked={!isChecked} onClick={() => onClick(name, false)}>
          {value2}
        </RadioButton>
      </ValueBox>
    </LabelBoxs>
  )
}

export default LabelRadioButton

const LabelBoxs = styled.div`
  display: flex;
  align-items: center;
  font-size: 12px;
  gap: 5px;
`

const ValueBox = styled.div`
  margin-left: 10px;
  display: flex;
  gap: 5px;
`

const Label = styled.div`
  width: 30%;
`
const RadioButton = styled.div<{isChecked: boolean}>`
  width: 60px;
  height: 30px;
  ${flexCenter}
  border: ${({isChecked}) => (isChecked ? `${colors.subFontColor1}` : 'lightgray')} solid 1px;
  background-color: ${({isChecked}) => (isChecked ? `${colors.subColor3}` : `${colors.grey1}`)};
  color: ${({isChecked}) => (isChecked ? `black` : 'lightgray')};
  font-weight: 500;
  border-radius: 3px;
  cursor: pointer;
  white-space: nowrap;
`
