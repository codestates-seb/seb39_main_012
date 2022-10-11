export interface ILogin {
  email: FormDataEntryValue | null
  password: FormDataEntryValue | null
}
export interface UserSignUp {
  email: string
  password: string
  username: string
  phone: string
  companyName?: string
  address?: string
  detailAddress?: string
  ceo?: string
}

export interface IPostBookingWish {
  map: {
    small: number
    medium: number
    big: number
  }
  checkInDate: string
  checkOutDate: string
  checkInTime: string
  checkOutTime: string
}

interface reservationListProps {
  id: null
  companyId: number
  reservationDate: string
  dogCount: number
}

interface mapProps {
  small: number
  medium: number
  big: number
}

interface userInfoProps {
  name: string
  phone: string
  email: string
}
export interface IPostBooking {
  reservationCreateDto: {
    reservationList: reservationListProps[]
    dto: {
      map: mapProps
      checkInDate: string
      checkOutDate: string
      checkInTime: string
      checkOutTime: string
    }
    checkInDate: string
    checkOutDate: string
    checkInTime: string
    checkOutTime: string
    totalDogCount: number
    totalPrice: number
  }
  reservationUserInfoDto: {
    dogCardsId: number[]
    userInfo: userInfoProps
  }
}

interface roomCreateDtoListProps {
  size: string
  price: number | undefined
}
export interface IPostWrite {
  title: string
  content: string
  latitude: string
  longitude: string
  phone: string
  address: string
  detailAddress: string
  checkInTime: string
  checkOutTime: string
  hashTag: string[]
  serviceTag: string[]
  roomCount: number
  roomCreateDtoList: roomCreateDtoListProps[]
}
