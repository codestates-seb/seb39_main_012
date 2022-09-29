import React from 'react'
import styled from 'styled-components'
import Input, {InputProps} from '../Input/Input'

interface Props extends InputProps {
  label: string
  Errors?: boolean
  ErrorMessage?: string
  emailDuplicate?: boolean
  marginRight?: string
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
  emailDuplicate,
  marginRight,
  ...props
}: Props) {
  return (
    <LabelContainer>
      <LabelBox>
        <Label marginRight={marginRight}>{label}</Label>
        <Input
          type={type}
          name={name}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          {...props}
        />
      </LabelBox>
      {(Errors && value && value.length >= 1 && (
        <ErrorMessageBox>{ErrorMessage}</ErrorMessageBox>
      )) || (
        <NoError>
          {emailDuplicate && <ErrorMessageBox>이미 사용중인 이메일입니다.</ErrorMessageBox>}
        </NoError>
      )}
    </LabelContainer>
  )
}

export default LabelInput

const LabelContainer = styled.div`
  display: flex;
  flex-direction: column;
  white-space: nowrap;
`

const LabelBox = styled.div`
  display: flex;
  align-items: center;

  @media (max-width: 390px) {
    flex-direction: column;
    align-items: flex-start;
  }
`
const Label = styled.label<{marginRight?: string}>`
  font-size: 14px;
  font-weight: 400;
  margin-right: ${({marginRight}) => marginRight ?? '47px'};
  width: 80px;
`
const ErrorMessageBox = styled.div`
  height: 10px;
  margin-top: 10px;
  margin-left: 13rem;
  font-size: 10px;
  color: red;

  @media (max-width: 390px) {
    margin-left: 0;
  }
`

const NoError = styled.div`
  height: 10px;
  margin-bottom: 5px;
`
