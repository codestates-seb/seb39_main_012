import React from 'react'
import styled from 'styled-components'
import CheckBox from '../CheckBox/CheckBox'
import {Checks} from './AgreeBox'

interface Props {
  label: string
  checks: Checks
  setChecks: React.Dispatch<React.SetStateAction<Checks>>
  onClick: () => void
  name: keyof Checks
}

function CheckBoxLabel({label, checks, setChecks, onClick, name}: Props) {
  return (
    <Container>
      <CheckBox setChecks={setChecks} checks={checks} onClick={onClick} name={name} />
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

const Label = styled.h1`
  font-size: 14px;
`
