export interface Post {
  id: number
  title: string
  address: string
  avgScore: number
  likesCount: number
  img: Img
  minPrice: number
}

export interface PageInfo {
  page: number
  size: number
  totalElements: number
  totalPages: number
}

export interface Img {
  fileName: string
  url: string
}

interface PostsImgListProps {
  fileName: string
  url: string
}

interface HashTagProps {
  hashTagId: number
  tag: string
}

interface ServiceTagProps {
  serviceTagId: number
  tag: string
}

interface RoomDtosProps {
  roomId: number
  size: string
  price: number
}

interface ReviewImgListProps {
  id: number
  fireName: string
  imgUrl: string
}
interface ReviewListProps {
  // [x: string]: string | number | ReviewImgListProps[]
  id: number
  title: string
  content: string
  score: number
  writer: string
  postId: number
  createdAt: string
  modifiedAt: string
  reviewImgList: ReviewImgListProps[]
}

export interface PostById {
  json: any
  data: any
  id: number
  title: string
  content: string
  latitude: string
  longitude: string
  address: string
  detailAddress: string
  phone: string
  companyId: number
  avgScore: number
  likesCount: number
  checkIn: string
  checkOut: string
  postsImgList: PostsImgListProps[]
  hashTag: HashTagProps[]
  serviceTag: ServiceTagProps[]
  roomDtos: RoomDtosProps[]
  reviewList: ReviewListProps[]
}

interface CompanyInfoProps {
  id: number
  companyName: string
  ceo: string
  address: string
  detailAddress: string
  userId: number
}

interface PostsImgListProps {
  id: number
  fileName: string
  imgUrl: string
}
interface PostsInfoProps {
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
  avgScore: number
  checkInTime: string
  checkOutTime: string
  postImgList: PostsImgListProps[]
}

interface userInfoProps {
  name: string
  phone: string
  email: string
}

interface reservationListPageProps {
  reservedId: number
  checkInDate: string
  checkOutDate: string
  checkInTime: string
  checkOutTime: string
  status: string
  usersId: number
  postsId: number
  totalPrice: number
  companyId: number
  dogCount: number
  dogIdList: number[]
  userInfo: userInfoProps
}

export interface CEO {
  username: string
  email: string
  phone: string
  companyInfo: CompanyInfoProps
  postsInfo: PostsInfoProps
  reservationListPage: reservationListPageProps
}
