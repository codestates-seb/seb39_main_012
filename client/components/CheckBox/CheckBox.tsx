import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'
import {Checks} from '../SignUp/AgreeBox'

interface Props {
  onClick: () => void
  checks: Checks
  setChecks: React.Dispatch<React.SetStateAction<Checks>>
  name: keyof Checks
}

function CheckBox({onClick, checks, name}: Props) {
  return (
    <CheckBoxs onClick={onClick} isChecked={checks[name]}>
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

const CheckBoxs = styled.div<{isChecked: boolean}>`
  transition: all 0.1s;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 15px;
  height: 15px;
  border-radius: 50%;
  border: solid 1px gray;
  background-color: ${({isChecked}) => (isChecked ? colors.mainColor : 'white')};
  border: ${({isChecked}) => (isChecked ? `${colors.mainColor} solid 0.5px` : 'gray solid 0.5px')};
  cursor: pointer;

  svg {
    color: ${({isChecked}) => (isChecked ? 'white' : colors.grey3)};
    width: 100%;
    height: 100%;
  }
`
