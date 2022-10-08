// 반복문 돌 때 필요한 인자 타입

export interface Mypage {
  users: Users
  reviewList: Review[]
}

export interface Users {
  createdAt: Date
  modifiedAt: Date
  id: number
  email: string
  password: string
  username: string
  phone: string
  roles: string
  dogCardList: DogCard[]
  roleList: string[]
}

export interface DogCard {
  data: DogCard
  id: number
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

export interface EditDogCard {
  name: string
  type: string
  age: string
  gender: boolean
  weight: string
  surgery: boolean
  bowel_trained: boolean
  snack_method: boolean
  etc: boolean
  bark: boolean
}

export interface Review {
  createdAt: Date
  modifiedAt: Date
  id: number
  title: string
  content: string
  score: number
  userId: number
  postsId: number
  companyInfo: CompanyInfo
  reviewImg: ReviewImg[]
}

interface CompanyInfo {
  postsCompanyName: string
  postsImg: string
  totalPrice: number
}

interface ReviewImg {
  fileName: string
  id: number
  imgUrl: string
}

export interface Reservation {
  reservedId: number
  checkIn: Date
  checkOut: Date
  status: string
  usersId: number
  postsId: number
  totalPrice: number
  companyId: number
  dogCount: number
  reservationCode: string
  dogIdList: number[]
  userInfo: UserInfo
}

export interface UserInfo {
  name: string
  phone: string
  email: string
}

export interface BeforeReservation {
  roomPrice: number
  title: string
  url: string
}

export interface afterReservation {
  postsId: number
  roomPrice: number
  title: string
  url: string
}
