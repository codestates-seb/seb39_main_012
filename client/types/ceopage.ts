export interface Ceo {
  username: string
  email: string
  phone: string
  companyInfo: CompanyInfo
  postsInfo: PostsInfo
  reservationListPage: Reservation[]
}

export interface CeoInfo {
  ceoName: string
  ceoEmail: string
  ceoPhone: string
}

export interface CompanyInfo {
  id: number
  companyName: string
  ceo: string
  address: string
  detailAddress: string
  userId: number
}

export interface PostsInfo {
  id: number
  title: string
  content: string
  latitude: string
  longitude: string
  address: string
  detailAddress: string
  phone: string
  companyId: number
  roomCount: number
  likesCount: number
  avgScore: number
  checkInTime: string
  checkOutTime: string
  postsImgList: PostsImgList[]
}

export interface PostsImgList {
  id: number
  fileName: string
  imgUrl: string
}

export interface PostRecoil {
  id: number
  title: string
  content: string
  thumbnail: string
}

export interface Reservation {
  reservedId: number
  checkInDate: Date
  checkOutDate: Date
  checkInTime: string
  checkOutTime: string
  status: string
  usersId: number
  postsId: number
  totalPrice: number
  companyId: number
  dogCount: number
  dogIdList: number[]
  userInfo: UserInfo
}

export interface UserInfo {
  name: string
  phone: string
  email: string
}
