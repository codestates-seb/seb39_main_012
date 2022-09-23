import React, {HTMLInputTypeAttribute} from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'

export interface InputProps {
  type: HTMLInputTypeAttribute
  name: string
  placeholder?: string
  value?: string
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void
  width?: string
  height?: string
  marginBottom?: string
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
  margin-bottom: ${({marginBottom}) => marginBottom ?? '0'};
  border-radius: 3px;
  background-color: ${colors.grey1};
  border: 0.05rem solid ${colors.grey1};
  outline: 0.4rem solid ${colors.grey1};

  ::placeholder {
    color: ${colors.grey2};
    letter-spacing: 0.05rem;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`
