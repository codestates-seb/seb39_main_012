import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title?: string
  width?: string
  height?: string
  backgroundColor?: string
  marginTop?: string
  hoverFontColor?: string
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void
}

function AuthButton({
  title,
  width,
  height,
  backgroundColor,
  marginTop,
  hoverFontColor,
  onClick,
}: Props) {
  return (
    <Button
      title={title}
      width={width}
      height={height}
      backgroundColor={backgroundColor}
      marginTop={marginTop}
      hoverFontColor={hoverFontColor}
      onClick={onClick}
    >
      {title}
    </Button>
  )
}

export default AuthButton

export const Button = styled.button<Props>`
  margin: 0 auto;
  width: ${({width}) => width ?? '34rem'};
  height: ${({height}) => height ?? '4rem'};
  background-color: ${({backgroundColor}) => backgroundColor ?? colors.mainColor};
  border: none;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  font-size: 18px;
  margin-top: ${({marginTop}) => marginTop ?? '0'};
  cursor: pointer;

  :hover {
    background-color: rgba(111, 167, 103, 0.8);
    color: ${({hoverFontColor}) => hoverFontColor ?? 'rgb(255, 255, 255)'};
  }
`
