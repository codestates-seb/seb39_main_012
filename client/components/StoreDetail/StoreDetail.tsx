/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable no-irregular-whitespace */
/* eslint-disable @typescript-eslint/no-empty-function */
/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
import React, {useState, useEffect} from 'react'
import {colors} from '@/styles/colors'
import styled from 'styled-components'
import SectionTitle from './SectionTitle'
import AvailableServiceCard from './AvailableServiceCard'
import {FiMapPin} from 'react-icons/fi'
import CompanyReviewCard from './CompanyReviewCard'
import CompanyReviewListCard from './CompanyReviewListCard'
import Map from '../Map/Map'
import {DateRange} from 'react-date-range'
import {format} from 'date-fns'
import koLocale from 'date-fns/locale/ko'
import {BiPlusCircle} from 'react-icons/bi'
import {BiMinusCircle} from 'react-icons/bi'
import AuthButton from '../AuthButton/AuthButton'
import {toast} from 'react-toastify'
import {PostById} from '@/types/post'
import CompanyImageSlider from './CompanyImageSlider'
import CompanyTitleSection from './CompanyTitleSection'
import {availableServices} from '@/utils/options/options'
import LocalStorage from '@/utils/util/localStorage'
import {bookingService} from '@/apis/bookingAPI'
import {useRouter} from 'next/router'
import {postService} from '@/apis/PostAndSearchAPI'
import {userService} from '@/apis/MyPageAPI'
import Footer from '../Layout/Footer/Footer'

export const getDayOfDate = (reservationDate: string) => {
  const date = ['일', '월', '화', '수', '목', '금', '토']
  const getDayOfDate = date[new Date(reservationDate).getDay()]
  return getDayOfDate
}

const StoreDetail = ({postId}: {postId: number}) => {
  const router = useRouter()
  const [post, setPost] = useState<PostById>()
  const [addLikes, setAddLikes] = useState(0)
  const [reviewClicked1, setReviewClicked1] = useState(false)
  const [openDate, setOpenDate] = useState(false)
  const [openDogNum, setOpenDogNum] = useState(false)
  const [smallDogNum, setSmallDogNum] = useState(0)
  const [mediumDogNum, setMediumDogNum] = useState(0)
  const [largeDogNum, setLargeDogNum] = useState(0)
  const [checkInTimeAMPM, setCheckInTimeAMPM] = useState('오전')
  const [checkInTime, setCheckInTime] = useState('00:00')
  const [checkOutTimeAMPM, setCheckOutTimeAMPM] = useState('오후')
  const [checkOutTime, setCheckOutTime] = useState('00:00')
  const companyAddress = post?.address.split(' ')[0] + ' ' + post?.address.split(' ')[1]
  const companyName = post?.title.replace(/\s+/g, '')
  const [openTime, setOpenTime] = useState(false)
  const [date, setDate] = useState([
    {
      startDate: new Date(),
      endDate: new Date(),
      key: 'selection',
    },
  ])

  useEffect(() => {
    if (!postId) {
      return
    }

    postService.getPostById(postId).then((result) => {
      setPost(result)
      setAddLikes(result.likesCount)
    })
  }, [postId])

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    if (!LocalStorage.getItem('accessToken')) {
      toast.error('로그인이 필요한 서비스입니다.')
      return
    }

    if (smallDogNum === 0 && mediumDogNum === 0 && largeDogNum === 0) {
      return toast.error('모든 항목을 입력해주세요.')
    }

    const requestForm = {
      map: {
        small: smallDogNum,
        medium: mediumDogNum,
        big: largeDogNum,
      },
      checkInDate: `${format(date[0].startDate, `yyyy-MM-dd`)}`,
      checkOutDate: `${format(date[0].endDate, `yyyy-MM-dd`)}`,
      checkInTime: checkInTimeAMPM + ' ' + checkInTime,
      checkOutTime: checkOutTimeAMPM + ' ' + checkOutTime,
    }

    if (
      !requestForm.checkInDate ||
      !requestForm.checkOutDate ||
      !requestForm.checkInTime ||
      !requestForm.checkOutTime
    ) {
      return toast.error('모든 항목을 입력해주세요.')
    }

    const result = await userService.getMyPage()

    if (result.data.users.dogCardList.length === 0) {
      return toast.error('마이페이지에서 강아지 카드를 등록해주세요.')
    }

    const tempBooking = await bookingService.postBookingWish(postId, requestForm)

    if (tempBooking.status === 200) {
      LocalStorage.setItem('tempBooking', JSON.stringify(tempBooking.data))
      router.push(`/book/${postId}`)
    } else {
      toast.error('예약이 실패했습니다.')
    }
  }

  return (
    <CompanyDetailContainer>
      <CompanyInfoTop>
        <CompanyTitleSection
          companyAddress={companyAddress}
          companyName={companyName}
          post={post}
          postId={postId}
          // clickedLike={clickedLike}
          // setClickedLike={setClickedLike}
          addLikes={addLikes}
          setAddLikes={setAddLikes}
        />
        <CompanyImageSlider
          postImg={post?.postsImgList}
          companyName={companyName}
          companyAddress={companyAddress}
        />
      </CompanyInfoTop>
      <CompanyInfoBottom>
        <CompanyDesc>
          <CompanyIntro>
            <SectionTitle title={'소개'} />
            <CompanyIntroBg>
              <p>{post?.content}</p>
            </CompanyIntroBg>
          </CompanyIntro>
          <CompanyAvailableService>
            <SectionTitle title={'이용 가능 서비스'} />
            <AvailableServiceCards>
              {post?.serviceTag.map((service, idx) => {
                const tagName = service.tag
                const tagDesc = availableServices.filter((ele) => {
                  return ele[0] === tagName
                })

                return (
                  <AvailableServiceCard
                    key={idx}
                    serviceTitle={post?.serviceTag[idx].tag as string}
                    serviceContent={tagDesc[0][1]}
                    iconImageSrc={tagDesc[0][2]}
                    alt={'소형견 케어'}
                    width={40}
                    height={40}
                  />
                )
              })}
            </AvailableServiceCards>
          </CompanyAvailableService>
          <CompanyLocation>
            <SectionTitle title={'위치'} />
            <CompanyLocationAddress>
              <span>
                <FiMapPin size={14} />
              </span>
              <span>{`${post?.address} ${post?.detailAddress}`}</span>
            </CompanyLocationAddress>
            <CompanyLocationMap>
              {/* <Map latitude={Number(post?.latitude)} longitude={Number(post?.longitude)} /> */}
              <Map address={post?.address as string} companyName={post?.title as string} />
            </CompanyLocationMap>
          </CompanyLocation>
          <CompanyReviews>
            <SectionTitle title={'리뷰'} sub1={String(post?.reviewList.length)} sub2={'건'} />
            <CompanyReviewsCards>
              {post?.reviewList.slice(0, 3).map((review, idx) => {
                return (
                  <CompanyReviewCard
                    key={idx}
                    reviewImageSrc={post?.reviewList[idx].reviewImgList[0].imgUrl}
                    alt={'company-review-image1'}
                    reviewTitle={review.title}
                    reviewStarNum={review.score}
                    reviewContent={review.content}
                    reviewDate={review.modifiedAt ? review.modifiedAt : review.createdAt}
                    reviewerName={review.writer}
                    reviewClicked={reviewClicked1}
                    setReviewClicked={setReviewClicked1}
                  />
                )
              })}
            </CompanyReviewsCards>
            <CompanyReviewsList>
              {post?.reviewList.slice(3).map((review, idx) => {
                return (
                  <CompanyReviewListCard
                    key={idx}
                    reviewStarNum={review.score as number}
                    reviewerName={review.writer as string}
                    reviewDate={
                      review.modifiedAt ? review.modifiedAt : (review.createdAt as string)
                    }
                    reviewTitle={review.title as string}
                    reviewContent={review.content as string}
                    reviewImageSrc1={post?.reviewList[idx]?.reviewImgList[0]?.imgUrl}
                    reviewImageSrc2={post?.reviewList[idx]?.reviewImgList[1]?.imgUrl}
                    reviewImageSrc3={post?.reviewList[idx]?.reviewImgList[2]?.imgUrl}
                  />
                )
              })}
            </CompanyReviewsList>
            <CompanyReviewsPagination></CompanyReviewsPagination>
          </CompanyReviews>
        </CompanyDesc>
        <CompanyReservation onSubmit={onSubmit}>
          <CompanyReservationTop>
            <CompanyReservationTopContainer>
              <CompanyReservationTopTitle>상세 정보</CompanyReservationTopTitle>
              <CompanyReservationForm>
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    언제 호텔링이 필요한가요?
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={() => {
                      setOpenDate(!openDate)
                    }}
                  >
                    {openDate ? (
                      <span className="booking">{`${format(
                        date[0].startDate,
                        `yyyy년 MM월 dd일 (${getDayOfDate(
                          format(date[0].startDate, `yyyy-MM-dd`)
                        )})`
                      )} → ${format(
                        date[0].endDate,
                        `yyyy년 MM월 dd일 (${getDayOfDate(format(date[0].endDate, `yyyy-MM-dd`))})`
                      )} `}</span>
                    ) : (
                      <span>호텔링 날짜를 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                </CompanyReservationSubSection>
                {openDate && (
                  <CompanyReservationSelectCalendar>
                    <DateRange
                      editableDateInputs={true}
                      onChange={(item: any) => setDate([item.selection])}
                      moveRangeOnFirstSelection={false}
                      ranges={date}
                      locale={koLocale}
                      className="dateRange"
                    />
                  </CompanyReservationSelectCalendar>
                )}
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    체크인 / 체크아웃 시간
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={() => {
                      setOpenTime(!openTime)
                    }}
                  >
                    {openTime ? (
                      <span className="booking">
                        체크인: {checkInTimeAMPM} {checkInTime} / 체크아웃: {checkOutTimeAMPM}{' '}
                        {checkOutTime}
                      </span>
                    ) : (
                      <span>체크인 / 체크아웃 시간을 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                  {openTime && (
                    <CompanyReservationTimeSelect>
                      <CompanyReservationTimeSelectContainer>
                        <CompanyReservationDetailTimeSelect>
                          <CompanyReservationTimeCheck>
                            <span>체크인 시간</span>
                          </CompanyReservationTimeCheck>
                          <CompanyReservationTimeSelectDivisions>
                            <CompanyReservationTimeDivision>
                              <div
                                onClick={(e) => {
                                  e.stopPropagation()
                                  setCheckInTimeAMPM('오전')
                                }}
                              >
                                <span>오전</span>
                              </div>
                              <div
                                onClick={(e) => {
                                  e.stopPropagation()
                                  setCheckInTimeAMPM('오후')
                                }}
                              >
                                <span>오후</span>
                              </div>
                            </CompanyReservationTimeDivision>

                            <CompanyReservationTimeItems>
                              {[
                                '12:00',
                                '01:00',
                                '02:00',
                                '03:00',
                                '04:00',
                                '05:00',
                                '06:00',
                                '07:00',
                                '08:00',
                                '09:00',
                                '10:00',
                                '11:00',
                              ].map((time, index) => {
                                return (
                                  <div
                                    key={index}
                                    onClick={(e) => {
                                      e.stopPropagation()
                                      setCheckInTime(time)
                                    }}
                                  >
                                    <span>{time}</span>
                                  </div>
                                )
                              })}
                            </CompanyReservationTimeItems>
                          </CompanyReservationTimeSelectDivisions>
                        </CompanyReservationDetailTimeSelect>
                        <CompanyReservationDetailTimeSelect>
                          <CompanyReservationTimeCheck>
                            <span>체크아웃 시간</span>
                          </CompanyReservationTimeCheck>
                          <CompanyReservationTimeSelectDivisions>
                            <CompanyReservationTimeDivision>
                              <div
                                onClick={(e) => {
                                  e.stopPropagation()
                                  setCheckOutTimeAMPM('오전')
                                }}
                              >
                                <span>오전</span>
                              </div>
                              <div
                                onClick={(e) => {
                                  e.stopPropagation()
                                  setCheckOutTimeAMPM('오후')
                                }}
                              >
                                <span>오후</span>
                              </div>
                            </CompanyReservationTimeDivision>
                            <CompanyReservationTimeItems>
                              {[
                                '12:00',
                                '01:00',
                                '02:00',
                                '03:00',
                                '04:00',
                                '05:00',
                                '06:00',
                                '07:00',
                                '08:00',
                                '09:00',
                                '10:00',
                                '11:00',
                              ].map((time, index) => {
                                return (
                                  <div
                                    key={index}
                                    onClick={(e) => {
                                      e.stopPropagation()
                                      setCheckOutTime(time)
                                    }}
                                  >
                                    <span>{time}</span>
                                  </div>
                                )
                              })}
                            </CompanyReservationTimeItems>
                          </CompanyReservationTimeSelectDivisions>
                        </CompanyReservationDetailTimeSelect>
                      </CompanyReservationTimeSelectContainer>
                    </CompanyReservationTimeSelect>
                  )}
                </CompanyReservationSubSection>
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    맡기시는 반려동물
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={(e) => {
                      e.stopPropagation()
                      setOpenDogNum(!openDogNum)
                    }}
                  >
                    {openDogNum ? (
                      <span className="booking">
                        {`소형견: ${smallDogNum} / 중형견: ${mediumDogNum} / 대형견: ${largeDogNum}`}
                      </span>
                    ) : (
                      <span>반려동물 수를 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                  {openDogNum && (
                    <CompanyReservationHowManyDogs>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">소형견</div>
                          <div className="optionTextDesc">7kg 미만</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter minus"
                            onClick={(e) => {
                              e.stopPropagation()
                              smallDogNum === 0
                                ? setSmallDogNum(0)
                                : setSmallDogNum(smallDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{smallDogNum}</span>
                          <span
                            className="optionCounter plus"
                            onClick={(e) => {
                              e.stopPropagation()
                              smallDogNum === 5
                                ? setSmallDogNum(5)
                                : setSmallDogNum(smallDogNum + 1)
                            }}
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">중형견</div>
                          <div className="optionTextDesc">7kg~14.9kg 미만</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter plus"
                            onClick={(e) => {
                              e.stopPropagation()
                              mediumDogNum === 0
                                ? setMediumDogNum(0)
                                : setMediumDogNum(mediumDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{mediumDogNum}</span>
                          <span
                            className="optionCounter minus"
                            onClick={(e) => {
                              e.stopPropagation()
                              mediumDogNum === 5
                                ? setMediumDogNum(5)
                                : setMediumDogNum(mediumDogNum + 1)
                            }}
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">대형견</div>
                          <div className="optionTextDesc">15kg 이상</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter plus"
                            onClick={(e) => {
                              e.stopPropagation()
                              largeDogNum === 0
                                ? setLargeDogNum(0)
                                : setLargeDogNum(largeDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{largeDogNum}</span>
                          <span
                            className="optionCounter minus"
                            onClick={(e) => {
                              e.stopPropagation()
                              largeDogNum === 5
                                ? setLargeDogNum(5)
                                : setLargeDogNum(largeDogNum + 1)
                            }}
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptionsNotice>
                        최대 5마리까지만 선택 가능합니다.
                      </CompanyReservationDogOptionsNotice>
                    </CompanyReservationHowManyDogs>
                  )}
                </CompanyReservationSubSection>
                <AlertNotice>
                  <ReservationAlert>
                    ✔ <a>오늘 이후의 날짜</a>를 선택해주세요.
                  </ReservationAlert>
                  <ReservationAlert>
                    ✔ 예약 요청 전, {/* <Link href="/mypage" passHref> */}
                    <a> 마이페이지</a>
                    {/* </Link> */}
                    에서 반려견카드를 작성해주세요.
                  </ReservationAlert>
                </AlertNotice>
                <CompanyReservationSubSection>
                  <AuthButton title={'예약 요청'} width={'100%'} />
                </CompanyReservationSubSection>
              </CompanyReservationForm>
            </CompanyReservationTopContainer>
          </CompanyReservationTop>
          <CompanyReservationMiddle>
            <CompanyReservationMiddleContainer>
              <CompanyReservationTable>
                <CompanyReservationTableLeft>이용요금</CompanyReservationTableLeft>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>소형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>(7kg 미만)</CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>
                  {post?.roomDtos[0]['price']}원<span>&nbsp;/1박 케어</span>
                </CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>중형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>
                    (7kg~14.9kg)
                  </CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>
                  {post?.roomDtos[1]['price']}원<span>&nbsp;/1박 케어</span>
                </CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>대형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>(15kg 이상)</CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>
                  {post?.roomDtos[2]['price']}원<span>&nbsp;/1박 케어</span>
                </CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
            </CompanyReservationMiddleContainer>
          </CompanyReservationMiddle>
          <CompanyReservationBottom></CompanyReservationBottom>
        </CompanyReservation>
      </CompanyInfoBottom>
      <Footer />
    </CompanyDetailContainer>
  )
}

export default StoreDetail

const CompanyDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 4rem;
  width: 100%;
`

const CompanyInfoTop = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  width: 100%;
`

const CompanyInfoBottom = styled.div`
  flex: 4;
  display: flex;
  gap: 5rem;
  margin-top: 8rem;
  width: 100%;
`

const CompanyDesc = styled.div`
  flex: 2;
`

const CompanyIntro = styled.div`
  p {
    font-size: 1.5rem;
    line-height: 3rem;
  }
`

const CompanyIntroBg = styled.div`
  background-color: ${colors.grey1};
  padding: 4rem;
  border-radius: 1rem;
  color: ${colors.grey4};
`

const CompanyAvailableService = styled.div`
  margin-top: 8rem;
`

const AvailableServiceCards = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;

  & > div:nth-child(odd) {
    background-color: ${colors.subColor2};
  }

  & > div:nth-child(even) {
    background-color: ${colors.subColor1};
  }
`

const CompanyLocation = styled.div`
  margin-top: 8rem;
  display: flex;
  flex-direction: column;
`

const CompanyLocationAddress = styled.div`
  display: flex;
  margin-left: 0.5rem;
  color: ${colors.grey4};

  span:last-child {
    font-size: 1.6rem;
    margin-left: 0.2rem;
  }
`

const CompanyLocationMap = styled.div`
  display: flex;
  margin: 3rem 0 0 0;
`

const CompanyReviews = styled.div`
  margin-top: 8rem;
`

const CompanyReviewsCards = styled.div`
  display: flex;
  gap: 1rem;
`

const CompanyReviewsList = styled.div`
  margin: 5rem 0;
`

const CompanyReviewsPagination = styled.div``

const CompanyReservation = styled.form`
  flex: 1;
  margin-top: 4.5rem;
`

const CompanyReservationTop = styled.div``

const CompanyReservationTopContainer = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 2rem;
`
const CompanyReservationTopTitle = styled.div`
  font-size: 1.8rem;
  color: ${colors.mainColor};
  font-weight: 700;
`

const AlertNotice = styled.div`
  margin-top: 2rem;
`

const ReservationAlert = styled.div`
  margin: 1rem 0 0 0.5rem;
  font-size: 1.2rem;
  color: #666;

  a {
    text-decoration: underline;
    outline: none;
    color: #555;
  }
    a:hover,
  a:active {
    text-decoration: underline;
  }
`

const CompanyReservationForm = styled.div`
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  width: 100%;
`
const CompanyReservationSubSection = styled.div`
  margin-top: 2.5em;
`

const CompanyReservationSubSectionTitle = styled.h3`
  font-size: 1.6rem;
  font-weight: 600;
  color: rgb(102, 102, 102);
  margin-left: 0.5rem;
`

const CompanyReservationSubSectionInput = styled.div`
  cursor: pointer;
  padding: 1.3rem;
  border: 1px solid rgb(235, 235, 235);
  width: 100%;
  box-sizing: border-box;
  margin-top: 1rem;
  border-radius: 8px;
  outline: none;
  line-height: 2rem;

  span {
    font-size: 1.4rem;
    color: ${colors.grey2};
  }

  :focus {
    outline: 0.4rem solid rgb(231, 245, 232);
  }

  .booking {
    color: rgb(29, 47, 40);
    font-weight: 500;
  }
`

const CompanyReservationSelectCalendar = styled.div`
  margin-top: 1rem;
  .dateRange {
    padding: 1.5rem 0;
    color: ${colors.mainColor};
    font-family: 나눔스퀘어, 'NanumSquare', 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
      'Open Sans', 'Helvetica Neue', sans-serif !important;

    .rdrDateDisplayWrapper {
      border-radius: 5px;
    }
    .rdrDateDisplay input {
      text-transform: uppercase;
      font-family: 나눔스퀘어, 'NanumSquare', 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
        'Open Sans', 'Helvetica Neue', sans-serif !important;
    }

    .rdrDateDisplay {
      color: rgba(186, 213, 178, 0.762) !important;
    }

    .rdrStartEdge,
    .rdrEndEdge {
      color: rgb(123, 176, 114) !important;
    }

    .rdrInRange {
      color: rgb(174, 205, 164) !important;
    }

    .rdrDayToday .rdrDayNumber {
      span::after {
        background: ${colors.mainColor} !important;
      }
    }
  }
`

const CompanyReservationTimeSelect = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 2.5rem 0;
  border-top: none;
`

const CompanyReservationTimeSelectContainer = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  gap: 20px;
`
const CompanyReservationDetailTimeSelect = styled.div``

const CompanyReservationTimeCheck = styled.div`
  font-size: 1.3rem;
  font-weight: 700;
  color: rgb(216, 216, 216);
  margin-bottom: 1rem;
`

const CompanyReservationTimeSelectDivisions = styled.div`
  display: flex;
  font-size: 1.5rem;
  border-top: 1px solid rgb(236, 236, 236);
`

const CompanyReservationTimeDivision = styled.div`
  cursor: pointer;

  div {
    margin-top: 1rem;
    color: rgb(164, 190, 155);
    font-weight: 700;
    padding: 0.2rem;

    :hover {
      background-color: ${colors.mainColor};
      color: rgb(255, 255, 255);
      border-radius: 3px;
    }
  }

  span {
    margin: 0.5rem;
    padding: 1rem;
  }
`

const CompanyReservationTimeItems = styled.div`
  color: rgb(111, 115, 118);
  /* font-weight: 500; */
  cursor: pointer;

  div {
    margin-top: 1rem;
    padding: 0.2rem;

    :hover {
      background-color: rgb(122, 176, 114);
      color: rgb(255, 255, 255);
      border-radius: 3px;
      font-weight: 600;
    }
  }
  span {
    margin: 0.5rem;
    padding: 1rem;
  }
`

const CompanyReservationHowManyDogs = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-top: none;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 2rem 5rem;
  font-size: 1.5rem;
`

const CompanyReservationDogOptions = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem 0;
`
const CompanyReservationDogOptionsTitle = styled.div`
  font-weight: 600;

  & > :nth-child(2) {
    font-size: 1.2rem;
    color: ${colors.grey2};
    margin-top: 0.7rem;
  }
`

const CompanyReservationDogOptionCounter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;

  span {
    display: flex;
    text-align: center;
  }

  .optionCounter {
    cursor: pointer;
  }
`

const CompanyReservationDogOptionsNotice = styled.div`
  font-size: 1.2rem;
  margin-top: 1rem;
  color: rgb(212, 212, 212);
`

const CompanyReservationMiddle = styled.div``

const CompanyReservationMiddleContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 3rem;
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 0rem 6rem 2rem 2rem;
`

const CompanyReservationTable = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 2rem;
`

const CompanyReservationTableOption = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin-left: 1rem;
`

const CompanyReservationTableOptionPrice = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;

  span {
    font-size: 1.5rem;
    color: rgb(194, 194, 194);
    font-weight: 600;
  }
`

const CompanyReservationTableOptionTitle = styled.div`
  font-size: 1.4rem;
`

const CompanyReservationTableOptionDesc = styled.div`
  font-size: 1rem;
  margin-top: 0.5rem;
`

const CompanyReservationTableLeft = styled.div`
  flex: 1;
  font-size: 1.8rem;
  color: ${colors.mainColor};
  font-weight: 700;
  margin-bottom: 1.5rem;
`

const CompanyReservationBottom = styled.div``
