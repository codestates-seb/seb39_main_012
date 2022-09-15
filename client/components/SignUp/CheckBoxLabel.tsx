import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'
import CheckBox from '../CheckBox/CheckBox'

interface Props {
  label: string
}

function CheckBoxLabel({label}: Props) {
  return (
    <Container>
      <CheckBox size={15} color={colors.mainColor} bgColor={colors.mainColor} />
      <Label>{label}</Label>
    </Container>
  )
}

export default CheckBoxLabel

const Container = styled.div`
  display: flex;
  align-items: center;
  gap: 7.25px;
`

const Label = styled.h2`
  font-size: 14px;
`
