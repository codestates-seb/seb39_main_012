import React from 'react'
import styled from 'styled-components'
import Input, {InputProps} from '../Input/Input'

interface Props extends InputProps {
  label: string
}

function LabelInput({type, placeholder, value, onChange, label, name, ...props}: Props) {
  return (
    <LabelBox>
      <Label>{label}</Label>
      <Input type={type} name={name} value={value} onChange={onChange} {...props} />
    </LabelBox>
  )
}

export default LabelInput

const LabelBox = styled.div`
  display: flex;
  align-items: center;
`
const Label = styled.label`
  font-size: 14px;
  font-weight: 400;
  margin-right: 47px;
  width: 80px;
`
