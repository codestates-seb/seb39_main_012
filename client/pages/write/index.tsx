import AuthButton from '@/components/AuthButton/AuthButton'
import SectionTitle from '@/components/StoreDetail/SectionTitle'
import {colors} from '@/styles/colors'
import React, {useState} from 'react'
import styled from 'styled-components'
import {ToastContainer} from 'react-toastify'
import {toast} from 'react-toastify'
import {AiOutlineCamera} from 'react-icons/ai'
// import {AiFillCloseSquare} from 'react-icons/ai'
import {FaDog} from 'react-icons/fa'
// import {availableServices} from '@/utils/options/options'
import {BsRecordCircle} from 'react-icons/bs'
import AvailableServicesAll from '@/components/Write/AvailableServicesAll'
import Map from '@/components/Map/Map'
import {useDaumPostcodePopup} from 'react-daum-postcode'
import axios from 'axios'
import router from 'next/router'
import {authPostService} from '@/apis/AuthPostAPI'

const Write = () => {
  const [form, setForm] = useState({
    companyName: '',
    contact: '',
    tags: [],
    time: '',
    content: '',
    prices: [],
    availableServices: [],
    address: '',
    detailAddress: '',
  })

  const [selectedImage, setSelectedImage] = useState([])
  const [selectedServices, setSelectedServices] = useState([])
  const [clickedService1, setClickedService1] = useState(false)
  const [clickedService2, setClickedService2] = useState(false)
  const [clickedService3, setClickedService3] = useState(false)
  const [clickedService4, setClickedService4] = useState(false)
  const [clickedService5, setClickedService5] = useState(false)
  const [clickedService6, setClickedService6] = useState(false)
  const [clickedService7, setClickedService7] = useState(false)
  const [clickedService8, setClickedService8] = useState(false)
  const [clickedService9, setClickedService9] = useState(false)
  const [clickedService10, setClickedService10] = useState(false)
  const [clickedService11, setClickedService11] = useState(false)
  const [clickedService12, setClickedService12] = useState(false)

  const imageHandleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      if (Number(e.target.files?.length) > 5 || selectedImage.length >= 5) {
        return toast.error('사진은 최대 5장까지 등록 가능합니다.')
      } else if (Number(e.target.files?.length) <= 5) {
        const fileArray = Array.from(e.target.files).map((file) => URL.createObjectURL(file))
        setSelectedImage((prevImages) => prevImages.concat(fileArray as any))
        Array.from(e.target.files)?.map((file) => URL.revokeObjectURL(file as any))
      }
    }
  }

  const open = useDaumPostcodePopup()

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

    setForm((prev) => ({...prev, detailAddress: fullAddress}))
  }

  const handleAddressClick = () => {
    open({onComplete: handleComplete})
  }

  // const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   const {name, value} = e.target

  //   setForm({
  //     ...form,
  //     [name]: value,
  //   })
  // }

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    // const {
    //   companyName,
    //   contact,
    //   tags,
    //   time,
    //   content,
    //   prices,
    //   availableServices,
    //   address,
    //   detailAddress,
    // } = form

    // if (
    //   !companyName ||
    //   !contact ||
    //   !tags ||
    //   !time ||
    //   !content ||
    //   !prices ||
    //   !availableServices ||
    //   !address ||
    //   !detailAddress
    // ) {
    //   return toast.error('모든 항목을 입력해주세요')
    // }

    const result = await authPostService.writeCompanyPost({
      title: '제목',
      content: '글 내용',
      coordinate: ['000000', '1111111', '2222222', '333333'],
      checkIn: '09:00',
      checkOut: '09:00',
      hashTag: ['강아지', '강아지 종류', '위치'],
      serviceTag: ['샤워가능', '산책가능', '간식배식', '공기청정기'],
      roomCount: 10,
      roomDtoList: [
        {size: '대형', price: 50000},
        {size: '중형', price: 40000},
        {size: '소형', price: 30000},
      ],
    })

    if (result.status === 200) {
      router.push('/')
      toast.success('새로운 포스트 등록이 완료되었습니다.')
    } else {
      toast.error('새로운 포스트 등록에 실패했습니다.')
    }
  }

  return (
    <CreatePostForm onSubmit={onSubmit}>
      <ToastContainer />
      <Container>
        <CompanyInfo>
          <SectionTitle title={'애견호텔 소개'} />
          <CompanyInfoBox>
            <CompanyInfoItem>
              <Label>업체명</Label>
              <Input type="text" placeholder="업체명을 입력하세요." name="companyName" />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>연락처</Label>
              <Input type="tel" placeholder="예시) '010-1234-5678'" name="contact" />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>태그</Label>
              <Input type="text" placeholder="업체 설명 태그를 선택해주세요." name="tags" />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>이용시간</Label>
              <Input type="text" placeholder="체크인시간 (예시) 오전 09:00" name="time"></Input>
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label></Label>
              <Input type="text" placeholder="체크아웃 시간 (예시) 오후 07:00" name="time"></Input>
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>소개글</Label>
              <Textarea placeholder="호텔링 소개글을 작성해주세요. " name="content" />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>사진</Label>
              <CompanyImages>
                <CompanyImagesBox>
                  <AddImages>
                    <label htmlFor="companyImg">
                      <AiOutlineCamera size={30} />
                      사진 {selectedImage.length}/5
                    </label>
                    <input
                      type="file"
                      id="companyImg"
                      onChange={imageHandleChange}
                      accept="image/png, image,jpeg, image/jpg"
                      multiple
                    />
                  </AddImages>
                  <Preview>
                    {selectedImage.map((image: any) => (
                      <img
                        src={image}
                        alt="companyImg"
                        key={image}
                        onClick={() => {
                          setSelectedImage(selectedImage.filter((e) => e !== image))
                        }}
                      />
                    ))}
                  </Preview>
                </CompanyImagesBox>
              </CompanyImages>
            </CompanyInfoItem>
          </CompanyInfoBox>
        </CompanyInfo>
        <DogOptions>
          <DogOptionsSection>반려견 옵션</DogOptionsSection>
          <DogOptionsCategories>
            <div className="tableHeader">반려견 크기</div>
            <div className="tableHeader">객실수</div>
            <div className="tableHeader">1박 케어 가격</div>
          </DogOptionsCategories>
          <DogOptionsCategories>
            <div>소형견</div>
            <DogPriceOption
              type="text"
              name="availableRooms"
              placeholder="예시) 객실 5개 -> 5 입력"
            />
            <DogPriceOption
              type="text"
              name="oneDayPrice"
              placeholder="예시) 3만원 -> 30000 입력"
            />
          </DogOptionsCategories>
          <DogOptionsCategories>
            <div>중형견</div>
            <DogPriceOption
              type="text"
              name="availableRooms"
              placeholder="예시) 객실 5개 -> 5 입력"
            />
            <DogPriceOption
              type="text"
              name="oneDayPrice"
              placeholder="예시) 3만원 -> 30000 입력"
            />
          </DogOptionsCategories>
          <DogOptionsCategories>
            <div>대형견</div>
            <DogPriceOption
              type="text"
              name="availableRooms"
              placeholder="예시) 객실 5개 -> 5 입력"
            />
            <DogPriceOption
              type="text"
              name="oneDayPrice"
              placeholder="예시) 3만원 -> 30000 입력"
            />
          </DogOptionsCategories>
        </DogOptions>
        <DogOptionsSection>이용가능 서비스 선택</DogOptionsSection>
        <AvailableServices>
          <AvailableServiceChoice>
            {/* {Array.from(Array(12).keys()).map((num, idx) => {
              return (  )
            })} */}
            <AvailableServicesAll
              clickedService1={clickedService1}
              clickedService2={clickedService2}
              clickedService3={clickedService3}
              clickedService4={clickedService4}
              clickedService5={clickedService5}
              clickedService6={clickedService6}
              clickedService7={clickedService7}
              clickedService8={clickedService8}
              clickedService9={clickedService9}
              clickedService10={clickedService10}
              clickedService11={clickedService11}
              clickedService12={clickedService12}
              setClickedService1={setClickedService1}
              setClickedService2={setClickedService2}
              setClickedService3={setClickedService3}
              setClickedService4={setClickedService4}
              setClickedService5={setClickedService5}
              setClickedService6={setClickedService6}
              setClickedService7={setClickedService7}
              setClickedService8={setClickedService8}
              setClickedService9={setClickedService9}
              setClickedService10={setClickedService10}
              setClickedService11={setClickedService11}
              setClickedService12={setClickedService12}
            />
          </AvailableServiceChoice>
        </AvailableServices>
        <CompanyAddress>
          <DogOptionsSection>주소</DogOptionsSection>
          <CompanyAddressBox>
            <AddressMain>
              <AddressInputWrapper>
                <AddressInput type="text" />
              </AddressInputWrapper>
              <AddressSearch>
                <AddressSearchButton onClick={handleAddressClick}>주소 검색</AddressSearchButton>
              </AddressSearch>
            </AddressMain>
            <AddressSub>
              <AddressInput type="text" />
            </AddressSub>
          </CompanyAddressBox>
        </CompanyAddress>
        <MapDisplay>
          {/* <Map latitude={35.976749396987046} longitude={126.99599512792346} /> */}
          <Map address={'경기 화성시 동탄기흥로257번 나길 34-4 1층'} companyName={'도그플래닛'} />
        </MapDisplay>
        <SectionWrapper>
          <AuthButton title={'저장하기'} />
        </SectionWrapper>
      </Container>
    </CreatePostForm>
  )
}

export default Write

const CreatePostForm = styled.form`
  width: 100%;
`

const Container = styled.div`
  width: 80%;
`

const CompanyInfo = styled.div`
  margin-top: 4rem;
`

const CompanyInfoBox = styled.div`
  margin: 3rem 0 0 1rem;
  display: flex;
  flex-direction: column;
`

const CompanyInfoItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1.5rem;
  width: 90%;
`

const Label = styled.label`
  flex: 1;
  font-size: 1.4rem;
  color: rgb(84, 84, 84);
  font-weight: 600;
  margin-right: 5rem;
`

const Input = styled.input`
  flex: 5;
  width: 80%;
  height: 2.5rem;
  border-radius: 0.3rem;
  padding: 0.5rem 1rem;
  font-size: 1.3rem;
  color: rgb(34, 34, 34);
  background-color: ${colors.grey1};
  border: 0.05rem solid ${colors.grey1};
  outline: 0.1rem solid ${colors.grey1};

  ::placeholder {
    color: ${colors.grey2};
    letter-spacing: 0.05rem;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`

const Textarea = styled.textarea`
  flex: 5;
  width: 80%;
  height: 20rem;
  border-radius: 0.3rem;
  padding: 1rem;
  font-size: 1.3rem;
  color: rgb(34, 34, 34);
  color: rgb(34, 34, 34);
  background-color: ${colors.grey1};
  border: 0.05rem solid ${colors.grey1};
  outline: 0.1rem solid ${colors.grey1};

  ::placeholder {
    color: ${colors.grey2};
    letter-spacing: 0.05rem;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`
const CompanyImages = styled.div`
  flex: 5;
`

const CompanyImagesBox = styled.div`
  height: 10rem;
  border-radius: 0.3rem;
  display: flex;
`
const AddImages = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;

  label {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 5px;
    width: 60px;
    height: 60px;
    border: solid 1px lightgray;
    font-size: 12px;
    margin-right: 10px;
    cursor: pointer;
  }

  & input[type='file'] {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
  }
`

const Preview = styled.div`
  flex: 5;
  display: flex;
  gap: 10px;
  cursor: pointer;

  img {
    width: 20%;
    border-radius: 3px;
  }
`

const DogOptions = styled.div`
  margin: 5rem 0 7rem 0;
`

const DogOptionsSection = styled.div`
  font-size: 1.4rem;
  color: rgb(84, 84, 84);
  font-weight: 600;
  margin-left: 1rem;
  margin-bottom: 2rem;
`
const DogOptionsBox = styled.div``

const DogOptionsItem = styled.div``

const DogOptionsCategories = styled.div`
  display: flex;
  width: 90%;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
  margin: 0 0 1rem 1rem;

  div {
    flex: 1;
    border-radius: 0.3rem;
    background-color: ${colors.subColor1};
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.4rem;
    color: rgb(84, 84, 84);
    font-weight: 600;
    padding: 0.5rem 1rem;
    height: 2.5rem;
  }

  .tableHeader {
    background-color: transparent;
    font-size: 1.1rem;
    color: ${colors.grey2};
  }
`

const DogPriceOption = styled.input`
  display: flex;
  flex: 1;
  height: 2.5rem;
  border-radius: 0.3rem;
  padding: 0.5rem 1rem;
  font-size: 1.3rem;
  color: rgb(34, 34, 34);
  background-color: ${colors.grey1};
  border: 0.05rem solid ${colors.grey1};
  outline: 0.1rem solid ${colors.grey1};

  ::placeholder {
    color: #d1d1d1;
    letter-spacing: 0.05rem;
    text-align: center;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`

const AvailableServices = styled.div`
  margin-left: 1rem;
  width: 90%;
`
const AvailableServiceChoice = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 10px;
`

const CompanyAddress = styled.div`
  margin-top: 7rem;
`
const CompanyAddressBox = styled.div`
  margin-left: 1rem;
`
const AddressMain = styled.div`
  display: flex;
  width: 90%;
  gap: 10px;
`

const AddressInputWrapper = styled.div`
  flex: 3;
  display: flex;
`

const AddressInput = styled.input`
  flex: 1;
  height: 2.5rem;
  border-radius: 0.3rem;
  padding: 0.5rem 1rem;
  font-size: 1.3rem;
  color: rgb(34, 34, 34);
  background-color: ${colors.grey1};
  border: 0.05rem solid ${colors.grey1};
  outline: 0.1rem solid ${colors.grey1};

  ::placeholder {
    color: rgb(209, 209, 209);
    letter-spacing: 0.05rem;
    text-align: center;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`

const AddressSearch = styled.div`
  flex: 1;
`

const AddressSearchButton = styled.button`
  width: 100%;
  height: 100%;
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(219, 219, 219);
  color: rgb(219, 219, 219);
  border-radius: 5px;
  cursor: pointer;
`

const AddressSub = styled.div`
  display: flex;
  width: 90%;
  margin-top: 1rem;
`

const MapDisplay = styled.div`
  display: flex;
  width: 90%;
  margin: 2rem 0 0 1rem;
`

const SectionWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 7rem 0 10rem 0;
`
