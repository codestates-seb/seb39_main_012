import {colors} from '@/styles/colors'
import React, {HTMLInputTypeAttribute} from 'react'
import styled from 'styled-components'

export interface InputProps {
  type: HTMLInputTypeAttribute
  name: string
  placeholder?: string
  value: string
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
  width?: string
  height?: string
}

function Input({type, placeholder, value, onChange, ...props}: InputProps) {
  return (
    <StyleInput
      type={type}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      {...props}
    />
  )
}

export default Input

const StyleInput = styled.input<InputProps>`
  padding-left: 1rem;
  width: ${({width}) => width ?? '30rem'};
  height: ${({height}) => height ?? '3rem'};
  border-radius: 3px;
  background-color: ${colors.grey1};
  border: none;
`
