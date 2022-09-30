/* eslint-disable @typescript-eslint/no-explicit-any */
import {Form} from '@/pages/signup'
import {colors} from '@/styles/colors'
import React from 'react'
import {useDaumPostcodePopup} from 'react-daum-postcode'
import styled from 'styled-components'

interface Props {
  scriptUrl?: string
  setForm: React.Dispatch<React.SetStateAction<Form>>
}

const Postcode = ({scriptUrl, setForm}: Props) => {
  const open = useDaumPostcodePopup(scriptUrl)

  const handleComplete = (data: any) => {
    let fullAddress = data.address
    let extraAddress = ''

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : ''
    }

    console.log(fullAddress) // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    setForm((prev: Form) => ({...prev, detailAddress: fullAddress}))
  }

  const handleClick = () => {
    open({onComplete: handleComplete})
  }

  return (
    <Button type="button" onClick={handleClick}>
      주소검색
    </Button>
  )
}

export default Postcode

const Button = styled.button`
  width: 73px;
  height: 35px;
  font-size: 12px;
  border: solid 1px ${colors.mainColor};
  background-color: white;
  color: ${colors.mainColor};
  cursor: pointer;
`
