import {colors} from '@/styles/colors'
import React, {useState} from 'react'
import styled from 'styled-components'

interface Props {
  size: number
  bgColor?: string
  color?: string
  isChecked?: boolean
}

function CheckBox({...props}: Props) {
  const [isChecked, setIsChecked] = useState(false)
  return (
    <CheckBoxs {...props} isChecked={isChecked} onClick={() => setIsChecked(!isChecked)}>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        strokeWidth={1}
        stroke="currentColor"
        className="w-6 h-6"
      >
        <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 12.75l6 6 9-13.5" />
      </svg>
    </CheckBoxs>
  )
}

export default CheckBox

const CheckBoxs = styled.div<Props>`
  transition: all 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
  width: ${({size}) => size ?? '3'}px;
  height: ${({size}) => size ?? '3'}px;
  border-radius: 50%;
  background-color: ${({isChecked, bgColor}) => (isChecked ? bgColor ?? '#56d402' : 'none')};
  border: ${({isChecked}) => (isChecked ? 'none' : 'gray solid 0.5px')};
  cursor: pointer;

  svg {
    width: 10rem;
    height: 10rem;
    color: ${({isChecked}) => (isChecked ? 'white' : colors.grey3)};
  }
`
