import React from 'react'
import {colors} from '@/styles/colors'
import styled from 'styled-components'
import {BsRecordCircle} from 'react-icons/bs'
import {availableServices} from '@/utils/options/options'
import Image from 'next/image'
import {IPostWrite} from '@/apis/type/types'

interface Props {
  clickedService1: boolean
  setClickedService1: React.Dispatch<React.SetStateAction<boolean>>
  clickedService2: boolean
  setClickedService2: React.Dispatch<React.SetStateAction<boolean>>
  clickedService3: boolean
  setClickedService3: React.Dispatch<React.SetStateAction<boolean>>
  clickedService4: boolean
  setClickedService4: React.Dispatch<React.SetStateAction<boolean>>
  clickedService5: boolean
  setClickedService5: React.Dispatch<React.SetStateAction<boolean>>
  clickedService6: boolean
  setClickedService6: React.Dispatch<React.SetStateAction<boolean>>
  clickedService7: boolean
  setClickedService7: React.Dispatch<React.SetStateAction<boolean>>
  clickedService8: boolean
  setClickedService8: React.Dispatch<React.SetStateAction<boolean>>
  clickedService9: boolean
  setClickedService9: React.Dispatch<React.SetStateAction<boolean>>
  clickedService10: boolean
  setClickedService10: React.Dispatch<React.SetStateAction<boolean>>
  clickedService11: boolean
  setClickedService11: React.Dispatch<React.SetStateAction<boolean>>
  clickedService12: boolean
  setClickedService12: React.Dispatch<React.SetStateAction<boolean>>
  setForm: React.Dispatch<React.SetStateAction<IPostWrite>>
}

const AvailableServicesAll = (props: Props) => {
  return (
    <>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService1 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon1.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[0][0]}</h3>
            <h3>{availableServices[0][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService1(!props.clickedService1)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[0][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService1 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService2 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon2.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[1][0]}</h3>
            <h3>{availableServices[1][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService2(!props.clickedService2)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[1][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService2 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService3 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon3.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[2][0]}</h3>
            <h3>{availableServices[2][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService3(!props.clickedService3)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[2][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService3 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService4 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon4.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[3][0]}</h3>
            <h3>{availableServices[3][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService4(!props.clickedService4)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[3][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService4 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService5 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon5.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[4][0]}</h3>
            <h3>{availableServices[4][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService5(!props.clickedService5)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[4][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService5 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService6 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon6.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[5][0]}</h3>
            <h3>{availableServices[5][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService6(!props.clickedService6)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[5][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService6 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService7 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon7.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[6][0]}</h3>
            <h3>{availableServices[6][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService7(!props.clickedService7)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[6][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService7 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService8 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon8.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[7][0]}</h3>
            <h3>{availableServices[7][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService8(!props.clickedService8)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[7][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService8 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService9 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon9.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[8][0]}</h3>
            <h3>{availableServices[8][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService9(!props.clickedService9)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[8][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService9 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService10 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon9.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[9][0]}</h3>
            <h3>{availableServices[9][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService10(!props.clickedService10)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[9][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService10 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService11 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon10.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[10][0]}</h3>
            <h3>{availableServices[10][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService11(!props.clickedService11)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[10][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService11 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
      <AvailableServiceItem
        style={{backgroundColor: props.clickedService12 ? colors.subColor3 : colors.grey1}}
      >
        <AvailableServiceBg>
          <AvailableServiceIcon>
            <AvailableServiceIconBg>
              <AvailableServiceIconWrapper>
                {/* <FaDog size={40} color={colors.grey2} /> */}
                <Image
                  src={'/svg/serviceIcons/ServiceIcon11.png'}
                  alt={'service-icon'}
                  width={40}
                  height={40}
                />
              </AvailableServiceIconWrapper>
            </AvailableServiceIconBg>
          </AvailableServiceIcon>
          <AvailableServiceContent>
            <h3>{availableServices[11][0]}</h3>
            <h3>{availableServices[11][1]}</h3>
          </AvailableServiceContent>
          <ServiceChoiceButton
            onClick={() => {
              props.setClickedService12(!props.clickedService12)
              props.setForm((prev) => ({
                ...prev,
                serviceTag: [...prev.serviceTag, availableServices[11][0]],
              }))
            }}
          >
            <BsRecordCircle
              size={18}
              style={{color: props.clickedService12 ? colors.mainColor : colors.grey2}}
            />
          </ServiceChoiceButton>
        </AvailableServiceBg>
      </AvailableServiceItem>
    </>
  )
}

export default AvailableServicesAll

const AvailableServiceItem = styled.div`
  background-color: ${colors.grey1};
  border-radius: 10px;

  .selectedItem {
    background-color: ${colors.subColor3};
  }
`

const AvailableServiceBg = styled.div`
  display: flex;
  flex: 0 0 30%;
  border-radius: 1.5rem;
  padding: 0.8rem;
`

const AvailableServiceIconBg = styled.div`
  margin: 1rem;
  width: 6rem;
  height: 6rem;
  background-color: rgb(255, 255, 255);
  border-radius: 50%;
  position: relative;
`

const AvailableServiceIconWrapper = styled.div`
  position: absolute;
  top: 7px;
  left: 10px;
`

const AvailableServiceIcon = styled.div`
  flex: 1;
`

const AvailableServiceContent = styled.div`
  flex: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  line-height: 2rem;
  color: rgb(51, 51, 51);

  h3 {
    font-size: 1.5rem;
  }

  h3:first-child {
    font-weight: 700;
  }
`

const ServiceChoiceButton = styled.div`
  cursor: pointer;
`
