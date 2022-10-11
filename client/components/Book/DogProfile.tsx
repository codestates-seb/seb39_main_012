import React, {useState} from 'react'
import styled from 'styled-components'
import {BsGenderMale, BsGenderFemale} from 'react-icons/bs'
import Image from 'next/image'
import {colors} from '@/styles/colors'

interface DogProfileProps {
  id: string
  photoImgUrl: string
  dogName: string
  type: string
  gender: string
  age: number
  weight: number
  snackMethod: string
  bark: string
  surgery: string
  bowelTrained: string
  etc: string
}

interface Props {
  dog: DogProfileProps
  setDogId: React.Dispatch<React.SetStateAction<number[]>>
}

const DogProfile = ({dog, setDogId}: Props) => {
  const [dogSelected, setDogSelected] = useState(false)
  const dogSelectHandler = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
    setDogSelected(!dogSelected)
  }

  const dogInfoHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    // setDogId(Number(e.target.id))
    setDogId((prev) => [...prev, Number(e.target.id)])
  }

  return (
    <Container>
      <ImgBox>
        <Image src={dog.photoImgUrl} alt={'img'} width={300} height={200} />
      </ImgBox>
      <ImageTextBox>
        <DogInfoBox>
          <div>
            <DogName>{dog.dogName}</DogName>
            <DogGender>
              <BsGenderMale />
            </DogGender>
          </div>
          <div>
            <DogAge>{dog.age}</DogAge>
            <DogWeight>{dog.weight}kg</DogWeight>
          </div>
        </DogInfoBox>
        <DogBreed>{dog.type}</DogBreed>
      </ImageTextBox>
      <DogBottomInfoBox>
        <InfoBox>
          <div>
            <InfoTitle>중성화</InfoTitle>
            <InfoContent>{dog.surgery}</InfoContent>
          </div>
          <div>
            <InfoTitle>입질&짖음</InfoTitle>
            <InfoContent>{dog.bark}</InfoContent>
          </div>
          <div>
            <InfoTitle>특이사항</InfoTitle>
            <InfoContent>{dog.etc}</InfoContent>
          </div>
        </InfoBox>
        <InfoBox>
          <div>
            <InfoTitle>배변방식</InfoTitle>
            <InfoContent>{dog.bowelTrained}</InfoContent>
          </div>
          <div>
            <InfoTitle>간식급여</InfoTitle>
            <InfoContent>{dog.snackMethod}</InfoContent>
          </div>
          <Checkbox>
            <label htmlFor={dog.id} className="chk_box">
              <input
                type="checkbox"
                id={dog.id}
                onClick={dogSelectHandler}
                onChange={dogInfoHandler}
                checked={dogSelected}
              />
              <span className="on"></span>
            </label>
          </Checkbox>
        </InfoBox>
        {/* <DeleteButton onClick={() => console.log('삭제')}>
          <IoMdRemoveCircle />
        </DeleteButton> */}
      </DogBottomInfoBox>
    </Container>
  )
}

export default DogProfile

const Container = styled.div`
  width: 30%;
  height: 350px;
  position: relative;
  border-radius: 10px;
  overflow: hidden;
  /* box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px; */
  white-space: nowrap;
  border-radius: 20px;
  transition: 0.3s;
  box-shadow: 4px 4px 8px 0 rgba(0, 0, 0, 0.2);

  :hover {
    transition: 0.3s;
    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.2), -3px -3px 3px -3px rgba(0, 0, 0, 0.2);
  }

  @media (max-width: 768px) {
    width: 220px;
    height: 300px;
  }

  @media (max-width: 390px) {
    width: 170px;
    height: 250px;
  }
`
const ImgBox = styled.div``

const ImageTextBox = styled.div`
  position: absolute;
  width: 100%;
  top: 0;
  font-size: 15px;

  @media (max-width: 768px) {
    font-size: 13px;
  }

  @media (max-width: 390px) {
    font-size: 12px;
  }
`

const DogInfoBox = styled.div`
  display: flex;
  justify-content: space-between;

  & > div {
    display: flex;
    margin: 10px 20px 0 20px;
    align-items: center;
  }
`

const DogName = styled.h2`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 25px;
  line-height: 25px;

  @media (max-width: 768px) {
    font-size: 20px;
  }

  @media (max-width: 390px) {
    font-size: 15px;
  }
`
const DogGender = styled.div`
  font-size: 15px;
  padding-left: 5px;

  @media (max-width: 768px) {
    font-size: 10px;
  }
`
const DogAge = styled.p``
const DogWeight = styled.p`
  margin-left: 5px;
`
const DogBreed = styled.p`
  margin-left: 20px;
`

const DogBottomInfoBox = styled.div`
  display: flex;
  justify-content: flex-start;
  /* position: absolute; */
`
const InfoBox = styled.div`
  display: flex;
  flex-direction: column;
  margin: 2rem 0;
  width: 50%;
  font-size: 15px;

  & > div {
    /* white-space: nowrap; */
    display: flex;
    margin: 1rem;
    align-items: center;
    @media (max-width: 768px) {
      margin: 5px 15px;
    }

    @media (max-width: 390px) {
      margin: 3px 10px;
    }
  }
`

const InfoTitle = styled.p`
  font-style: normal;
  font-weight: 700;
  font-size: 13px;
  line-height: 16px;
  color: rgb(68, 68, 68);

  @media (max-width: 768px) {
    font-size: 12px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`
const InfoContent = styled.p`
  margin-left: 10px;
  font-size: 13px;
  line-height: 16px;
  color: rgb(85, 85, 85);

  @media (max-width: 768px) {
    font-size: 13.5px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`
const Checkbox = styled.div`
  cursor: pointer;

  .chk_box {
    font-size: 14px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    cursor: pointer;
  }

  /* 기본 체크박스 숨기기 */
  .chk_box input[type='checkbox'] {
    display: none;
  }

  /* 선택되지 않은 체크박스 스타일 꾸미기 */
  .on {
    width: 20px;
    height: 20px;
    background: rgb(221, 221, 221);
    position: absolute;
    bottom: 11px;
    right: 30px;
    cursor: pointer;
  }

  /* 선택된 체크박스 스타일 꾸미기 */
  .chk_box input[type='checkbox']:checked + .on {
    background: rgb(253, 208, 124);
  }

  .on:after {
    content: '';
    position: absolute;
    display: none;
  }

  .chk_box input[type='checkbox']:checked + .on:after {
    display: block;
    cursor: pointer;
  }

  .on:after {
    width: 6px;
    height: 10px;
    border: solid rgb(255, 255, 255);
    border-width: 0 2px 2px 0;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
    position: absolute;
    left: 6px;
    top: 2px;
    cursor: pointer;
  }
`
