/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @next/next/no-img-element */
import AuthButton from '@/components/AuthButton/AuthButton'
import SectionTitle from '@/components/StoreDetail/SectionTitle'
import {colors} from '@/styles/colors'
import React, {useState} from 'react'
import styled from 'styled-components'
import {ToastContainer} from 'react-toastify'
import {toast} from 'react-toastify'
import {AiOutlineCamera} from 'react-icons/ai'
import AvailableServicesAll from '@/components/Write/AvailableServicesAll'
import Map from '@/components/Map/Map'
import {useDaumPostcodePopup} from 'react-daum-postcode'
import router from 'next/router'
import {authPostService} from '@/apis/AuthPostAPI'
import {IPostWrite} from '@/apis/type/types'

const Write = () => {
  const [form, setForm] = useState<IPostWrite>({
    title: '',
    content: '',
    latitude: '00000000',
    longitude: '00000000',
    address: '',
    detailAddress: '',
    phone: '',
    checkInTime: '',
    checkOutTime: '',
    hashTag: [],
    serviceTag: [],
    roomCount: 0,
    roomCreateDtoList: [
      {
        size: 'small',
        price: undefined,
      },
      {
        size: 'medium',
        price: undefined,
      },
      {
        size: 'big',
        price: undefined,
      },
    ],
  })

  const [selectedImage, setSelectedImage] = useState([])
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

  const [clickedTag1, setClickedTag1] = useState(false)
  const [clickedTag2, setClickedTag2] = useState(false)
  const [clickedTag3, setClickedTag3] = useState(false)
  const [clickedTag4, setClickedTag4] = useState(false)
  const [clickedTag5, setClickedTag5] = useState(false)
  const [clickedTag6, setClickedTag6] = useState(false)
  const [clickedTag7, setClickedTag7] = useState(false)

  const [smallDogPrice, setSmallDogPrice] = useState(0)
  const [mediumDogPrice, setMediumDogPrice] = useState(0)
  const [bigDogPrice, setBigDogPrice] = useState(0)

  const handlePricePerDog = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target
    if (name === 'small') {
      setSmallDogPrice(Number(value))
    } else if (name === 'medium') {
      setMediumDogPrice(Number(value))
    } else if (name === 'big') {
      setBigDogPrice(Number(value))
    }
  }

  const [files, setFiles] = useState<File[]>([])

  const imageHandleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      if (Number(e.target.files?.length) > 5 || selectedImage.length >= 5) {
        return toast.error('사진은 최대 5장까지 등록 가능합니다.')
      } else if (Number(e.target.files?.length) <= 5) {
        setFiles((prevImages) => prevImages.concat(Array.from(e.target.files as any)))
        const fileArray = Array.from(e.target.files).map((file) => URL.createObjectURL(file))
        setSelectedImage((prevImages) => prevImages.concat(fileArray as any))
        Array.from(e.target.files)?.map((file) => URL.revokeObjectURL(file as any))
      }
    }
  }

  const open = useDaumPostcodePopup()
  const [updatedAddress, setUpdatedAddress] = useState('')
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

    setUpdatedAddress(fullAddress)
    setForm((prev) => ({...prev, address: fullAddress}))
  }

  const handleAddressClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault()
    open({onComplete: handleComplete})
  }

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target

    setForm({
      ...form,
      [name]: value,
    })
  }

  const onChangeTextarea = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    })
  }

  const roomCountHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({
      ...form,
      roomCount: form.roomCount + Number(e.target.value),
    })
  }

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const finalRoomDto = [
      {
        size: 'small',
        price: Number(smallDogPrice),
      },
      {
        size: 'medium',
        price: Number(mediumDogPrice),
      },
      {
        size: 'big',
        price: Number(bigDogPrice),
      },
    ]
    setForm((prev) => ({...prev, roomCreateDtoList: finalRoomDto}))

    const formData = new FormData()
    if (files) {
      files.forEach((file: any) => {
        formData.append('file', file)
      })
    }

    formData.append(
      'request',
      new Blob([JSON.stringify(form)], {
        type: 'application/json',
      })
    )

    const result = await authPostService.authPostWriteAPI(formData as any)
    if (result.status === 201) {
      // router.push(`book_confirm/${result.data.id}`)
      router.push(`/ceopage`)
      toast.success('새 포스트 등록이 완료되었습니다.')
    } else {
      toast.error('새 포스트 등록에 실패했습니다.')
      return
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
              <Input
                type="text"
                placeholder="업체명을 입력하세요."
                name="title"
                value={form?.title}
                onChange={onChange}
              />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>연락처</Label>
              <Input
                type="tel"
                placeholder="예시) '010-1234-5678'"
                name="phone"
                value={form?.phone}
                onChange={onChange}
              />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>태그</Label>
              {clickedTag1 ||
              clickedTag2 ||
              clickedTag3 ||
              clickedTag4 ||
              clickedTag5 ||
              clickedTag6 ||
              clickedTag7 ? (
                <TagListBox>
                  {clickedTag1 && (
                    <Tag
                      onClick={() => {
                        setClickedTag1(!clickedTag1)
                      }}
                    >
                      #소형견
                    </Tag>
                  )}
                  {clickedTag2 && (
                    <Tag
                      onClick={() => {
                        setClickedTag2(!clickedTag2)
                      }}
                    >
                      #중형견
                    </Tag>
                  )}
                  {clickedTag3 && (
                    <Tag
                      onClick={() => {
                        setClickedTag3(!clickedTag3)
                      }}
                    >
                      #대형견
                    </Tag>
                  )}
                  {clickedTag4 && (
                    <Tag
                      onClick={() => {
                        setClickedTag4(!clickedTag4)
                      }}
                    >
                      #노견케어
                    </Tag>
                  )}
                  {clickedTag5 && (
                    <Tag
                      onClick={() => {
                        setClickedTag5(!clickedTag5)
                      }}
                    >
                      #미용
                    </Tag>
                  )}
                  {clickedTag6 && (
                    <Tag
                      onClick={() => {
                        setClickedTag6(!clickedTag6)
                      }}
                    >
                      #산책
                    </Tag>
                  )}
                  {clickedTag7 && (
                    <Tag
                      onClick={() => {
                        setClickedTag7(!clickedTag7)
                      }}
                    >
                      #훈련
                    </Tag>
                  )}
                </TagListBox>
              ) : (
                <Input
                  type="text"
                  placeholder="업체 설명 태그를 선택해주세요. (최대 3개까지만 반영)"
                  name="tags"
                  disabled
                />
              )}
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label></Label>
              <TagList>
                {clickedTag1 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag1(!clickedTag1)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '소형견'],
                      })
                    }}
                  >
                    #소형견
                  </Tag>
                )}
                {clickedTag2 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag2(!clickedTag2)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '중형견'],
                      })
                    }}
                  >
                    #중형견
                  </Tag>
                )}
                {clickedTag3 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag3(!clickedTag3)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '대형견'],
                      })
                    }}
                  >
                    #대형견
                  </Tag>
                )}
                {clickedTag4 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag4(!clickedTag4)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '노견케어'],
                      })
                    }}
                  >
                    #노견케어
                  </Tag>
                )}
                {clickedTag5 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag5(!clickedTag5)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '미용'],
                      })
                    }}
                  >
                    #미용
                  </Tag>
                )}
                {clickedTag6 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag6(!clickedTag6)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '산책'],
                      })
                    }}
                  >
                    #산책
                  </Tag>
                )}
                {clickedTag7 === false && (
                  <Tag
                    onClick={() => {
                      setClickedTag7(!clickedTag7)
                      setForm({
                        ...form,
                        hashTag: [...form.hashTag, '훈련'],
                      })
                    }}
                  >
                    #훈련
                  </Tag>
                )}
              </TagList>
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>이용시간</Label>
              <Input
                type="text"
                placeholder="체크인시간 (예시) 오전 09:00"
                name="checkInTime"
                value={form?.checkInTime}
                onChange={onChange}
              ></Input>
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label></Label>
              <Input
                type="text"
                placeholder="체크아웃 시간 (예시) 오후 07:00"
                name="checkOutTime"
                value={form?.checkOutTime}
                onChange={onChange}
              ></Input>
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>소개글</Label>
              <Textarea
                placeholder="호텔링 소개글을 작성해주세요."
                name="content"
                value={form?.content}
                onChange={onChangeTextarea}
              />
            </CompanyInfoItem>
            <CompanyInfoItem>
              <Label>사진</Label>
              <CompanyImages>
                <div>용량이 작은 이미지 파일 5개를 업로드해주세요. (500kb 미만) </div>
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
              onChange={roomCountHandler}
            />
            <DogPriceOption
              type="text"
              name="small"
              placeholder="예시) 3만원 -> 30000 입력"
              onChange={handlePricePerDog}
            />
          </DogOptionsCategories>
          <DogOptionsCategories>
            <div>중형견</div>
            <DogPriceOption
              type="text"
              name="availableRooms"
              placeholder="예시) 객실 5개 -> 5 입력"
              onChange={roomCountHandler}
            />
            <DogPriceOption
              type="text"
              name="medium"
              placeholder="예시) 3만원 -> 30000 입력"
              onChange={handlePricePerDog}
            />
          </DogOptionsCategories>
          <DogOptionsCategories>
            <div>대형견</div>
            <DogPriceOption
              type="text"
              name="availableRooms"
              placeholder="예시) 객실 5개 -> 5 입력"
              onChange={roomCountHandler}
            />
            <DogPriceOption
              type="text"
              name="big"
              placeholder="예시) 3만원 -> 30000 입력"
              onChange={handlePricePerDog}
            />
          </DogOptionsCategories>
        </DogOptions>
        <DogOptionsSection>이용가능 서비스 선택</DogOptionsSection>
        <DogOptionGuide>상단 오른쪽 동그라미 버튼을 눌러 카드를 선택하세요.</DogOptionGuide>
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
              setForm={setForm}
            />
          </AvailableServiceChoice>
        </AvailableServices>
        <CompanyAddress>
          <DogOptionsSection>주소</DogOptionsSection>
          <CompanyAddressBox>
            <AddressMain>
              <AddressInputWrapper>
                <AddressInput
                  type="text"
                  name="address"
                  value={updatedAddress}
                  onChange={onChange}
                />
              </AddressInputWrapper>
              <AddressSearch>
                <AddressSearchButton onClick={handleAddressClick}>주소 검색</AddressSearchButton>
              </AddressSearch>
            </AddressMain>
            <AddressSub>
              <AddressInput type="text" name="detailAddress" onChange={onChange} />
            </AddressSub>
          </CompanyAddressBox>
        </CompanyAddress>
        <MapDisplay>
          {updatedAddress.length > 0 ? (
            <Map address={updatedAddress} companyName={form.title} />
          ) : (
            <Map
              address={'서울특별시 마포구 매봉산로 31 에스플렉스 지하 1층'}
              companyName={'뭉텔 Moongtel'}
            />
          )}
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

const TagList = styled.div`
  flex: 5;
  display: flex;
  align-items: center;
  gap: 10px;
`

const TagListBox = styled(TagList)`
  background-color: ${colors.grey1};
  height: 2.5rem;
  border-radius: 0.3rem;
  padding: 0.5rem 1rem;
`

const Tag = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  background-color: ${colors.mainColor2};
  border-radius: 0.3rem;
  padding: 0.5rem;
  font-size: 1.3rem;
  color: rgb(72, 71, 71);
  border: none;
  cursor: pointer;

  :hover {
    background-color: rgba(253, 208, 124, 0.831);
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

  div {
    margin-bottom: -1rem;
    color: #666;
  }
`

const CompanyImagesBox = styled.div`
  height: 10rem;
  border-radius: 0.3rem;
  display: flex;
  width: 90%;
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

const DogOptionGuide = styled.div`
  color: rgb(102, 102, 102);
  font-size: 1rem;
  margin-top: -1rem;
  margin-left: 1rem;
  margin-bottom: 2rem;
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
  border: 1px solid rgb(185, 185, 185);
  color: rgb(185, 185, 185);
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
