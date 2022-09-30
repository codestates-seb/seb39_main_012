import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import React from 'react'
import styled from 'styled-components'

interface Props {
  mode: string
  setMode: React.Dispatch<React.SetStateAction<'user' | 'company'>>
}

function SignUpTab({mode, setMode}: Props) {
  return (
    <TabBox>
      <Tab current={'user'} mode={mode} onClick={() => setMode('user')}>
        견주님 회원가입
      </Tab>
      <Tab current={'company'} mode={mode} onClick={() => setMode('company')}>
        사장님 회원가입
      </Tab>
    </TabBox>
  )
}

export default SignUpTab

const TabBox = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
`
const Tab = styled.div<{mode: string; current: string}>`
  ${flexCenter}
  font-size: 14px;
  font-weight: bold;
  width: 50%;
  height: 38px;
  background-color: ${({mode, current}) => (mode === current ? colors.mainColor : 'none')};
  color: ${({mode, current}) => (mode === current ? 'white' : colors.grey3)};
  border: ${({mode, current}) => (mode === current ? colors.mainColor : 'lightgray')} solid 1px;
  cursor: pointer;
`
