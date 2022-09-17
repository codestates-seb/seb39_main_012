import React from 'react'
import styled from 'styled-components'
import CheckBoxLabel from './CheckBoxLabel'

export interface Checks {
  totalAgree: boolean
  useAgree: boolean
  privateAgree: boolean
  snsAgree: boolean
  ageAgree: boolean
}

interface Props {
  checks: Checks
  setChecks: React.Dispatch<React.SetStateAction<Checks>>
}

function AgreeBox({checks, setChecks}: Props) {
  const handleAllChecks = () => {
    if (checks.totalAgree) {
      setChecks({
        totalAgree: false,
        useAgree: false,
        privateAgree: false,
        snsAgree: false,
        ageAgree: false,
      })
    } else {
      setChecks({
        totalAgree: true,
        useAgree: true,
        privateAgree: true,
        snsAgree: true,
        ageAgree: true,
      })
    }
  }

  const handleChecks = (name: keyof Checks) => {
    setChecks({
      ...checks,
      [name]: !checks[name],
    })
  }

  return (
    <Container>
      <AgreeTitle>이용약관동의</AgreeTitle>
      <AgreeContent>
        <CheckBoxLabel
          label={'전체동의'}
          checks={checks}
          setChecks={setChecks}
          onClick={handleAllChecks}
          name={'totalAgree'}
        />
        <CheckBoxLabel
          label={'이용약관 동의 (필수)'}
          checks={checks}
          setChecks={setChecks}
          onClick={() => handleChecks('useAgree')}
          name={'useAgree'}
        />
        <CheckBoxLabel
          label={'개인정보 수집 및 이용 동의 (필수)'}
          checks={checks}
          setChecks={setChecks}
          onClick={() => handleChecks('privateAgree')}
          name={'privateAgree'}
        />
        <CheckBoxLabel
          label={'SNS 마케팅 정보 수신 동의 (선택)'}
          checks={checks}
          setChecks={setChecks}
          onClick={() => handleChecks('snsAgree')}
          name={'snsAgree'}
        />
        <CheckBoxLabel
          label={'만 14세 이상 (필수)'}
          checks={checks}
          setChecks={setChecks}
          onClick={() => handleChecks('ageAgree')}
          name={'ageAgree'}
        />
      </AgreeContent>
    </Container>
  )
}

export default AgreeBox

const Container = styled.div`
  display: flex;
  width: 100%;
  margin-bottom: 40px;

  @media (max-width: 390px) {
    width: 80%;
    flex-direction: column;
  }
`
const AgreeTitle = styled.h2`
  flex: 2;
  font-size: 14px;
`
const AgreeContent = styled.div`
  flex: 8;
  display: flex;
  flex-direction: column;
  gap: 10px;
`
