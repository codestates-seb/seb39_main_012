import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title?: string
  width?: string
  height?: string
  backgroundColor?: string
  marginTop?: string
}

function AuthButton({title, width, height, backgroundColor, marginTop}: Props) {
  return (
    <Button
      title={title}
      width={width}
      height={height}
      backgroundColor={backgroundColor}
      marginTop={marginTop}
    >
      {title}
    </Button>
  )
}

export default AuthButton

export const Button = styled.button<Props>`
  margin: 0 auto;
  width: ${({width}) => width ?? '39rem'};
  height: ${({height}) => height ?? '5rem'};
  background-color: ${({backgroundColor}) => backgroundColor ?? colors.mainColor};
  border: none;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  font-size: 22px;
  margin-top: ${({marginTop}) => marginTop ?? '0'};
  cursor: pointer;

  :hover {
    background-color: rgba(111, 167, 103, 0.8);
  }
`
