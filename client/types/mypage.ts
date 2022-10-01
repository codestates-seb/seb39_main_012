export interface Mypage {
  users: Users
  dogCardList: DogCard[]
  reservationList: any[]
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
  id: number
  content: string
  score: number
  userId: number
  postsId: number
}
