import {searchValueState} from '@/recoil/searchValue'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {dogSizeOption} from '@/utils/options/options'
import {useRouter} from 'next/router'
import React, {useState} from 'react'
import {toast} from 'react-toastify'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'
import DayPickerRange from '../DayPickerRange/DayPickerRange'

function SearchBar() {
  const route = useRouter()
  const [value, setValue] = useRecoilState(searchValueState)

  const [dropdown, setDropdown] = useState({
    dogSizeOpen: false,
    dateRangeOpen: false,
  })

  const handleClick = () => {
    if (!value.local || value.checkin.length <= 7 || value.checkout.length <= 7 || !value.dogSize) {
      return toast.error('모든 항목을 입력해주세요.')
    }

    if (value.local) {
      route.push(`/search?address=${value.local}`)
      return
    }

    route.push(
      `/search?local=${value.local}&checkin=${value.checkin}&checkout=${value.checkout}&dogSize=${value.dogSize}`
    )
  }

  return (
    <Container>
      <Content>
        <TextBox>
          <input
            type={'text'}
            value={value.local}
            placeholder="지역"
            onChange={(e) => setValue((pre) => ({...pre, local: e.target.value}))}
          />
        </TextBox>
        <DateBox>
          <DateDropDown
            onClick={() => {
              if (value.checkin.length > 5 && value.checkout.length > 5) {
                setValue({
                  ...value,
                  checkin: '체크인',
                  checkout: '체크아웃',
                })
              }

              setDropdown((pre) => ({...pre, dateRangeOpen: !pre.dateRangeOpen}))
            }}
          >
            <span>
              {value.checkin} / {value.checkout}
            </span>
            {dropdown.dateRangeOpen && (
              <DateModal
                onClick={(e) => {
                  if (value.checkin.length > 5 || value.checkout.length > 5) {
                    return
                  }
                  e.stopPropagation()
                }}
              >
                <DayPickerRange setValue={setValue} />
              </DateModal>
            )}
          </DateDropDown>
        </DateBox>
        <DogInfoBox>
          <DogDropDown
            onClick={() => setDropdown((pre) => ({...pre, dogSizeOpen: !pre.dogSizeOpen}))}
          >
            <span>{value.dogSize || '사이즈'}</span>
            {dropdown.dogSizeOpen && (
              <Options>
                {dogSizeOption.map((option) => (
                  <option
                    key={option.value}
                    onClick={() =>
                      setValue({
                        ...value,
                        dogSize: option.label,
                      })
                    }
                  >
                    {option.label}
                  </option>
                ))}
              </Options>
            )}
          </DogDropDown>
        </DogInfoBox>
      </Content>
      <Button onClick={() => handleClick()}>검색</Button>
    </Container>
  )
}

export default SearchBar

const Container = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 20px;

  @media (max-width: 768px) {
    flex-direction: column;
    gap: 10px;
  }
`

const Content = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex: 1;
  height: 100%;

  @media (max-width: 768px) {
    width: 100%;
  }
`

const TextBox = styled.div`
  ${flexCenter}
  width: 30%;
  height: 80%;
  margin-left: 20px;
  border-radius: 10px;
  background-color: white;
  font-size: 15px;

  input {
    text-align: center;
    width: 80%;
    height: 50%;
    border: none;
    font-size: 15px;

    &:focus {
      outline: none;
    }

    &::placeholder {
      text-align: center;
      color: #333;
    }

    @media (max-width: 768px) {
      font-size: 10px;
    }
  }

  @media (max-width: 768px) {
    height: 100%;
    width: 20%;
  }
`

const DogDropDown = styled.div`
  ${flexCenter}
  position: relative;
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 10px;

  cursor: pointer;
`
const DateBox = styled.div`
  width: 30%;
  height: 80%;
  font-size: 15px;

  @media (max-width: 768px) {
    height: 100%;
    font-size: 10px;
    width: 40%;
  }
`

const DateDropDown = styled(DogDropDown)`
  height: 100%;
  width: 100%;
`

const DateModal = styled.div`
  padding: 1rem;
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  bottom: -50px;
  border-radius: 10px;
`

const DogInfoBox = styled.div`
  ${flexCenter}
  width: 20%;
  height: 80%;
  font-size: 15px;

  @media (max-width: 768px) {
    margin-right: 20px;
    height: 100%;
    font-size: 10px;
  }
`

const Options = styled.div`
  padding: 1rem;
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  bottom: -100px;
  border-radius: 10px;
  width: 90%;
  animation: growDown 250ms ease-in;
  transform-origin: top center;
  gap: 10px;

  @keyframes growDown {
    0% {
      transform: scaleY(0);
    }
    100% {
      transform: scaleY(1);
    }
  }

  option {
  }
`

const Button = styled.button`
  width: 10%;
  height: 80%;
  font-size: 16px;
  margin-right: 20px;
  background-color: ${colors.mainColor};
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 10px;

  @media (max-width: 768px) {
    height: 30%;
    width: 60%;
    font-size: 15px;
    margin-right: 0;
    margin-top: 1rem;
  }
`
