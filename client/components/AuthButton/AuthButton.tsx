import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'

interface Props {
  title: string
}

function AuthButton({title}: Props) {
  return <Button>{title}</Button>
}

export default AuthButton

const Button = styled.button`
  margin: 0 auto;
  width: 400px;
  height: 50px;
  background-color: ${colors.mainColor};
  border: none;
  border-radius: 3px;
  color: white;
  font-size: 22px;
  cursor: pointer;
`
