import React from 'react'
import styled from 'styled-components'
import Input, {InputProps} from '../Input/Input'

interface Props extends InputProps {
  label: string
  Errors?: boolean
  ErrorMessage?: string
}

function LabelInput({
  type,
  placeholder,
  value,
  onChange,
  label,
  name,
  Errors,
  ErrorMessage,
  ...props
}: Props) {
  return (
    <LabelContainer>
      <LabelBox>
        <Label>{label}</Label>
        <Input type={type} name={name} value={value} onChange={onChange} {...props} />
      </LabelBox>
      {Errors && value.length > 1 && <ErrorMessageBox>{ErrorMessage}</ErrorMessageBox>}
    </LabelContainer>
  )
}

export default LabelInput

const LabelContainer = styled.div`
  display: flex;
  flex-direction: column;
`

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
const ErrorMessageBox = styled.div`
  margin-top: 5px;
  margin-left: 13rem;
  font-size: 10px;
  color: red;
`
